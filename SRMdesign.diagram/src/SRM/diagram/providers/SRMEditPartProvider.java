package SRM.diagram.providers;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;

import SRM.diagram.edit.parts.SRMEditPartFactory;
import SRM.diagram.edit.parts.SRMmodelEditPart;
import SRM.diagram.part.SRMVisualIDRegistry;

/**
 * @generated
 */
public class SRMEditPartProvider extends DefaultEditPartProvider {

	/**
	* @generated
	*/
	public SRMEditPartProvider() {
		super(new SRMEditPartFactory(), SRMVisualIDRegistry.TYPED_INSTANCE, SRMmodelEditPart.MODEL_ID);
	}

}
