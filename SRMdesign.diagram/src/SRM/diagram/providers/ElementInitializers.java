package SRM.diagram.providers;

import SRM.diagram.part.SRMDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	* @generated
	*/
	public static ElementInitializers getInstance() {
		ElementInitializers cached = SRMDiagramEditorPlugin.getInstance().getElementInitializers();
		if (cached == null) {
			SRMDiagramEditorPlugin.getInstance().setElementInitializers(cached = new ElementInitializers());
		}
		return cached;
	}
}
