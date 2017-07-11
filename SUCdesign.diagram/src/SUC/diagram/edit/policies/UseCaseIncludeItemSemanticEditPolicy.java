package SUC.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

import SUC.diagram.providers.SUCElementTypes;

/**
 * @generated
 */
public class UseCaseIncludeItemSemanticEditPolicy extends SUCBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public UseCaseIncludeItemSemanticEditPolicy() {
		super(SUCElementTypes.UseCaseInclude_4001);
	}

	/**
	* @generated
	*/
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
