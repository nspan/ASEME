package statechart.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import statechart.diagram.providers.StateChartElementTypes;
import statechart.diagram.providers.StateChartModelingAssistantProvider;

/**
 * @generated
 */
public class StateChartModelingAssistantProviderOfModelEditPart extends StateChartModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(7);
		types.add(StateChartElementTypes.Node_2001);
		types.add(StateChartElementTypes.Node_2002);
		types.add(StateChartElementTypes.Variable_2003);
		types.add(StateChartElementTypes.Node_2004);
		types.add(StateChartElementTypes.Node_2005);
		types.add(StateChartElementTypes.Node_2006);
		types.add(StateChartElementTypes.Node_2007);
		return types;
	}

}
