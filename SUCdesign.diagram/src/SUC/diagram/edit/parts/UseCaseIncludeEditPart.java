package SUC.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

import SUC.diagram.edit.policies.UseCaseIncludeItemSemanticEditPolicy;

/**
 * @generated
 */
public class UseCaseIncludeEditPart extends ConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 4001;

	/**
	* @generated
	*/
	public UseCaseIncludeEditPart(View view) {
		super(view);
	}

	/**
	* @generated
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new UseCaseIncludeItemSemanticEditPolicy());
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
		return new UseCaseIncludeFigure();
	}

	/**
	* @generated
	*/
	public UseCaseIncludeFigure getPrimaryShape() {
		return (UseCaseIncludeFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class UseCaseIncludeFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureIncludeLabel;

		/**
		 * @generated
		 */
		public UseCaseIncludeFigure() {
			this.setForegroundColor(ColorConstants.black);
			this.setBackgroundColor(ColorConstants.black);

			createContents();
			setTargetDecoration(createTargetDecoration());
		}

		/**
		 * @generated NOT
		 */
		private void createContents() {

			fFigureIncludeLabel = new WrappingLabel();

			fFigureIncludeLabel.setText("<<includes>>");

			fFigureIncludeLabel.setFont(FFIGUREINCLUDELABEL_FONT);

			this.add(fFigureIncludeLabel, new MidpointLocator(this, 0));

		}

		/**
		 * @generated
		 */
		private RotatableDecoration createTargetDecoration() {
			PolylineDecoration df = new PolylineDecoration();
			return df;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureIncludeLabel() {
			return fFigureIncludeLabel;
		}

	}

	/**
	 * @generated
	 */
	static final Font FFIGUREINCLUDELABEL_FONT = new Font(Display.getCurrent(),
			Display.getDefault().getSystemFont().getFontData()[0].getName(), 9, SWT.ITALIC);

}
