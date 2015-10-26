package SRM.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import SRM.diagram.providers.SRMElementTypes;
import SRM.diagram.providers.SRMModelingAssistantProvider;

/**
 * @generated
 */
public class SRMModelingAssistantProviderOfSRMmodelEditPart extends
		SRMModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(4);
		types.add(SRMElementTypes.Activity_2004);
		types.add(SRMElementTypes.Functionality_2003);
		types.add(SRMElementTypes.Role_2002);
		types.add(SRMElementTypes.Capability_2001);
		return types;
	}

}
