package statechart.diagram.edit.parts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.edit.policies.reparent.CreationEditPolicyWithCustomReparent;

import statechart.diagram.edit.policies.ModelCanonicalEditPolicy;
import statechart.diagram.edit.policies.ModelItemSemanticEditPolicy;
import statechart.diagram.part.StateChartVisualIDRegistry;

/**
 * @generated
 */
public class ModelEditPart extends DiagramEditPart {

	/**
	* @generated
	*/
	public final static String MODEL_ID = "StateChart"; //$NON-NLS-1$

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 1000;

	/**
	* @generated
	*/
	public ModelEditPart(View view) {
		super(view);
	}

	/**
	* @generated
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new ModelItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new ModelCanonicalEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicyWithCustomReparent(StateChartVisualIDRegistry.TYPED_INSTANCE));
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
	}

}
