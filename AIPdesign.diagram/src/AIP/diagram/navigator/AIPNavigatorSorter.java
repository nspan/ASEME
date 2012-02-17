package AIP.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import AIP.diagram.part.AIPVisualIDRegistry;

/**
 * @generated
 */
public class AIPNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 4004;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof AIPNavigatorItem) {
			AIPNavigatorItem item = (AIPNavigatorItem) element;
			return AIPVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
