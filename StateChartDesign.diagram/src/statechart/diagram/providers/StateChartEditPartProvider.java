package statechart.diagram.providers;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;

import statechart.diagram.edit.parts.ModelEditPart;
import statechart.diagram.edit.parts.StateChartEditPartFactory;
import statechart.diagram.part.StateChartVisualIDRegistry;

/**
 * @generated
 */
public class StateChartEditPartProvider extends DefaultEditPartProvider {

	/**
	* @generated
	*/
	public StateChartEditPartProvider() {
		super(new StateChartEditPartFactory(), StateChartVisualIDRegistry.TYPED_INSTANCE, ModelEditPart.MODEL_ID);
	}

}
