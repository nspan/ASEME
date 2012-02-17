package AIP.diagram.providers;

import AIP.diagram.part.AIPDiagramEditorPlugin;

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
		ElementInitializers cached = AIPDiagramEditorPlugin.getInstance()
				.getElementInitializers();
		if (cached == null) {
			AIPDiagramEditorPlugin.getInstance().setElementInitializers(
					cached = new ElementInitializers());
		}
		return cached;
	}
}
