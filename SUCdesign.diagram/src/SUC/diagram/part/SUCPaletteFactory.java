package SUC.diagram.part;

import java.util.ArrayList;
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

import SUC.diagram.providers.SUCElementTypes;

/**
 * @generated
 */
public class SUCPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createSUC1Group());
	}

	/**
	 * Creates "SUC" palette tool group
	 * @generated
	 */
	private PaletteContainer createSUC1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.SUC1Group_title);
		paletteContainer.setId("createSUC1Group"); //$NON-NLS-1$
		paletteContainer.add(createUseCase1CreationTool());
		paletteContainer.add(createUseCaseInclude2CreationTool());
		paletteContainer.add(createRole3CreationTool());
		paletteContainer.add(createRoleParticipates_in4CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createUseCase1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.UseCase1CreationTool_title,
				Messages.UseCase1CreationTool_desc,
				Collections.singletonList(SUCElementTypes.UseCase_2009));
		entry.setId("createUseCase1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SUCElementTypes
				.getImageDescriptor(SUCElementTypes.UseCase_2009));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createUseCaseInclude2CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.UseCaseInclude2CreationTool_title,
				Messages.UseCaseInclude2CreationTool_desc,
				Collections.singletonList(SUCElementTypes.UseCaseInclude_4009));
		entry.setId("createUseCaseInclude2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SUCElementTypes
				.getImageDescriptor(SUCElementTypes.UseCaseInclude_4009));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRole3CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(3);
		types.add(SUCElementTypes.SystemRole_2007);
		types.add(SUCElementTypes.HumanRole_2008);
		types.add(SUCElementTypes.Role_2010);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Role3CreationTool_title,
				Messages.Role3CreationTool_desc, types);
		entry.setId("createRole3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SUCDiagramEditorPlugin
				.findImageDescriptor("/Suc_design.edit/icons/full/obj16/Role.gif")); //$NON-NLS-1$
		entry.setLargeIcon(SUCDiagramEditorPlugin
				.findImageDescriptor("/Suc_design.edit/icons/full/obj16/Role.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRoleParticipates_in4CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.RoleParticipates_in4CreationTool_title,
				Messages.RoleParticipates_in4CreationTool_desc,
				Collections
						.singletonList(SUCElementTypes.RoleParticipates_in_4008));
		entry.setId("createRoleParticipates_in4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SUCElementTypes
				.getImageDescriptor(SUCElementTypes.RoleParticipates_in_4008));
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
