package AIP.diagram.part;

import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import AIP.diagram.providers.AIPElementTypes;

/**
 * @generated
 */
public class AIPPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createAIP1Group());
	}

	/**
	 * Creates "AIP" palette tool group
	 * @generated
	 */
	private PaletteContainer createAIP1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.AIP1Group_title);
		paletteContainer.setId("createAIP1Group"); //$NON-NLS-1$
		paletteContainer.add(createProtocol1CreationTool());
		paletteContainer.add(createProtocolParticipants2CreationTool());
		paletteContainer.add(createParticipant3CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProtocol1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Protocol1CreationTool_title,
				Messages.Protocol1CreationTool_desc,
				Collections.singletonList(AIPElementTypes.Protocol_2004));
		entry.setId("createProtocol1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(AIPElementTypes
				.getImageDescriptor(AIPElementTypes.Protocol_2004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProtocolParticipants2CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.ProtocolParticipants2CreationTool_title,
				Messages.ProtocolParticipants2CreationTool_desc,
				Collections
						.singletonList(AIPElementTypes.ProtocolParticipants_4002));
		entry.setId("createProtocolParticipants2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(AIPElementTypes
				.getImageDescriptor(AIPElementTypes.ProtocolParticipants_4002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createParticipant3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Participant3CreationTool_title,
				Messages.Participant3CreationTool_desc,
				Collections.singletonList(AIPElementTypes.Participant_2003));
		entry.setId("createParticipant3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(AIPElementTypes
				.getImageDescriptor(AIPElementTypes.Participant_2003));
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
		private final List<IElementType> elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List<IElementType> elementTypes) {
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
		private final List<IElementType> relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List<IElementType> relationshipTypes) {
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
