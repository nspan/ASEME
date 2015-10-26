package asemedashboardview;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import asemedashboardview.views.ASEMEActionRegistry;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "ASEMEDashboardView"; //$NON-NLS-1$
	
	public static final String GMF_LOGO_IMAGE = "/icons/logo.gif"; //$NON-NLS-1$

	public static final String SYNC_ICON = "icons\\sample.gif"; //$NON-NLS-1$

	public static final String SAG_ICON = "icons\\sample.gif"; //$NON-NLS-1$	
	
	public static final String SUC_ICON = "icons\\sample.gif"; //$NON-NLS-1$

	public static final String AIP_ICON = "icons\\sample.gif"; //$NON-NLS-1$

	public static final String SRM_ICON = "icons\\sample.gif"; //$NON-NLS-1$
	
	public static final String XPDL_ICON = "icons\\sample.gif"; //$NON-NLS-1$

	public static final String EAC_ICON = "icons\\sample.gif"; //$NON-NLS-1$

	public static final String IAC_ICON = "icons\\sample.gif"; //$NON-NLS-1$
	
	public static final String JADE_ICON = "icons\\sample.gif"; //$NON-NLS-1$
	
	public static final String GG_ICON = "icons\\sample.gif"; //$NON-NLS-1$
	// The shared instance
	private static Activator plugin;
	private ASEMEActionRegistry daRegistry;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		if (daRegistry != null) {
			daRegistry.dispose();
			daRegistry = null;
		}
		super.stop(context);
		//plugin = null;
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	
	protected void initializeImageRegistry(ImageRegistry reg) {
		loadImage(reg, GMF_LOGO_IMAGE);
//		loadImage(reg, SYNC_ICON);
//		loadImage(reg, DGM_ICON, "org.eclipse.emf.codegen.ecore.ui"); //$NON-NLS-1$
//		loadImage(reg, GDM_ICON, "org.eclipse.gmf.graphdef.edit"); //$NON-NLS-1$
//		loadImage(reg, DM_ICON, "org.eclipse.emf.ecore.editor"); //$NON-NLS-1$
//		loadImage(reg, TDM_ICON, "org.eclipse.gmf.tooldef.edit"); //$NON-NLS-1$
//		loadImage(reg, MM_ICON, "org.eclipse.gmf.map.edit"); //$NON-NLS-1$
//		loadImage(reg, GM_ICON, "org.eclipse.gmf.codegen.edit"); //$NON-NLS-1$
	}

	protected void loadImage(ImageRegistry registry, String id) {
		loadImage(registry, id, getBundle().getSymbolicName());
	}

	protected void loadImage(ImageRegistry registry, String id, String bundleId) {
		ImageDescriptor descriptor = imageDescriptorFromPlugin(bundleId, id);
		if (descriptor != null) {
			registry.put(id, descriptor);
		}
	}

	public static String getBundleString(String key) {
		return Platform.getResourceBundle(getDefault().getBundle()).getString(key);
	}

	public static String getBundleString(String key, Object[] args) {
		String val = getBundleString(key);
		if (val == null) {
			return key;
		}
		return MessageFormat.format(val, args);
	}

	public ASEMEActionRegistry getDashboardActionRegistry() {
		if (daRegistry == null) {
			daRegistry = new ASEMEActionRegistry();
		}
		return daRegistry;
	}

	public static IStatus createStatus(int statusCode, String message, Exception ex) {
		return new Status(statusCode, getPluginID(), 0, message, ex);
	}

	public static IStatus createError(String message, Exception ex) {
		return createStatus(IStatus.ERROR, message, ex);
	}

	public static IStatus createWarning(String message) {
		return createStatus(IStatus.WARNING, message, null);
	}

	public static IStatus createInfo(String message) {
		return createStatus(IStatus.INFO, message, null);
	}

	public static String getPluginID() {
		return getDefault().getBundle().getSymbolicName();
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
