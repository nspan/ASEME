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
	
//////These are parameters that can be changed by the user. Ideally they should go in a resource file!//////

	// each action {ADD, EXPAND, REMOVE} has probability 1/probabilities to happen for every node; the rest goes to do NOTHING
	private static int probabilities = 100;
	private static int maxNumGenerations = 10;
	private static int maxNumIncrPopulation = 5;

///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		for (int gen=0; gen<maxNumGenerations; gen++) {
			int maxPop = (int)Math.min(Math.pow(2,gen), Math.pow(2,maxNumIncrPopulation));
			for (int pop=1; pop<=maxPop; pop++) {
				String input = (gen < 10 ? "0" : "") + gen + "_" + (pop < 10 ? "0" : "") + pop + ".sct";
				String output1 = (gen+1 < 10 ? "0" : "") + (gen+1) + "_" + (pop < 10 ? "0" : "") + pop + ".sct";
				//System.out.println("Calling mutate with:: " + input + " " + output1);
				System.out.println("Calling mutate with:1:" + input);
				generateNewModel(input, output1);
				if(gen < maxNumIncrPopulation) {
					int new_pop= (int)(Math.pow(2,gen)+pop);
					String output2 = "0" + (gen+1) + "_" + (new_pop < 10 ? "0" : "") + new_pop + ".sct";
					//System.out.println("Calling mutate with:: " + input + " " + output2);
					System.out.println("Calling mutate with:2:" + input);
					generateNewModel(input, output2);
				}
			}
		}
	}
	
	// gets an input model and outputs an output model of the next generation (after mutation)
	public static void generateNewModel(String inputName, String outputName) {
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

		// load input model
		Resource r = resourceSet.getResource(URI.createFileURI(inputName), true);
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
								Mutate.performRelabelling(outputModel);
								output_equals_input = false;
								break;
							case EXPAND:
								Mutate.expandNode(outputModel, child);
								Mutate.performRelabelling(outputModel);
								output_equals_input = false;
								break; 
							case REMOVE:
								List<Node> toBeDeleted = new LinkedList<Node>();
								// collect all the nodes to be deleted (removing all transitions at the same time)
								Mutate.collectNodes(outputModel, child, toBeDeleted);
								// remove collected nodes
								Mutate.removeNodes(outputModel, toBeDeleted);
								Mutate.performRelabelling(outputModel);
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
		// save the output model
		Resource newResource = resourceSet.createResource(URI.createURI("http://statechart/1.0"));
		newResource.getContents().add(outputModel);
		try {
			FileOutputStream out = new FileOutputStream(new File(outputName));
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
	}// end generateNewModel
}// end Runner
