package SRM.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import SRM.diagram.edit.parts.ActivityEditPart;
import SRM.diagram.providers.SRMElementTypes;
import SRM.diagram.providers.SRMModelingAssistantProvider;

/**
 * @generated
 */
public class SRMModelingAssistantProviderOfActivityEditPart extends
		SRMModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnTarget((ActivityEditPart) targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnTarget(ActivityEditPart target) {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(SRMElementTypes.FunctionalityActivities_4001);
		types.add(SRMElementTypes.CapabilityCapability_activities_4003);
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForSource(IAdaptable target,
			IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForSource((ActivityEditPart) targetEditPart,
				relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForSource(ActivityEditPart target,
			IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == SRMElementTypes.FunctionalityActivities_4001) {
			types.add(SRMElementTypes.Functionality_2003);
		} else if (relationshipType == SRMElementTypes.CapabilityCapability_activities_4003) {
			types.add(SRMElementTypes.Capability_2001);
		}
		return types;
	}

}
