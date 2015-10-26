package SUC.diagram.part;

import java.util.Collections;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultLinkToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;

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
		paletteContainer.add(createRoleParticipates_in2CreationTool());
		paletteContainer.add(createUseCaseInclude3CreationTool());
		paletteContainer.add(createRole4CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createUseCase1CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.UseCase1CreationTool_title,
				Messages.UseCase1CreationTool_desc,
				Collections.singletonList(SUCElementTypes.UseCase_2002));
		entry.setId("createUseCase1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SUCElementTypes
				.getImageDescriptor(SUCElementTypes.UseCase_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRoleParticipates_in2CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.RoleParticipates_in2CreationTool_title,
				Messages.RoleParticipates_in2CreationTool_desc,
				Collections.singletonList(SUCElementTypes.Role_2001));
		entry.setId("createRoleParticipates_in2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SUCElementTypes
				.getImageDescriptor(SUCElementTypes.Role_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createUseCaseInclude3CreationTool() {
		ToolEntry entry = new ToolEntry(
				Messages.UseCaseInclude3CreationTool_title,
				Messages.UseCaseInclude3CreationTool_desc, null, null) {
		};
		entry.setId("createUseCaseInclude3CreationTool"); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRole4CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(
				Messages.Role4CreationTool_title,
				Messages.Role4CreationTool_desc,
				Collections
						.singletonList(SUCElementTypes.RoleParticipates_in_4001));
		entry.setId("createRole4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SUCDiagramEditorPlugin
				.findImageDescriptor("/Suc_design.edit/icons/full/obj16/Role.gif")); //$NON-NLS-1$
		entry.setLargeIcon(SUCDiagramEditorPlugin
				.findImageDescriptor("/Suc_design.edit/icons/full/obj16/Role.gif")); //$NON-NLS-1$
		return entry;
	}

}
