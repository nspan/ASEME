package SAG.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

import SAG.diagram.providers.SAGElementTypes;

/**
 * @generated
 */
public class ActorMy_goalItemSemanticEditPolicy extends
		SAGBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ActorMy_goalItemSemanticEditPolicy() {
		super(SAGElementTypes.ActorMy_goal_4011);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
