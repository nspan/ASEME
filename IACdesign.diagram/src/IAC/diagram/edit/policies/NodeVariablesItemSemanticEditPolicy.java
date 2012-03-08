package IAC.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

import IAC.diagram.providers.IACElementTypes;

/**
 * @generated
 */
public class NodeVariablesItemSemanticEditPolicy extends
		IACBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public NodeVariablesItemSemanticEditPolicy() {
		super(IACElementTypes.NodeVariables_4006);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
