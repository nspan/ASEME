
package SRM.diagram.part;

import java.util.Collections;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultLinkToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;

import SRM.diagram.providers.SRMElementTypes;

/**
 * @generated
 */
public class SRMPaletteFactory {

	/**
	* @generated
	*/
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createSRM1Group());
	}

	/**
	* Creates "SRM" palette tool group
	* @generated NOT
	*/
	private PaletteContainer createSRM1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(Messages.SRM1Group_title);
		paletteContainer.setId("createSRM1Group"); //$NON-NLS-1$
		paletteContainer.setDescription(Messages.SRM1Group_desc);
		paletteContainer.add(createRole1CreationTool());
		paletteContainer.add(createCapability4CreationTool());
		paletteContainer.add(createActivity3CreationTool());
		paletteContainer.add(createFunctionality6CreationTool());
		paletteContainer.add(createRoleCapabilities2CreationTool());
		paletteContainer.add(createRoleActivities8CreationTool());
		paletteContainer.add(createCapabilityCapability_activities5CreationTool());
		paletteContainer.add(createFunctionalityActivities7CreationTool());
		
		return paletteContainer;
	}

	/**
	* @generated
	*/
	private ToolEntry createRole1CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Role1CreationTool_title,
				Messages.Role1CreationTool_desc, Collections.singletonList(SRMElementTypes.Role_2003));
		entry.setId("createRole1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj16/Role.gif")); //$NON-NLS-1$
		entry.setLargeIcon(SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj32/Role.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createRoleCapabilities2CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.RoleCapabilities2CreationTool_title,
				Messages.RoleCapabilities2CreationTool_desc,
				Collections.singletonList(SRMElementTypes.RoleCapabilities_4004));
		entry.setId("createRoleCapabilities2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj16/Edge.gif")); //$NON-NLS-1$
		entry.setLargeIcon(SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj32/Edge.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createActivity3CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Activity3CreationTool_title,
				Messages.Activity3CreationTool_desc, Collections.singletonList(SRMElementTypes.Activity_2004));
		entry.setId("createActivity3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj16/Activity.gif")); //$NON-NLS-1$
		entry.setLargeIcon(SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj32/Activity.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createCapability4CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Capability4CreationTool_title,
				Messages.Capability4CreationTool_desc, Collections.singletonList(SRMElementTypes.Capability_2002));
		entry.setId("createCapability4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj16/Capability.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj32/Capability.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createCapabilityCapability_activities5CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(
				Messages.CapabilityCapability_activities5CreationTool_title,
				Messages.CapabilityCapability_activities5CreationTool_desc,
				Collections.singletonList(SRMElementTypes.CapabilityCapability_activities_4002));
		entry.setId("createCapabilityCapability_activities5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj16/Edge.gif")); //$NON-NLS-1$
		entry.setLargeIcon(SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj32/Edge.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createFunctionality6CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Functionality6CreationTool_title,
				Messages.Functionality6CreationTool_desc,
				Collections.singletonList(SRMElementTypes.Functionality_2001));
		entry.setId("createFunctionality6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj16/Functionality.gif")); //$NON-NLS-1$
		entry.setLargeIcon(
				SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj32/Functionality.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createFunctionalityActivities7CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.FunctionalityActivities7CreationTool_title,
				Messages.FunctionalityActivities7CreationTool_desc,
				Collections.singletonList(SRMElementTypes.FunctionalityActivities_4003));
		entry.setId("createFunctionalityActivities7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj16/Edge.gif")); //$NON-NLS-1$
		entry.setLargeIcon(SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj32/Edge.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createRoleActivities8CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.RoleActivities8CreationTool_title,
				Messages.RoleActivities8CreationTool_desc,
				Collections.singletonList(SRMElementTypes.RoleRole_activities_4001));
		entry.setId("createRoleActivities8CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj16/Edge.gif")); //$NON-NLS-1$
		entry.setLargeIcon(SRMDiagramEditorPlugin.findImageDescriptor("/SRMdesign.edit/icons/full/obj32/Edge.gif")); //$NON-NLS-1$
		return entry;
	}

}
