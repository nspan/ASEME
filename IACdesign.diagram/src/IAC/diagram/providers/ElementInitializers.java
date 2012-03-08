package IAC.diagram.providers;

import IAC.diagram.part.IACDiagramEditorPlugin;

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
		ElementInitializers cached = IACDiagramEditorPlugin.getInstance()
				.getElementInitializers();
		if (cached == null) {
			IACDiagramEditorPlugin.getInstance().setElementInitializers(
					cached = new ElementInitializers());
		}
		return cached;
	}
}
