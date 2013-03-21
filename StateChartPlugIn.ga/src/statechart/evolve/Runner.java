package statechart.evolve;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import statechart.Model;
import statechart.Node;
import statechart.StatechartPackage;
import statechart.evolve.Mutate.Action;

public class Runner {

	
	public static void main(String[] args) {	
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register the appropriate resource factory to handle all file extensions
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());

		// Register the package to ensure it is available during loading.
		resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI, StatechartPackage.eINSTANCE);

		// Options for XML???
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		options.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);

		// load last generation model
		Resource r = null;
		if (args != null && args.length > 1) 
			r = resourceSet.getResource(URI.createFileURI(args[0]), true);
		else
			r = resourceSet.getResource(URI.createFileURI("testAnd.sct"), true);
		Model inputModel = (Model) r.getContents().get(0);

		//print inputModel to the console (just debugging)
		try {
			r.save(System.out, options);
			System.out.println("\n");
		} catch (Exception e) {
			System.out.println("Problem printing input model!\n");
		}

		// Create a new generation model instance
		Model outputModel = inputModel;

		// mutation code:
		boolean output_equals_input = true;
		// run until at least one change is made to the outputModel
		while(output_equals_input) {
			// find the root of the tree
			Node root = null;
			for (Node iterator : outputModel.getNodes()) {
				if(iterator.getLabel().equalsIgnoreCase("0")) {
					root = iterator;
					break;
				}
			}

			// use 50 for 2% probability for each action {ADD, EXPAND, REMOVE} and all the rest for do NOTHING
			int probabilities = 50;
			
			// the for loop below can break sometimes as the tree is changing dynamically
			// adding a children somewhere at random could break the iterator
			try {
				// for each children of the root
				for (Node child : root.getChildren()) {
					// mutate only if the node is BASIC, OR, CONDITION
					if(child.getType().equalsIgnoreCase("BASIC") || 
							child.getType().equalsIgnoreCase("OR") || 
							child.getType().equalsIgnoreCase("CONDITION")) {		
						Action action = Mutate.selectAction(child, probabilities);
						switch (action) {
							case ADD:
								Mutate.addNode(outputModel, child);
								output_equals_input = false;
								break;
							case EXPAND:
								Mutate.expandNode(outputModel, child);
								output_equals_input = false;
								break; 
							case REMOVE:
								List<Node> toBeDeleted = new LinkedList<Node>();
								// collect all the nodes to be deleted (removing all transitions at the same time)
								Mutate.collectNodes(outputModel, child, toBeDeleted);
								// remove collected nodes
								Mutate.removeNodes(outputModel, toBeDeleted);
								output_equals_input = false;
								break;
							case NOTHING: break;
						}
					}
				}// end for
			} catch (Exception e) {
				break; // exit for loop; the model is already mutated and this for is problematic
			}
		}

		// save the new generation model
		Resource newResource = resourceSet.createResource(URI.createURI("http://statechart/1.0"));
		newResource.getContents().add(outputModel);
		try {
			FileOutputStream out = null;
			if (args != null && args.length > 1)
				out = new FileOutputStream(new File(args[1]));
			else
				out = new FileOutputStream(new File("testAndMutated.sct"));
			newResource.save(out, options);
		} catch (Exception e) {
			System.out.println("Problem saving the output .xml file!\n");
			e.printStackTrace();
		} finally {
			try {
				newResource.save(System.out, options);
			} catch (Exception e) {
				System.out.println("Problem saving the output file!\n");
			}
		}
	}// end main
}
