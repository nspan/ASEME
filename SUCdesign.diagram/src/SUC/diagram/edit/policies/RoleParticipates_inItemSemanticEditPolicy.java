package SUC.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

import SUC.diagram.providers.SUCElementTypes;

/**
 * @generated
 */
public class RoleParticipates_inItemSemanticEditPolicy extends
		SUCBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public RoleParticipates_inItemSemanticEditPolicy() {
		super(SUCElementTypes.RoleParticipates_in_4008);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
