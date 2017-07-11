package SUC.diagram.providers;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;

import SUC.diagram.edit.parts.SUCEditPartFactory;
import SUC.diagram.edit.parts.SUCmodelEditPart;
import SUC.diagram.part.SUCVisualIDRegistry;

/**
 * @generated
 */
public class SUCEditPartProvider extends DefaultEditPartProvider {

	/**
	* @generated
	*/
	public SUCEditPartProvider() {
		super(new SUCEditPartFactory(), SUCVisualIDRegistry.TYPED_INSTANCE, SUCmodelEditPart.MODEL_ID);
	}

}
