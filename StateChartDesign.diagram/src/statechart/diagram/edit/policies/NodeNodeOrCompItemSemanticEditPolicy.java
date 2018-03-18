package statechart.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import statechart.diagram.edit.commands.Node10CreateCommand;
import statechart.diagram.edit.commands.Node11CreateCommand;
import statechart.diagram.edit.commands.Node12CreateCommand;
import statechart.diagram.edit.commands.Node13CreateCommand;
import statechart.diagram.edit.commands.Node14CreateCommand;
import statechart.diagram.edit.commands.Node15CreateCommand;
import statechart.diagram.edit.commands.Node16CreateCommand;
import statechart.diagram.edit.commands.Node7CreateCommand;
import statechart.diagram.edit.commands.Node8CreateCommand;
import statechart.diagram.edit.commands.Node9CreateCommand;
import statechart.diagram.providers.StateChartElementTypes;

/**
 * @generated
 */
public class NodeNodeOrCompItemSemanticEditPolicy extends StateChartBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public NodeNodeOrCompItemSemanticEditPolicy() {
		super(StateChartElementTypes.Node_2001);
	}

	/**
	* @generated
	*/
	protected Command getCreateCommand(CreateElementRequest req) {
		if (StateChartElementTypes.Node_3001 == req.getElementType()) {
			return getGEFWrapper(new Node9CreateCommand(req));
		}
		if (StateChartElementTypes.Node_3002 == req.getElementType()) {
			return getGEFWrapper(new Node10CreateCommand(req));
		}
		if (StateChartElementTypes.Node_3003 == req.getElementType()) {
			return getGEFWrapper(new Node11CreateCommand(req));
		}
		if (StateChartElementTypes.Node_3004 == req.getElementType()) {
			return getGEFWrapper(new Node12CreateCommand(req));
		}
		if (StateChartElementTypes.Node_3005 == req.getElementType()) {
			return getGEFWrapper(new Node13CreateCommand(req));
		}
		if (StateChartElementTypes.Node_3006 == req.getElementType()) {
			return getGEFWrapper(new Node14CreateCommand(req));
		}
		if (StateChartElementTypes.Node_3007 == req.getElementType()) {
			return getGEFWrapper(new Node15CreateCommand(req));
		}
		if (StateChartElementTypes.Node_3008 == req.getElementType()) {
			return getGEFWrapper(new Node16CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
