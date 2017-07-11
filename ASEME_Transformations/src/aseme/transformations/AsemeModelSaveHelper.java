package aseme.transformations;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import SAG.SAGPackage;
import SRM.SRMPackage;
import SUC.SUCPackage;
import AIP.AIPPackage;
import statechart.StatechartPackage;

public class AsemeModelSaveHelper {
	
	public static ResourceSet staticResourceSet = null;
	
	public static void init(){
		staticResourceSet = new ResourceSetImpl();
		// Register the appropriate resource factory to handle all file
		// extensions.
		
		
		staticResourceSet
				.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());
		
		staticResourceSet.getPackageRegistry().put(SAGPackage.eNS_URI, SAGPackage.eINSTANCE);
		staticResourceSet.getPackageRegistry().put(SUCPackage.eNS_URI, SUCPackage.eINSTANCE);
		staticResourceSet.getPackageRegistry().put(SRMPackage.eNS_URI, SRMPackage.eINSTANCE);
		staticResourceSet.getPackageRegistry().put(AIPPackage.eNS_URI, AIPPackage.eINSTANCE);
		staticResourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI, StatechartPackage.eINSTANCE);
	}
	
	public static void saveFilename( EObject model,String filename){
		
		try {
		
			Resource newResource = staticResourceSet.createResource( URI.
				createURI(filename));	
			
		
			newResource.getContents().add(model);
		
			 
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(XMLResource.OPTION_ENCODING, "UTF8");
			newResource.save(options);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

public static void saveURI( EObject model,URI uri){ 		
		
		try {
			
			Resource newResource = staticResourceSet.createResource(uri);
			newResource.getContents().add(model);
		
			 
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(XMLResource.OPTION_ENCODING, "UTF8");
			newResource.save(options);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
}
