
package SAG.diagram.part;

import java.util.Collections;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultLinkToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;

import SAG.diagram.providers.SAGElementTypes;

/**
 * @generated
 */
public class SAGPaletteFactory {

	/**
	* @generated
	*/
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createSAG1Group());
	}

	/**
	* Creates "SAG" palette tool group
	* @generated
	*/
	private PaletteContainer createSAG1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(Messages.SAG1Group_title);
		paletteContainer.setId("createSAG1Group"); //$NON-NLS-1$
		paletteContainer.add(createActor1CreationTool());
		paletteContainer.add(createActorMy_goal2CreationTool());
		paletteContainer.add(createGoal3CreationTool());
		paletteContainer.add(createGoalDependee4CreationTool());
		return paletteContainer;
	}

	/**
	* @generated
	*/
	private ToolEntry createActor1CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Actor1CreationTool_title,
				Messages.Actor1CreationTool_desc, Collections.singletonList(SAGElementTypes.Actor_2001));
		entry.setId("createActor1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SAGElementTypes.getImageDescriptor(SAGElementTypes.Actor_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createActorMy_goal2CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.ActorMy_goal2CreationTool_title,
				Messages.ActorMy_goal2CreationTool_desc, Collections.singletonList(SAGElementTypes.ActorMy_goal_4001));
		entry.setId("createActorMy_goal2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SAGElementTypes.getImageDescriptor(SAGElementTypes.ActorMy_goal_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createGoal3CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Goal3CreationTool_title,
				Messages.Goal3CreationTool_desc, Collections.singletonList(SAGElementTypes.Goal_2002));
		entry.setId("createGoal3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SAGElementTypes.getImageDescriptor(SAGElementTypes.Goal_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createGoalDependee4CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.GoalDependee4CreationTool_title,
				Messages.GoalDependee4CreationTool_desc, Collections.singletonList(SAGElementTypes.GoalDependee_4002));
		entry.setId("createGoalDependee4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SAGElementTypes.getImageDescriptor(SAGElementTypes.GoalDependee_4002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

}
