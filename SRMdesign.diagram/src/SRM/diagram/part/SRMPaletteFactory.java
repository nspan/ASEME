package SRM.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

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
	 * @generated
	 */
	private PaletteContainer createSRM1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.SRM1Group_title);
		paletteContainer.setId("createSRM1Group"); //$NON-NLS-1$
		paletteContainer.add(createRole1CreationTool());
		paletteContainer.add(createRoleActivities2CreationTool());
		paletteContainer.add(createRoleCapabilities3CreationTool());
		paletteContainer.add(createActivity4CreationTool());
		paletteContainer.add(createCapability5CreationTool());
		paletteContainer.add(createCapabilityActivities6CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRole1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(SRMElementTypes.Role_2005);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Role1CreationTool_title,
				Messages.Role1CreationTool_desc, types);
		entry.setId("createRole1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SRMElementTypes
				.getImageDescriptor(SRMElementTypes.Role_2005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRoleActivities2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(SRMElementTypes.RoleActivities_4005);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.RoleActivities2CreationTool_title,
				Messages.RoleActivities2CreationTool_desc, types);
		entry.setId("createRoleActivities2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SRMElementTypes
				.getImageDescriptor(SRMElementTypes.RoleActivities_4005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRoleCapabilities3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(SRMElementTypes.RoleCapabilities_4004);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.RoleCapabilities3CreationTool_title,
				Messages.RoleCapabilities3CreationTool_desc, types);
		entry.setId("createRoleCapabilities3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SRMElementTypes
				.getImageDescriptor(SRMElementTypes.RoleCapabilities_4004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createActivity4CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(SRMElementTypes.Activity_2006);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Activity4CreationTool_title,
				Messages.Activity4CreationTool_desc, types);
		entry.setId("createActivity4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SRMElementTypes
				.getImageDescriptor(SRMElementTypes.Activity_2006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCapability5CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(SRMElementTypes.Capability_2004);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Capability5CreationTool_title,
				Messages.Capability5CreationTool_desc, types);
		entry.setId("createCapability5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SRMElementTypes
				.getImageDescriptor(SRMElementTypes.Capability_2004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCapabilityActivities6CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(SRMElementTypes.CapabilityActivities_4006);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.CapabilityActivities6CreationTool_title,
				Messages.CapabilityActivities6CreationTool_desc, types);
		entry.setId("createCapabilityActivities6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SRMElementTypes
				.getImageDescriptor(SRMElementTypes.CapabilityActivities_4006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
