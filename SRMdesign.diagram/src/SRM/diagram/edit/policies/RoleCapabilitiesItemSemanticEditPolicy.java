package SRM.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

import SRM.diagram.providers.SRMElementTypes;

/**
 * @generated
 */
public class RoleCapabilitiesItemSemanticEditPolicy extends
		SRMBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public RoleCapabilitiesItemSemanticEditPolicy() {
		super(SRMElementTypes.RoleCapabilities_4002);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
