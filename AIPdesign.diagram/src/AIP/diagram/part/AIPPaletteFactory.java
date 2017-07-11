
package AIP.diagram.part;

import java.util.Collections;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;

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
		PaletteGroup paletteContainer = new PaletteGroup(Messages.AIP1Group_title);
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
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Protocol1CreationTool_title,
				Messages.Protocol1CreationTool_desc, Collections.singletonList(AIPElementTypes.Protocol_2001));
		entry.setId("createProtocol1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(AIPElementTypes.getImageDescriptor(AIPElementTypes.Protocol_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createProtocolParticipants2CreationTool() {
		ToolEntry entry = new ToolEntry(Messages.ProtocolParticipants2CreationTool_title,
				Messages.ProtocolParticipants2CreationTool_desc, null, null) {
		};
		entry.setId("createProtocolParticipants2CreationTool"); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createParticipant3CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Participant3CreationTool_title,
				Messages.Participant3CreationTool_desc, Collections.singletonList(AIPElementTypes.Participant_2002));
		entry.setId("createParticipant3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(AIPElementTypes.getImageDescriptor(AIPElementTypes.Participant_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

}
