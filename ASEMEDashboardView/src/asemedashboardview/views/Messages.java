package asemedashboardview.views;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "asemedashboardview.views.messages"; //$NON-NLS-1$
	
	public static String ASEMEFigure_SAG;
	
	public static String ASEMEFigure_SUC;

	public static String ASEMEFigure_AIP;
	
	public static String ASEMEFigure_SRM;
	
	public static String ASEMEFigure_EAC;
	
	public static String ASEMEFigure_IAC;
	
	public static String ASEMEFigure_JADE;
	
	public static String ASEMEMediator_Select;

	public static String ASEMEMediator_Edit;

	public static String ASEMEMediator_Project;

	public static String ASEMEMediator_Progress;

	public static String ASEMEMediator_FailToOpen;

	public static String ASEMEMediator_Reload;

	public static String ASEMEMediator_Create;

	public static String ASEMEMediator_Derive;

	public static String ASEMEMediator_Combine;

	public static String ASEMEMediator_SelectProject;

	public static String ASEMEMediator_Transform;

	public static String ASEMEMediator_RCP;

	public static String ASEMEPart_Synchronize;

	public static String ASEMEPart_SynchronizeSelection;

	public static String ModelFigure_NoName;


	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
