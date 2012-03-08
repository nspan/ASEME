package asemedashboardview.views;

import org.eclipse.swt.widgets.Shell;

public interface ASEMEFacade {

	public static final String LOCATION_SAG = "SAG"; //$NON-NLS-1$

	public static final String LOCATION_SUC = "SUC"; //$NON-NLS-1$
	
	public static final String LOCATION_AIP = "AIP"; //$NON-NLS-1$
	
	public static final String LOCATION_SRM = "SRM"; //$NON-NLS-1$

	public static final String LOCATION_EAC = "EAC"; //$NON-NLS-1$

	public static final String LOCATION_IAC = "IAC"; //$NON-NLS-1$

	public static final String LOCATION_JADE = "JADE"; //$NON-NLS-1$

//	public static final String LOCATION_MM = "map_model"; //$NON-NLS-1$
//
//	public static final String LOCATION_GM = "genmodel"; //$NON-NLS-1$
//
//	public static final String LOCATION_MM2GM = "map_model-genmodel"; //$NON-NLS-1$

//	public static final String OPTION_RCP = "rcp"; //$NON-NLS-1$

	public ASEMEState getState();

	public void updateStatus();

	public boolean isStrict();

	public Shell getShell();
}
