package SUC.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import SUC.diagram.providers.SUCElementTypes;
import SUC.diagram.providers.SUCModelingAssistantProvider;

/**
 * @generated
 */
public class SUCModelingAssistantProviderOfSUCmodelEditPart extends SUCModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(SUCElementTypes.UseCase_2002);
		types.add(SUCElementTypes.Role_2001);
		return types;
	}

}
