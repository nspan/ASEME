package statechart.diagram.actionbar;


import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.GroupMarker;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * @generated NOT
 */
public class DiagramEditorActionBarAdvisor extends ActionBarAdvisor {

	/**
	 * @generated NOT
	 */
	private ActionFactory.IWorkbenchAction lockToolBarAction;

	/**
	 * @generated NOT
	 */
	private ActionFactory.IWorkbenchAction toggleCoolbarAction;

	/**
	 * @generated NOT
	 */
	public DiagramEditorActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	/**
	 * @generated NOT
	 */
	private IWorkbenchWindow getWindow() {
		return getActionBarConfigurer().getWindowConfigurer().getWindow();
	}

	/**
	 * @generated NOT
	 */
	protected void makeActions(IWorkbenchWindow window) {
		toggleCoolbarAction = ActionFactory.TOGGLE_COOLBAR.create(window);
		register(toggleCoolbarAction);
		lockToolBarAction = ActionFactory.LOCK_TOOL_BAR.create(window);
		register(lockToolBarAction);

		
	}

	/**
	 * @generated NOT
	 */
	protected void fillMenuBar(IMenuManager menu) {

			IMenuManager menuX = new MenuManager( "ASEME", "ASEME");
			menuX.add(new GroupMarker("ASEME"));
			menu.add(menuX);

	}

	/**
	 * @generated
	 */
	protected void fillCoolBar(ICoolBarManager toolBar) {
		IMenuManager popUpMenu = new MenuManager();
		popUpMenu.add(new ActionContributionItem(lockToolBarAction));
		popUpMenu.add(new ActionContributionItem(toggleCoolbarAction));
		toolBar.setContextMenuManager(popUpMenu);

		toolBar.add(new GroupMarker("group.ASEME"));
		
		IToolBarManager toolBarX = new ToolBarManager();

				
	}

}
