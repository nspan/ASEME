package asemedashboardview.views;

/**
 * 
 *  The ASEMEAction interface allows other plugins to contribute actions to the dashboard.
 * 
 * @author efloros
 *
 */

public interface ASEMEAction {

	/**
	 * Initializing the Action, providing the existing context needed
	 * 
	 * @param context
	 */
	public void init(ASEMEFacade context);
	
	/**
	 * Checking if the needed conditions are met for the action
	 * @return
	 */
	public boolean isEnabled();
	
	/**
	 * The main part of the action that contains the functionality
	 */
	public void run();
}
