package SRM.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.View;

import SRM.diagram.edit.policies.CapabilityCapability_activitiesItemSemanticEditPolicy;

/**
 * @generated
 */
public class CapabilityCapability_activitiesEditPart extends ConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 4002;

	/**
	* @generated
	*/
	public CapabilityCapability_activitiesEditPart(View view) {
		super(view);
	}

	/**
	* @generated
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new CapabilityCapability_activitiesItemSemanticEditPolicy());
	}

	/**
	* Creates figure for this edit part.
	* 
	* Body of this method does not depend on settings in generation model
	* so you may safely remove <i>generated</i> tag and modify it.
	* 
	* @generated
	*/

	protected Connection createConnectionFigure() {
		return new CapabilityCapability_activitiesFigure();
	}

	/**
	* @generated
	*/
	public CapabilityCapability_activitiesFigure getPrimaryShape() {
		return (CapabilityCapability_activitiesFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class CapabilityCapability_activitiesFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		public CapabilityCapability_activitiesFigure() {
			this.setForegroundColor(ColorConstants.black);
			this.setBackgroundColor(ColorConstants.black);

			setTargetDecoration(createTargetDecoration());
		}

		/**
		 * @generated
		 */
		private RotatableDecoration createTargetDecoration() {
			PolylineDecoration df = new PolylineDecoration();
			return df;
		}

	}

}
