package AIP.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import AIP.diagram.providers.AIPElementTypes;
import AIP.diagram.providers.AIPModelingAssistantProvider;

/**
 * @generated
 */
public class AIPModelingAssistantProviderOfAIPmodelEditPart extends AIPModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(AIPElementTypes.Protocol_2001);
		types.add(AIPElementTypes.Participant_2002);
		return types;
	}

}
