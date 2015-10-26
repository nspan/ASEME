package SRM.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

import SRM.diagram.part.SRMVisualIDRegistry;

/**
 * @generated
 */
public class SRMEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (SRMVisualIDRegistry.getVisualID(view)) {

			case SRMmodelEditPart.VISUAL_ID:
				return new SRMmodelEditPart(view);

			case ActivityEditPart.VISUAL_ID:
				return new ActivityEditPart(view);

			case ActivityNameEditPart.VISUAL_ID:
				return new ActivityNameEditPart(view);

			case FunctionalityEditPart.VISUAL_ID:
				return new FunctionalityEditPart(view);

			case FunctionalityDescriptionEditPart.VISUAL_ID:
				return new FunctionalityDescriptionEditPart(view);

			case RoleEditPart.VISUAL_ID:
				return new RoleEditPart(view);

			case RoleNameEditPart.VISUAL_ID:
				return new RoleNameEditPart(view);

			case CapabilityEditPart.VISUAL_ID:
				return new CapabilityEditPart(view);

			case CapabilityNameEditPart.VISUAL_ID:
				return new CapabilityNameEditPart(view);

			case FunctionalityActivitiesEditPart.VISUAL_ID:
				return new FunctionalityActivitiesEditPart(view);

			case RoleCapabilitiesEditPart.VISUAL_ID:
				return new RoleCapabilitiesEditPart(view);

			case CapabilityCapability_activitiesEditPart.VISUAL_ID:
				return new CapabilityCapability_activitiesEditPart(view);

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
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		return CellEditorLocatorAccess.INSTANCE
				.getTextCellEditorLocator(source);
	}

}
