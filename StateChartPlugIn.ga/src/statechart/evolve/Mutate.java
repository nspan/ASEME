package statechart.evolve;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import statechart.Model;
import statechart.Node;
import statechart.StatechartFactory;
import statechart.StatechartPackage;
import statechart.Transition;

public class Mutate {

	// list of attributes that could be randomly selected
	public static enum Attribute {
		TE, TYPE, ACTIVITY
	}

	// list of actions that mutate could perform
	public static enum Action {
		ADD, REMOVE, EXPAND, NOTHING
	}

	// list of TE used for returning a random TE
	// caution: if you add a TE add also its opposite to the second array
	public static final String[] TEXPS = {"OtherSameTeamPlayerApproachesBall == true",
			"BallFound == true",
			"BallDistanceX <= 0.3 && abs(BallDistanceY) <= 0.1)",
			"BallBearing >= 0",
			"PlayerPlaying == true"};
	public static final String[] TEXPS_OPPOSITE = {"OtherSameTeamPlayerApproachesBall == false",
			"BallFound == false",
			"BallDistanceX > 0.3 && abs(BallDistanceY) > 0.1)",
			"BallBearing < 0",
			"PlayerPlaying == false"};
	
	// list of activities used for returning a random node activity
	public static final String[] ACTIVITIES = {"PassBallLeftKick",
			"PassBallRightKick",
			"ShootToGoalLeftKick",
			"ShootToGoalRightKick",
			"ScanBall",
			"FollowBall",
			"ApproachBall"};

	// list of labels used for returning a random node label
	public static final String[] TYPES = {"BASIC", "CONDITION"};

	public static int NrTemplates = 8;
	public static int MaxNodesTemp4 = 5;
	public static int MaxNodesTemp1 = 5;
	public static int MaxNodesTemp8 = 5;
	public static int MaxNodesTemp7 = 5;

	// collect all the nodes of the branch below the selected node (also remove transitions)
	public static void collectNodes(Model model, Node node, List<Node> toBeDeleted) {
		// if the node has no children
		if (node.getChildren().size()==0) {
			removeTransitions(model, node);
			toBeDeleted.add(node);
		} else { // if the node has children
			for (Node children : node.getChildren()) {
				removeTransitions(model, children);
				toBeDeleted.add(children);
				collectNodes(model, node, toBeDeleted);
			}
		}
	}

	// remove all the branch after the selected node
	public static void removeNodes(Model model, List<Node> toBeDeleted){
		for (Node node : toBeDeleted) {
			boolean hasThisChild = false;//---spanoudo
			if ((node.getFather_of() != null)
					&& (node.getFather_of().getChildren().size() > 0)) {
				for (Node node2 : node.getFather_of().getChildren()) {
					if (node2.equals(node))
						hasThisChild = true;
				}
			}
			if (hasThisChild)
				node.getFather_of().getChildren().remove(node);
			node.setFather_of(null);
			model.getNodes().remove(node);
		}
	}

	// remove all transitions related to the deleted node
	public static void removeTransitions(Model model, Node node) {
		List<Transition> toBeDeleted = new LinkedList<Transition>();
		for (Transition transition : model.getTransitions()) {
			// transitions that have this node as source
			if (transition.getSource().equals(node)) {
				toBeDeleted.add(transition);
			}
			// transitions that have this node as target---spanoudo
			if (transition.getTarget().equals(node)) {
				toBeDeleted.add(transition);
			}
		}
		for (Transition transition : toBeDeleted) {
			model.getTransitions().remove(transition);
		}
	}

	// add a node after the selected child
	public static void addNode(Model model, Node node) {
		// create a newNode and add it after node
		Node newNode = newNode(model, node, "");
		
		// connect the two nodes with a new transition and TE
		List<Transition> nodeTransitions = new LinkedList<Transition>();
		for (Transition transition : model.getTransitions()) {
			if (transition.getSource().equals(node))
				nodeTransitions.add(transition);
		}
		int selectTransition = randomGenerator(nodeTransitions.size());
		Transition selectedTransition = nodeTransitions.get(selectTransition-1);
		newTransition(model, newNode, selectedTransition.getTarget());
		selectedTransition.setTarget(newNode);

		// check the other nodes of the same level and define interactions with the newNode
		// creating a List with all the nodes that could potentially be connected
		List<Node> nodes = new LinkedList<Node>();
		for (Node iterator : model.getNodes()) {
			if (!iterator.getType().equalsIgnoreCase("START") && 
					!iterator.equals(newNode) && 
					iterator.getFather_of().equals(node.getFather_of()))
				nodes.add(iterator);
		}
		// if there are other nodes define randomly transitions and TE, else newNode is BASIC
		if (nodes.size() >= 2) {
			newNode.setType(randomAttribute(Attribute.TYPE));
			int numberOfNewTransitions = randomGenerator(nodes.size());
			for (int i = 1; i <= numberOfNewTransitions; i++) {
				int selectNode = randomGenerator(nodes.size());
				newTransition(model, newNode, nodes.get(selectNode));
				// removing selectNode from the remaining List
				nodes.remove(selectNode);
			}
		}
		else {
			newNode.setType("BASIC");
			newNode.setActivity(randomAttribute(Attribute.ACTIVITY));
		}
	}
	
	// creating a new node (given the father) and adding it to the model
	public static Node newNode(Model model, Node father, String type) {
		Node newNode = StatechartFactory.eINSTANCE.createNode();
		newNode.setLabel(father.getLabel() + "." + (father.getChildren().size()+1));
		newNode.setName(newNode.getLabel());
		newNode.setFather_of(father);
		newNode.setType(type);
		father.getChildren().add(newNode);
		return newNode;
	}
	// Giati o Node exei kai onoma kai label? Einai panta to idio ektos an???
	// Otan svinoume ena Node ap to dentro den allazoume analoga ta onomata ton paidion tou, opote
	// sta onomata tha iparxoun tripes meta apo kapoies gennies. Iparxei problem me auto??

	// creating a new transition (given source and target) and adding it to the model
	public static void newTransition(Model model, Node source, Node target) {
		Transition newTransition = StatechartFactory.eINSTANCE.createTransition();
		newTransition.setSource(source);
		newTransition.setTarget(target);
		newTransition.setTE(randomAttribute(Attribute.TE));
		newTransition.setName(newTransition.getSource().getName() + "TO" + newTransition.getTarget().getName());
		model.getTransitions().add(newTransition);
	}
	
	// select action for this node ADD, EXPAND, REMOVE, NOTHING
	public static Action selectAction(Node node, int denominator) {
		int action = randomGenerator(denominator);
		if(node.getType().equalsIgnoreCase("BASIC")) {
			switch (action) {
				case 1: return Action.ADD;		// probability: 1/denominator
				case 2: return Action.REMOVE;	// probability: 1/denominator
				case 3: return Action.EXPAND;	// probability: 1/denominator
				default: return Action.NOTHING;	// probability: rest
			}
		} else {
			switch (action) {
				case 1: return Action.ADD;		// probability: 1/denominator
				case 2: return Action.REMOVE;	// probability: 1/denominator
				default: return Action.NOTHING;	// probability: rest
			}			
		}
	}

	public static void expandNode(Model model, Node node) {
		int chooseTemplate = randomGenerator(NrTemplates);
		switch (chooseTemplate) {
		case 1:
			int n1 = randomGenerator(MaxNodesTemp4);
			sequentialMatcher(model, node, n1);
			break;			
		case 2:
			int n2 = randomGenerator(MaxNodesTemp1);
			orMatcher(model, node, n2);
			break;			
		case 3:
			int n3 = randomGenerator(MaxNodesTemp8);
			parallelMatcher(model, node, n3);
			break;			
		case 4:
			complexOptionalTermMatcher(model, node);
			break;			
		case 5:
			zeroOrMoreTimesTermMatcher(model, node);
			break;			
		case 6:
			foreverTermMatcher(model, node);
			break;			
		case 7:
			oneOrMoreTimesTermMatcher(model, node);
			break;			
		case 8:
			int n4 = randomGenerator(MaxNodesTemp7);
			parallelManyTimesTermMatcher(model, node, n4);
			break;			
	}

		// FIX ME
	}
	
	// generate random integer between 1 and bound
	public static int randomGenerator(int bound) {
		Random generator = new Random();
		return generator.nextInt(bound)+1;
	}

	// return a random attribute
	public static String randomAttribute(Attribute attribute) {	
		String[] randomAttribute = null;
		switch (attribute) {
			case TE:
				// for now we just choose one random TE (no complex TE included)
				int chooseArray = randomGenerator(2);
				if (chooseArray == 1)
					randomAttribute = TEXPS;
				else
					randomAttribute = TEXPS_OPPOSITE;
				break;
			case TYPE:
				randomAttribute = TYPES;
				break;
			case ACTIVITY:
				randomAttribute = ACTIVITIES;
				break;
			default:
				System.out.println("Check the randomAttribute method!\n");
				break;
		}
		int chooseAttrribute = randomGenerator(randomAttribute.length);
		return randomAttribute[chooseAttrribute-1];
	}

	public static void main(String[] args) {	
		ResourceSet resourceSet = new ResourceSetImpl();
		// Register the appropriate resource factory to handle all file extensions
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());

		// Register the package to ensure it is available during loading.
		resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI, StatechartPackage.eINSTANCE);

		// Options for XML???
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(XMLResource.OPTION_ENCODING, "UTF8");
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
			
			// for each children of the root
			for (Node child : root.getChildren()) {
				// mutate only if the node is BASIC, OR, CONDITION
				if(child.getType().equalsIgnoreCase("BASIC") || 
						child.getType().equalsIgnoreCase("OR") || 
						child.getType().equalsIgnoreCase("CONDITION")) {		
					Action action = selectAction(child, probabilities);
					switch (action) {
						case ADD:
							addNode(outputModel, child);
							output_equals_input = false;
							break;
						case EXPAND:
							expandNode(outputModel, child);
							output_equals_input = false;
							break; 
						case REMOVE:
							List<Node> toBeDeleted = new LinkedList<Node>();
							// collect all the nodes to be deleted (removing all transitions at the same time)
							collectNodes(outputModel, child, toBeDeleted);
							// remove collected nodes
							removeNodes(outputModel, toBeDeleted);
							output_equals_input = false;
							break;
						case NOTHING: break;
					}
				}
			}// end for
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

	// PDF file: Template 4
	public static void sequentialMatcher(Model model, Node father, int n) {
		father.setType("OR");
		@SuppressWarnings("unused")
		Node newNode1 = newNode(model, father, "START");
		for (int k=1; k<=n; k++) {
			Node newNode2 = newNode(model, father, "BASIC");
			newTransition(model, getNodeByLabel(father, new String(father
					.getLabel()
					+ "." + k)), newNode2);
		}
		Node newNode3 = newNode(model, father, "END");
		newTransition(model, getNodeByLabel(father, new String(father
				.getLabel()
				+ "." + n+1)), newNode3);
	}

	// PDF file: Template 1
	private static void orMatcher(Model model, Node father, int n) {
		father.setType("OR");
		Node newNode1 = newNode(model, father, "START");
		Node newNode2 = newNode(model, father, "CONDITION");
		newTransition(model, newNode1, newNode2);
		for (int k=1; k<=n; k++) {
			Node newNode3 = newNode(model, father, "BASIC");
			newTransition(model, newNode2, newNode3);
		}
		Node newNode4 = newNode(model, father, "END");
		for (int k=3; k<=n+2; k++) {
			newTransition(model, getNodeByLabel(father, new String(father
					.getLabel()
					+ "." + k)), newNode4);
		}
	}

	// PDF file: Template 8
	private static void parallelMatcher(Model model, Node father, int n) {
		father.setType("OR");
		Node newNode1 = newNode(model, father, "START");
		Node newNode2 = newNode(model, father, "AND");
		newTransition(model, newNode1, newNode2);
		Node newNode3 = newNode(model, father, "END");
		newTransition(model, newNode2, newNode3);
		for (int k=1; k<=n; k++) {
			Node newNode_ORs = newNode(model, newNode2, "OR");
			sequentialMatcher(model, newNode_ORs, 1);
		}
	}

	// PDF file: Template 6
	private static void complexOptionalTermMatcher(Model model, Node father) {
		father.setType("OR");
		Node newNode1 = newNode(model, father, "START");
		Node newNode2 = newNode(model, father, "CONDITION");
		Node newNode3 = newNode(model, father, "BASIC");
		Node newNode4 = newNode(model, father, "END");
		newTransition(model, newNode1, newNode2);
		newTransition(model, newNode2, newNode3);
		newTransition(model, newNode2, newNode4);
		newTransition(model, newNode3, newNode4);
	}

	// PDF file: Template 2
	private static void zeroOrMoreTimesTermMatcher(Model model, Node father) {
		father.setType("OR");
		Node newNode1 = newNode(model, father, "START");
		Node newNode2 = newNode(model, father, "CONDITION");
		Node newNode3 = newNode(model, father, "BASIC");
		Node newNode4 = newNode(model, father, "END");
		newTransition(model, newNode1, newNode2);
		newTransition(model, newNode2, newNode3);
		newTransition(model, newNode3, newNode3);
		newTransition(model, newNode2, newNode4);
		newTransition(model, newNode3, newNode4);
	}

	// PDF file: Template 3
	private static void foreverTermMatcher(Model model, Node father) {
		father.setType("OR");
		Node newNode1 = newNode(model, father, "START");
		Node newNode2 = newNode(model, father, "BASIC");
		newTransition(model, newNode1, newNode2);
		newTransition(model, newNode2, newNode2);
	}

	// PDF file: Template 5
	private static void oneOrMoreTimesTermMatcher(Model model, Node father) {
		father.setType("OR");
		Node newNode1 = newNode(model, father, "START");
		Node newNode2 = newNode(model, father, "BASIC");
		Node newNode3 = newNode(model, father, "END");
		newTransition(model, newNode1, newNode2);
		newTransition(model, newNode2, newNode2);
		newTransition(model, newNode2, newNode3);
	}

	// PDF file: Template 7
	private static void parallelManyTimesTermMatcher(Model model, Node father, int n) {
		father.setType("OR");
		Node newNode1 = newNode(model, father, "START");
		Node newNode2 = newNode(model, father, "AND");
		newTransition(model, newNode1, newNode2);
		for (int k=1; k<=n; k++) {
			Node newNode_ORs = newNode(model, newNode2, "OR");
			foreverTermMatcher(model, newNode_ORs);
		}
	}

	// the two methods below are used for getNodeByLabel
	public static Node getNodeByLabel(Node root, String label) {
		if(root.getLabel().equals(label))
			return root;
		else
			return DFSearch(root, label);
	}

	public static Node DFSearch(Node root, String label){
		Iterator<Node> it = root.getChildren().iterator();
		Node search = null;
		while( it.hasNext()){
			search = it.next();
			if(search.getLabel().equals(label)){
				return search;
			}
			else if(search.getChildren().size()>0){
				Node newsearch = DFSearch(search, label);
				if (newsearch!=null)
					return newsearch;
			}
		}
		return null;
	}

}// end Mutate
