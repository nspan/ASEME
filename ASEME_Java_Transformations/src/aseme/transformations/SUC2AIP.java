package aseme.transformations;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import AIP.AIPFactory;
import AIP.AIPPackage;
import AIP.AIPmodel;
import AIP.Participant;
import AIP.Protocol;
import SUC.Role;
import SUC.SUCPackage;
import SUC.SUCmodel;
import SUC.SystemRole;
import SUC.UseCase;

public class SUC2AIP {

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
		resourceSet.getPackageRegistry().put(AIPPackage.eNS_URI,
				AIPPackage.eINSTANCE);
		// load SUC model
		Resource r = null;
		if (args != null && args.length > 1) {
			r = resourceSet.getResource(URI.createFileURI(args[0]), true);
		} else {
			r = resourceSet.getResource(URI.createFileURI("mms-refined.suc"),
					true);
		}
		SUCmodel suc_model = (SUCmodel) r.getContents().get(0);

		// Create a new AIP model instance
		Resource newResource = resourceSet.createResource(URI
				.createURI("http:///My.aip"));
		AIPmodel aip = AIPFactory.eINSTANCE.createAIPmodel();
		for (Iterator<UseCase> iterator = suc_model.getUsecases().iterator(); iterator
				.hasNext();) {
			UseCase usecase = iterator.next();
			List<SystemRole> systemRoleUseCaseParticipants = new LinkedList<SystemRole>();
			for (Iterator<Role> iterator2 = usecase.getParticipant().iterator(); iterator2
					.hasNext();) {
				Role role =  iterator2.next();
				if (role instanceof SystemRole)
					systemRoleUseCaseParticipants.add(((SystemRole) role));
			}
			if (systemRoleUseCaseParticipants.size() > 1) {
				Protocol tmpProtocol = AIPFactory.eINSTANCE.createProtocol();
				tmpProtocol.setName(usecase.getName());
				HashMap<String, Participant> participants = new HashMap<String, Participant>();
				for (Iterator<SystemRole> iterator2 = systemRoleUseCaseParticipants
						.iterator(); iterator2.hasNext();) {
					SystemRole systemRole =  iterator2.next();
					Participant tmpParticipant = AIPFactory.eINSTANCE
							.createParticipant();
					tmpParticipant.setName(new String(tmpProtocol.getName()
							+ "_" + systemRole.getName()));
					tmpProtocol.getParticipants().add(tmpParticipant);
					aip.getParticipants().add(tmpParticipant);
					participants.put(tmpParticipant.getName(), tmpParticipant);
					tmpParticipant.setLiveness(new String(tmpParticipant
							.getName() + "="));
				}
				for (Iterator<UseCase> iterator2 = usecase.getInclude()
						.iterator(); iterator2.hasNext();) {
					UseCase tmpUsecase = (UseCase) iterator2.next();
					if (participants.get(tmpProtocol.getName() + "_"
							+ tmpUsecase.getParticipant().get(0).getName()) != null) {
						participants.get(
								tmpProtocol.getName()
										+ "_"
										+ tmpUsecase.getParticipant().get(0)
												.getName()).setLiveness(
								participants.get(
										tmpProtocol.getName()
												+ "_"
												+ tmpUsecase.getParticipant()
														.get(0).getName())
										.getLiveness()
										+ tmpUsecase.getName() + "?");
					}
				}
				aip.getProtocols().add(tmpProtocol);
				// add the activities!!!
			}
		}
		// save the AIP model
		newResource.getContents().add(aip);
		try {
			FileOutputStream out = null;
			if (args != null && args.length > 1) {
				out = new FileOutputStream(new File(args[1]));
			}else{
				out = new FileOutputStream(new File("mms.aip"));
			}
			Map<String, Object> options = new HashMap<String, Object>();
		    options.put( XMLResource.OPTION_ENCODING, "UTF8" );
			newResource.save(out, options);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
