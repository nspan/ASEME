package asemedashboardview.views.actions;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import statechart.Model;
import statechart.StatechartPackage;
import statechart2jade.Stct2Jade;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;



public class TransformIAC2JADEModelAction implements ASEMEAction {

	private ASEMEFacade context;
	
	@Override
	public void init(ASEMEFacade context) {
		// TODO Auto-generated method stub
		this.context = context;

	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		ASEMEState state = context.getState();
		
		if(state.getIAC()==null) {
			return false;
		}
		return true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		ASEMEState state = context.getState();
		
		//URI iac = state.getIAC();
		
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
		resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI, StatechartPackage.eINSTANCE);
		
		Resource resource = resourceSet.getResource(state.getIAC(), true);	
		Model statechart = (Model) resource.getContents().get(0);
		
		
		//Stct2Jade tr = new Stct2Jade();
		String src = state.getProject().getLocation() + "/" + "src-gen/"+ "JADE/"+statechart.getName() + "/";
		
		Stct2Jade.genarateJade(src, statechart);
		
		try {
			state.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
