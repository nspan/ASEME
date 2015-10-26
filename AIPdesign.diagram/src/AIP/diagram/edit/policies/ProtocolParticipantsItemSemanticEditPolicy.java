package AIP.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

import AIP.diagram.providers.AIPElementTypes;

/**
 * @generated
 */
public class ProtocolParticipantsItemSemanticEditPolicy extends AIPBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public ProtocolParticipantsItemSemanticEditPolicy() {
		super(AIPElementTypes.ProtocolParticipants_4002);
	}

	/**
	* @generated
	*/
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
