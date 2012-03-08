package SAG.diagram.providers;

import SAG.diagram.part.SAGDiagramEditorPlugin;

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
		ElementInitializers cached = SAGDiagramEditorPlugin.getInstance()
				.getElementInitializers();
		if (cached == null) {
			SAGDiagramEditorPlugin.getInstance().setElementInitializers(
					cached = new ElementInitializers());
		}
		return cached;
	}
}
