package asemedashboardview.views.actions;


import SAG.SAGmodel;
import SUC.SUCPackage;
import aseme.transformations.AsemeModelSaveHelper;
import aseme.transformations.SAG2SUC;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;

import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;

public class TransformSAG2SUCModelAction implements ASEMEAction {

	private ASEMEFacade context;

	@Override
	public void init(ASEMEFacade context) {
		this.context = context;
	}

	@Override
	public boolean isEnabled() {
		ASEMEState state = context.getState();
		if(state.getSAG()==null) {
			return false;
		}
		return true;
	}

	@Override
	public void run() {
		ASEMEState state = context.getState(); 
		URI sag = state.getSAG();
		URI suc = state.getSUC();
		if (suc == null) {
			suc = sag.trimFileExtension().appendFileExtension("suc"); //$NON-NLS-1$
			//state.setSUC(suc);
		}
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register the appropriate resource factory to handle all file
		// extensions.
		//
		
		
		resourceSet
				.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());
		
		// Register the package to ensure it is available during loading.
		//
		resourceSet.getPackageRegistry().put(SUCPackage.eNS_URI, SUCPackage.eINSTANCE);
		
		// load SUC model

		Resource resource = resourceSet.getResource(state.getSAG(), true);	
		SAGmodel sagModel = (SAGmodel) resource.getContents().get(0);

		
		URI test = sag.trimFileExtension().appendFileExtension("suc"); 
		
		//String[] temp = state.getSAG().toString().split("/");
		
		//String name = temp[temp.length -1];
		
		//String filename="/"+state.getProject().getName() + "/" + name + ".suc";
		
		try{
		AsemeModelSaveHelper.saveURI(SAG2SUC.transformSag2Suc(sagModel), test);	
		}
		catch( NullPointerException e){
			//Diagnostician.INSTANCE.validate(sagModel).getMessage();
			//MultiStatus status = createMultiStatus(e.getLocalizedMessage(), e);
			//ErrorDialog.openError(context.getShell(), "SAG2SUC Error", Diagnostician.INSTANCE.validate(sagModel).getMessage(), null);
			Diagnostic diag = Diagnostician.INSTANCE.validate(sagModel);
			if (diag.getCode() == 0){
				MessageDialog.openError(context.getShell(), "Error", "Error in validating SAG model" );
			}
			else{
				MessageDialog.openError(context.getShell(), "Error", "Error in SAG2SUC transformation" );
			}
		}
	}

}


