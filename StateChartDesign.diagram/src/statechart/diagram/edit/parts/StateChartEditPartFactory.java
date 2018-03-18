package statechart.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

import statechart.diagram.part.StateChartVisualIDRegistry;

/**
 * @generated
 */
public class StateChartEditPartFactory implements EditPartFactory {

	/**
	* @generated
	*/
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (StateChartVisualIDRegistry.getVisualID(view)) {

			case ModelEditPart.VISUAL_ID:
				return new ModelEditPart(view);

			case NodeEditPart.VISUAL_ID:
				return new NodeEditPart(view);

			case NodeNameEditPart.VISUAL_ID:
				return new NodeNameEditPart(view);

			case NodeActionsEditPart.VISUAL_ID:
				return new NodeActionsEditPart(view);

			case Node2EditPart.VISUAL_ID:
				return new Node2EditPart(view);

			case NodeName2EditPart.VISUAL_ID:
				return new NodeName2EditPart(view);

			case VariableEditPart.VISUAL_ID:
				return new VariableEditPart(view);

			case VariableNameEditPart.VISUAL_ID:
				return new VariableNameEditPart(view);

			case VariableTypeEditPart.VISUAL_ID:
				return new VariableTypeEditPart(view);

			case Node3EditPart.VISUAL_ID:
				return new Node3EditPart(view);

			case Node4EditPart.VISUAL_ID:
				return new Node4EditPart(view);

			case Node5EditPart.VISUAL_ID:
				return new Node5EditPart(view);

			case NodeName3EditPart.VISUAL_ID:
				return new NodeName3EditPart(view);

			case NodeActions2EditPart.VISUAL_ID:
				return new NodeActions2EditPart(view);

			case Node6EditPart.VISUAL_ID:
				return new Node6EditPart(view);

			case WrappingLabelEditPart.VISUAL_ID:
				return new WrappingLabelEditPart(view);

			case Node7EditPart.VISUAL_ID:
				return new Node7EditPart(view);

			case WrappingLabel2EditPart.VISUAL_ID:
				return new WrappingLabel2EditPart(view);

			case Node8EditPart.VISUAL_ID:
				return new Node8EditPart(view);

			case WrappingLabel3EditPart.VISUAL_ID:
				return new WrappingLabel3EditPart(view);

			case Node9EditPart.VISUAL_ID:
				return new Node9EditPart(view);

			case WrappingLabel4EditPart.VISUAL_ID:
				return new WrappingLabel4EditPart(view);

			case Node10EditPart.VISUAL_ID:
				return new Node10EditPart(view);

			case NodeName4EditPart.VISUAL_ID:
				return new NodeName4EditPart(view);

			case NodeActions3EditPart.VISUAL_ID:
				return new NodeActions3EditPart(view);

			case Node11EditPart.VISUAL_ID:
				return new Node11EditPart(view);

			case NodeName5EditPart.VISUAL_ID:
				return new NodeName5EditPart(view);

			case NodeActions4EditPart.VISUAL_ID:
				return new NodeActions4EditPart(view);

			case Node12EditPart.VISUAL_ID:
				return new Node12EditPart(view);

			case Node13EditPart.VISUAL_ID:
				return new Node13EditPart(view);

			case NodeName6EditPart.VISUAL_ID:
				return new NodeName6EditPart(view);

			case Node14EditPart.VISUAL_ID:
				return new Node14EditPart(view);

			case Node15EditPart.VISUAL_ID:
				return new Node15EditPart(view);

			case WrappingLabel5EditPart.VISUAL_ID:
				return new WrappingLabel5EditPart(view);

			case Node16EditPart.VISUAL_ID:
				return new Node16EditPart(view);

			case WrappingLabel6EditPart.VISUAL_ID:
				return new WrappingLabel6EditPart(view);

			case NodeNodeOrCompEditPart.VISUAL_ID:
				return new NodeNodeOrCompEditPart(view);

			case NodeNodeOrComp2EditPart.VISUAL_ID:
				return new NodeNodeOrComp2EditPart(view);

			case NodeNodeAndCompEditPart.VISUAL_ID:
				return new NodeNodeAndCompEditPart(view);

			case NodeNodeAndComp2EditPart.VISUAL_ID:
				return new NodeNodeAndComp2EditPart(view);

			case TransitionEditPart.VISUAL_ID:
				return new TransitionEditPart(view);

			case TransitionTEEditPart.VISUAL_ID:
				return new TransitionTEEditPart(view);

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
