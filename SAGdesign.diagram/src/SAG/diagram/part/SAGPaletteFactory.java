package SAG.diagram.part;

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
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.SAG1Group_title);
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
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Actor1CreationTool_title,
				Messages.Actor1CreationTool_desc,
				Collections.singletonList(SAGElementTypes.Actor_2007));
		entry.setId("createActor1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SAGElementTypes
				.getImageDescriptor(SAGElementTypes.Actor_2007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createActorMy_goal2CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.ActorMy_goal2CreationTool_title,
				Messages.ActorMy_goal2CreationTool_desc,
				Collections.singletonList(SAGElementTypes.ActorMy_goal_4011));
		entry.setId("createActorMy_goal2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SAGElementTypes
				.getImageDescriptor(SAGElementTypes.ActorMy_goal_4011));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createGoal3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Goal3CreationTool_title,
				Messages.Goal3CreationTool_desc,
				Collections.singletonList(SAGElementTypes.Goal_2008));
		entry.setId("createGoal3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SAGElementTypes
				.getImageDescriptor(SAGElementTypes.Goal_2008));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createGoalDependee4CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.GoalDependee4CreationTool_title,
				Messages.GoalDependee4CreationTool_desc,
				Collections.singletonList(SAGElementTypes.GoalDependee_4013));
		entry.setId("createGoalDependee4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(SAGElementTypes
				.getImageDescriptor(SAGElementTypes.GoalDependee_4013));
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
