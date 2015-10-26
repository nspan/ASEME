package AIP.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import AIP.diagram.edit.parts.ParticipantEditPart;
import AIP.diagram.providers.AIPElementTypes;
import AIP.diagram.providers.AIPModelingAssistantProvider;

/**
 * @generated
 */
public class AIPModelingAssistantProviderOfParticipantEditPart extends AIPModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnTarget((ParticipantEditPart) targetEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnTarget(ParticipantEditPart target) {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(AIPElementTypes.ProtocolParticipants_4002);
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForSource(IAdaptable target, IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForSource((ParticipantEditPart) targetEditPart, relationshipType);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetTypesForSource(ParticipantEditPart target, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == AIPElementTypes.ProtocolParticipants_4002) {
			types.add(AIPElementTypes.Protocol_2004);
		}
		return types;
	}

}
