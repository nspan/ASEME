package SAG.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import SAG.diagram.part.SAGVisualIDRegistry;

/**
 * @generated
 */
public class SAGNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 4015;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof SAGNavigatorItem) {
			SAGNavigatorItem item = (SAGNavigatorItem) element;
			return SAGVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
