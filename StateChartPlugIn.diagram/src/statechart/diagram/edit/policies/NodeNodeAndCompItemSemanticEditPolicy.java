/*
 * Kouretes Statechart Editor-KSE developed 
 * by Angeliki Topalidou-Kyniazopoulou
 * for Kouretes Team. Code developed by Nikolaos Spanoudakis, Alex Parashos,
 * ibm, apache and eclipse is used.
 */
package statechart.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import statechart.diagram.edit.commands.Node9CreateCommand;
import statechart.diagram.providers.StateChartElementTypes;

/**
 * @generated
 */
public class NodeNodeAndCompItemSemanticEditPolicy extends
		StateChartBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public NodeNodeAndCompItemSemanticEditPolicy() {
		super(StateChartElementTypes.Node_3005);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (StateChartElementTypes.Node_3003 == req.getElementType()) {
			return getGEFWrapper(new Node9CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
