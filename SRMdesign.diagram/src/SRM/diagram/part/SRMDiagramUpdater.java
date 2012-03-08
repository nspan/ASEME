package SRM.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

import SRM.Activity;
import SRM.Capability;
import SRM.Role;
import SRM.SRMPackage;
import SRM.SRMmodel;
import SRM.diagram.edit.parts.ActivityEditPart;
import SRM.diagram.edit.parts.CapabilityActivitiesEditPart;
import SRM.diagram.edit.parts.CapabilityEditPart;
import SRM.diagram.edit.parts.RoleActivitiesEditPart;
import SRM.diagram.edit.parts.RoleCapabilitiesEditPart;
import SRM.diagram.edit.parts.RoleEditPart;
import SRM.diagram.edit.parts.SRMmodelEditPart;
import SRM.diagram.providers.SRMElementTypes;

/**
 * @generated
 */
public class SRMDiagramUpdater {

	/**
	 * @generated
	 */
	public static List getSemanticChildren(View view) {
		switch (SRMVisualIDRegistry.getVisualID(view)) {
		case SRMmodelEditPart.VISUAL_ID:
			return getSRMmodel_1000SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSRMmodel_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		SRMmodel modelElement = (SRMmodel) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getCapabilities().iterator(); it
				.hasNext();) {
			Capability childElement = (Capability) it.next();
			int visualID = SRMVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == CapabilityEditPart.VISUAL_ID) {
				result.add(new SRMNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getRoles().iterator(); it.hasNext();) {
			Role childElement = (Role) it.next();
			int visualID = SRMVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == RoleEditPart.VISUAL_ID) {
				result.add(new SRMNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getActivities().iterator(); it
				.hasNext();) {
			Activity childElement = (Activity) it.next();
			int visualID = SRMVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ActivityEditPart.VISUAL_ID) {
				result.add(new SRMNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (SRMVisualIDRegistry.getVisualID(view)) {
		case SRMmodelEditPart.VISUAL_ID:
			return getSRMmodel_1000ContainedLinks(view);
		case CapabilityEditPart.VISUAL_ID:
			return getCapability_2004ContainedLinks(view);
		case RoleEditPart.VISUAL_ID:
			return getRole_2005ContainedLinks(view);
		case ActivityEditPart.VISUAL_ID:
			return getActivity_2006ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (SRMVisualIDRegistry.getVisualID(view)) {
		case CapabilityEditPart.VISUAL_ID:
			return getCapability_2004IncomingLinks(view);
		case RoleEditPart.VISUAL_ID:
			return getRole_2005IncomingLinks(view);
		case ActivityEditPart.VISUAL_ID:
			return getActivity_2006IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (SRMVisualIDRegistry.getVisualID(view)) {
		case CapabilityEditPart.VISUAL_ID:
			return getCapability_2004OutgoingLinks(view);
		case RoleEditPart.VISUAL_ID:
			return getRole_2005OutgoingLinks(view);
		case ActivityEditPart.VISUAL_ID:
			return getActivity_2006OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSRMmodel_1000ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getCapability_2004ContainedLinks(View view) {
		Capability modelElement = (Capability) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_Capability_Activities_4006(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRole_2005ContainedLinks(View view) {
		Role modelElement = (Role) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_Role_Capabilities_4004(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_Role_Activities_4005(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getActivity_2006ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getCapability_2004IncomingLinks(View view) {
		Capability modelElement = (Capability) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingFeatureModelFacetLinks_Role_Capabilities_4004(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRole_2005IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getActivity_2006IncomingLinks(View view) {
		Activity modelElement = (Activity) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingFeatureModelFacetLinks_Role_Activities_4005(
				modelElement, crossReferences));
		result
				.addAll(getIncomingFeatureModelFacetLinks_Capability_Activities_4006(
						modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getCapability_2004OutgoingLinks(View view) {
		Capability modelElement = (Capability) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_Capability_Activities_4006(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRole_2005OutgoingLinks(View view) {
		Role modelElement = (Role) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_Role_Capabilities_4004(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_Role_Activities_4005(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getActivity_2006OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_Role_Capabilities_4004(
			Capability target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == SRMPackage.eINSTANCE
					.getRole_Capabilities()) {
				result.add(new SRMLinkDescriptor(setting.getEObject(), target,
						SRMElementTypes.RoleCapabilities_4004,
						RoleCapabilitiesEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_Role_Activities_4005(
			Activity target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == SRMPackage.eINSTANCE
					.getRole_Activities()) {
				result.add(new SRMLinkDescriptor(setting.getEObject(), target,
						SRMElementTypes.RoleActivities_4005,
						RoleActivitiesEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_Capability_Activities_4006(
			Activity target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == SRMPackage.eINSTANCE
					.getCapability_Activities()) {
				result.add(new SRMLinkDescriptor(setting.getEObject(), target,
						SRMElementTypes.CapabilityActivities_4006,
						CapabilityActivitiesEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_Role_Capabilities_4004(
			Role source) {
		Collection result = new LinkedList();
		for (Iterator destinations = source.getCapabilities().iterator(); destinations
				.hasNext();) {
			Capability destination = (Capability) destinations.next();
			result.add(new SRMLinkDescriptor(source, destination,
					SRMElementTypes.RoleCapabilities_4004,
					RoleCapabilitiesEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_Role_Activities_4005(
			Role source) {
		Collection result = new LinkedList();
		for (Iterator destinations = source.getActivities().iterator(); destinations
				.hasNext();) {
			Activity destination = (Activity) destinations.next();
			result.add(new SRMLinkDescriptor(source, destination,
					SRMElementTypes.RoleActivities_4005,
					RoleActivitiesEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_Capability_Activities_4006(
			Capability source) {
		Collection result = new LinkedList();
		for (Iterator destinations = source.getActivities().iterator(); destinations
				.hasNext();) {
			Activity destination = (Activity) destinations.next();
			result.add(new SRMLinkDescriptor(source, destination,
					SRMElementTypes.CapabilityActivities_4006,
					CapabilityActivitiesEditPart.VISUAL_ID));
		}
		return result;
	}

}
