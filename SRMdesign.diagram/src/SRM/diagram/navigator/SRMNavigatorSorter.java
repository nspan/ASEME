package SRM.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import SRM.diagram.part.SRMVisualIDRegistry;

/**
 * @generated
 */
public class SRMNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 4008;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof SRMNavigatorItem) {
			SRMNavigatorItem item = (SRMNavigatorItem) element;
			return SRMVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
