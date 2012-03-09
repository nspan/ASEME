/*
 * Kouretes Statechart Editor-KSE developed 
 * by Angeliki Topalidou-Kyniazopoulou
 * for Kouretes Team. Code developed by Nikolaos Spanoudakis, Alex Parashos,
 * ibm, apache and eclipse is used.
 */
package statechart.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import statechart.diagram.part.StateChartVisualIDRegistry;

/**
 * @generated
 */
public class StateChartNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 7006;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof StateChartNavigatorItem) {
			StateChartNavigatorItem item = (StateChartNavigatorItem) element;
			return StateChartVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
