package asemedashboardview.views.actions;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import SAG.Actor;
import SAG.Goal;
import SAG.SAGPackage;
import SAG.SAGmodel;
import SUC.Role;
import SUC.SUCFactory;
import SUC.SUCPackage;
import SUC.SUCmodel;
import SUC.UseCase;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

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
			state.setSUC(suc);
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
		resourceSet.getPackageRegistry().put(SAGPackage.eNS_URI, SAGPackage.eINSTANCE);
		// load SUC model

		Resource resource = resourceSet.getResource(state.getSAG(), true);	
		SAGmodel sagModel = (SAGmodel) resource.getContents().get(0);

		// Create a new SUC model instance
		Resource newResource = resourceSet.createResource(state.getSUC());
		SUCmodel sucModel = SUCFactory.eINSTANCE.createSUCmodel();
		newResource.getContents().add(sucModel);
		// transformation code:
		HashMap<String, Role> roles = new HashMap<String, Role>();
		int xmiID=1;
		for (Iterator<Actor> iterator = sagModel.getActors().iterator(); iterator.hasNext();) {
			Actor tmpActor = iterator.next();
			Role tmpRole = SUCFactory.eINSTANCE.createRole();
			tmpRole.setName(tmpActor.getName());
			newResource.getContents().add(tmpRole);
			ASEMEXmlHelper.setXmlId(tmpRole, new String("role"+xmiID));
			xmiID++;
			sucModel.getRoles().add(tmpRole);
			roles.put(tmpRole.getName(), tmpRole);
			//newResource.getContents().add(tmpRole);
		}
		xmiID=1;
		for (Iterator<Goal> iterator = sagModel.getGoals().iterator(); iterator.hasNext();) {
			Goal tmpGoal = iterator.next();
			UseCase tmpUsecase = SUCFactory.eINSTANCE.createUseCase();
			tmpUsecase.setName(tmpGoal.getName());
			newResource.getContents().add(tmpUsecase);
			ASEMEXmlHelper.setXmlId(tmpUsecase, new String("usecase"+xmiID));
			xmiID++;
			sucModel.getUsecases().add(tmpUsecase);
			tmpUsecase.getParticipant().add(roles.get(tmpGoal.getDepender().getName()));
			for (Iterator<Actor> iterator2 = tmpGoal.getDependee().iterator(); iterator2
					.hasNext();) {
				tmpUsecase.getParticipant().add(roles.get(iterator2.next().getName()));
			}
			tmpUsecase.setSpecified_by(tmpGoal.getRequirements());
		}
		// save the SUC model
		try {
			Map<String, Object> options = new HashMap<String, Object>();
		    options.put( XMLResource.OPTION_ENCODING, "UTF8" );
	        options.put( XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
	        options.put( XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION , Boolean.TRUE);
			newResource.save(options);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}


