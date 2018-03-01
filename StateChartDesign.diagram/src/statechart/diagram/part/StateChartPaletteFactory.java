
package statechart.diagram.part;

import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.runtime.part.DefaultLinkToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;

import statechart.diagram.providers.StateChartElementTypes;

/**
 * @generated
 */
public class StateChartPaletteFactory {

	/**
	* @generated
	*/
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createStatechart1Group());
	}

	/**
	* Creates "statechart" palette tool group
	* @generated
	*/
	private PaletteContainer createStatechart1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(Messages.Statechart1Group_title);
		paletteContainer.setId("createStatechart1Group"); //$NON-NLS-1$
		paletteContainer.add(createNodeOR1CreationTool());
		paletteContainer.add(createNodeAND2CreationTool());
		paletteContainer.add(createNodeBASIC3CreationTool());
		paletteContainer.add(createNodeCONDITION4CreationTool());
		paletteContainer.add(createNodeSTART5CreationTool());
		paletteContainer.add(createNodeEND6CreationTool());
		paletteContainer.add(createTransition7CreationTool());
		paletteContainer.add(createVariable8CreationTool());
		paletteContainer.add(createNodeHISTORY9CreationTool());
		paletteContainer.add(createNodeSHALLOWHISTORY10CreationTool());
		return paletteContainer;
	}

	/**
	* @generated
	*/
	private ToolEntry createNodeOR1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(StateChartElementTypes.Node_2001);
		types.add(StateChartElementTypes.Node_3003);
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.NodeOR1CreationTool_title,
				Messages.NodeOR1CreationTool_desc, types);
		entry.setId("createNodeOR1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StateChartElementTypes.getImageDescriptor(StateChartElementTypes.Node_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createNodeAND2CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(StateChartElementTypes.Node_3005);
		types.add(StateChartElementTypes.Node_2002);
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.NodeAND2CreationTool_title,
				Messages.NodeAND2CreationTool_desc, types);
		entry.setId("createNodeAND2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StateChartElementTypes.getImageDescriptor(StateChartElementTypes.Node_3005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createNodeBASIC3CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(StateChartElementTypes.Node_3002);
		types.add(StateChartElementTypes.Node_2006);
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.NodeBASIC3CreationTool_title,
				Messages.NodeBASIC3CreationTool_desc, types);
		entry.setId("createNodeBASIC3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StateChartElementTypes.getImageDescriptor(StateChartElementTypes.Node_3002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createNodeCONDITION4CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(StateChartElementTypes.Node_3001);
		types.add(StateChartElementTypes.Node_2007);
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.NodeCONDITION4CreationTool_title,
				Messages.NodeCONDITION4CreationTool_desc, types);
		entry.setId("createNodeCONDITION4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StateChartElementTypes.getImageDescriptor(StateChartElementTypes.Node_3001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createNodeSTART5CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(StateChartElementTypes.Node_3004);
		types.add(StateChartElementTypes.Node_2004);
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.NodeSTART5CreationTool_title,
				Messages.NodeSTART5CreationTool_desc, types);
		entry.setId("createNodeSTART5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StateChartElementTypes.getImageDescriptor(StateChartElementTypes.Node_3004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createNodeEND6CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(StateChartElementTypes.Node_3006);
		types.add(StateChartElementTypes.Node_2005);
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.NodeEND6CreationTool_title,
				Messages.NodeEND6CreationTool_desc, types);
		entry.setId("createNodeEND6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StateChartElementTypes.getImageDescriptor(StateChartElementTypes.Node_3006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createTransition7CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(Messages.Transition7CreationTool_title,
				Messages.Transition7CreationTool_desc,
				Collections.singletonList(StateChartElementTypes.Transition_4001));
		entry.setId("createTransition7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StateChartElementTypes.getImageDescriptor(StateChartElementTypes.Transition_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createVariable8CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.Variable8CreationTool_title,
				Messages.Variable8CreationTool_desc, Collections.singletonList(StateChartElementTypes.Variable_2003));
		entry.setId("createVariable8CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StateChartElementTypes.getImageDescriptor(StateChartElementTypes.Variable_2003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createNodeHISTORY9CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.NodeHISTORY9CreationTool_title,
				Messages.NodeHISTORY9CreationTool_desc, Collections.singletonList(StateChartElementTypes.Node_2008));
		entry.setId("createNodeHISTORY9CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StateChartElementTypes.getImageDescriptor(StateChartElementTypes.Node_2008));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createNodeSHALLOWHISTORY10CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(Messages.NodeSHALLOWHISTORY10CreationTool_title,
				Messages.NodeSHALLOWHISTORY10CreationTool_desc,
				Collections.singletonList(StateChartElementTypes.Node_2009));
		entry.setId("createNodeSHALLOWHISTORY10CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StateChartElementTypes.getImageDescriptor(StateChartElementTypes.Node_2009));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

}
