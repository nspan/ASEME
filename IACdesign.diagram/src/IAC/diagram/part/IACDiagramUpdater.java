package IAC.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

import IAC.IACPackage;
import IAC.Model;
import IAC.Node;
import IAC.Transition;
import IAC.Variable;
import IAC.diagram.edit.parts.ModelEditPart;
import IAC.diagram.edit.parts.NodeEditPart;
import IAC.diagram.edit.parts.NodeFather_ofEditPart;
import IAC.diagram.edit.parts.NodeVariablesEditPart;
import IAC.diagram.edit.parts.TransitionEditPart;
import IAC.diagram.edit.parts.VariableEditPart;
import IAC.diagram.providers.IACElementTypes;

/**
 * @generated
 */
public class IACDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<IACNodeDescriptor> getSemanticChildren(View view) {
		switch (IACVisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<IACNodeDescriptor> getModel_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Model modelElement = (Model) view.getElement();
		LinkedList<IACNodeDescriptor> result = new LinkedList<IACNodeDescriptor>();
		for (Iterator<?> it = modelElement.getVariables().iterator(); it
				.hasNext();) {
			Variable childElement = (Variable) it.next();
			int visualID = IACVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == VariableEditPart.VISUAL_ID) {
				result.add(new IACNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getNodes().iterator(); it.hasNext();) {
			Node childElement = (Node) it.next();
			int visualID = IACVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == NodeEditPart.VISUAL_ID) {
				result.add(new IACNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<IACLinkDescriptor> getContainedLinks(View view) {
		switch (IACVisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_1000ContainedLinks(view);
		case VariableEditPart.VISUAL_ID:
			return getVariable_2005ContainedLinks(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2006ContainedLinks(view);
		case TransitionEditPart.VISUAL_ID:
			return getTransition_4007ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<IACLinkDescriptor> getIncomingLinks(View view) {
		switch (IACVisualIDRegistry.getVisualID(view)) {
		case VariableEditPart.VISUAL_ID:
			return getVariable_2005IncomingLinks(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2006IncomingLinks(view);
		case TransitionEditPart.VISUAL_ID:
			return getTransition_4007IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<IACLinkDescriptor> getOutgoingLinks(View view) {
		switch (IACVisualIDRegistry.getVisualID(view)) {
		case VariableEditPart.VISUAL_ID:
			return getVariable_2005OutgoingLinks(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2006OutgoingLinks(view);
		case TransitionEditPart.VISUAL_ID:
			return getTransition_4007OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<IACLinkDescriptor> getModel_1000ContainedLinks(View view) {
		Model modelElement = (Model) view.getElement();
		LinkedList<IACLinkDescriptor> result = new LinkedList<IACLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Transition_4007(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<IACLinkDescriptor> getVariable_2005ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<IACLinkDescriptor> getNode_2006ContainedLinks(View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<IACLinkDescriptor> result = new LinkedList<IACLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Node_Variables_4006(modelElement));
		result.addAll(getOutgoingFeatureModelFacetLinks_Node_Father_of_4008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<IACLinkDescriptor> getTransition_4007ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<IACLinkDescriptor> getVariable_2005IncomingLinks(
			View view) {
		Variable modelElement = (Variable) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<IACLinkDescriptor> result = new LinkedList<IACLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_Node_Variables_4006(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<IACLinkDescriptor> getNode_2006IncomingLinks(View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<IACLinkDescriptor> result = new LinkedList<IACLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Transition_4007(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_Node_Father_of_4008(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<IACLinkDescriptor> getTransition_4007IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<IACLinkDescriptor> getVariable_2005OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<IACLinkDescriptor> getNode_2006OutgoingLinks(View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<IACLinkDescriptor> result = new LinkedList<IACLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Node_Variables_4006(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Transition_4007(modelElement));
		result.addAll(getOutgoingFeatureModelFacetLinks_Node_Father_of_4008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<IACLinkDescriptor> getTransition_4007OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<IACLinkDescriptor> getContainedTypeModelFacetLinks_Transition_4007(
			Model container) {
		LinkedList<IACLinkDescriptor> result = new LinkedList<IACLinkDescriptor>();
		for (Iterator<?> links = container.getTransitions().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Transition) {
				continue;
			}
			Transition link = (Transition) linkObject;
			if (TransitionEditPart.VISUAL_ID != IACVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getTarget();
			Node src = link.getSource();
			result.add(new IACLinkDescriptor(src, dst, link,
					IACElementTypes.Transition_4007,
					TransitionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<IACLinkDescriptor> getIncomingFeatureModelFacetLinks_Node_Variables_4006(
			Variable target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<IACLinkDescriptor> result = new LinkedList<IACLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == IACPackage.eINSTANCE
					.getNode_Variables()) {
				result.add(new IACLinkDescriptor(setting.getEObject(), target,
						IACElementTypes.NodeVariables_4006,
						NodeVariablesEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<IACLinkDescriptor> getIncomingTypeModelFacetLinks_Transition_4007(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<IACLinkDescriptor> result = new LinkedList<IACLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != IACPackage.eINSTANCE
					.getTransition_Target()
					|| false == setting.getEObject() instanceof Transition) {
				continue;
			}
			Transition link = (Transition) setting.getEObject();
			if (TransitionEditPart.VISUAL_ID != IACVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getSource();
			result.add(new IACLinkDescriptor(src, target, link,
					IACElementTypes.Transition_4007,
					TransitionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<IACLinkDescriptor> getIncomingFeatureModelFacetLinks_Node_Father_of_4008(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<IACLinkDescriptor> result = new LinkedList<IACLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == IACPackage.eINSTANCE
					.getNode_Father_of()) {
				result.add(new IACLinkDescriptor(setting.getEObject(), target,
						IACElementTypes.NodeFather_of_4008,
						NodeFather_ofEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<IACLinkDescriptor> getOutgoingFeatureModelFacetLinks_Node_Variables_4006(
			Node source) {
		LinkedList<IACLinkDescriptor> result = new LinkedList<IACLinkDescriptor>();
		for (Iterator<?> destinations = source.getVariables().iterator(); destinations
				.hasNext();) {
			Variable destination = (Variable) destinations.next();
			result.add(new IACLinkDescriptor(source, destination,
					IACElementTypes.NodeVariables_4006,
					NodeVariablesEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<IACLinkDescriptor> getOutgoingTypeModelFacetLinks_Transition_4007(
			Node source) {
		Model container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Model) {
				container = (Model) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<IACLinkDescriptor> result = new LinkedList<IACLinkDescriptor>();
		for (Iterator<?> links = container.getTransitions().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Transition) {
				continue;
			}
			Transition link = (Transition) linkObject;
			if (TransitionEditPart.VISUAL_ID != IACVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getTarget();
			Node src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new IACLinkDescriptor(src, dst, link,
					IACElementTypes.Transition_4007,
					TransitionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<IACLinkDescriptor> getOutgoingFeatureModelFacetLinks_Node_Father_of_4008(
			Node source) {
		LinkedList<IACLinkDescriptor> result = new LinkedList<IACLinkDescriptor>();
		for (Iterator<?> destinations = source.getFather_of().iterator(); destinations
				.hasNext();) {
			Node destination = (Node) destinations.next();
			result.add(new IACLinkDescriptor(source, destination,
					IACElementTypes.NodeFather_of_4008,
					NodeFather_ofEditPart.VISUAL_ID));
		}
		return result;
	}

}
