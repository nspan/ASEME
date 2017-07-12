package SUC.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import SUC.diagram.part.SUCVisualIDRegistry;

/**
 * @generated
 */
public class SUCNavigatorSorter extends ViewerSorter {

	/**
	* @generated
	*/
	private static final int GROUP_CATEGORY = 4004;

	/**
	* @generated
	*/
	public int category(Object element) {
		if (element instanceof SUCNavigatorItem) {
			SUCNavigatorItem item = (SUCNavigatorItem) element;
			return SUCVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
