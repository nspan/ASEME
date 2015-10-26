package asemedashboardview.views.actions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import AIP.AIPFactory;
import AIP.AIPPackage;
import AIP.AIPmodel;
import AIP.Participant;
import AIP.Protocol;
import SAG.Actor;
import SAG.Goal;
import SAG.SAGPackage;
import SAG.SAGmodel;
import SUC.Role;
import SUC.SUCFactory;
import SUC.SUCPackage;
import SUC.SUCmodel;
import SUC.UseCase;
import aseme.transformations.AsemeModelSaveHelper;
import aseme.transformations.SUC2AIP;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.dialogs.MessageDialog;

public class TransformSUC2AIPModelAction implements ASEMEAction {

	private ASEMEFacade context;

	@Override
	public void init(ASEMEFacade context) {
		this.context = context;
	}

	@Override
	public boolean isEnabled() {
		ASEMEState state = context.getState();
		if(state.getSUC()==null) {
			return false;
		}
		return true;
	}

	@Override
	public void run() {
		ASEMEState state = context.getState(); 
		URI suc = state.getSUC();
		URI aip = state.getAIP();
		if (aip == null) {
			aip = suc.trimFileExtension().appendFileExtension("aip"); //$NON-NLS-1$
			//state.setAIP(aip);
		}
		//aip = state.getAIP();
		ResourceSet resourceSet = new ResourceSetImpl();

		resourceSet
		.getResourceFactoryRegistry()
		.getExtensionToFactoryMap()
		.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
				new XMIResourceFactoryImpl());

		// Register the package to ensure it is available during loading.
		//
		resourceSet.getPackageRegistry().put(SUCPackage.eNS_URI, SUCPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(AIPPackage.eNS_URI, AIPPackage.eINSTANCE);

		// load SUC model

		Resource resource = resourceSet.getResource(state.getSUC(), true);	
		SUCmodel sucModel = (SUCmodel) resource.getContents().get(0);
		
		//SUC2AIP tr = new SUC2AIP();
		
		//AsemeModelSaveHelper helper = new AsemeModelSaveHelper();
		
		URI aip1 = suc.trimFileExtension().appendFileExtension("aip");
		
		try{
			AsemeModelSaveHelper.saveURI(SUC2AIP.transformSuc2Aip(sucModel), aip1);
		}
		catch( Exception e){
			Diagnostic diag = Diagnostician.INSTANCE.validate(sucModel);
			if (diag.getCode() == 0){
				MessageDialog.openError(context.getShell(), "Error", "Error in validating SUC model" );
			}
			else{
				MessageDialog.openError(context.getShell(), "Error", "Error in SUC2AIP transformation" );
			}
		}
		

		
	}

}
