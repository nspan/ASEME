
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
	* @generated NOT
	*/
	private PaletteContainer createSUC1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(Messages.SUC1Group_title);
		paletteContainer.setId("createSUC1Group"); //$NON-NLS-1$
		paletteContainer.add(createRole4CreationTool());
		paletteContainer.add(createUseCase1CreationTool());
		paletteContainer.add(createRoleParticipates_in2CreationTool());
		paletteContainer.add(createUseCaseInclude3CreationTool());

		return paletteContainer;
	}

	/**
	* @generated
	*/
	private ToolEntry createUseCase1CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.UseCase1CreationTool_title,
				Messages.UseCase1CreationTool_desc, Collections.singletonList(SUCElementTypes.UseCase_2002));
		entry.setId("createUseCase1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SUCDiagramEditorPlugin.findImageDescriptor("/SUCdesign.edit/icons/full/obj16/UseCase.gif")); //$NON-NLS-1$
		entry.setLargeIcon(SUCDiagramEditorPlugin.findImageDescriptor("/SUCdesign.edit/icons/full/obj32/UseCase.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createRoleParticipates_in2CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.RoleParticipates_in2CreationTool_title,
				Messages.RoleParticipates_in2CreationTool_desc,
				Collections.singletonList(SUCElementTypes.RoleParticipates_in_4002));
		entry.setId("createRoleParticipates_in2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SUCDiagramEditorPlugin.findImageDescriptor("/SUCdesign.edit/icons/full/obj16/Edge.gif")); //$NON-NLS-1$
		entry.setLargeIcon(SUCDiagramEditorPlugin.findImageDescriptor("/SUCdesign.edit/icons/full/obj32/Edge.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createUseCaseInclude3CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.UseCaseInclude3CreationTool_title,
				Messages.UseCaseInclude3CreationTool_desc,
				Collections.singletonList(SUCElementTypes.UseCaseInclude_4001));
		entry.setId("createUseCaseInclude3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SUCDiagramEditorPlugin.findImageDescriptor("/SUCdesign.edit/icons/full/obj16/Edge.gif")); //$NON-NLS-1$
		entry.setLargeIcon(SUCDiagramEditorPlugin.findImageDescriptor("/SUCdesign.edit/icons/full/obj32/Edge.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createRole4CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Role4CreationTool_title,
				Messages.Role4CreationTool_desc, Collections.singletonList(SUCElementTypes.Role_2001));
		entry.setId("createRole4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SUCDiagramEditorPlugin.findImageDescriptor("/SUCdesign.edit/icons/full/obj16/Role.gif")); //$NON-NLS-1$
		entry.setLargeIcon(SUCDiagramEditorPlugin.findImageDescriptor("/SUCdesign.edit/icons/full/obj32/Role.gif")); //$NON-NLS-1$
		return entry;
	}

}
