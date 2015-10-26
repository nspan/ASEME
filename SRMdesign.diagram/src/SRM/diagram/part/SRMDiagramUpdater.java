package SRM.diagram.part;

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
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;

import SRM.Activity;
import SRM.Capability;
import SRM.Functionality;
import SRM.Role;
import SRM.SRMPackage;
import SRM.SRMmodel;
import SRM.diagram.edit.parts.ActivityEditPart;
import SRM.diagram.edit.parts.CapabilityCapability_activitiesEditPart;
import SRM.diagram.edit.parts.CapabilityEditPart;
import SRM.diagram.edit.parts.FunctionalityActivitiesEditPart;
import SRM.diagram.edit.parts.FunctionalityEditPart;
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
	public static List<SRMNodeDescriptor> getSemanticChildren(View view) {
		switch (SRMVisualIDRegistry.getVisualID(view)) {
		case SRMmodelEditPart.VISUAL_ID:
			return getSRMmodel_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SRMNodeDescriptor> getSRMmodel_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		SRMmodel modelElement = (SRMmodel) view.getElement();
		LinkedList<SRMNodeDescriptor> result = new LinkedList<SRMNodeDescriptor>();
		for (Iterator<?> it = modelElement.getActivities().iterator(); it
				.hasNext();) {
			Activity childElement = (Activity) it.next();
			int visualID = SRMVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ActivityEditPart.VISUAL_ID) {
				result.add(new SRMNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getFunctionalities().iterator(); it
				.hasNext();) {
			Functionality childElement = (Functionality) it.next();
			int visualID = SRMVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == FunctionalityEditPart.VISUAL_ID) {
				result.add(new SRMNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getRoles().iterator(); it.hasNext();) {
			Role childElement = (Role) it.next();
			int visualID = SRMVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == RoleEditPart.VISUAL_ID) {
				result.add(new SRMNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getCapabilities().iterator(); it
				.hasNext();) {
			Capability childElement = (Capability) it.next();
			int visualID = SRMVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == CapabilityEditPart.VISUAL_ID) {
				result.add(new SRMNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getContainedLinks(View view) {
		switch (SRMVisualIDRegistry.getVisualID(view)) {
		case SRMmodelEditPart.VISUAL_ID:
			return getSRMmodel_1000ContainedLinks(view);
		case ActivityEditPart.VISUAL_ID:
			return getActivity_2004ContainedLinks(view);
		case FunctionalityEditPart.VISUAL_ID:
			return getFunctionality_2003ContainedLinks(view);
		case RoleEditPart.VISUAL_ID:
			return getRole_2002ContainedLinks(view);
		case CapabilityEditPart.VISUAL_ID:
			return getCapability_2001ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getIncomingLinks(View view) {
		switch (SRMVisualIDRegistry.getVisualID(view)) {
		case ActivityEditPart.VISUAL_ID:
			return getActivity_2004IncomingLinks(view);
		case FunctionalityEditPart.VISUAL_ID:
			return getFunctionality_2003IncomingLinks(view);
		case RoleEditPart.VISUAL_ID:
			return getRole_2002IncomingLinks(view);
		case CapabilityEditPart.VISUAL_ID:
			return getCapability_2001IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getOutgoingLinks(View view) {
		switch (SRMVisualIDRegistry.getVisualID(view)) {
		case ActivityEditPart.VISUAL_ID:
			return getActivity_2004OutgoingLinks(view);
		case FunctionalityEditPart.VISUAL_ID:
			return getFunctionality_2003OutgoingLinks(view);
		case RoleEditPart.VISUAL_ID:
			return getRole_2002OutgoingLinks(view);
		case CapabilityEditPart.VISUAL_ID:
			return getCapability_2001OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getSRMmodel_1000ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getActivity_2004ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getFunctionality_2003ContainedLinks(
			View view) {
		Functionality modelElement = (Functionality) view.getElement();
		LinkedList<SRMLinkDescriptor> result = new LinkedList<SRMLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Functionality_Activities_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getRole_2002ContainedLinks(View view) {
		Role modelElement = (Role) view.getElement();
		LinkedList<SRMLinkDescriptor> result = new LinkedList<SRMLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Role_Capabilities_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getCapability_2001ContainedLinks(
			View view) {
		Capability modelElement = (Capability) view.getElement();
		LinkedList<SRMLinkDescriptor> result = new LinkedList<SRMLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Capability_Capability_activities_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getActivity_2004IncomingLinks(
			View view) {
		Activity modelElement = (Activity) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<SRMLinkDescriptor> result = new LinkedList<SRMLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_Functionality_Activities_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_Capability_Capability_activities_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getFunctionality_2003IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getRole_2002IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getCapability_2001IncomingLinks(
			View view) {
		Capability modelElement = (Capability) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<SRMLinkDescriptor> result = new LinkedList<SRMLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_Role_Capabilities_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getActivity_2004OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getFunctionality_2003OutgoingLinks(
			View view) {
		Functionality modelElement = (Functionality) view.getElement();
		LinkedList<SRMLinkDescriptor> result = new LinkedList<SRMLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Functionality_Activities_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getRole_2002OutgoingLinks(View view) {
		Role modelElement = (Role) view.getElement();
		LinkedList<SRMLinkDescriptor> result = new LinkedList<SRMLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Role_Capabilities_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SRMLinkDescriptor> getCapability_2001OutgoingLinks(
			View view) {
		Capability modelElement = (Capability) view.getElement();
		LinkedList<SRMLinkDescriptor> result = new LinkedList<SRMLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Capability_Capability_activities_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SRMLinkDescriptor> getIncomingFeatureModelFacetLinks_Functionality_Activities_4001(
			Activity target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<SRMLinkDescriptor> result = new LinkedList<SRMLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == SRMPackage.eINSTANCE
					.getFunctionality_Activities()) {
				result.add(new SRMLinkDescriptor(setting.getEObject(), target,
						SRMElementTypes.FunctionalityActivities_4001,
						FunctionalityActivitiesEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SRMLinkDescriptor> getIncomingFeatureModelFacetLinks_Role_Capabilities_4002(
			Capability target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<SRMLinkDescriptor> result = new LinkedList<SRMLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == SRMPackage.eINSTANCE
					.getRole_Capabilities()) {
				result.add(new SRMLinkDescriptor(setting.getEObject(), target,
						SRMElementTypes.RoleCapabilities_4002,
						RoleCapabilitiesEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SRMLinkDescriptor> getIncomingFeatureModelFacetLinks_Capability_Capability_activities_4003(
			Activity target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<SRMLinkDescriptor> result = new LinkedList<SRMLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == SRMPackage.eINSTANCE
					.getCapability_Capability_activities()) {
				result.add(new SRMLinkDescriptor(setting.getEObject(), target,
						SRMElementTypes.CapabilityCapability_activities_4003,
						CapabilityCapability_activitiesEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SRMLinkDescriptor> getOutgoingFeatureModelFacetLinks_Functionality_Activities_4001(
			Functionality source) {
		LinkedList<SRMLinkDescriptor> result = new LinkedList<SRMLinkDescriptor>();
		for (Iterator<?> destinations = source.getActivities().iterator(); destinations
				.hasNext();) {
			Activity destination = (Activity) destinations.next();
			result.add(new SRMLinkDescriptor(source, destination,
					SRMElementTypes.FunctionalityActivities_4001,
					FunctionalityActivitiesEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SRMLinkDescriptor> getOutgoingFeatureModelFacetLinks_Role_Capabilities_4002(
			Role source) {
		LinkedList<SRMLinkDescriptor> result = new LinkedList<SRMLinkDescriptor>();
		for (Iterator<?> destinations = source.getCapabilities().iterator(); destinations
				.hasNext();) {
			Capability destination = (Capability) destinations.next();
			result.add(new SRMLinkDescriptor(source, destination,
					SRMElementTypes.RoleCapabilities_4002,
					RoleCapabilitiesEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SRMLinkDescriptor> getOutgoingFeatureModelFacetLinks_Capability_Capability_activities_4003(
			Capability source) {
		LinkedList<SRMLinkDescriptor> result = new LinkedList<SRMLinkDescriptor>();
		for (Iterator<?> destinations = source.getCapability_activities()
				.iterator(); destinations.hasNext();) {
			Activity destination = (Activity) destinations.next();
			result.add(new SRMLinkDescriptor(source, destination,
					SRMElementTypes.CapabilityCapability_activities_4003,
					CapabilityCapability_activitiesEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static final DiagramUpdater TYPED_INSTANCE = new DiagramUpdater() {
		/**
		 * @generated
		 */
		@Override
		public List<SRMNodeDescriptor> getSemanticChildren(View view) {
			return SRMDiagramUpdater.getSemanticChildren(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<SRMLinkDescriptor> getContainedLinks(View view) {
			return SRMDiagramUpdater.getContainedLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<SRMLinkDescriptor> getIncomingLinks(View view) {
			return SRMDiagramUpdater.getIncomingLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<SRMLinkDescriptor> getOutgoingLinks(View view) {
			return SRMDiagramUpdater.getOutgoingLinks(view);
		}
	};

}
