package AIP.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import AIP.diagram.edit.commands.ParticipantCreateCommand;
import AIP.diagram.edit.commands.ProtocolCreateCommand;
import AIP.diagram.providers.AIPElementTypes;

/**
 * @generated
 */
public class AIPmodelItemSemanticEditPolicy extends AIPBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public AIPmodelItemSemanticEditPolicy() {
		super(AIPElementTypes.AIPmodel_1000);
	}

	/**
	* @generated
	*/
	protected Command getCreateCommand(CreateElementRequest req) {
		if (AIPElementTypes.Participant_2003 == req.getElementType()) {
			return getGEFWrapper(new ParticipantCreateCommand(req));
		}
		if (AIPElementTypes.Protocol_2004 == req.getElementType()) {
			return getGEFWrapper(new ProtocolCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	* @generated
	*/
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	* @generated
	*/
	private static class DuplicateAnythingCommand extends DuplicateEObjectsCommand {

		/**
		* @generated
		*/
		public DuplicateAnythingCommand(TransactionalEditingDomain editingDomain, DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req.getElementsToBeDuplicated(), req.getAllDuplicatedElementsMap());
		}

	}

}
