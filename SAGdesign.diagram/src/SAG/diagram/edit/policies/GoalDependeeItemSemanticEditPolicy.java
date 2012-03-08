package SAG.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

import SAG.diagram.providers.SAGElementTypes;

/**
 * @generated
 */
public class GoalDependeeItemSemanticEditPolicy extends
		SAGBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public GoalDependeeItemSemanticEditPolicy() {
		super(SAGElementTypes.GoalDependee_4013);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
