package asemedashboardview.views.actions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.dialogs.MessageDialog;

import AIP.AIPmodel;
import AIP.Participant;
import SRM.Activity;
import SRM.Capability;
import SRM.Role;
import SRM.SRMFactory;
import SRM.SRMPackage;
import SRM.SRMmodel;
import SUC.SUCFactory;
import SUC.SUCPackage;
import SUC.SUCmodel;
import SUC.UseCase;
import aseme.transformations.AsemeModelSaveHelper;
import aseme.transformations.SUC2AIP;
import aseme.transformations.SUC2SRM;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;

public class TransformSUC2SRMModelAction implements ASEMEAction {

	private ASEMEFacade context;

	@Override
	public void init(ASEMEFacade context) {
		this.context = context;
	}

	@Override
	public boolean isEnabled() {
		ASEMEState state = context.getState();
		//TODO : Verify if AIP also needs to be enabled
		if(state.getSUC()==null ) {
			return false;
		}
		return true;
	}

	@Override
	public void run() {
		ASEMEState state = context.getState(); 
		URI suc = state.getSUC();
		URI srm = state.getSRM();
		URI aip = state.getAIP();
		
		boolean nosrm = false, importaip = false ;
		
		
		if (srm == null) {
			srm = suc.trimFileExtension().appendFileExtension("srm"); //$NON-NLS-1$
			//state.setSRM(srm);
			nosrm = true;
		}
		
		
		
		//if(aip!=null) {
		//importaip = true;
		//}
		
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet
		.getResourceFactoryRegistry()
		.getExtensionToFactoryMap()
		.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
				new XMIResourceFactoryImpl());

		resourceSet.getPackageRegistry().put(SUCPackage.eNS_URI, SUCPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(SRMPackage.eNS_URI, SRMPackage.eINSTANCE);

		Resource resource = resourceSet.getResource(suc, true);
		SUCmodel sucModel = (SUCmodel) resource.getContents().get(0);
		
		//Resource newResource = resourceSet.createResource(state.getSRM());
		SRMmodel srmModel = SRMFactory.eINSTANCE.createSRMmodel();
		//newResource.getContents().add(srmModel);
		
		//SUC2SRM tr = new SUC2SRM();
		
		//if (nosrm){
		
		try{
			srmModel = SUC2SRM.transformSuc2srm(sucModel, srmModel);
		}
		catch( Exception e){
			//Diagnostic diag = Diagnostician.INSTANCE.validate(sucModel);
			//if (diag.getCode() == 0){
				MessageDialog.openError(context.getShell(), "Error", "Error in validating SUC model" );
			}
			//else{
			//	MessageDialog.openError(context.getShell(), "Error", "Error in SUC2AIP transformation" );
			//}
		//}
		
		
		//}
			
		String[] teste = suc.trimFileExtension().toString().split("/");
		//String filename= "/" + state.getProject().getName() + "/" + statechart.getName()+".stct";
		IFile temp = state.getProject().getFile( teste[teste.length-1] + ".aip");
		
		if (temp.exists()){
			Resource aipResource = resourceSet.getResource(URI.createPlatformResourceURI(temp.getFullPath().toString(), true), true);
			AIPmodel aipModel = (AIPmodel) aipResource.getContents().get(0);
			
			try{
				srmModel = SUC2SRM.srmImportAip(srmModel, aipModel);
			}
			catch( Exception e){
				MessageDialog.openError(context.getShell(), "Error", "Error importing AIP to SRM model" );
			}
						
		}
		
		
		URI test = suc.trimFileExtension().appendFileExtension("srm"); 
		
		
		
		AsemeModelSaveHelper.saveURI(srmModel, test);
	}
}
