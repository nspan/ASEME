package SAG.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import SAG.diagram.providers.SAGElementTypes;
import SAG.diagram.providers.SAGModelingAssistantProvider;

/**
 * @generated
 */
public class SAGModelingAssistantProviderOfSAGmodelEditPart extends SAGModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(SAGElementTypes.Actor_2001);
		types.add(SAGElementTypes.Goal_2002);
		return types;
	}

}
