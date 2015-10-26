package SRM.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import SRM.diagram.edit.commands.ActivityCreateCommand;
import SRM.diagram.edit.commands.CapabilityCreateCommand;
import SRM.diagram.edit.commands.FunctionalityCreateCommand;
import SRM.diagram.edit.commands.RoleCreateCommand;
import SRM.diagram.providers.SRMElementTypes;

/**
 * @generated
 */
public class SRMmodelItemSemanticEditPolicy extends
		SRMBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public SRMmodelItemSemanticEditPolicy() {
		super(SRMElementTypes.SRMmodel_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (SRMElementTypes.Activity_2004 == req.getElementType()) {
			return getGEFWrapper(new ActivityCreateCommand(req));
		}
		if (SRMElementTypes.Functionality_2003 == req.getElementType()) {
			return getGEFWrapper(new FunctionalityCreateCommand(req));
		}
		if (SRMElementTypes.Role_2002 == req.getElementType()) {
			return getGEFWrapper(new RoleCreateCommand(req));
		}
		if (SRMElementTypes.Capability_2001 == req.getElementType()) {
			return getGEFWrapper(new CapabilityCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}

	}

}
