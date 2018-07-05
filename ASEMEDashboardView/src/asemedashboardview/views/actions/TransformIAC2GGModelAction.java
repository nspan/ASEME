package asemedashboardview.views.actions;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;

import aseme.transformations.AsemeModelSaveHelper;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;
import statechart.Model;
import statechart2naoth.Stct2Naoth;

public class TransformIAC2GGModelAction implements ASEMEAction {

	private ASEMEFacade context;

	@Override
	public void init(ASEMEFacade context) {
		this.context = context;
	}

	@Override
	public boolean isEnabled() {
		ASEMEState state = context.getState();

		if (state.getIAC() == null) {
			return false;
		}
		return true;
	}

	@Override
	public void run() {

		ASEMEState state = context.getState();

		// URI iac = state.getIAC();

		// ResourceSet resourceSet = new ResourceSetImpl();
		// // Register the appropriate resource factory to handle all file
		// // extensions.
		// //
		//
		// resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
		// .put(Resource.Factory.Registry.DEFAULT_EXTENSION, new
		// XMIResourceFactoryImpl());
		//
		// // Register the package to ensure it is available during loading.
		// //
		// resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI,
		// StatechartPackage.eINSTANCE);

		Resource resource = AsemeModelSaveHelper.staticResourceSet.getResource(state.getIAC(), true);
		Model statechart = (Model) resource.getContents().get(0);

		String src = state.getProject().getLocation() + "/" + "src-gen/" + "NAOTH/" + statechart.getName() + "/";

		Stct2Naoth.genarateNaoth(src, statechart);

		try {
			state.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
