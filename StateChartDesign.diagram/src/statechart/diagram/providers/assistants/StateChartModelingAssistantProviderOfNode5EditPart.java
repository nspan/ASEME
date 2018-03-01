package statechart.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import statechart.diagram.edit.parts.Node10EditPart;
import statechart.diagram.edit.parts.Node11EditPart;
import statechart.diagram.edit.parts.Node12EditPart;
import statechart.diagram.edit.parts.Node13EditPart;
import statechart.diagram.edit.parts.Node14EditPart;
import statechart.diagram.edit.parts.Node2EditPart;
import statechart.diagram.edit.parts.Node3EditPart;
import statechart.diagram.edit.parts.Node4EditPart;
import statechart.diagram.edit.parts.Node5EditPart;
import statechart.diagram.edit.parts.Node6EditPart;
import statechart.diagram.edit.parts.Node7EditPart;
import statechart.diagram.edit.parts.Node8EditPart;
import statechart.diagram.edit.parts.Node9EditPart;
import statechart.diagram.edit.parts.NodeEditPart;
import statechart.diagram.providers.StateChartElementTypes;
import statechart.diagram.providers.StateChartModelingAssistantProvider;

/**
 * @generated
 */
public class StateChartModelingAssistantProviderOfNode5EditPart extends StateChartModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSource((Node5EditPart) sourceEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnSource(Node5EditPart source) {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(StateChartElementTypes.Transition_4001);
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSourceAndTarget((Node5EditPart) sourceEditPart, targetEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnSourceAndTarget(Node5EditPart source, IGraphicalEditPart targetEditPart) {
		List<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof NodeEditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node2EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node3EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node4EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node5EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node6EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node7EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node8EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node9EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node10EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node11EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node12EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node13EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node14EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForTarget(IAdaptable source, IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForTarget((Node5EditPart) sourceEditPart, relationshipType);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetTypesForTarget(Node5EditPart source, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == StateChartElementTypes.Transition_4001) {
			types.add(StateChartElementTypes.Node_2001);
			types.add(StateChartElementTypes.Node_2002);
			types.add(StateChartElementTypes.Node_2004);
			types.add(StateChartElementTypes.Node_2005);
			types.add(StateChartElementTypes.Node_2006);
			types.add(StateChartElementTypes.Node_2007);
			types.add(StateChartElementTypes.Node_2008);
			types.add(StateChartElementTypes.Node_2009);
			types.add(StateChartElementTypes.Node_3001);
			types.add(StateChartElementTypes.Node_3002);
			types.add(StateChartElementTypes.Node_3003);
			types.add(StateChartElementTypes.Node_3004);
			types.add(StateChartElementTypes.Node_3005);
			types.add(StateChartElementTypes.Node_3006);
		}
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnTarget((Node5EditPart) targetEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnTarget(Node5EditPart target) {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(StateChartElementTypes.Transition_4001);
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForSource(IAdaptable target, IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForSource((Node5EditPart) targetEditPart, relationshipType);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetTypesForSource(Node5EditPart target, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == StateChartElementTypes.Transition_4001) {
			types.add(StateChartElementTypes.Node_2001);
			types.add(StateChartElementTypes.Node_2002);
			types.add(StateChartElementTypes.Node_2004);
			types.add(StateChartElementTypes.Node_2005);
			types.add(StateChartElementTypes.Node_2006);
			types.add(StateChartElementTypes.Node_2007);
			types.add(StateChartElementTypes.Node_2008);
			types.add(StateChartElementTypes.Node_2009);
			types.add(StateChartElementTypes.Node_3001);
			types.add(StateChartElementTypes.Node_3002);
			types.add(StateChartElementTypes.Node_3003);
			types.add(StateChartElementTypes.Node_3004);
			types.add(StateChartElementTypes.Node_3005);
			types.add(StateChartElementTypes.Node_3006);
		}
		return types;
	}

}
