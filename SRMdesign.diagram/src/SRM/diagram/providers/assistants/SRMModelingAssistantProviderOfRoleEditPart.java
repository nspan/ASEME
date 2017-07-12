package SRM.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import SRM.diagram.edit.parts.ActivityEditPart;
import SRM.diagram.edit.parts.CapabilityEditPart;
import SRM.diagram.edit.parts.RoleEditPart;
import SRM.diagram.providers.SRMElementTypes;
import SRM.diagram.providers.SRMModelingAssistantProvider;

/**
 * @generated
 */
public class SRMModelingAssistantProviderOfRoleEditPart extends SRMModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSource((RoleEditPart) sourceEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnSource(RoleEditPart source) {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(SRMElementTypes.RoleRole_activities_4001);
		types.add(SRMElementTypes.RoleCapabilities_4004);
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSourceAndTarget((RoleEditPart) sourceEditPart, targetEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnSourceAndTarget(RoleEditPart source, IGraphicalEditPart targetEditPart) {
		List<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof ActivityEditPart) {
			types.add(SRMElementTypes.RoleRole_activities_4001);
		}
		if (targetEditPart instanceof CapabilityEditPart) {
			types.add(SRMElementTypes.RoleCapabilities_4004);
		}
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForTarget(IAdaptable source, IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForTarget((RoleEditPart) sourceEditPart, relationshipType);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetTypesForTarget(RoleEditPart source, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == SRMElementTypes.RoleRole_activities_4001) {
			types.add(SRMElementTypes.Activity_2004);
		} else if (relationshipType == SRMElementTypes.RoleCapabilities_4004) {
			types.add(SRMElementTypes.Capability_2002);
		}
		return types;
	}

}
