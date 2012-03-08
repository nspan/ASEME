package SUC.diagram.part;

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

import SUC.HumanRole;
import SUC.Role;
import SUC.SUCPackage;
import SUC.SUCmodel;
import SUC.SystemRole;
import SUC.UseCase;
import SUC.diagram.edit.parts.HumanRoleEditPart;
import SUC.diagram.edit.parts.RoleEditPart;
import SUC.diagram.edit.parts.RoleParticipates_inEditPart;
import SUC.diagram.edit.parts.SUCmodelEditPart;
import SUC.diagram.edit.parts.SystemRoleEditPart;
import SUC.diagram.edit.parts.UseCaseEditPart;
import SUC.diagram.edit.parts.UseCaseIncludeEditPart;
import SUC.diagram.providers.SUCElementTypes;

/**
 * @generated
 */
public class SUCDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<SUCNodeDescriptor> getSemanticChildren(View view) {
		switch (SUCVisualIDRegistry.getVisualID(view)) {
		case SUCmodelEditPart.VISUAL_ID:
			return getSUCmodel_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SUCNodeDescriptor> getSUCmodel_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		SUCmodel modelElement = (SUCmodel) view.getElement();
		LinkedList<SUCNodeDescriptor> result = new LinkedList<SUCNodeDescriptor>();
		for (Iterator<?> it = modelElement.getRoles().iterator(); it.hasNext();) {
			Role childElement = (Role) it.next();
			int visualID = SUCVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SystemRoleEditPart.VISUAL_ID) {
				result.add(new SUCNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == HumanRoleEditPart.VISUAL_ID) {
				result.add(new SUCNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == RoleEditPart.VISUAL_ID) {
				result.add(new SUCNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getUsecases().iterator(); it
				.hasNext();) {
			UseCase childElement = (UseCase) it.next();
			int visualID = SUCVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == UseCaseEditPart.VISUAL_ID) {
				result.add(new SUCNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getContainedLinks(View view) {
		switch (SUCVisualIDRegistry.getVisualID(view)) {
		case SUCmodelEditPart.VISUAL_ID:
			return getSUCmodel_1000ContainedLinks(view);
		case SystemRoleEditPart.VISUAL_ID:
			return getSystemRole_2007ContainedLinks(view);
		case HumanRoleEditPart.VISUAL_ID:
			return getHumanRole_2008ContainedLinks(view);
		case UseCaseEditPart.VISUAL_ID:
			return getUseCase_2009ContainedLinks(view);
		case RoleEditPart.VISUAL_ID:
			return getRole_2010ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getIncomingLinks(View view) {
		switch (SUCVisualIDRegistry.getVisualID(view)) {
		case SystemRoleEditPart.VISUAL_ID:
			return getSystemRole_2007IncomingLinks(view);
		case HumanRoleEditPart.VISUAL_ID:
			return getHumanRole_2008IncomingLinks(view);
		case UseCaseEditPart.VISUAL_ID:
			return getUseCase_2009IncomingLinks(view);
		case RoleEditPart.VISUAL_ID:
			return getRole_2010IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getOutgoingLinks(View view) {
		switch (SUCVisualIDRegistry.getVisualID(view)) {
		case SystemRoleEditPart.VISUAL_ID:
			return getSystemRole_2007OutgoingLinks(view);
		case HumanRoleEditPart.VISUAL_ID:
			return getHumanRole_2008OutgoingLinks(view);
		case UseCaseEditPart.VISUAL_ID:
			return getUseCase_2009OutgoingLinks(view);
		case RoleEditPart.VISUAL_ID:
			return getRole_2010OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getSUCmodel_1000ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getSystemRole_2007ContainedLinks(
			View view) {
		SystemRole modelElement = (SystemRole) view.getElement();
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Role_Participates_in_4008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getHumanRole_2008ContainedLinks(
			View view) {
		HumanRole modelElement = (HumanRole) view.getElement();
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Role_Participates_in_4008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getUseCase_2009ContainedLinks(
			View view) {
		UseCase modelElement = (UseCase) view.getElement();
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_UseCase_Include_4009(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getRole_2010ContainedLinks(View view) {
		Role modelElement = (Role) view.getElement();
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Role_Participates_in_4008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getSystemRole_2007IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getHumanRole_2008IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getUseCase_2009IncomingLinks(View view) {
		UseCase modelElement = (UseCase) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_Role_Participates_in_4008(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_UseCase_Include_4009(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getRole_2010IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getSystemRole_2007OutgoingLinks(
			View view) {
		SystemRole modelElement = (SystemRole) view.getElement();
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Role_Participates_in_4008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getHumanRole_2008OutgoingLinks(
			View view) {
		HumanRole modelElement = (HumanRole) view.getElement();
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Role_Participates_in_4008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getUseCase_2009OutgoingLinks(View view) {
		UseCase modelElement = (UseCase) view.getElement();
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_UseCase_Include_4009(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getRole_2010OutgoingLinks(View view) {
		Role modelElement = (Role) view.getElement();
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Role_Participates_in_4008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SUCLinkDescriptor> getIncomingFeatureModelFacetLinks_Role_Participates_in_4008(
			UseCase target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == SUCPackage.eINSTANCE
					.getRole_Participates_in()) {
				result.add(new SUCLinkDescriptor(setting.getEObject(), target,
						SUCElementTypes.RoleParticipates_in_4008,
						RoleParticipates_inEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SUCLinkDescriptor> getIncomingFeatureModelFacetLinks_UseCase_Include_4009(
			UseCase target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == SUCPackage.eINSTANCE
					.getUseCase_Include()) {
				result.add(new SUCLinkDescriptor(setting.getEObject(), target,
						SUCElementTypes.UseCaseInclude_4009,
						UseCaseIncludeEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SUCLinkDescriptor> getOutgoingFeatureModelFacetLinks_Role_Participates_in_4008(
			Role source) {
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		for (Iterator<?> destinations = source.getParticipates_in().iterator(); destinations
				.hasNext();) {
			UseCase destination = (UseCase) destinations.next();
			result.add(new SUCLinkDescriptor(source, destination,
					SUCElementTypes.RoleParticipates_in_4008,
					RoleParticipates_inEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SUCLinkDescriptor> getOutgoingFeatureModelFacetLinks_UseCase_Include_4009(
			UseCase source) {
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		for (Iterator<?> destinations = source.getInclude().iterator(); destinations
				.hasNext();) {
			UseCase destination = (UseCase) destinations.next();
			result.add(new SUCLinkDescriptor(source, destination,
					SUCElementTypes.UseCaseInclude_4009,
					UseCaseIncludeEditPart.VISUAL_ID));
		}
		return result;
	}

}
