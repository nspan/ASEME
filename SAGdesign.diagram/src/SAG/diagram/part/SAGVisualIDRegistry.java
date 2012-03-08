package SAG.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

import SAG.SAGPackage;
import SAG.SAGmodel;
import SAG.diagram.edit.parts.ActorEditPart;
import SAG.diagram.edit.parts.ActorNameEditPart;
import SAG.diagram.edit.parts.GoalEditPart;
import SAG.diagram.edit.parts.GoalNameEditPart;
import SAG.diagram.edit.parts.SAGmodelEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class SAGVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "SAGdesign.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (SAGmodelEditPart.MODEL_ID.equals(view.getType())) {
				return SAGmodelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return SAG.diagram.part.SAGVisualIDRegistry.getVisualID(view.getType());
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
				SAGDiagramEditorPlugin.getInstance().logError(
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
		if (SAGPackage.eINSTANCE.getSAGmodel().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((SAGmodel) domainElement)) {
			return SAGmodelEditPart.VISUAL_ID;
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
		String containerModelID = SAG.diagram.part.SAGVisualIDRegistry
				.getModelID(containerView);
		if (!SAGmodelEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (SAGmodelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = SAG.diagram.part.SAGVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = SAGmodelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case SAGmodelEditPart.VISUAL_ID:
			if (SAGPackage.eINSTANCE.getActor().isSuperTypeOf(
					domainElement.eClass())) {
				return ActorEditPart.VISUAL_ID;
			}
			if (SAGPackage.eINSTANCE.getGoal().isSuperTypeOf(
					domainElement.eClass())) {
				return GoalEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = SAG.diagram.part.SAGVisualIDRegistry
				.getModelID(containerView);
		if (!SAGmodelEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (SAGmodelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = SAG.diagram.part.SAGVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = SAGmodelEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case SAGmodelEditPart.VISUAL_ID:
			if (ActorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (GoalEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ActorEditPart.VISUAL_ID:
			if (ActorNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case GoalEditPart.VISUAL_ID:
			if (GoalNameEditPart.VISUAL_ID == nodeVisualID) {
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
	private static boolean isDiagram(SAGmodel element) {
		return true;
	}

}
