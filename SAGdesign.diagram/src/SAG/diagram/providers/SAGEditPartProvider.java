package SAG.diagram.providers;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;

import SAG.diagram.edit.parts.SAGEditPartFactory;
import SAG.diagram.edit.parts.SAGmodelEditPart;
import SAG.diagram.part.SAGVisualIDRegistry;

/**
 * @generated
 */
public class SAGEditPartProvider extends DefaultEditPartProvider {

	/**
	* @generated
	*/
	public SAGEditPartProvider() {
		super(new SAGEditPartFactory(), SAGVisualIDRegistry.TYPED_INSTANCE, SAGmodelEditPart.MODEL_ID);
	}

}
