package statechart.diagram.part;
import javax.swing.JOptionPane;

import org.eclipse.emf.common.ui.action.WorkbenchWindowActionDelegate;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * 
 */

/**
 * @author angelica
 *
 */
public class GenerateAction extends WorkbenchWindowActionDelegate implements
		IWorkbenchWindowActionDelegate {

	/**
	 * 
	 */
	public GenerateAction() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(null, "ACTION OK");
	}

}
