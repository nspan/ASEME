package AIP.diagram.providers;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;

import AIP.diagram.edit.parts.AIPEditPartFactory;
import AIP.diagram.edit.parts.AIPmodelEditPart;
import AIP.diagram.part.AIPVisualIDRegistry;

/**
 * @generated
 */
public class AIPEditPartProvider extends DefaultEditPartProvider {

	/**
	* @generated
	*/
	public AIPEditPartProvider() {
		super(new AIPEditPartFactory(), AIPVisualIDRegistry.TYPED_INSTANCE, AIPmodelEditPart.MODEL_ID);
	}

}
