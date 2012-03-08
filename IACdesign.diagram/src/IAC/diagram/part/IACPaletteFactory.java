package IAC.diagram.part;

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

import IAC.diagram.providers.IACElementTypes;

/**
 * @generated
 */
public class IACPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createIAC1Group());
	}

	/**
	 * Creates "IAC" palette tool group
	 * @generated
	 */
	private PaletteContainer createIAC1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.IAC1Group_title);
		paletteContainer.setId("createIAC1Group"); //$NON-NLS-1$
		paletteContainer.add(createNode1CreationTool());
		paletteContainer.add(createNodeVariables2CreationTool());
		paletteContainer.add(createVariable3CreationTool());
		paletteContainer.add(createTransition4CreationTool());
		paletteContainer.add(createNodeFather_of5CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNode1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Node1CreationTool_title,
				Messages.Node1CreationTool_desc,
				Collections.singletonList(IACElementTypes.Node_2006));
		entry.setId("createNode1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(IACElementTypes
				.getImageDescriptor(IACElementTypes.Node_2006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNodeVariables2CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.NodeVariables2CreationTool_title,
				Messages.NodeVariables2CreationTool_desc,
				Collections.singletonList(IACElementTypes.NodeVariables_4006));
		entry.setId("createNodeVariables2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(IACElementTypes
				.getImageDescriptor(IACElementTypes.NodeVariables_4006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createVariable3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Variable3CreationTool_title,
				Messages.Variable3CreationTool_desc,
				Collections.singletonList(IACElementTypes.Variable_2005));
		entry.setId("createVariable3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(IACElementTypes
				.getImageDescriptor(IACElementTypes.Variable_2005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTransition4CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Transition4CreationTool_title,
				Messages.Transition4CreationTool_desc,
				Collections.singletonList(IACElementTypes.Transition_4007));
		entry.setId("createTransition4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(IACElementTypes
				.getImageDescriptor(IACElementTypes.Transition_4007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNodeFather_of5CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.NodeFather_of5CreationTool_title,
				Messages.NodeFather_of5CreationTool_desc,
				Collections.singletonList(IACElementTypes.NodeFather_of_4008));
		entry.setId("createNodeFather_of5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(IACElementTypes
				.getImageDescriptor(IACElementTypes.NodeFather_of_4008));
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
