package SAG.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.View;

import SAG.diagram.edit.policies.ActorMy_goalItemSemanticEditPolicy;

/**
 * @generated
 */
public class ActorMy_goalEditPart extends ConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 4001;

	/**
	* @generated
	*/
	public ActorMy_goalEditPart(View view) {
		super(view);
	}

	/**
	* @generated
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new ActorMy_goalItemSemanticEditPolicy());
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
		return new ActorMy_goalFigure();
	}

	/**
	* @generated
	*/
	public ActorMy_goalFigure getPrimaryShape() {
		return (ActorMy_goalFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class ActorMy_goalFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		public ActorMy_goalFigure() {
			this.setLineWidth(2);
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
