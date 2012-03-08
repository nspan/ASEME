package aseme.transformations;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ResourceEntityHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import SAG.*;
import SUC.*;

public class SAG2SUC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
		resourceSet.getPackageRegistry().put(SUCPackage.eNS_URI,
				SUCPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(SAGPackage.eNS_URI,
				SAGPackage.eINSTANCE);
		// load SUC model
		Resource r = null;
		if (args != null && args.length > 1) {
			r = resourceSet.getResource(URI.createFileURI(args[0]), true);
		} else {
			r = resourceSet.getResource(URI.createFileURI("mms.sag"),
					true);
		}
		SAGmodel sag = (SAGmodel) r.getContents().get(0);

		// Create a new SUC model instance
		Resource newResource = resourceSet.createResource(URI
				.createURI("http:///My.suc"));
		SUCmodel suc = SUCFactory.eINSTANCE.createSUCmodel();
		newResource.getContents().add(suc);
		// transformation code:
		HashMap<String, Role> roles = new HashMap<String, Role>();
		int xmiID=1;
		for (Iterator<Actor> iterator = sag.getActors().iterator(); iterator.hasNext();) {
			Actor tmpActor = iterator.next();
			Role tmpRole = SUCFactory.eINSTANCE.createRole();
			tmpRole.setName(tmpActor.getName());
			newResource.getContents().add(tmpRole);
			XMIHelper.setXmlId(tmpRole, new String("role"+xmiID));
			xmiID++;
			suc.getRoles().add(tmpRole);
			roles.put(tmpRole.getName(), tmpRole);
			//newResource.getContents().add(tmpRole);
		}
		xmiID=1;
		for (Iterator<Goal> iterator = sag.getGoals().iterator(); iterator.hasNext();) {
			Goal tmpGoal = iterator.next();
			UseCase tmpUsecase = SUCFactory.eINSTANCE.createUseCase();
			tmpUsecase.setName(tmpGoal.getName());
			newResource.getContents().add(tmpUsecase);
			XMIHelper.setXmlId(tmpUsecase, new String("usecase"+xmiID));
			xmiID++;
			suc.getUsecases().add(tmpUsecase);
			tmpUsecase.getParticipant().add(roles.get(tmpGoal.getDepender().getName()));
			for (Iterator<Actor> iterator2 = tmpGoal.getDependee().iterator(); iterator2
					.hasNext();) {
				tmpUsecase.getParticipant().add(roles.get(iterator2.next().getName()));
			}
			tmpUsecase.setSpecified_by(tmpGoal.getRequirements());
		}
		// save the SUC model
		try {
			FileOutputStream out = null;
			if (args != null && args.length > 1) {
				out = new FileOutputStream(new File(args[1]));
			}else{
				out = new FileOutputStream(new File("mms-initial.suc"));
			}
			Map<String, Object> options = new HashMap<String, Object>();
		    options.put( XMLResource.OPTION_ENCODING, "UTF8" );
	        options.put( XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
	        options.put( XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION , Boolean.TRUE);
		    
			newResource.save(out, options);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
