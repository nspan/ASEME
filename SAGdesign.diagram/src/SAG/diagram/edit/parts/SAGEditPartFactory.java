package SAG.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

import SAG.diagram.part.SAGVisualIDRegistry;

/**
 * @generated
 */
public class SAGEditPartFactory implements EditPartFactory {

	/**
	* @generated
	*/
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (SAGVisualIDRegistry.getVisualID(view)) {

			case SAGmodelEditPart.VISUAL_ID:
				return new SAGmodelEditPart(view);

			case ActorEditPart.VISUAL_ID:
				return new ActorEditPart(view);

			case ActorNameEditPart.VISUAL_ID:
				return new ActorNameEditPart(view);

			case GoalEditPart.VISUAL_ID:
				return new GoalEditPart(view);

			case GoalNameEditPart.VISUAL_ID:
				return new GoalNameEditPart(view);

			case ActorMy_goalEditPart.VISUAL_ID:
				return new ActorMy_goalEditPart(view);

			case GoalDependeeEditPart.VISUAL_ID:
				return new GoalDependeeEditPart(view);

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
