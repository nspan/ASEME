package IAC.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
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
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

import IAC.diagram.edit.policies.NodeItemSemanticEditPolicy;
import IAC.diagram.part.IACVisualIDRegistry;
import IAC.diagram.providers.IACElementTypes;

/**
 * @generated
 */
public class NodeEditPart extends ShapeNodeEditPart {

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
	public NodeEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new NodeItemSemanticEditPolicy());
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
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
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
		return primaryShape = new NodeFigureCustom();
	}

	/**
	 * @generated
	 */
	public NodeFigureCustom getPrimaryShape() {
		return (NodeFigureCustom) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof NodeNameEditPart) {
			((NodeNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureNodeNameFigureCustom());
			return true;
		}
		if (childEditPart instanceof NodeTypeEditPart) {
			((NodeTypeEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureNodeTypeFigureCustom());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof NodeNameEditPart) {
			return true;
		}
		if (childEditPart instanceof NodeTypeEditPart) {
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
		return getChildBySemanticHint(IACVisualIDRegistry
				.getType(NodeNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSource() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(3);
		types.add(IACElementTypes.NodeVariables_4006);
		types.add(IACElementTypes.Transition_4007);
		types.add(IACElementTypes.NodeFather_of_4008);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSourceAndTarget(
			IGraphicalEditPart targetEditPart) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof VariableEditPart) {
			types.add(IACElementTypes.NodeVariables_4006);
		}
		if (targetEditPart instanceof IAC.diagram.edit.parts.NodeEditPart) {
			types.add(IACElementTypes.Transition_4007);
		}
		if (targetEditPart instanceof IAC.diagram.edit.parts.NodeEditPart) {
			types.add(IACElementTypes.NodeFather_of_4008);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForTarget(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == IACElementTypes.NodeVariables_4006) {
			types.add(IACElementTypes.Variable_2005);
		} else if (relationshipType == IACElementTypes.Transition_4007) {
			types.add(IACElementTypes.Node_2006);
		} else if (relationshipType == IACElementTypes.NodeFather_of_4008) {
			types.add(IACElementTypes.Node_2006);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnTarget() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(IACElementTypes.Transition_4007);
		types.add(IACElementTypes.NodeFather_of_4008);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForSource(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == IACElementTypes.Transition_4007) {
			types.add(IACElementTypes.Node_2006);
		} else if (relationshipType == IACElementTypes.NodeFather_of_4008) {
			types.add(IACElementTypes.Node_2006);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public class NodeFigureCustom extends Ellipse {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureNodeNameFigureCustom;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureNodeTypeFigureCustom;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureNodeLabelFigureCustom;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureNodeActivityFigureCustom;

		/**
		 * @generated
		 */
		public NodeFigureCustom() {
			this.setLineWidth(3);
			this.setForegroundColor(THIS_FORE);
			this.setBackgroundColor(THIS_BACK);
			this.setSize(getMapMode().DPtoLP(7), getMapMode().DPtoLP(7));
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureNodeNameFigureCustom = new WrappingLabel();
			fFigureNodeNameFigureCustom.setText("");

			this.add(fFigureNodeNameFigureCustom);

			fFigureNodeTypeFigureCustom = new WrappingLabel();
			fFigureNodeTypeFigureCustom.setText("");

			this.add(fFigureNodeTypeFigureCustom);

			fFigureNodeLabelFigureCustom = new WrappingLabel();
			fFigureNodeLabelFigureCustom.setText("");

			this.add(fFigureNodeLabelFigureCustom);

			fFigureNodeActivityFigureCustom = new WrappingLabel();
			fFigureNodeActivityFigureCustom.setText("");

			this.add(fFigureNodeActivityFigureCustom);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureNodeNameFigureCustom() {
			return fFigureNodeNameFigureCustom;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureNodeTypeFigureCustom() {
			return fFigureNodeTypeFigureCustom;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureNodeLabelFigureCustom() {
			return fFigureNodeLabelFigureCustom;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureNodeActivityFigureCustom() {
			return fFigureNodeActivityFigureCustom;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_FORE = new Color(null, 0, 0, 0);

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 214, 249, 218);

}
