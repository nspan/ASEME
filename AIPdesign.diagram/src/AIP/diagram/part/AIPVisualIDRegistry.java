package AIP.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;

import AIP.AIPPackage;
import AIP.AIPmodel;
import AIP.diagram.edit.parts.AIPmodelEditPart;
import AIP.diagram.edit.parts.ParticipantEditPart;
import AIP.diagram.edit.parts.ParticipantEngaging_rulesEditPart;
import AIP.diagram.edit.parts.ParticipantLivenessEditPart;
import AIP.diagram.edit.parts.ParticipantNameEditPart;
import AIP.diagram.edit.parts.ParticipantOutcomesEditPart;
import AIP.diagram.edit.parts.ProtocolEditPart;
import AIP.diagram.edit.parts.ProtocolNameEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class AIPVisualIDRegistry {

	/**
	* @generated
	*/
	private static final String DEBUG_KEY = "AIPdesign.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	* @generated
	*/
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (AIPmodelEditPart.MODEL_ID.equals(view.getType())) {
				return AIPmodelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return AIP.diagram.part.AIPVisualIDRegistry.getVisualID(view.getType());
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
			if (Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(DEBUG_KEY))) {
				AIPDiagramEditorPlugin.getInstance()
						.logError("Unable to parse view type as a visualID number: " + type);
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
		if (AIPPackage.eINSTANCE.getAIPmodel().isSuperTypeOf(domainElement.eClass())
				&& isDiagram((AIPmodel) domainElement)) {
			return AIPmodelEditPart.VISUAL_ID;
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
		String containerModelID = AIP.diagram.part.AIPVisualIDRegistry.getModelID(containerView);
		if (!AIPmodelEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (AIPmodelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = AIP.diagram.part.AIPVisualIDRegistry.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = AIPmodelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case AIPmodelEditPart.VISUAL_ID:
			if (AIPPackage.eINSTANCE.getProtocol().isSuperTypeOf(domainElement.eClass())) {
				return ProtocolEditPart.VISUAL_ID;
			}
			if (AIPPackage.eINSTANCE.getParticipant().isSuperTypeOf(domainElement.eClass())) {
				return ParticipantEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	* @generated
	*/
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = AIP.diagram.part.AIPVisualIDRegistry.getModelID(containerView);
		if (!AIPmodelEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (AIPmodelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = AIP.diagram.part.AIPVisualIDRegistry.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = AIPmodelEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case AIPmodelEditPart.VISUAL_ID:
			if (ProtocolEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ParticipantEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ProtocolEditPart.VISUAL_ID:
			if (ProtocolNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ParticipantEditPart.VISUAL_ID:
			if (ParticipantNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ParticipantLivenessEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ParticipantEngaging_rulesEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ParticipantOutcomesEditPart.VISUAL_ID == nodeVisualID) {
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
	private static boolean isDiagram(AIPmodel element) {
		return true;
	}

	/**
	* @generated
	*/
	public static boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
		if (candidate == -1) {
			//unrecognized id is always bad
			return false;
		}
		int basic = getNodeVisualID(containerView, domainElement);
		return basic == candidate;
	}

	/**
	* @generated
	*/
	public static boolean isCompartmentVisualID(int visualID) {
		return false;
	}

	/**
	* @generated
	*/
	public static boolean isSemanticLeafVisualID(int visualID) {
		switch (visualID) {
		case AIPmodelEditPart.VISUAL_ID:
			return false;
		case ProtocolEditPart.VISUAL_ID:
		case ParticipantEditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	* @generated
	*/
	public static final DiagramStructure TYPED_INSTANCE = new DiagramStructure() {
		/**
		* @generated
		*/
		@Override

		public int getVisualID(View view) {
			return AIP.diagram.part.AIPVisualIDRegistry.getVisualID(view);
		}

		/**
		* @generated
		*/
		@Override

		public String getModelID(View view) {
			return AIP.diagram.part.AIPVisualIDRegistry.getModelID(view);
		}

		/**
		* @generated
		*/
		@Override

		public int getNodeVisualID(View containerView, EObject domainElement) {
			return AIP.diagram.part.AIPVisualIDRegistry.getNodeVisualID(containerView, domainElement);
		}

		/**
		* @generated
		*/
		@Override

		public boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
			return AIP.diagram.part.AIPVisualIDRegistry.checkNodeVisualID(containerView, domainElement, candidate);
		}

		/**
		* @generated
		*/
		@Override

		public boolean isCompartmentVisualID(int visualID) {
			return AIP.diagram.part.AIPVisualIDRegistry.isCompartmentVisualID(visualID);
		}

		/**
		* @generated
		*/
		@Override

		public boolean isSemanticLeafVisualID(int visualID) {
			return AIP.diagram.part.AIPVisualIDRegistry.isSemanticLeafVisualID(visualID);
		}
	};

}
