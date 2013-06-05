package statechart.evolve;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import statechart.Model;
import statechart.Node;
import statechart.StatechartFactory;
import statechart.Transition;

public class Mutate {

//////These are parameters that can be changed by the user. Ideally they should go in a resource file!//////

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
	
///////////////////////////////////////////////////////////////////////////////////////////////////////

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
				collectNodes(model, children, toBeDeleted);
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
			if (!iterator.getLabel().equalsIgnoreCase("0") &&
					iterator.getFather_of().equals(newNode.getFather_of()) &&
					!iterator.equals(newNode) &&
					!iterator.getType().equalsIgnoreCase("START"))
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
	
	// PDF file: Template 4
	public static void sequentialMatcher(Model model, Node father, int n) {
		father.setType("OR");
		//@SuppressWarnings("unused")
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
				+ "." + (n+1))), newNode3);
	}

	// PDF file: Template 1
	public static void orMatcher(Model model, Node father, int n) {
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
	public static void parallelMatcher(Model model, Node father, int n) {
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
	public static void complexOptionalTermMatcher(Model model, Node father) {
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
	public static void zeroOrMoreTimesTermMatcher(Model model, Node father) {
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
	public static void foreverTermMatcher(Model model, Node father) {
		father.setType("OR");
		Node newNode1 = newNode(model, father, "START");
		Node newNode2 = newNode(model, father, "BASIC");
		newTransition(model, newNode1, newNode2);
		newTransition(model, newNode2, newNode2);
	}

	// PDF file: Template 5
	public static void oneOrMoreTimesTermMatcher(Model model, Node father) {
		father.setType("OR");
		Node newNode1 = newNode(model, father, "START");
		Node newNode2 = newNode(model, father, "BASIC");
		Node newNode3 = newNode(model, father, "END");
		newTransition(model, newNode1, newNode2);
		newTransition(model, newNode2, newNode2);
		newTransition(model, newNode2, newNode3);
	}

	// PDF file: Template 7
	public static void parallelManyTimesTermMatcher(Model model, Node father, int n) {
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
	
	// relabel the whole model
	public static void performRelabelling(Model m){
		Node node = StatechartFactory.eINSTANCE.createNode();
		node = m.getNodes().get(0);
		node.setLabel("0");
		labelingChildren(m, node);
		labelingTransitions(m);
	}

	// label the transitions of a model
	// (method copied from Angeliki -- KSE/StateChartDesign.diagram/src/statechart/diagram/edit/commands/)
	public static void labelingTransitions(Model m){
		Transition t = StatechartFactory.eINSTANCE.createTransition();
		for(int i=0; i<m.getTransitions().size(); i++){
			t = m.getTransitions().get(i);
			if(t.getSource().getName()!=null && t.getTarget().getName()!=null)
				t.setName(t.getSource().getName()+ "_TO_" + t.getTarget().getName());
			else
				t.setName("transition_"+i);
			m.getTransitions().set(i, t);
		}
	}
	
	// label all the children of the root of a model
	// (method copied from Angeliki -- KSE/StateChartDesign.diagram/src/statechart/diagram/edit/commands/)	
	public static void labelingChildren(Model m, Node parent){
		int order = 1;
		int i=0;
		int start = -1;
		int end = -1;
		for( i=0; i<parent.getChildren().size(); i++){
			if(parent.getChildren().get(i).getType().equals("START")){
				start = i;
				if(end!=-1)
					break;
			}
			if(parent.getChildren().get(i).getType().equals("END")){
				end = i;
				if(start!=-1)
					break;
			}
		}
		if(end!=-1)
				parent.getChildren().get(end).setLabel(parent.getLabel()+"."+parent.getChildren().size());
		//search target nodes of transitions with start source
		LinkedList<Integer> index = new LinkedList<Integer>();
		for(int q=0; q<m.getTransitions().size(); q++){
			if(m.getTransitions().get(q).getSource().getLabel().equals(parent.getChildren().get(start).getLabel()))
				index.add(q);
		}

		//set proper label for start
		parent.getChildren().get(start).setLabel(parent.getLabel()+"."+Integer.toString(order));
		order++;

		i = start;
		boolean done = true;
		for(int o =0; o<parent.getChildren().size(); o++){
			if(!parent.getChildren().get(o).getLabel().contains(".")){
				done = false;
				System.out.println("false");
				break;
			}
		}
		if(done)
			return;
		LinkedList<Integer> newIndex = new LinkedList<Integer>();
		LinkedList<Integer> nodes = new LinkedList<Integer>();
		while(!done){
			for (int u=0; u<index.size(); u++){
				if( parent.getChildren().contains(((Transition)m.getTransitions().get((Integer)index.get(u))).getTarget()) &&
						!((Transition)m.getTransitions().get((Integer)index.get(u))).getTarget().getLabel().contains(".")){
					for(int q=0; q<m.getTransitions().size(); q++){
						if(m.getTransitions().get(q).getSource().getLabel().equals(((Transition)m.getTransitions().get((Integer)index.get(u))).getTarget().getLabel())){
							newIndex.add(q);
							nodes.add(parent.getChildren().indexOf(m.getTransitions().get(q).getTarget()));
						}
					}
					parent.getChildren().get(parent.getChildren().indexOf(((Transition)m.getTransitions().get((Integer)index.get(u))).getTarget())).setLabel(parent.getLabel()+"."+Integer.toString(order));
					if(parent.getChildren().get(parent.getChildren().indexOf(((Transition)m.getTransitions().get((Integer)index.get(u))).getTarget())).getName()==null ||
							!parent.getChildren().get(parent.getChildren().indexOf(((Transition)m.getTransitions().get((Integer)index.get(u))).getTarget())).getType().equals("BASIC"))
						parent.getChildren().get(parent.getChildren().indexOf(((Transition)m.getTransitions().get((Integer)index.get(u))).getTarget())).setName(parent.getChildren().get(parent.getChildren().indexOf(((Transition)m.getTransitions().get((Integer)index.get(u))).getTarget())).getLabel());

					order++;
				}
			}
			done = true;
			for(int o =0; o<parent.getChildren().size(); o++){
				if(!parent.getChildren().get(o).getLabel().contains(".")){
					done = false;
					System.out.println("false");
					break;
				}
			}
			index = newIndex;
			if(!nodes.isEmpty()){
				i = (Integer) nodes.remove(0);
			}else
				break;
		}

		for(int k =0;  k<parent.getChildren().size(); k++){
			if(!parent.getChildren().get(k).getChildren().isEmpty())
			labelingChildren(m, parent.getChildren().get(k));
		}
	}

}// end Mutate
