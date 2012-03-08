package IAC.diagram.edit.policies;

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
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

import IAC.diagram.edit.commands.NodeFather_ofCreateCommand;
import IAC.diagram.edit.commands.NodeFather_ofReorientCommand;
import IAC.diagram.edit.commands.NodeVariablesCreateCommand;
import IAC.diagram.edit.commands.NodeVariablesReorientCommand;
import IAC.diagram.edit.commands.TransitionCreateCommand;
import IAC.diagram.edit.commands.TransitionReorientCommand;
import IAC.diagram.edit.parts.NodeFather_ofEditPart;
import IAC.diagram.edit.parts.NodeVariablesEditPart;
import IAC.diagram.edit.parts.TransitionEditPart;
import IAC.diagram.part.IACVisualIDRegistry;
import IAC.diagram.providers.IACElementTypes;

/**
 * @generated
 */
public class NodeItemSemanticEditPolicy extends IACBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public NodeItemSemanticEditPolicy() {
		super(IACElementTypes.Node_2006);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(
				getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
			Edge incomingLink = (Edge) it.next();
			if (IACVisualIDRegistry.getVisualID(incomingLink) == TransitionEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (IACVisualIDRegistry.getVisualID(incomingLink) == NodeFather_ofEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (IACVisualIDRegistry.getVisualID(outgoingLink) == NodeVariablesEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						outgoingLink.getSource().getElement(), null,
						outgoingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (IACVisualIDRegistry.getVisualID(outgoingLink) == TransitionEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (IACVisualIDRegistry.getVisualID(outgoingLink) == NodeFather_ofEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						outgoingLink.getSource().getElement(), null,
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
		return command != null ? command : super
				.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (IACElementTypes.NodeVariables_4006 == req.getElementType()) {
			return getGEFWrapper(new NodeVariablesCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (IACElementTypes.Transition_4007 == req.getElementType()) {
			return getGEFWrapper(new TransitionCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (IACElementTypes.NodeFather_of_4008 == req.getElementType()) {
			return getGEFWrapper(new NodeFather_ofCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (IACElementTypes.NodeVariables_4006 == req.getElementType()) {
			return null;
		}
		if (IACElementTypes.Transition_4007 == req.getElementType()) {
			return getGEFWrapper(new TransitionCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (IACElementTypes.NodeFather_of_4008 == req.getElementType()) {
			return getGEFWrapper(new NodeFather_ofCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case TransitionEditPart.VISUAL_ID:
			return getGEFWrapper(new TransitionReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(
			ReorientReferenceRelationshipRequest req) {
		switch (getVisualID(req)) {
		case NodeVariablesEditPart.VISUAL_ID:
			return getGEFWrapper(new NodeVariablesReorientCommand(req));
		case NodeFather_ofEditPart.VISUAL_ID:
			return getGEFWrapper(new NodeFather_ofReorientCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
