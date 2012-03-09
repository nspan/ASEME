/*
 * Kouretes Statechart Editor-KSE developed 
 * by Angeliki Topalidou-Kyniazopoulou
 * for Kouretes Team. Code developed by Nikolaos Spanoudakis, Alex Parashos,
 * ibm, apache and eclipse is used.
 */
package statechart.diagram.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

import statechart.diagram.edit.parts.ModelEditPart;
import statechart.diagram.edit.parts.Node10EditPart;
import statechart.diagram.edit.parts.Node11EditPart;
import statechart.diagram.edit.parts.Node12EditPart;
import statechart.diagram.edit.parts.Node2EditPart;
import statechart.diagram.edit.parts.Node3EditPart;
import statechart.diagram.edit.parts.Node4EditPart;
import statechart.diagram.edit.parts.Node5EditPart;
import statechart.diagram.edit.parts.Node6EditPart;
import statechart.diagram.edit.parts.Node7EditPart;
import statechart.diagram.edit.parts.Node8EditPart;
import statechart.diagram.edit.parts.Node9EditPart;
import statechart.diagram.edit.parts.NodeEditPart;
import statechart.diagram.edit.parts.NodeNodeAndComp2EditPart;
import statechart.diagram.edit.parts.NodeNodeAndCompEditPart;
import statechart.diagram.edit.parts.NodeNodeOrComp2EditPart;
import statechart.diagram.edit.parts.NodeNodeOrCompEditPart;
import statechart.diagram.edit.parts.TransitionEditPart;
import statechart.diagram.edit.parts.VariableEditPart;
import statechart.diagram.part.Messages;
import statechart.diagram.part.StateChartVisualIDRegistry;

/**
 * @generated
 */
public class StateChartNavigatorContentProvider implements
		ICommonContentProvider {

	/**
	 * @generated
	 */
	private static final Object[] EMPTY_ARRAY = new Object[0];

	/**
	 * @generated
	 */
	private Viewer myViewer;

	/**
	 * @generated
	 */
	private AdapterFactoryEditingDomain myEditingDomain;

	/**
	 * @generated
	 */
	private WorkspaceSynchronizer myWorkspaceSynchronizer;

	/**
	 * @generated
	 */
	private Runnable myViewerRefreshRunnable;

	/**
	 * @generated
	 */
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public StateChartNavigatorContentProvider() {
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
				.createEditingDomain();
		myEditingDomain = (AdapterFactoryEditingDomain) editingDomain;
		myEditingDomain.setResourceToReadOnlyMap(new HashMap() {
			public Object get(Object key) {
				if (!containsKey(key)) {
					put(key, Boolean.TRUE);
				}
				return super.get(key);
			}
		});
		myViewerRefreshRunnable = new Runnable() {
			public void run() {
				if (myViewer != null) {
					myViewer.refresh();
				}
			}
		};
		myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain,
				new WorkspaceSynchronizer.Delegate() {
					public void dispose() {
					}

					public boolean handleResourceChanged(final Resource resource) {
						unloadAllResources();
						asyncRefresh();
						return true;
					}

					public boolean handleResourceDeleted(Resource resource) {
						unloadAllResources();
						asyncRefresh();
						return true;
					}

					public boolean handleResourceMoved(Resource resource,
							final URI newURI) {
						unloadAllResources();
						asyncRefresh();
						return true;
					}
				});
	}

	/**
	 * @generated
	 */
	public void dispose() {
		myWorkspaceSynchronizer.dispose();
		myWorkspaceSynchronizer = null;
		myViewerRefreshRunnable = null;
		myViewer = null;
		unloadAllResources();
		((TransactionalEditingDomain) myEditingDomain).dispose();
		myEditingDomain = null;
	}

	/**
	 * @generated
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		myViewer = viewer;
	}

	/**
	 * @generated
	 */
	void unloadAllResources() {
		for (Resource nextResource : myEditingDomain.getResourceSet()
				.getResources()) {
			nextResource.unload();
		}
	}

	/**
	 * @generated
	 */
	void asyncRefresh() {
		if (myViewer != null && !myViewer.getControl().isDisposed()) {
			myViewer.getControl().getDisplay()
					.asyncExec(myViewerRefreshRunnable);
		}
	}

	/**
	 * @generated
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile) {
			IFile file = (IFile) parentElement;
			URI fileURI = URI.createPlatformResourceURI(file.getFullPath()
					.toString(), true);
			Resource resource = myEditingDomain.getResourceSet().getResource(
					fileURI, true);
			ArrayList<StateChartNavigatorItem> result = new ArrayList<StateChartNavigatorItem>();
			ArrayList<View> topViews = new ArrayList<View>(resource
					.getContents().size());
			for (EObject o : resource.getContents()) {
				if (o instanceof View) {
					topViews.add((View) o);
				}
			}
			return result.toArray();
		}

		if (parentElement instanceof StateChartNavigatorGroup) {
			StateChartNavigatorGroup group = (StateChartNavigatorGroup) parentElement;
			return group.getChildren();
		}

		if (parentElement instanceof StateChartNavigatorItem) {
			StateChartNavigatorItem navigatorItem = (StateChartNavigatorItem) parentElement;
			if (navigatorItem.isLeaf() || !isOwnView(navigatorItem.getView())) {
				return EMPTY_ARRAY;
			}
			return getViewChildren(navigatorItem.getView(), parentElement);
		}

		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	private Object[] getViewChildren(View view, Object parentElement) {
		switch (StateChartVisualIDRegistry.getVisualID(view)) {

		case Node11EditPart.VISUAL_ID: {
			LinkedList<StateChartAbstractNavigatorItem> result = new LinkedList<StateChartAbstractNavigatorItem>();
			Node sv = (Node) view;
			StateChartNavigatorGroup incominglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_3005_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			StateChartNavigatorGroup outgoinglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_3005_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(NodeNodeAndCompEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					StateChartVisualIDRegistry.getType(Node9EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case Node2EditPart.VISUAL_ID: {
			LinkedList<StateChartAbstractNavigatorItem> result = new LinkedList<StateChartAbstractNavigatorItem>();
			Node sv = (Node) view;
			StateChartNavigatorGroup incominglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_2002_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			StateChartNavigatorGroup outgoinglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_2002_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(NodeNodeAndComp2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					StateChartVisualIDRegistry.getType(Node9EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case Node12EditPart.VISUAL_ID: {
			LinkedList<StateChartAbstractNavigatorItem> result = new LinkedList<StateChartAbstractNavigatorItem>();
			Node sv = (Node) view;
			StateChartNavigatorGroup incominglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_3006_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			StateChartNavigatorGroup outgoinglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_3006_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case NodeEditPart.VISUAL_ID: {
			LinkedList<StateChartAbstractNavigatorItem> result = new LinkedList<StateChartAbstractNavigatorItem>();
			Node sv = (Node) view;
			StateChartNavigatorGroup incominglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_2001_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			StateChartNavigatorGroup outgoinglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_2001_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(NodeNodeOrCompEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					StateChartVisualIDRegistry.getType(Node7EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(NodeNodeOrCompEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					StateChartVisualIDRegistry.getType(Node8EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(NodeNodeOrCompEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					StateChartVisualIDRegistry.getType(Node9EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(NodeNodeOrCompEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					StateChartVisualIDRegistry
							.getType(Node10EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(NodeNodeOrCompEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					StateChartVisualIDRegistry
							.getType(Node11EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(NodeNodeOrCompEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					StateChartVisualIDRegistry
							.getType(Node12EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case Node8EditPart.VISUAL_ID: {
			LinkedList<StateChartAbstractNavigatorItem> result = new LinkedList<StateChartAbstractNavigatorItem>();
			Node sv = (Node) view;
			StateChartNavigatorGroup incominglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_3002_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			StateChartNavigatorGroup outgoinglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_3002_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case Node5EditPart.VISUAL_ID: {
			LinkedList<StateChartAbstractNavigatorItem> result = new LinkedList<StateChartAbstractNavigatorItem>();
			Node sv = (Node) view;
			StateChartNavigatorGroup incominglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_2006_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			StateChartNavigatorGroup outgoinglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_2006_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case Node3EditPart.VISUAL_ID: {
			LinkedList<StateChartAbstractNavigatorItem> result = new LinkedList<StateChartAbstractNavigatorItem>();
			Node sv = (Node) view;
			StateChartNavigatorGroup incominglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_2004_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			StateChartNavigatorGroup outgoinglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_2004_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case Node6EditPart.VISUAL_ID: {
			LinkedList<StateChartAbstractNavigatorItem> result = new LinkedList<StateChartAbstractNavigatorItem>();
			Node sv = (Node) view;
			StateChartNavigatorGroup incominglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_2007_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			StateChartNavigatorGroup outgoinglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_2007_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case Node9EditPart.VISUAL_ID: {
			LinkedList<StateChartAbstractNavigatorItem> result = new LinkedList<StateChartAbstractNavigatorItem>();
			Node sv = (Node) view;
			StateChartNavigatorGroup incominglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_3003_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			StateChartNavigatorGroup outgoinglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_3003_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(NodeNodeOrComp2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					StateChartVisualIDRegistry.getType(Node7EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(NodeNodeOrComp2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					StateChartVisualIDRegistry.getType(Node8EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(NodeNodeOrComp2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					StateChartVisualIDRegistry.getType(Node9EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(NodeNodeOrComp2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					StateChartVisualIDRegistry
							.getType(Node10EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(NodeNodeOrComp2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					StateChartVisualIDRegistry
							.getType(Node11EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(NodeNodeOrComp2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					StateChartVisualIDRegistry
							.getType(Node12EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case Node10EditPart.VISUAL_ID: {
			LinkedList<StateChartAbstractNavigatorItem> result = new LinkedList<StateChartAbstractNavigatorItem>();
			Node sv = (Node) view;
			StateChartNavigatorGroup incominglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_3004_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			StateChartNavigatorGroup outgoinglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_3004_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case TransitionEditPart.VISUAL_ID: {
			LinkedList<StateChartAbstractNavigatorItem> result = new LinkedList<StateChartAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			StateChartNavigatorGroup target = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Transition_4001_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			StateChartNavigatorGroup source = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Transition_4001_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node6EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node7EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node8EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node9EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(Node10EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(Node11EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(Node12EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node6EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node7EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node8EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node9EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(Node10EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(Node11EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(Node12EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case Node7EditPart.VISUAL_ID: {
			LinkedList<StateChartAbstractNavigatorItem> result = new LinkedList<StateChartAbstractNavigatorItem>();
			Node sv = (Node) view;
			StateChartNavigatorGroup incominglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_3001_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			StateChartNavigatorGroup outgoinglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_3001_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ModelEditPart.VISUAL_ID: {
			LinkedList<StateChartAbstractNavigatorItem> result = new LinkedList<StateChartAbstractNavigatorItem>();
			Diagram sv = (Diagram) view;
			StateChartNavigatorGroup links = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Model_1000_links,
					"icons/linksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(VariableEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node3EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node4EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node5EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					StateChartVisualIDRegistry.getType(Node6EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			if (!links.isEmpty()) {
				result.add(links);
			}
			return result.toArray();
		}

		case Node4EditPart.VISUAL_ID: {
			LinkedList<StateChartAbstractNavigatorItem> result = new LinkedList<StateChartAbstractNavigatorItem>();
			Node sv = (Node) view;
			StateChartNavigatorGroup incominglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_2005_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			StateChartNavigatorGroup outgoinglinks = new StateChartNavigatorGroup(
					Messages.NavigatorGroupName_Node_2005_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					StateChartVisualIDRegistry
							.getType(TransitionEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}
		}
		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	private Collection<View> getLinksSourceByType(Collection<Edge> edges,
			String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (Edge nextEdge : edges) {
			View nextEdgeSource = nextEdge.getSource();
			if (type.equals(nextEdgeSource.getType())
					&& isOwnView(nextEdgeSource)) {
				result.add(nextEdgeSource);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getLinksTargetByType(Collection<Edge> edges,
			String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (Edge nextEdge : edges) {
			View nextEdgeTarget = nextEdge.getTarget();
			if (type.equals(nextEdgeTarget.getType())
					&& isOwnView(nextEdgeTarget)) {
				result.add(nextEdgeTarget);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getOutgoingLinksByType(
			Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getSourceEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getIncomingLinksByType(
			Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getTargetEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getChildrenByType(
			Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getChildren(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getDiagramLinksByType(
			Collection<Diagram> diagrams, String type) {
		ArrayList<View> result = new ArrayList<View>();
		for (Diagram nextDiagram : diagrams) {
			result.addAll(selectViewsByType(nextDiagram.getEdges(), type));
		}
		return result;
	}

	// TODO refactor as static method
	/**
	 * @generated
	 */
	private Collection<View> selectViewsByType(Collection<View> views,
			String type) {
		ArrayList<View> result = new ArrayList<View>();
		for (View nextView : views) {
			if (type.equals(nextView.getType()) && isOwnView(nextView)) {
				result.add(nextView);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return ModelEditPart.MODEL_ID.equals(StateChartVisualIDRegistry
				.getModelID(view));
	}

	/**
	 * @generated
	 */
	private Collection<StateChartNavigatorItem> createNavigatorItems(
			Collection<View> views, Object parent, boolean isLeafs) {
		ArrayList<StateChartNavigatorItem> result = new ArrayList<StateChartNavigatorItem>(
				views.size());
		for (View nextView : views) {
			result.add(new StateChartNavigatorItem(nextView, parent, isLeafs));
		}
		return result;
	}

	/**
	 * @generated
	 */
	public Object getParent(Object element) {
		if (element instanceof StateChartAbstractNavigatorItem) {
			StateChartAbstractNavigatorItem abstractNavigatorItem = (StateChartAbstractNavigatorItem) element;
			return abstractNavigatorItem.getParent();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean hasChildren(Object element) {
		return element instanceof IFile || getChildren(element).length > 0;
	}

}
