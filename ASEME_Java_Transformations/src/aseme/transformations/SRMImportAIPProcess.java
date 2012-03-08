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
import SRM.*;

public class SRMImportAIPProcess {

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
		resourceSet.getPackageRegistry().put(SRMPackage.eNS_URI,
				SRMPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(AIPPackage.eNS_URI,
				AIPPackage.eINSTANCE);
		// load AIP model
		Resource r = null;
		if (args != null && args.length > 1) {
			r = resourceSet.getResource(URI.createFileURI(args[0]), true);
		} else {
			r = resourceSet.getResource(URI.createFileURI("mms-refined.aip"),
					true);
		}
		AIPmodel aip = (AIPmodel) r.getContents().get(0);

		// load SRM model
		Resource newResource = null;
		if (args != null && args.length > 1) {
			newResource = resourceSet.getResource(URI.createFileURI(args[1]), true);
		} else {
			newResource = resourceSet.getResource(URI.createFileURI("mms-initial-PersonalAssistant.srm"),
					true);
		}
		SRMmodel srm = (SRMmodel)newResource.getContents().get(0);
		
		// update the SRM model
		for (Iterator<Role> iterator = srm.getRoles().iterator(); iterator.hasNext();) {
			Role role = iterator.next();
			for (Iterator<Capability> iterator1 = role.getCapabilities().iterator(); iterator1.hasNext();) {
				Capability cp = iterator1.next();
				for (Iterator<Participant> iterator2 = aip.getParticipants().iterator(); iterator2
						.hasNext();) {
					Participant participant = iterator2.next();
					if (participant.getName().equalsIgnoreCase(cp.getName())){
						//now replace the 
					}
				}
			}
		}

		// save the SRM model
		newResource.getContents().add(aip);
		try {
			FileOutputStream out = null;
			if (args != null && args.length > 1) {
				out = new FileOutputStream(new File(args[1]));
			}else{
				out = new FileOutputStream(new File("mms-initial-with-AIP-PersonalAssistant.srm"));
			}
			Map<String, Object> options = new HashMap<String, Object>();
		    options.put( XMLResource.OPTION_ENCODING, "UTF8" );
			newResource.save(out, options);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
