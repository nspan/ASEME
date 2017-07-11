package SUC.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

import SUC.diagram.edit.commands.RoleParticipates_inCreateCommand;
import SUC.diagram.edit.commands.RoleParticipates_inReorientCommand;
import SUC.diagram.edit.commands.UseCaseIncludeCreateCommand;
import SUC.diagram.edit.commands.UseCaseIncludeReorientCommand;
import SUC.diagram.edit.parts.RoleParticipates_inEditPart;
import SUC.diagram.edit.parts.UseCaseIncludeEditPart;
import SUC.diagram.part.SUCVisualIDRegistry;
import SUC.diagram.providers.SUCElementTypes;

/**
 * @generated
 */
public class UseCaseItemSemanticEditPolicy extends SUCBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public UseCaseItemSemanticEditPolicy() {
		super(SUCElementTypes.UseCase_2002);
	}

	/**
	* @generated
	*/
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
			Edge incomingLink = (Edge) it.next();
			if (SUCVisualIDRegistry.getVisualID(incomingLink) == UseCaseIncludeEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (SUCVisualIDRegistry.getVisualID(incomingLink) == RoleParticipates_inEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (SUCVisualIDRegistry.getVisualID(outgoingLink) == UseCaseIncludeEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(outgoingLink.getSource().getElement(), null,
						outgoingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
		}
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (SUCElementTypes.UseCaseInclude_4001 == req.getElementType()) {
			return getGEFWrapper(new UseCaseIncludeCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (SUCElementTypes.RoleParticipates_in_4002 == req.getElementType()) {
			return null;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (SUCElementTypes.UseCaseInclude_4001 == req.getElementType()) {
			return getGEFWrapper(new UseCaseIncludeCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (SUCElementTypes.RoleParticipates_in_4002 == req.getElementType()) {
			return getGEFWrapper(new RoleParticipates_inCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(ReorientReferenceRelationshipRequest req) {
		switch (getVisualID(req)) {
		case UseCaseIncludeEditPart.VISUAL_ID:
			return getGEFWrapper(new UseCaseIncludeReorientCommand(req));
		case RoleParticipates_inEditPart.VISUAL_ID:
			return getGEFWrapper(new RoleParticipates_inReorientCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
