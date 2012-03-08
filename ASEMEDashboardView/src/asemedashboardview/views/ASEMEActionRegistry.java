package asemedashboardview.views;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.ui.PlatformUI;

import asemedashboardview.Activator;
import asemedashboardview.views.ASEMEAction;

public class ASEMEActionRegistry implements IExtensionChangeHandler {

	private static String EXTENSIONPOINT_UNIQUE_ID = "asemedashboardview.views.actions"; //$NON-NLS-1$

	private Set<ASEMEMediator> mediators;

	private Set<ASEMEActionDescriptor> descriptors;

	public ASEMEActionRegistry() {
		mediators = new HashSet<ASEMEMediator>();
		descriptors = new HashSet<ASEMEActionDescriptor>();
		PlatformUI.getWorkbench().getExtensionTracker().registerHandler(this, ExtensionTracker.createExtensionPointFilter(getExtensionPointFilter()));
		IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(EXTENSIONPOINT_UNIQUE_ID);
		if (point != null) {
			IExtension[] extensions = point.getExtensions();
			extensions = orderExtensions(extensions);
			for (IExtension extension : extensions) {
				addDescriptors(extension);
			}
		}
	}

	public void dispose() {
		PlatformUI.getWorkbench().getExtensionTracker().unregisterHandler(this);
	}

	private IExtensionPoint getExtensionPointFilter() {
		return Platform.getExtensionRegistry().getExtensionPoint(EXTENSIONPOINT_UNIQUE_ID);
	}

	void registerMediator(ASEMEMediator mediator) {
		mediators.add(mediator);
	}

	void unregisterMediator(ASEMEMediator mediator) {
		mediators.remove(mediator);
	}

	public void addExtension(IExtensionTracker tracker, IExtension addedExtension) {
		addDescriptors(addedExtension);
	}

	public void removeExtension(IExtension extension, Object[] objects) {
		for (Object object : objects) {
			if (object instanceof ASEMEActionDescriptor) {
				ASEMEActionDescriptor descriptor = (ASEMEActionDescriptor) object;
				descriptors.remove(descriptor);
				for (ASEMEMediator mediator : mediators) {
					mediator.removeDashboardAction(descriptor);
				}
			}
		}
	}

	public void addDescriptors(IExtension extension) {
		for (IConfigurationElement element : extension.getConfigurationElements()) {
			if (element.getName().equals("action")) { //$NON-NLS-1$
				ASEMEActionDescriptor desc = new ASEMEActionDescriptor(element);
				descriptors.add(desc);
				PlatformUI.getWorkbench().getExtensionTracker().registerObject(element.getDeclaringExtension(), desc, IExtensionTracker.REF_STRONG);
				for (ASEMEMediator mediator : mediators) {
					mediator.addDashboardAction(desc);
				}
			}
		}
	}

	public static IExtension[] orderExtensions(IExtension[] extensions) {
		// By default, the order is based on plugin id sorted
		// in ascending order. The order for a plugin providing
		// more than one extension for an extension point is
		// dependent in the order listed in the XML file.
		IExtension[] sortedExtension = new IExtension[extensions.length];
		System.arraycopy(extensions, 0, sortedExtension, 0, extensions.length);
		Comparator<IExtension> comparer = new Comparator<IExtension>() {

			public int compare(IExtension arg0, IExtension arg1) {
				String s1 = arg0.getNamespaceIdentifier();
				String s2 = arg1.getNamespaceIdentifier();
				return s1.compareToIgnoreCase(s2);
			}
		};
		Collections.sort(Arrays.asList(sortedExtension), comparer);
		return sortedExtension;
	}

	public ASEMEActionDescriptor[] getDescriptors() {
		return descriptors.toArray(new ASEMEActionDescriptor[descriptors.size()]);
	}

	public static class ASEMEActionDescriptor {

		private final IConfigurationElement element;

		private final String label;

		private final String location;

		private final boolean standard;

		public ASEMEActionDescriptor(IConfigurationElement element) {
			this.element = element;
			label = element.getAttribute("label"); //$NON-NLS-1$
			location = element.getAttribute("location"); //$NON-NLS-1$
			standard = Boolean.valueOf(element.getAttribute("standard")).booleanValue(); //$NON-NLS-1$
		}

		public IConfigurationElement getElement() {
			return element;
		}

		public String getLabel() {
			return label;
		}

		public ASEMEAction createDashboardAction() {
			return new Proxy();
		}

		public ASEMEAction createContributedDashboardAction() {
			try {
				return (ASEMEAction) element.createExecutableExtension("class"); //$NON-NLS-1$
			} catch (Exception e) {
				Activator.getDefault().getLog().log(Activator.createError("Unable to create ASEME Dashboard action", e)); //$NON-NLS-1$
			}
			return null;
		}

		public String getLocation() {
			return location;
		}

		public boolean isStandard() {
			return standard;
		}

		private class Proxy implements ASEMEAction {

			private ASEMEFacade context;

			private boolean inited;

			private ASEMEAction delegate;

			private boolean notAvailable;

			public void init(ASEMEFacade context) {
				this.context = context;
				inited = true;
			}

			public boolean isEnabled() {
				if (delegate != null) {
					return delegate.isEnabled();
				}
				if (notAvailable) {
					return false;
				}
				return true;
			}

			public void run() {
				if (notAvailable) {
					return;
				}
				if (delegate == null) {
					delegate = createContributedDashboardAction();
					if (delegate == null) {
						notAvailable = true;
						return;
					}
					if (inited) {
						delegate.init(context);
					}
				}
				if (delegate.isEnabled()) {
					delegate.run();
				}
			}
		}
	}
}

