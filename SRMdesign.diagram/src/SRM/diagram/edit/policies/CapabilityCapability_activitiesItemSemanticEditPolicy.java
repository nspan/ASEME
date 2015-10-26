package SRM.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

import SRM.diagram.providers.SRMElementTypes;

/**
 * @generated
 */
public class CapabilityCapability_activitiesItemSemanticEditPolicy extends
		SRMBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public CapabilityCapability_activitiesItemSemanticEditPolicy() {
		super(SRMElementTypes.CapabilityCapability_activities_4003);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
