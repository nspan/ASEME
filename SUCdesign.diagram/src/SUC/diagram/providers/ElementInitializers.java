package SUC.diagram.providers;

import SUC.diagram.part.SUCDiagramEditorPlugin;

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
		ElementInitializers cached = SUCDiagramEditorPlugin.getInstance().getElementInitializers();
		if (cached == null) {
			SUCDiagramEditorPlugin.getInstance().setElementInitializers(cached = new ElementInitializers());
		}
		return cached;
	}
}
