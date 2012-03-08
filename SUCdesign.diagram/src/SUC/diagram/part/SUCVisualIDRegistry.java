package SUC.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

import SUC.SUCPackage;
import SUC.SUCmodel;
import SUC.diagram.edit.parts.HumanRoleEditPart;
import SUC.diagram.edit.parts.HumanRoleNameEditPart;
import SUC.diagram.edit.parts.RoleEditPart;
import SUC.diagram.edit.parts.RoleNameEditPart;
import SUC.diagram.edit.parts.SUCmodelEditPart;
import SUC.diagram.edit.parts.SystemRoleEditPart;
import SUC.diagram.edit.parts.SystemRoleNameEditPart;
import SUC.diagram.edit.parts.UseCaseEditPart;
import SUC.diagram.edit.parts.UseCaseIncludeEditPart;
import SUC.diagram.edit.parts.UseCaseNameEditPart;
import SUC.diagram.edit.parts.UseCaseSpecified_byEditPart;
import SUC.diagram.edit.parts.WrappingLabelEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class SUCVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "SUCdesign.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (SUCmodelEditPart.MODEL_ID.equals(view.getType())) {
				return SUCmodelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return SUC.diagram.part.SUCVisualIDRegistry.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				SUCDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (SUCPackage.eINSTANCE.getSUCmodel().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((SUCmodel) domainElement)) {
			return SUCmodelEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = SUC.diagram.part.SUCVisualIDRegistry
				.getModelID(containerView);
		if (!SUCmodelEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (SUCmodelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = SUC.diagram.part.SUCVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = SUCmodelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case SUCmodelEditPart.VISUAL_ID:
			if (SUCPackage.eINSTANCE.getSystemRole().isSuperTypeOf(
					domainElement.eClass())) {
				return SystemRoleEditPart.VISUAL_ID;
			}
			if (SUCPackage.eINSTANCE.getHumanRole().isSuperTypeOf(
					domainElement.eClass())) {
				return HumanRoleEditPart.VISUAL_ID;
			}
			if (SUCPackage.eINSTANCE.getUseCase().isSuperTypeOf(
					domainElement.eClass())) {
				return UseCaseEditPart.VISUAL_ID;
			}
			if (SUCPackage.eINSTANCE.getRole().isSuperTypeOf(
					domainElement.eClass())) {
				return RoleEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = SUC.diagram.part.SUCVisualIDRegistry
				.getModelID(containerView);
		if (!SUCmodelEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (SUCmodelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = SUC.diagram.part.SUCVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = SUCmodelEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case SUCmodelEditPart.VISUAL_ID:
			if (SystemRoleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (HumanRoleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (UseCaseEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SystemRoleEditPart.VISUAL_ID:
			if (SystemRoleNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case HumanRoleEditPart.VISUAL_ID:
			if (HumanRoleNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case UseCaseEditPart.VISUAL_ID:
			if (UseCaseNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (UseCaseSpecified_byEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case RoleEditPart.VISUAL_ID:
			if (RoleNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case UseCaseIncludeEditPart.VISUAL_ID:
			if (WrappingLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(SUCmodel element) {
		return true;
	}

}
