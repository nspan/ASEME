package IAC.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

import IAC.diagram.providers.IACElementTypes;

/**
 * @generated
 */
public class NodeFather_ofItemSemanticEditPolicy extends
		IACBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public NodeFather_ofItemSemanticEditPolicy() {
		super(IACElementTypes.NodeFather_of_4008);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
