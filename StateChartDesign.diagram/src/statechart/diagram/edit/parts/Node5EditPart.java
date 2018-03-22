package statechart.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

import statechart.diagram.edit.policies.Node5ItemSemanticEditPolicy;
import statechart.diagram.part.StateChartVisualIDRegistry;

/**
 * @generated
 */
public class Node5EditPart extends ShapeNodeEditPart {

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 2006;

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
	public Node5EditPart(View view) {
		super(view);
	}

	/**
	* @generated
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new Node5ItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	* @generated
	*/
	protected LayoutEditPolicy createLayoutEditPolicy() {
		org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
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
		return primaryShape = new NodeBasicFigure();
	}

	/**
	* @generated
	*/
	public NodeBasicFigure getPrimaryShape() {
		return (NodeBasicFigure) primaryShape;
	}

	/**
	* @generated
	*/
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof NodeName3EditPart) {
			((NodeName3EditPart) childEditPart).setLabel(getPrimaryShape().getFigureNodeBasicName());
			return true;
		}
		if (childEditPart instanceof NodeActions2EditPart) {
			((NodeActions2EditPart) childEditPart).setLabel(getPrimaryShape().getFigureNodeBasicActions());
			return true;
		}
		return false;
	}

	/**
	* @generated
	*/
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof NodeName3EditPart) {
			return true;
		}
		if (childEditPart instanceof NodeActions2EditPart) {
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
	* @generated
	*/
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
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
		return getChildBySemanticHint(StateChartVisualIDRegistry.getType(NodeName3EditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public class NodeBasicFigure extends RoundedRectangle {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureNodeBasicName;

		/**
		* @generated
		*/
		private WrappingLabel fFigureNodeBasicActions;

		/**
			 * @generated
			 */
		public NodeBasicFigure() {
			this.setCornerDimensions(new Dimension(getMapMode().DPtoLP(18), getMapMode().DPtoLP(18)));
			this.setLineWidth(2);
			this.setForegroundColor(ColorConstants.black);
			this.setBackgroundColor(THIS_BACK);
			createContents();
		}

		/**
		 * @generated NOT
		 */
		private void createContents() {

			fFigureNodeBasicName = new WrappingLabel();

			fFigureNodeBasicName.setText("name");

			fFigureNodeBasicName.setFont(FFIGURENODEBASICNAME_FONT);

			fFigureNodeBasicName.setBorder(new MarginBorder(getMapMode().DPtoLP(10), getMapMode().DPtoLP(10),
					getMapMode().DPtoLP(10), getMapMode().DPtoLP(10)));

			this.add(fFigureNodeBasicName);

			fFigureNodeBasicActions = new WrappingLabel();

			fFigureNodeBasicActions.setText("");
			//added following line for showing more than one lines
			fFigureNodeBasicActions.setTextWrap(true);

			fFigureNodeBasicActions.setFont(FFIGURENODEBASICACTIONS_FONT);

			fFigureNodeBasicActions.setBorder(new MarginBorder(getMapMode().DPtoLP(10), getMapMode().DPtoLP(10),
					getMapMode().DPtoLP(10), getMapMode().DPtoLP(10)));

			this.add(fFigureNodeBasicActions);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureNodeBasicName() {
			return fFigureNodeBasicName;
		}

		/**
		* @generated
		*/
		public WrappingLabel getFigureNodeBasicActions() {
			return fFigureNodeBasicActions;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 192, 255, 124);

	/**
	 * @generated
	 */
	static final Font FFIGURENODEBASICNAME_FONT = new Font(Display.getCurrent(), "TitleFont", 14, SWT.BOLD);

	/**
	* @generated
	*/
	static final Font FFIGURENODEBASICACTIONS_FONT = new Font(Display.getCurrent(), "TextFont", 12, SWT.NORMAL);

}
