package SAG.diagram.edit.policies;

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

import SAG.diagram.edit.commands.ActorMy_goalCreateCommand;
import SAG.diagram.edit.commands.ActorMy_goalReorientCommand;
import SAG.diagram.edit.commands.GoalDependeeCreateCommand;
import SAG.diagram.edit.commands.GoalDependeeReorientCommand;
import SAG.diagram.edit.parts.ActorMy_goalEditPart;
import SAG.diagram.edit.parts.GoalDependeeEditPart;
import SAG.diagram.part.SAGVisualIDRegistry;
import SAG.diagram.providers.SAGElementTypes;

/**
 * @generated
 */
public class GoalItemSemanticEditPolicy extends SAGBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public GoalItemSemanticEditPolicy() {
		super(SAGElementTypes.Goal_2002);
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
			if (SAGVisualIDRegistry.getVisualID(incomingLink) == ActorMy_goalEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (SAGVisualIDRegistry.getVisualID(outgoingLink) == GoalDependeeEditPart.VISUAL_ID) {
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
		if (SAGElementTypes.ActorMy_goal_4001 == req.getElementType()) {
			return null;
		}
		if (SAGElementTypes.GoalDependee_4002 == req.getElementType()) {
			return getGEFWrapper(new GoalDependeeCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (SAGElementTypes.ActorMy_goal_4001 == req.getElementType()) {
			return getGEFWrapper(new ActorMy_goalCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if (SAGElementTypes.GoalDependee_4002 == req.getElementType()) {
			return null;
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
		case ActorMy_goalEditPart.VISUAL_ID:
			return getGEFWrapper(new ActorMy_goalReorientCommand(req));
		case GoalDependeeEditPart.VISUAL_ID:
			return getGEFWrapper(new GoalDependeeReorientCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
