package IAC.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import IAC.diagram.providers.IACElementTypes;

/**
 * @generated
 */
public class TransitionItemSemanticEditPolicy extends
		IACBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public TransitionItemSemanticEditPolicy() {
		super(IACElementTypes.Transition_4007);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
