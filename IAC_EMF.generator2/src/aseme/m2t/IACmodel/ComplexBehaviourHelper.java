package aseme.m2t.IACmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

//import IAC.Model;
//import IAC.Node;
//import IAC.Transition;
//import IAC.Variable;

//import NodeHelper;


import statechart.Model;
import statechart.Node;
import statechart.Transition;
import statechart.Variable;

public class ComplexBehaviourHelper {

	public static final String TYPE_FOREVER = "forever";
	public static final String TYPE_ONE_OR_MORE_TIMES = "one_or_more_times";
	public static final String TYPE_ZERO_OR_MORE_TIMES = "zero_or_more_times";
	public static final String TYPE_SEQUENCE = "sequence";
	public static final String TYPE_OR = "or";
	public static final String TYPE_PARALLEL = "parallel";
	
	public static ArrayList<Node> nodeList = NodeHelper.p;

	public static boolean existConditionInSubNodesOf(Node e, Model m) {
		boolean result = false;
		// TO DO define the attributes
		EList<Node> tmp = subNodesOf(e, m);
		for (Iterator<Node> iterator = tmp.iterator(); iterator.hasNext();) {
			Node node = iterator.next();
			if (node.getType().equalsIgnoreCase("CONDITION")) {
				result = true;
			}
		}
		return result;
	}

	public static String addParallelBehaviours(Node e, Model m) {
		String result = new String();
		for (Iterator<Node> out_iterator = subBehavioursOf(e, m).iterator(); out_iterator
				.hasNext();) {
			Node out_node = out_iterator.next();
			for (Iterator<Node> iterator = subBehavioursOf(out_node, m)
					.iterator(); iterator.hasNext();) {
				Node node = iterator.next();
				if (node.getType().equalsIgnoreCase(TYPE_OR)) {
					String params = new String();
					boolean firstParam = true;
					for (Iterator<Variable> iterator2 = node.getVariables()
							.iterator(); iterator2.hasNext();) {
						Variable tmpVar2 = iterator2.next();
						for (Iterator<Variable> iterator3 = e.getVariables()
								.iterator(); iterator3.hasNext();) {
							Variable tmpVar3 = iterator3.next();
							if (tmpVar2.getName().equalsIgnoreCase(
									tmpVar3.getName())) {
								params = params + ", " + tmpVar2.getName();
								if (firstParam)
									firstParam = false;
							}
						}
					}
					result = result + "\nmyAgent.addBehaviour(tbf.wrap(new "
							+ node.getName() + "Behaviour(this.myAgent"
							+ params + ")));";
				}
			}
		}
		return result;
	}

	public static String getCyclicBehaviour(Node e, Model m) {
		String result = new String();
		for (Iterator<Node> iterator = subBehavioursOf(e, m).iterator(); iterator
				.hasNext();) {
			Node node = iterator.next();
			String params = new String();
			boolean firstParam = true;
			for (Iterator<Variable> iterator2 = node.getVariables().iterator(); iterator2
					.hasNext();) {
				Variable tmpVar2 = iterator2.next();
				for (Iterator<Variable> iterator3 = e.getVariables().iterator(); iterator3
						.hasNext();) {
					Variable tmpVar3 = iterator3.next();
					if (tmpVar2.getName().equalsIgnoreCase(tmpVar3.getName())) {
						params = params + ", " + tmpVar2.getName();
						if (firstParam)
							firstParam = false;
					}
				}
			}
			result = result + node.getName() + "Behaviour(this.myAgent"
					+ params + ")";
			break;
		}
		return result;
	}

	public static String getAgentBehaviour(Node e, Model m) {
		String result = new String();
		for (Iterator<Node> iterator = subBehavioursOf(e, m).iterator(); iterator
				.hasNext();) {
			Node node = iterator.next();
			String params = new String();
			boolean firstParam = true;
			for (Iterator<Variable> iterator2 = node.getVariables().iterator(); iterator2
					.hasNext();) {
				Variable tmpVar2 = iterator2.next();
				for (Iterator<Variable> iterator3 = e.getVariables().iterator(); iterator3
						.hasNext();) {
					Variable tmpVar3 = iterator3.next();
					if (tmpVar2.getName().equalsIgnoreCase(tmpVar3.getName())) {
						params = params + ", " + tmpVar2.getName();
						if (firstParam)
							firstParam = false;
					}
				}
			}
			result = result + "new " + node.getName() + "Behaviour(this"
					+ params + ")";
			break;
		}
		return result;
	}

	public static String addSubBehaviours(Node e, Model m) {
		String result = new String();
		for (Iterator<Node> iterator = sortSubNodes(subBehavioursOf(e, m))
				.iterator(); iterator.hasNext();) {
			Node node = iterator.next();
			String params = new String();
			boolean firstParam = true;
			for (Iterator<Variable> iterator2 = node.getVariables().iterator(); iterator2
					.hasNext();) {
				Variable tmpVar2 = iterator2.next();
				for (Iterator<Variable> iterator3 = e.getVariables().iterator(); iterator3
						.hasNext();) {
					Variable tmpVar3 = iterator3.next();
					if (tmpVar2.getName().equalsIgnoreCase(tmpVar3.getName())) {
						params = params + ", " + tmpVar2.getName();
						if (firstParam)
							firstParam = false;
					}
				}
			}
			result = result + "\naddSubBehaviour(new " + node.getName()
					+ "Behaviour(this.myAgent" + params + "));";
		}
		return result;
	}

	public static String addConditionalSubBehaviour(Node e, Model m) {
		String result = new String();
		EList<Node> tmp = subBehavioursOf(e, m);
		boolean allowedNullOnce = false;
		for (int i = 0; i < tmp.size(); i++) {
			Node node = tmp.get(i);
			String params = new String();
			boolean firstParam = true;
			for (Iterator<Variable> iterator2 = node.getVariables().iterator(); iterator2
					.hasNext();) {
				Variable tmpVar2 = iterator2.next();
				for (Iterator<Variable> iterator3 = e.getVariables().iterator(); iterator3
						.hasNext();) {
					Variable tmpVar3 = iterator3.next();
					if (tmpVar2.getName().equalsIgnoreCase(tmpVar3.getName())) {
						params = params + ", " + tmpVar2.getName();
						if (firstParam)
							firstParam = false;
					}
				}
			}
			String condition = null;
			for (Iterator<Transition> iterator = m.getTransitions().iterator(); iterator
					.hasNext();) {
				Transition tmpT = iterator.next();
				if (tmpT.getTarget().getLabel().equalsIgnoreCase(
						node.getLabel())) {
					condition = getConditionOfExpression(tmpT.getTE());
				}
			}
			if (condition == null)
				condition = "null";
			if ((condition.equalsIgnoreCase("null")) && (i < tmp.size() - 1)
					&& (!allowedNullOnce)) {
				tmp.remove(i);
				tmp.add(node);
				i--;
				allowedNullOnce = true;
			} else {
				if (i == 0) {
					result = result
							+ "\n\t\tif ("
							+ (condition.equalsIgnoreCase("null") ? "/*insert condition*/"
									: "/*" + condition + "*/") + ") ";
				} else if ((i == tmp.size() - 1)
						&& (condition.equalsIgnoreCase("null"))) {
					result = result + "\n\t\telse ";
				} else {
					result = result
							+ "\n\t\telse if("
							+ (condition.equalsIgnoreCase("null") ? "/*insert condition*/"
									: "/*" + condition + "*/") + ") ";
				}
				result = result + "addSubBehaviour(new " + node.getName()
						+ "Behaviour(this.myAgent" + params + "));";
			}
		}
		return result;
	}

	public static boolean existTransitionToSelf(Node e, Model m) {
		boolean result = false;
		for (Iterator<Transition> iterator = m.getTransitions().iterator(); iterator
				.hasNext();) {
			Transition tmp = iterator.next();
			if ((tmp.getSource().getLabel().equalsIgnoreCase(e.getLabel()))
					&& (tmp.getTarget().getLabel().equalsIgnoreCase(e
							.getLabel()))) {
				result = true;
			}
		}
		return result;
	}

	public static String getTransitionToSelfConditionOfChild(Node e, Model m) {
		String result = "true";
		for (Iterator<Transition> iterator = m.getTransitions().iterator(); iterator
				.hasNext();) {
			Transition tmp = iterator.next();
			if ((tmp.getSource().getLabel().equalsIgnoreCase(subBehavioursOf(e,
					m).get(0).getLabel()))
					&& (tmp.getTarget().getLabel()
							.equalsIgnoreCase(subBehavioursOf(e, m).get(0)
									.getLabel()))) {
				result = ((tmp.getTE() == null)
						|| (tmp.getTE().equalsIgnoreCase("null")) ? "/*insert condition*/"
						: tmp.getTE());
			}
		}
		return result;
	}

	public static String getTransitionToChildOf(Node e, Model m) {
		String result = "true";
		for (Iterator<Transition> iterator = m.getTransitions().iterator(); iterator
				.hasNext();) {
			Transition tmp = iterator.next();
			if ((!(tmp.getSource().getLabel().equalsIgnoreCase(subBehavioursOf(
					e, m).get(0).getLabel())))
					&& (tmp.getTarget().getLabel()
							.equalsIgnoreCase(subBehavioursOf(e, m).get(0)
									.getLabel()))) {
				result = tmp.getTE();
			}
		}
		return result;
	}

	public static String determineBehaviourType(Node e, Model m) {
		if (e.getType().equalsIgnoreCase("AND")) {
			System.out
					.print("\nFound a behaviour with name: " + e.getName()
							+ " found of type: "
							+ ComplexBehaviourHelper.TYPE_PARALLEL);
			return ComplexBehaviourHelper.TYPE_PARALLEL;
		} else if ((subNodesOf(e, m).size() == 2)
				&& (existTransitionToSelf((sortSubNodes(subNodesOf(e, m)))
						.get(1), m))) {
			System.out.print("\nFound a behaviour with name: " + e.getName()
					+ " found of type: " + ComplexBehaviourHelper.TYPE_FOREVER);
			return ComplexBehaviourHelper.TYPE_FOREVER;
		} else if ((subNodesOf(e, m).size() == 3)
				&& (existTransitionToSelf((sortSubNodes(subNodesOf(e, m)))
						.get(1), m))) {
			System.out.print("\nFound a behaviour with name: " + e.getName()
					+ " found of type: "
					+ ComplexBehaviourHelper.TYPE_ONE_OR_MORE_TIMES);
			return ComplexBehaviourHelper.TYPE_ONE_OR_MORE_TIMES;
		} else if (existConditionInSubNodesOf(e, m)) {
			if (subNodesOf(e, m).size() == 4) {
				System.out.print("\nFound a behaviour with name: "
						+ e.getName() + " found of type: "
						+ ComplexBehaviourHelper.TYPE_ZERO_OR_MORE_TIMES);
				return ComplexBehaviourHelper.TYPE_ZERO_OR_MORE_TIMES;
			} else {
				System.out.print("\nFound a behaviour with name: "
						+ e.getName() + " found of type: "
						+ ComplexBehaviourHelper.TYPE_OR);
				return ComplexBehaviourHelper.TYPE_OR;
			}
		} else {
			System.out
					.print("\nFound a behaviour with name: " + e.getName()
							+ " found of type: "
							+ ComplexBehaviourHelper.TYPE_SEQUENCE);
			return ComplexBehaviourHelper.TYPE_SEQUENCE;
		}
	}

	public static String addVariables(Node e, Model m) {
		String result = new String();
		for (Iterator<Variable> iterator = e.getVariables().iterator(); iterator
				.hasNext();) {
			Variable variable = iterator.next();
			if (existVariableInParentNode(variable, e, m)) {
				result = result + "\t\t" + variable.getType() + "Holder "
						+ variable.getName() + " = null;";
			} else {
				result = result + "\t\t" + variable.getType() + "Holder "
						+ variable.getName() + " = new " + variable.getType()
						+ "Holder(this);";
			}
		}
		return result;
	}

	public static String getParams(Node e, Model m) {
		String result = new String();
		for (Iterator<Variable> iterator = e.getVariables().iterator(); iterator
				.hasNext();) {
			Variable variable = iterator.next();
			if (existVariableInParentNode(variable, e, m)) {
				result = result + ", " + variable.getType() + "Holder "
						+ variable.getName();
			}
		}
		return result;
	}

	public static String instantiateParams(Node e, Model m) {
		String result = new String();
		for (Iterator<Variable> iterator = e.getVariables().iterator(); iterator
				.hasNext();) {
			Variable variable = iterator.next();
			if (existVariableInParentNode(variable, e, m)) {
				result = result + "\t\t\tthis." + variable.getName() + " = "
						+ variable.getName() + ";";
			}
		}
		return result;
	}

	public static String addMessageTemplateVariable(Node e) {
		if (e.getName().startsWith("Receive")) {
			return "protected MessageTemplate mt = null;";
		}
		return "";
	}

	public static String importMessageClasses(Node e) {
		String result = new String();
		if ((e.getName().startsWith("Receive"))
				|| (e.getName().startsWith("Send"))) {
			result = result + "\n\timport jade.lang.acl.ACLMessage;";
		}
		if (e.getName().startsWith("Receive")) {
			result = result + "\n\timport jade.lang.acl.MessageTemplate;";
		}
		return result;
	}

	public static String addAction(Node e, Model m) {
		System.out.print("\nadding action for node with name: " + e.getName());
		String result = new String();
		if (e.getName().startsWith("Receive")) {
			for (Iterator<Transition> iterator = m.getTransitions().iterator(); iterator
					.hasNext();) {
				Transition tmp = iterator.next();
				if ((tmp.getSource().getLabel().equalsIgnoreCase(e.getLabel()))
						&& (!(tmp.getTarget().getLabel().equalsIgnoreCase(e
								.getLabel())))) {
					
					if (tmp.getTE()==null){
						result = result
								+ "\n\t\tACLMessage msg = myAgent.receive(mt);"
								+ "\n\t\tif (msg != null) {"
								+ "\n\t\t//insert message handling code"
								+ "\n\t\t\tfinished = true;" + "\n\t\t}else {"
								+ "\n\t\t\tblock();" + "\n\t\t}";
					}
					
					else{
						Pattern messagePattern = Pattern
								.compile("[a-z]+\\([a-z,]+\\)");
						Matcher messageMatcher = messagePattern
								.matcher(getEventOfExpression(tmp.getTE()));
						boolean firstPerformative = true;
						while (messageMatcher.find()) {
							String nextEvent = messageMatcher.group();
							if (firstPerformative) {
								firstPerformative = false;
								result = result
										+ "\t\tmt = MessageTemplate.MatchPerformative(ACLMessage."
										+ nextEvent.substring(0,
												nextEvent.indexOf("("))
												.toUpperCase() + ");";
							} else {
								result = result
										+ "\n\t\tmt = MessageTemplate.or(mt,MessageTemplate.MatchPerformative(ACLMessage."
										+ nextEvent.substring(0,
												nextEvent.indexOf("("))
												.toUpperCase() + "));";
							}
						}
						if (firstPerformative) {
							result = result
									+ "\t\t/*insert MessageTemplate code here*/";
						}
						result = result
								+ "\n\t\tACLMessage msg = myAgent.receive(mt);"
								+ "\n\t\tif (msg != null) {"
								+ "\n\t\t//insert message handling code"
								+ "\n\t\t\tfinished = true;" + "\n\t\t}else {"
								+ "\n\t\t\tblock();" + "\n\t\t}";
					}
					
					
				}
			}
		} else if (e.getName().startsWith("Send")) {
			for (Iterator<Transition> iterator = m.getTransitions().iterator(); iterator
					.hasNext();) {
				Transition tmp = iterator.next();
				if ((tmp.getSource().getLabel().equalsIgnoreCase(e.getLabel()))
						&& (!(tmp.getTarget().getLabel().equalsIgnoreCase(e
								.getLabel())))) {
					
					if (tmp.getTE()==null){
						result = result + "ACLMessage msg = null;"
								+ "\n\t\t//insert message initialization code"
								+ "\n\t\tmyAgent.send(msg);"
								+ "\n\t\tfinished = true;";
					}
					else{
						Pattern messagePattern = Pattern
								.compile("[a-z]+\\([a-z,]+\\)");
						Matcher messageMatcher = messagePattern
								.matcher(getEventOfExpression(tmp.getTE()));
						boolean firstPerformative = true;
						result = result + "ACLMessage msg = null;";
						while (messageMatcher.find()) {
							String nextEvent = messageMatcher.group();
							if (firstPerformative) {
								firstPerformative = false;
								result = result
										+ "\n\t\tif (/*insert condition*/) {"
										+ "\n\t\t\tmsg = new ACLMessage(ACLMessage."
										+ nextEvent.substring(0,
												nextEvent.indexOf("("))
												.toUpperCase() + ");" + "\n\t\t}";
							} else {
								result = result
										+ "\n\t\telse if (/*insert condition*/) {"
										+ "\n\t\t\tmsg = new ACLMessage(ACLMessage."
										+ nextEvent.substring(0,
												nextEvent.indexOf("("))
												.toUpperCase() + ");" + "\n\t\t}";
							}
						}
						result = result
								+ "\n\t\t//insert message initialization code"
								+ "\n\t\tmyAgent.send(msg);"
								+ "\n\t\tfinished = true;";
					}
					
					
				}
			}
		} else {
			if (e.getActivity() != null) {
				if (e.getActivity().startsWith("/*Java code*/")) {
					result = result + "\n\t\t" + e.getActivity().substring(13)
							+ "\n\t\tfinished = true;";
				} else {
					result = result
							+ "\n\t\t/*"
							+ (e.getActivity().equalsIgnoreCase("null") ? "insert behaviour activity code here"
									: e.getActivity()) + "*/"
							+ "\n\t\tfinished = true;";
				}
			} else {
				result = result
						+ "\n\t\t/*insert behaviour activity code here*/"
						+ "\n\t\tfinished = true;";
			}
		}
		return result;
	}

	public static Node getProtocolParentNode(String performative, Node e,
			Model m) {
		// TO DO define the attributes
		for (Iterator<Node> iterator = m.getNodes().iterator(); iterator
				.hasNext();) {
			Node node = iterator.next();
			if ((e.getLabel().startsWith(node.getLabel()))
					&& (e.getLabel().compareTo(node.getLabel()) != 0)) {
				for (Iterator<Variable> iterator2 = node.getVariables()
						.iterator(); iterator2.hasNext();) {
					Variable variable = iterator2.next();
					if (variable.getName().equalsIgnoreCase(performative)) {
						return node;
					}
				}
			}
		}
		return null;
	}

	public static boolean existVariableInParentNode(Variable var, Node e,
			Model m) {
		boolean result = false;
		// TO DO define the attributes
		EList<Node> tmp = m.getNodes();
		for (Iterator<Node> iterator = tmp.iterator(); iterator.hasNext();) {
			Node node = iterator.next();
			if ((e.getLabel().startsWith(node.getLabel()))
					&& (e.getLabel().compareTo(node.getLabel()) != 0)) {
				for (Iterator<Variable> iterator2 = node.getVariables()
						.iterator(); iterator2.hasNext();) {
					Variable variable = iterator2.next();
					if (variable.getName().equalsIgnoreCase(var.getName())) {
						return true;
					}
				}
			}
		}
		return result;
	}

	public static EList<Node> subNodesOf(Node e, Model m) {
		EList<Node> results = new BasicEList<Node>();
		for (Iterator<Node> iterator = NodeHelper.p.iterator(); iterator	// ComplexBehaviourHelper.nodeList.iterator()
				.hasNext();) {
			Node node = iterator.next();
			if ((node.getLabel().startsWith(e.getLabel()))
					&& (node.getLabel().length() == e.getLabel().length() + 2)) {
				results.add(node);
			}
		}
		return results;
	}

	public static EList<Node> subBehavioursOf(Node e, Model m) {
		EList<Node> results = new BasicEList<Node>();
		for (Iterator<Node> iterator = NodeHelper.p.iterator(); iterator
				.hasNext();) {
			Node node = iterator.next();
			if ((node.getLabel().startsWith(e.getLabel()))
					&& (node.getLabel().length() == e.getLabel().length() + 2)
					&& (node.getType().equalsIgnoreCase("AND")
							|| (node.getType().equalsIgnoreCase("OR")) || (node
							.getType().equalsIgnoreCase("BASIC")))) {
				results.add(node);
			}
		}
		return results;
	}

	public static EList<Node> sortSubNodes(EList<Node> list) {
		int n = list.size();
		boolean doMore = true;
		while (doMore) {
			n--;
			doMore = false; // assume this is our last pass over the array
			for (int i = 0; i < n; i++) {
				if (Integer.parseInt(list.get(i).getLabel().substring(
						list.get(i).getLabel().lastIndexOf(".") + 1,
						list.get(i).getLabel().length())) > Integer
						.parseInt(list.get(i + 1).getLabel()
								.substring(
										list.get(i + 1).getLabel().lastIndexOf(
												".") + 1,
										list.get(i + 1).getLabel().length()))) {
					// exchange elements
					list.move(i, i + 1);
					doMore = true; // after an exchange, must look again
				}
			}
		}
		return list;
	}

	public static String getConditionOfExpression(String expression) {
		if (expression == null)
			return null;
		else{
			// pattern for conditions
			Pattern conditionPattern = Pattern
					.compile("^([\\w\\W&&[^/\\[\\]]]+)?(\\[[\\w\\W&&[^\\[\\]]]+\\])(/[\\w\\W]+)?$");
			Matcher conditionMatcher = conditionPattern.matcher(expression);
			if (conditionMatcher.find()
					&& (conditionMatcher.group().length() == expression.length())) {
				StringTokenizer st = new StringTokenizer(expression, "]");
				String condition = st.nextToken();
				condition = condition.substring(condition.indexOf("[") + 1);
				return condition;
			}
		}
		
		return null;
	}

	public static String getEventOfExpression(String expression) {
		
		if (expression == null)
			return null;
		else{
			// pattern for events
			Pattern eventPattern = Pattern
					.compile("^[\\w\\W&&[^/\\[\\]]]+(\\[[\\w\\W&&[^\\[\\]]]+\\])?(/[\\w\\W]+)?$");
			Matcher eventMatcher = eventPattern.matcher(expression);
			if (eventMatcher.find()
					&& (eventMatcher.group().length() == expression.length())) {
				StringTokenizer st = new StringTokenizer(expression, "[]/");
				return st.nextToken();
			}
		}
		
		return null;
	}

	public static String getActionOfExpression(String expression) {
		
		if (expression == null)
			return null;
		else{
			// pattern for actions
			Pattern actionPattern = Pattern
					.compile("^([\\w\\W&&[^/\\[\\]]]+)?(\\[[\\w\\W&&[^\\[\\]]]+\\])?(/[\\w\\W]+)$");
			Matcher actionMatcher = actionPattern.matcher(expression);
			if (actionMatcher.find()
					&& (actionMatcher.group().length() == expression.length())) {
				String action = expression
						.substring(expression.lastIndexOf("/") + 1);
				return action;
			}
		}
		return null;
	}
}
