package AIP.diagram.edit.parts;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

import AIP.diagram.edit.policies.ParticipantItemSemanticEditPolicy;
import AIP.diagram.part.AIPVisualIDRegistry;

/**
 * @generated
 */
public class ParticipantEditPart extends ShapeNodeEditPart {

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 2002;

	/**
	* @generated
	*/
	protected IFigure contentPane;

	/**
	* @generated
	*/
	protected IFigure primaryShape;

	/**
	* @generated
	*/
	public ParticipantEditPart(View view) {
		super(view);
	}

	/**
	* @generated
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new ParticipantItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	* @generated
	*/
	protected LayoutEditPolicy createLayoutEditPolicy() {

		FlowLayoutEditPolicy lep = new FlowLayoutEditPolicy() {

			protected Command createAddCommand(EditPart child, EditPart after) {
				return null;
			}

			protected Command createMoveChildCommand(EditPart child, EditPart after) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	* @generated
	*/
	protected IFigure createNodeShape() {
		return primaryShape = new ParticipantFigure();
	}

	/**
	* @generated
	*/
	public ParticipantFigure getPrimaryShape() {
		return (ParticipantFigure) primaryShape;
	}

	/**
	* @generated
	*/
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ParticipantNameEditPart) {
			((ParticipantNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureParticipantNameFigure());
			return true;
		}
		if (childEditPart instanceof ParticipantLivenessEditPart) {
			((ParticipantLivenessEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureParticipantLivenessFigure());
			return true;
		}
		if (childEditPart instanceof ParticipantEngaging_rulesEditPart) {
			((ParticipantEngaging_rulesEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureParticipantEngaging_rulesFigure());
			return true;
		}
		if (childEditPart instanceof ParticipantOutcomesEditPart) {
			((ParticipantOutcomesEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureParticipantOutcomesFigure());
			return true;
		}
		return false;
	}

	/**
	* @generated
	*/
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ParticipantNameEditPart) {
			return true;
		}
		if (childEditPart instanceof ParticipantLivenessEditPart) {
			return true;
		}
		if (childEditPart instanceof ParticipantEngaging_rulesEditPart) {
			return true;
		}
		if (childEditPart instanceof ParticipantOutcomesEditPart) {
			return true;
		}
		return false;
	}

	/**
	* @generated
	*/
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	* @generated
	*/
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	* @generated
	*/
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		return getContentPane();
	}

	/**
	* @generated
	*/
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
		return result;
	}

	/**
	* Creates figure for this edit part.
	* 
	* Body of this method does not depend on settings in generation model
	* so you may safely remove <i>generated</i> tag and modify it.
	* 
	* @generated NOT
	*/
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();

		Label tooltip = new Label("You can edit multiline attributes from the properties view ");
		figure.setToolTip(tooltip);

		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	* Default implementation treats passed figure as content pane.
	* Respects layout one may have set for generated figure.
	* @param nodeShape instance of generated figure class
	* @generated
	*/
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	* @generated
	*/
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	* @generated
	*/
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	* @generated
	*/
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	* @generated
	*/
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	* @generated
	*/
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	* @generated
	*/
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(AIPVisualIDRegistry.getType(ParticipantNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public class ParticipantFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureParticipantNameFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureParticipantEngaging_rulesFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureParticipantOutcomesFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureParticipantLivenessFigure;

		/**
		 * @generated
		 */
		public ParticipantFigure() {

			FlowLayout layoutThis = new FlowLayout();
			layoutThis.setStretchMinorAxis(false);
			layoutThis.setMinorAlignment(FlowLayout.ALIGN_LEFTTOP);

			layoutThis.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
			layoutThis.setMajorSpacing(5);
			layoutThis.setMinorSpacing(5);
			layoutThis.setHorizontal(false);

			this.setLayoutManager(layoutThis);

			this.setForegroundColor(THIS_FORE);
			this.setBackgroundColor(THIS_BACK);
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureParticipantNameFigure = new WrappingLabel();

			fFigureParticipantNameFigure.setText("<Insert Name>");

			this.add(fFigureParticipantNameFigure);

			fFigureParticipantEngaging_rulesFigure = new WrappingLabel();

			fFigureParticipantEngaging_rulesFigure.setText("<Insert Engaging rules>");

			this.add(fFigureParticipantEngaging_rulesFigure);

			fFigureParticipantOutcomesFigure = new WrappingLabel();

			fFigureParticipantOutcomesFigure.setText("<Insert Outcomes>");

			this.add(fFigureParticipantOutcomesFigure);

			fFigureParticipantLivenessFigure = new WrappingLabel();

			fFigureParticipantLivenessFigure.setText("<Insert Liveness property>");

			this.add(fFigureParticipantLivenessFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureParticipantNameFigure() {
			return fFigureParticipantNameFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureParticipantEngaging_rulesFigure() {
			return fFigureParticipantEngaging_rulesFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureParticipantOutcomesFigure() {
			return fFigureParticipantOutcomesFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureParticipantLivenessFigure() {
			return fFigureParticipantLivenessFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_FORE = new Color(null, 0, 0, 0);

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 214, 249, 248);

}
