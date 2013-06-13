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
	private static int maxNumIncrPopulation = 6;

///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		for (int gen=0; gen<maxNumGenerations; gen++) {
			int maxPop = (int)Math.min(Math.pow(2,gen), Math.pow(2,maxNumIncrPopulation));
			for (int pop=1; pop<=maxPop; pop++) {
				String input = (gen < 10 ? "0" : "") + gen + "_" + (pop < 10 ? "0" : "") + pop + ".sct";
				String output1 = (gen+1 < 10 ? "0" : "") + (gen+1) + "_" + (pop < 10 ? "0" : "") + pop + ".sct";
				generateNewModel(input, output1);
				if(gen < maxNumIncrPopulation) {
					int new_pop= (int)(Math.pow(2,gen)+pop);
					String output2 = "0" + (gen+1) + "_" + (new_pop < 10 ? "0" : "") + new_pop + ".sct";
					generateNewModel(input, output2);
				}
			}
			int numModels = gen < maxNumIncrPopulation ? 2*maxPop : maxPop;
			System.out.println("Finished generation " + (gen+1 < 10 ? "0" : "") + (gen+1) + ": " + (numModels < 10 ? "0" : "") + numModels + " models produced (filenames: " + (gen+1 < 10 ? "0" : "") + (gen+1) + "_xx.sct)");
		}
	}

	// gets an input model and outputs an output model of the next generation (after mutation)
	public static void generateNewModel(String inputName, String outputName) {
		// Create a new generation model instance
		Model outputModel = getResourceModel(inputName);

		//print the model to the console (just debugging)
		//printModel(r);

		// mutation code:
		boolean output_equals_input = true;
		boolean isMutable = true;
		// run until at least one change is made to the outputModel
		while(output_equals_input) {
			// find the root of the tree
			Node root = findRoot(outputModel);
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
								isMutable = checkModelSuitability(outputModel);
								if(isMutable==true)
									output_equals_input = false;
								else
									outputModel = getResourceModel(inputName);
								break;
							case EXPAND:
								Mutate.expandNode(outputModel, child);
								Mutate.performRelabelling(outputModel);
								isMutable = checkModelSuitability(outputModel);
								if(isMutable==true)
									output_equals_input = false;
								else
									outputModel = getResourceModel(inputName);
								break;
							case REMOVE:
								List<Node> toBeDeleted = new LinkedList<Node>();
								// collect all the nodes to be deleted (removing all transitions at the same time)
								Mutate.collectNodes(outputModel, child, toBeDeleted);
								// remove collected nodes
								Mutate.removeNodes(outputModel, toBeDeleted);
								Mutate.performRelabelling(outputModel);
								isMutable = checkModelSuitability(outputModel);
								if(isMutable==true)
									output_equals_input = false;
								else
									outputModel = getResourceModel(inputName);
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
		ResourceSet resourceSet = getResourceSet();
		Resource newResource = resourceSet.createResource(URI.createURI("http://statechart/1.0"));
		newResource.getContents().add(outputModel);
		try {
			FileOutputStream out = new FileOutputStream(new File(outputName));
			newResource.save(out, getOptions());
		} catch (Exception e) {
			System.out.println("Problem saving the output .xml file!\n");
			e.printStackTrace();
		} finally {
			//print outputModel to the console (just debugging)
			//printModel(newResource);
		}
	}// end generateNewModel
	
	// returns the root of a tree (model)
	public static Node findRoot(Model tree) {
		Node root = null;
		for (Node iterator : tree.getNodes()) {
			if(iterator.getLabel().equalsIgnoreCase("0")) {
				root = iterator;
				break;
			}
		}
		return root;
	}

	// define Options for XML resources
	public static Map<String, Object> getOptions() {
		// Options for XML
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		options.put(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION, Boolean.TRUE);
		return options;
	}
	
	// print a model to the console
	public static void printModel(Resource resource) {
		Map<String, Object> options = getOptions();
		try {
			resource.save(System.out, options);
			System.out.println("\n");
		} catch (Exception e) {
			System.out.println("Problem printing input model!\n");
		}
	}
	
	// we may end up with a model that cannot be further mutated and we don't want that
	// so if a model like this appears at some generation, we don't accept it and go back
	// to the previous model and mutate again
	// (this method returns the number of nodes that can be further mutated for this model)
	public static boolean checkModelSuitability(Model model) {
		Node output_root = findRoot(model);
		int count_mutable_nodes = 0;
		// for each children of the root
		for (Node children : output_root.getChildren()) {
			if(children.getType().equalsIgnoreCase("BASIC") ||
					children.getType().equalsIgnoreCase("OR") ||
					children.getType().equalsIgnoreCase("CONDITION")) {
				count_mutable_nodes += 1;
			}
		}
		if (count_mutable_nodes<1)
			return false;
		else
			return true;
	}

	// returns input model from resource file
	public static ResourceSet getResourceSet() {
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register the appropriate resource factory to handle all file extensions
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());
		// Register the package to ensure it is available during loading.
		resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI, StatechartPackage.eINSTANCE);
		return resourceSet;
	}

	// returns input model from resource file
	public static Model getResourceModel(String inputFile) {
		ResourceSet rSet = getResourceSet();
		Resource r = rSet.getResource(URI.createFileURI(inputFile), true);
		Model inputModel = (Model) r.getContents().get(0);
		return inputModel;
	}
}// end Runner
