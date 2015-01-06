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
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;

import SUC.Role;
import SUC.SUCPackage;
import SUC.SUCmodel;
import SUC.UseCase;
import SUC.diagram.edit.parts.RoleEditPart;
import SUC.diagram.edit.parts.RoleParticipates_inEditPart;
import SUC.diagram.edit.parts.SUCmodelEditPart;
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
		case RoleEditPart.VISUAL_ID:
			return getRole_2001ContainedLinks(view);
		case UseCaseEditPart.VISUAL_ID:
			return getUseCase_2002ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getIncomingLinks(View view) {
		switch (SUCVisualIDRegistry.getVisualID(view)) {
		case RoleEditPart.VISUAL_ID:
			return getRole_2001IncomingLinks(view);
		case UseCaseEditPart.VISUAL_ID:
			return getUseCase_2002IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getOutgoingLinks(View view) {
		switch (SUCVisualIDRegistry.getVisualID(view)) {
		case RoleEditPart.VISUAL_ID:
			return getRole_2001OutgoingLinks(view);
		case UseCaseEditPart.VISUAL_ID:
			return getUseCase_2002OutgoingLinks(view);
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
	public static List<SUCLinkDescriptor> getRole_2001ContainedLinks(View view) {
		Role modelElement = (Role) view.getElement();
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Role_Participates_in_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getUseCase_2002ContainedLinks(
			View view) {
		UseCase modelElement = (UseCase) view.getElement();
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_UseCase_Include_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getRole_2001IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getUseCase_2002IncomingLinks(View view) {
		UseCase modelElement = (UseCase) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_UseCase_Include_4003(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_Role_Participates_in_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getRole_2001OutgoingLinks(View view) {
		Role modelElement = (Role) view.getElement();
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Role_Participates_in_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<SUCLinkDescriptor> getUseCase_2002OutgoingLinks(View view) {
		UseCase modelElement = (UseCase) view.getElement();
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_UseCase_Include_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SUCLinkDescriptor> getIncomingFeatureModelFacetLinks_UseCase_Include_4003(
			UseCase target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == SUCPackage.eINSTANCE
					.getUseCase_Include()) {
				result.add(new SUCLinkDescriptor(setting.getEObject(), target,
						SUCElementTypes.UseCaseInclude_4003,
						UseCaseIncludeEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SUCLinkDescriptor> getIncomingFeatureModelFacetLinks_Role_Participates_in_4001(
			UseCase target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == SUCPackage.eINSTANCE
					.getRole_Participates_in()) {
				result.add(new SUCLinkDescriptor(setting.getEObject(), target,
						SUCElementTypes.RoleParticipates_in_4001,
						RoleParticipates_inEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SUCLinkDescriptor> getOutgoingFeatureModelFacetLinks_UseCase_Include_4003(
			UseCase source) {
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		for (Iterator<?> destinations = source.getInclude().iterator(); destinations
				.hasNext();) {
			UseCase destination = (UseCase) destinations.next();
			result.add(new SUCLinkDescriptor(source, destination,
					SUCElementTypes.UseCaseInclude_4003,
					UseCaseIncludeEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<SUCLinkDescriptor> getOutgoingFeatureModelFacetLinks_Role_Participates_in_4001(
			Role source) {
		LinkedList<SUCLinkDescriptor> result = new LinkedList<SUCLinkDescriptor>();
		for (Iterator<?> destinations = source.getParticipates_in().iterator(); destinations
				.hasNext();) {
			UseCase destination = (UseCase) destinations.next();
			result.add(new SUCLinkDescriptor(source, destination,
					SUCElementTypes.RoleParticipates_in_4001,
					RoleParticipates_inEditPart.VISUAL_ID));
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
		public List<SUCNodeDescriptor> getSemanticChildren(View view) {
			return SUCDiagramUpdater.getSemanticChildren(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<SUCLinkDescriptor> getContainedLinks(View view) {
			return SUCDiagramUpdater.getContainedLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<SUCLinkDescriptor> getIncomingLinks(View view) {
			return SUCDiagramUpdater.getIncomingLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<SUCLinkDescriptor> getOutgoingLinks(View view) {
			return SUCDiagramUpdater.getOutgoingLinks(view);
		}
	};

}
