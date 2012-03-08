package IAC.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import IAC.diagram.part.IACVisualIDRegistry;

/**
 * @generated
 */
public class IACNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 4010;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof IACNavigatorItem) {
			IACNavigatorItem item = (IACNavigatorItem) element;
			return IACVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
