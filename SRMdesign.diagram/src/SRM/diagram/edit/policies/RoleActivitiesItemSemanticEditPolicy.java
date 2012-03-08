package SRM.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

import SRM.diagram.providers.SRMElementTypes;

/**
 * @generated
 */
public class RoleActivitiesItemSemanticEditPolicy extends
		SRMBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public RoleActivitiesItemSemanticEditPolicy() {
		super(SRMElementTypes.RoleActivities_4005);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
