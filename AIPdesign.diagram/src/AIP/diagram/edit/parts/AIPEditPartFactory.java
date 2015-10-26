package AIP.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

import AIP.diagram.part.AIPVisualIDRegistry;

/**
 * @generated
 */
public class AIPEditPartFactory implements EditPartFactory {

	/**
	* @generated
	*/
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (AIPVisualIDRegistry.getVisualID(view)) {

			case AIPmodelEditPart.VISUAL_ID:
				return new AIPmodelEditPart(view);

			case ParticipantEditPart.VISUAL_ID:
				return new ParticipantEditPart(view);

			case ParticipantNameEditPart.VISUAL_ID:
				return new ParticipantNameEditPart(view);

			case ParticipantEngaging_rulesEditPart.VISUAL_ID:
				return new ParticipantEngaging_rulesEditPart(view);

			case ParticipantOutcomesEditPart.VISUAL_ID:
				return new ParticipantOutcomesEditPart(view);

			case ParticipantLivenessEditPart.VISUAL_ID:
				return new ParticipantLivenessEditPart(view);

			case ProtocolEditPart.VISUAL_ID:
				return new ProtocolEditPart(view);

			case ProtocolNameEditPart.VISUAL_ID:
				return new ProtocolNameEditPart(view);

			case ProtocolParticipantsEditPart.VISUAL_ID:
				return new ProtocolParticipantsEditPart(view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	* @generated
	*/
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	* @generated
	*/
	public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source) {
		return CellEditorLocatorAccess.INSTANCE.getTextCellEditorLocator(source);
	}

}
