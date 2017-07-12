package SAG.diagram.part;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.common.ui.URIEditorInput;

/**
 * @generated
 */
public class SAGUriEditorInputTester extends PropertyTester {

	/**
	* @generated
	*/
	public boolean test(Object receiver, String method, Object[] args, Object expectedValue) {
		if (false == receiver instanceof URIEditorInput) {
			return false;
		}
		URIEditorInput editorInput = (URIEditorInput) receiver;
		return "sagd".equals(editorInput.getURI().fileExtension()); //$NON-NLS-1$
	}

}
