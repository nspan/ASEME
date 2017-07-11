package SRM.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;

import SRM.SRMPackage;
import SRM.SRMmodel;
import SRM.diagram.edit.parts.ActivityEditPart;
import SRM.diagram.edit.parts.ActivityNameEditPart;
import SRM.diagram.edit.parts.CapabilityEditPart;
import SRM.diagram.edit.parts.CapabilityNameEditPart;
import SRM.diagram.edit.parts.FunctionalityDescriptionEditPart;
import SRM.diagram.edit.parts.FunctionalityEditPart;
import SRM.diagram.edit.parts.RoleEditPart;
import SRM.diagram.edit.parts.RoleNameEditPart;
import SRM.diagram.edit.parts.SRMmodelEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class SRMVisualIDRegistry {

	/**
	* @generated
	*/
	private static final String DEBUG_KEY = "SRMdesign.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	* @generated
	*/
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (SRMmodelEditPart.MODEL_ID.equals(view.getType())) {
				return SRMmodelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return SRM.diagram.part.SRMVisualIDRegistry.getVisualID(view.getType());
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
				SRMDiagramEditorPlugin.getInstance()
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
		if (SRMPackage.eINSTANCE.getSRMmodel().isSuperTypeOf(domainElement.eClass())
				&& isDiagram((SRMmodel) domainElement)) {
			return SRMmodelEditPart.VISUAL_ID;
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
		String containerModelID = SRM.diagram.part.SRMVisualIDRegistry.getModelID(containerView);
		if (!SRMmodelEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (SRMmodelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = SRM.diagram.part.SRMVisualIDRegistry.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = SRMmodelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case SRMmodelEditPart.VISUAL_ID:
			if (SRMPackage.eINSTANCE.getFunctionality().isSuperTypeOf(domainElement.eClass())) {
				return FunctionalityEditPart.VISUAL_ID;
			}
			if (SRMPackage.eINSTANCE.getCapability().isSuperTypeOf(domainElement.eClass())) {
				return CapabilityEditPart.VISUAL_ID;
			}
			if (SRMPackage.eINSTANCE.getRole().isSuperTypeOf(domainElement.eClass())) {
				return RoleEditPart.VISUAL_ID;
			}
			if (SRMPackage.eINSTANCE.getActivity().isSuperTypeOf(domainElement.eClass())) {
				return ActivityEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	* @generated
	*/
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = SRM.diagram.part.SRMVisualIDRegistry.getModelID(containerView);
		if (!SRMmodelEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (SRMmodelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = SRM.diagram.part.SRMVisualIDRegistry.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = SRMmodelEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case SRMmodelEditPart.VISUAL_ID:
			if (FunctionalityEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CapabilityEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ActivityEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case FunctionalityEditPart.VISUAL_ID:
			if (FunctionalityDescriptionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case CapabilityEditPart.VISUAL_ID:
			if (CapabilityNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case RoleEditPart.VISUAL_ID:
			if (RoleNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ActivityEditPart.VISUAL_ID:
			if (ActivityNameEditPart.VISUAL_ID == nodeVisualID) {
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
	private static boolean isDiagram(SRMmodel element) {
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
		case SRMmodelEditPart.VISUAL_ID:
			return false;
		case FunctionalityEditPart.VISUAL_ID:
		case CapabilityEditPart.VISUAL_ID:
		case RoleEditPart.VISUAL_ID:
		case ActivityEditPart.VISUAL_ID:
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
			return SRM.diagram.part.SRMVisualIDRegistry.getVisualID(view);
		}

		/**
		* @generated
		*/
		@Override

		public String getModelID(View view) {
			return SRM.diagram.part.SRMVisualIDRegistry.getModelID(view);
		}

		/**
		* @generated
		*/
		@Override

		public int getNodeVisualID(View containerView, EObject domainElement) {
			return SRM.diagram.part.SRMVisualIDRegistry.getNodeVisualID(containerView, domainElement);
		}

		/**
		* @generated
		*/
		@Override

		public boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
			return SRM.diagram.part.SRMVisualIDRegistry.checkNodeVisualID(containerView, domainElement, candidate);
		}

		/**
		* @generated
		*/
		@Override

		public boolean isCompartmentVisualID(int visualID) {
			return SRM.diagram.part.SRMVisualIDRegistry.isCompartmentVisualID(visualID);
		}

		/**
		* @generated
		*/
		@Override

		public boolean isSemanticLeafVisualID(int visualID) {
			return SRM.diagram.part.SRMVisualIDRegistry.isSemanticLeafVisualID(visualID);
		}
	};

}
