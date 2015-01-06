package asemedashboardview.views.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import statechart.StatechartPackage;
import AIP.AIPPackage;
import AIP.AIPmodel;
import IAC.IACFactory;
import IAC.IACPackage;
import IAC.Node;
import IAC.Transition;
import SRM.SRMPackage;
import SRM.SRMmodel;
import asemedashboardview.views.ASEMEAction;
import asemedashboardview.views.ASEMEFacade;
import asemedashboardview.views.ASEMEState;

public class TransformSRM2IACModelAction implements ASEMEAction {
	
	private ASEMEFacade context;
	String liveness = null;
	IAC.Model model = null;
	Hashtable<String, String> formulas = new Hashtable<String, String>();
	public static final String TYPE_OR = "OR";
	public static final String TYPE_AND = "AND";
	public static final String TYPE_START = "START";
	public static final String TYPE_END = "END";
	public static final String TYPE_CONDITION = "CONDITION";
	public static final String TYPE_BASIC = "BASIC";

	@Override
	public void init(ASEMEFacade context) {
		// TODO Auto-generated method stub
		this.context = context;

	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		ASEMEState state = context.getState();
		
		if(state.getSRM()==null) {
			return false;
		}
		return true;
	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ASEMEState state = context.getState(); 
		URI srm = state.getSRM();
		
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
		resourceSet.getPackageRegistry().put(SRMPackage.eNS_URI, SRMPackage.eINSTANCE);
		
		
		// load SRM model
					
		Resource resource = resourceSet.getResource(state.getSRM(), true);	
		SRMmodel srmModel = (SRMmodel) resource.getContents().get(0);
					
		//resource = resourceSet.createResource(URI
			//				.createURI(SRMPackage.eNS_URI));
		liveness = srmModel.getRoles().get(0).getLiveness();
		liveness = liveness.replaceAll(" ", "");
		transform();
		

	}

	public void transform() {
		
		ASEMEState state = context.getState();
		
		StringTokenizer line = new StringTokenizer(this.liveness, "\n");
		while (line.hasMoreTokens()) {
			String tmp = line.nextToken();
			StringTokenizer formulaElements = new StringTokenizer(tmp, "=");
			String leftHandSide = formulaElements.nextToken();
			String formula = formulaElements.nextToken();
			formulas.put(leftHandSide, formula);
		}
		line = new StringTokenizer(liveness, "\n");
		StringTokenizer formulaElements = new StringTokenizer(line.nextToken(),
				"=");
		String leftHandSide = formulaElements.nextToken();
		line = null;
		formulaElements = null;

		// Create a resource set to hold the resources.
		//
		ResourceSet resourceSet = new ResourceSetImpl();

		// Register the appropriate resource factory to handle all file
		// extensions.
		//
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());

		// Register the package to ensure it is available during loading.
		//
		resourceSet.getPackageRegistry().put(IACPackage.eNS_URI,
				IACPackage.eINSTANCE);

		Resource resource = resourceSet.createResource(URI
				.createURI("http:///My.iac"));
		model = IACFactory.eINSTANCE.createModel();
		model.setName("default_name");

		Node root = IACFactory.eINSTANCE.createNode();
		root.setLabel("0");
		root.setName(leftHandSide);
		root.setType("OR");
		model.getNodes().add(root);

		// call the createStatechart recursive process
		this.createStatechart(formulas.get(leftHandSide), root);
		resource.getContents().add(model);
		// create output
		
		IPath path = state.getProject().getLocation();
		
		try {
			FileOutputStream out = new FileOutputStream(
					new File(path + "/initialModel.iac").getAbsolutePath());
					//new File("initialModel.iac"));
			Map<String, Object> options = new HashMap<String, Object>();
		    options.put( XMLResource.OPTION_ENCODING, "UTF8" );
		    //resource.save(System.out, options);
			resource.save(out, options);
			 System.out.println("saved in: "+(new File(path + "/Goalie"+".iac").getAbsolutePath()));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void createStatechart(String expression, Node father) {
		// this integer will be used for selecting the connector for getting the
		// terms after the if section
		int expressionType = 0;
		// pattern for parallelExpression : expressionType=1
		Pattern patternParallelExpression = Pattern
				.compile("(((\\(.+\\))|([\\w&&[^()]])+)([ω+*]?)\\|\\|)+((\\(.+\\))|([\\w&&[^()]])+)([ω+*]?)");
		// .compile("(((\\(.+\\))|([\\w&&[^()]])+)(ω?)\\|\\|)+((\\(.+\\))|([\\w&&[^()]])+)(ω?)");
		// original
		Matcher parallelMatcher = patternParallelExpression.matcher(expression);
		// pattern for orExpression : expressionType=2
		Pattern patternOrExpression = Pattern
				.compile("(((\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([ω+*]?)\\|)+((\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([ω+*]?)");
		Matcher orMatcher = patternOrExpression.matcher(expression);
		// pattern for sequentialExpression : expressionType=3
		Pattern patternSequentialExpression = Pattern
				.compile("(((\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([ω+*]?)\\.)+((\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([ω+*]?)");
		// .compile("(((\\(.+\\))|([\\w&&[^()]])+)(ω?)\\.)+((\\(.+\\))|([\\w&&[^()]])+)(ω?)");
		// original
		Matcher sequentialMatcher = patternSequentialExpression
				.matcher(expression);
		Node tmpNode;
		Transition tmpTransition;
		if (sequentialMatcher.find()
				&& (sequentialMatcher.group().length() == expression.length())) {
			expressionType = 3;
			System.out.print("a sequential expression processed: " + expression
					+ "\n");
			father.setType(TYPE_OR);
			tmpNode = IACFactory.eINSTANCE.createNode();
			tmpNode.setLabel(father.getLabel() + ".1");
			tmpNode.setName(tmpNode.getLabel());
			tmpNode.setType(TYPE_START);
			tmpNode.getFather_of().add(father);
			model.getNodes().add(tmpNode);
			int k = 2;
			for (Iterator<String> iterator = this.findTermsInExpression(
					expression, ".").iterator(); iterator.hasNext();) {
				String term = iterator.next();
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(father.getLabel() + "." + k);
				tmpNode.setName(this.computeNodeName(term));
				tmpNode.getFather_of().add(father);
				model.getNodes().add(tmpNode);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(father
						.getLabel()
						+ "." + (k - 1))));
				tmpTransition.setTarget(getNodeByLabel(new String(father
						.getLabel()
						+ "." + k)));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
				k = k + 1;
			}
			tmpNode = IACFactory.eINSTANCE.createNode();
			tmpNode.setLabel(father.getLabel() + "." + k);
			tmpNode.setName(tmpNode.getLabel());
			tmpNode.setType(TYPE_END);
			tmpNode.getFather_of().add(father);
			model.getNodes().add(tmpNode);
			tmpTransition = IACFactory.eINSTANCE.createTransition();
			tmpTransition.setSource(getNodeByLabel(new String(father.getLabel()
					+ "." + (k - 1))));
			tmpTransition.setTarget(getNodeByLabel(new String(father.getLabel()
					+ "." + k)));
			tmpTransition.setName(tmpTransition.getSource().getName() + "TO"
					+ tmpTransition.getTarget().getName());
			model.getTransitions().add(tmpTransition);
		} else if (orMatcher.find()
				&& (orMatcher.group().length() == expression.length())) {
			expressionType = 2;
			System.out
					.print("an or expression processed: " + expression + "\n");
			father.setType(TYPE_OR);
			tmpNode = IACFactory.eINSTANCE.createNode();
			tmpNode.setLabel(father.getLabel() + ".1");
			tmpNode.setName(tmpNode.getLabel());
			tmpNode.setType(TYPE_START);
			tmpNode.getFather_of().add(father);
			model.getNodes().add(tmpNode);
			tmpNode = IACFactory.eINSTANCE.createNode();
			tmpNode.setLabel(father.getLabel() + ".2");
			tmpNode.setName(tmpNode.getLabel());
			tmpNode.setType(TYPE_CONDITION);
			tmpNode.getFather_of().add(father);
			model.getNodes().add(tmpNode);
			tmpTransition = IACFactory.eINSTANCE.createTransition();
			tmpTransition.setSource(getNodeByLabel(new String(father.getLabel()
					+ ".1")));
			tmpTransition.setTarget(getNodeByLabel(new String(father.getLabel()
					+ ".2")));
			tmpTransition.setName(tmpTransition.getSource().getName() + "TO"
					+ tmpTransition.getTarget().getName());
			model.getTransitions().add(tmpTransition);
			int k = 3;
			for (Iterator<String> iterator = this.findTermsInExpression(
					expression, "|").iterator(); iterator.hasNext();) {
				// the above line creates a bag if there are parallel operators
				// in the or expression
				String term = iterator.next();
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(father.getLabel() + "." + k);
				tmpNode.setName(this.computeNodeName(term));
				tmpNode.getFather_of().add(father);
				model.getNodes().add(tmpNode);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(father
						.getLabel()
						+ ".2")));
				tmpTransition.setTarget(getNodeByLabel(new String(father
						.getLabel()
						+ "." + k)));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
				k = k + 1;
			}
			tmpNode = IACFactory.eINSTANCE.createNode();
			tmpNode.setLabel(father.getLabel() + "." + k);
			tmpNode.setName(tmpNode.getLabel());
			tmpNode.setType(TYPE_END);
			tmpNode.getFather_of().add(father);
			model.getNodes().add(tmpNode);
			int tmpCounter = k;
			for (k = tmpCounter - 1; k > 2; k--) {
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(father
						.getLabel()
						+ "." + k)));
				tmpTransition.setTarget(getNodeByLabel(new String(father
						.getLabel()
						+ "." + tmpCounter)));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
			}
		} else if (parallelMatcher.find()
				&& (parallelMatcher.group().length() == expression.length())) {
			expressionType = 1;
			System.out.print("a parallel expression processed: " + expression
					+ "\n");
			father.setType(TYPE_OR);
			tmpNode = IACFactory.eINSTANCE.createNode();
			tmpNode.setLabel(father.getLabel() + ".1");
			tmpNode.setName(tmpNode.getLabel());
			tmpNode.setType(TYPE_START);
			tmpNode.getFather_of().add(father);
			model.getNodes().add(tmpNode);
			tmpNode = IACFactory.eINSTANCE.createNode();
			tmpNode.setLabel(father.getLabel() + ".2");
			// if not for the following line all AND states are not transformed
			// to JADE behaviours
			tmpNode.setName(this.computeNodeName(expression));
			tmpNode.setType(TYPE_AND);
			tmpNode.getFather_of().add(father);
			model.getNodes().add(tmpNode);
			tmpTransition = IACFactory.eINSTANCE.createTransition();
			tmpTransition.setSource(getNodeByLabel(new String(father.getLabel()
					+ ".1")));
			tmpTransition.setTarget(getNodeByLabel(new String(father.getLabel()
					+ ".2")));
			tmpTransition.setName(tmpTransition.getSource().getName() + "TO"
					+ tmpTransition.getTarget().getName());
			model.getTransitions().add(tmpTransition);
			tmpNode = IACFactory.eINSTANCE.createNode();
			tmpNode.setLabel(father.getLabel() + ".3");
			tmpNode.setName(tmpNode.getLabel());
			tmpNode.setType(TYPE_END);
			tmpNode.getFather_of().add(father);
			model.getNodes().add(tmpNode);
			tmpTransition = IACFactory.eINSTANCE.createTransition();
			tmpTransition.setSource(getNodeByLabel(new String(father.getLabel()
					+ ".2")));
			tmpTransition.setTarget(getNodeByLabel(new String(father.getLabel()
					+ ".3")));
			tmpTransition.setName(tmpTransition.getSource().getName() + "TO"
					+ tmpTransition.getTarget().getName());
			model.getTransitions().add(tmpTransition);
			int k = 1;
			for (Iterator<String> iterator = this.findTermsInExpression(
					expression, "||").iterator(); iterator.hasNext();) {
				String term = iterator.next();
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(father.getLabel() + ".2." + k);
				tmpNode.setName(tmpNode.getLabel());
				tmpNode.setType(TYPE_OR);
				tmpNode.getFather_of().add(
						getNodeByLabel(new String(father.getLabel() + ".2")));
				model.getNodes().add(tmpNode);
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(father.getLabel() + ".2." + k + ".1");
				tmpNode.setName(tmpNode.getLabel());
				tmpNode.setType(TYPE_START);
				tmpNode.getFather_of()
						.add(
								getNodeByLabel(new String(father.getLabel()
										+ ".2." + k)));
				model.getNodes().add(tmpNode);
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(father.getLabel() + ".2." + k + ".2");
				tmpNode.setName(this.computeNodeName(term));
				tmpNode.getFather_of()
						.add(
								getNodeByLabel(new String(father.getLabel()
										+ ".2." + k)));
				model.getNodes().add(tmpNode);
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(father.getLabel() + ".2." + k + ".3");
				tmpNode.setName(tmpNode.getLabel());
				tmpNode.setType(TYPE_END);
				tmpNode.getFather_of()
						.add(
								getNodeByLabel(new String(father.getLabel()
										+ ".2." + k)));
				model.getNodes().add(tmpNode);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(father
						.getLabel()
						+ ".2." + k + ".1")));
				tmpTransition.setTarget(getNodeByLabel(new String(father
						.getLabel()
						+ ".2." + k + ".2")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(father
						.getLabel()
						+ ".2." + k + ".2")));
				tmpTransition.setTarget(getNodeByLabel(new String(father
						.getLabel()
						+ ".2." + k + ".3")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
				k = k + 1;
			}
		}
		List<String> myTerms = null;
		switch (expressionType) {
		case 0:
			myTerms = new LinkedList<String>();
			myTerms.add(expression);
			break;
		case 1:
			myTerms = this.findTermsInExpression(expression, "||");
			break;
		case 2:
			myTerms = this.findTermsInExpression(expression, "|");
			break;
		case 3:
			myTerms = this.findTermsInExpression(expression, ".");
			break;
		}
		for (Iterator<String> iterator = myTerms.iterator(); iterator.hasNext();) {
			String term = iterator.next();
			// pattern for basicTerm
			Pattern patternBasicTerm = Pattern.compile("^\\w+$");
			Matcher basicTermMatcher = patternBasicTerm.matcher(term);
			// pattern for (term)
			Pattern patternComplexParenthesisTerm = Pattern
					.compile("^\\(.+\\)$");
			Matcher complexParenthesisTermMatcher = patternComplexParenthesisTerm
					.matcher(term);
			// pattern for [term]
			Pattern patternComplexOptionalTerm = Pattern.compile("^\\[.+\\]$");
			Matcher complexOptionalTermMatcher = patternComplexOptionalTerm
					.matcher(term);
			// pattern for termω
			Pattern patternForeverTerm = Pattern.compile(".+ω$");
			Matcher foreverTermMatcher = patternForeverTerm.matcher(term);
			// pattern for term+
			Pattern patternOneOrMoreTimesTerm = Pattern.compile(".+\\+$");
			Matcher oneOrMoreTimesTermMatcher = patternOneOrMoreTimesTerm
					.matcher(term);
			// pattern for term*
			Pattern patternZeroOrMoreTimesTerm = Pattern.compile(".+\\*$");
			Matcher zeroOrMoreTimesTermMatcher = patternZeroOrMoreTimesTerm
					.matcher(term);
			// pattern for |termω|n
			Pattern patternParallelManyTimesTerm = Pattern
					.compile("^\\|.+(ω\\|(\\d)+)$");
			Matcher parallelManyTimesTermMatcher = patternParallelManyTimesTerm
					.matcher(term);
			if (complexParenthesisTermMatcher.find()
					&& (complexParenthesisTermMatcher.group().length() == term
							.length())) {
				this.createStatechart(term.substring(1, term.length() - 1),
						this.getNode(father, term));
			} else if (complexOptionalTermMatcher.find()
					&& (complexOptionalTermMatcher.group().length() == term
							.length())) {
				Node tmpFather = this.getNode(father, term);
				if (tmpFather == null) tmpFather = father;
				tmpFather.setType(TYPE_OR);
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(tmpFather.getLabel() + ".1");
				tmpNode.setName(tmpNode.getLabel());
				tmpNode.setType(TYPE_START);
				tmpNode.getFather_of().add(tmpFather);
				model.getNodes().add(tmpNode);
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(tmpFather.getLabel() + ".2");
				tmpNode.setName(tmpNode.getLabel());
				tmpNode.setType(TYPE_CONDITION);
				tmpNode.getFather_of().add(tmpFather);
				model.getNodes().add(tmpNode);
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(tmpFather.getLabel() + ".3");
				String insideTerm = term.substring(1, term.length() - 1);
				tmpNode.setName(this.computeNodeName(insideTerm));
				tmpNode.getFather_of().add(tmpFather);
				model.getNodes().add(tmpNode);
				basicTermMatcher = patternBasicTerm.matcher(insideTerm);
				if (basicTermMatcher.find()
						&& (basicTermMatcher.group().length() == insideTerm
								.length())) {
					this.handleBasicTerm(insideTerm, tmpNode);
				} else {
					this.createStatechart(insideTerm, tmpNode);
				}
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(tmpFather.getLabel() + ".4");
				tmpNode.setName(tmpNode.getLabel());
				tmpNode.setType(TYPE_END);
				tmpNode.getFather_of().add(tmpFather);
				model.getNodes().add(tmpNode);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".1")));
				tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".2")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".2")));
				tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".3")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".2")));
				tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".4")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".3")));
				tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".4")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
			} else if (zeroOrMoreTimesTermMatcher.find()
					&& (zeroOrMoreTimesTermMatcher.group().length() == term
							.length())) {
				Node tmpFather = this.getNode(father, term);
				if (tmpFather == null) tmpFather = father;
				tmpFather.setType(TYPE_OR);
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(tmpFather.getLabel() + ".1");
				tmpNode.setName(tmpNode.getLabel());
				tmpNode.setType(TYPE_START);
				tmpNode.getFather_of().add(tmpFather);
				model.getNodes().add(tmpNode);
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(tmpFather.getLabel() + ".2");
				tmpNode.setName(tmpNode.getLabel());
				tmpNode.setType(TYPE_CONDITION);
				tmpNode.getFather_of().add(tmpFather);
				model.getNodes().add(tmpNode);
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(tmpFather.getLabel() + ".3");
				String insideTerm = term.substring(0, term.length() - 1);
				tmpNode.setName(this.computeNodeName(insideTerm));
				tmpNode.getFather_of().add(tmpFather);
				model.getNodes().add(tmpNode);
				basicTermMatcher = patternBasicTerm.matcher(insideTerm);
				if (basicTermMatcher.find()
						&& (basicTermMatcher.group().length() == insideTerm
								.length())) {
					this.handleBasicTerm(insideTerm, tmpNode);
				} else {
					this.createStatechart(insideTerm, tmpNode);
				}
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(tmpFather.getLabel() + ".4");
				tmpNode.setName(tmpNode.getLabel());
				tmpNode.setType(TYPE_END);
				tmpNode.getFather_of().add(tmpFather);
				model.getNodes().add(tmpNode);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".1")));
				tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".2")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".2")));
				tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".3")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".3")));
				tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".3")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".2")));
				tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".4")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".3")));
				tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".4")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
			} else if (foreverTermMatcher.find()
					&& (foreverTermMatcher.group().length() == term.length())) {
				Node tmpFather = this.getNode(father, term);
				if (tmpFather == null) tmpFather = father;
				tmpFather.setType(TYPE_OR);
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(tmpFather.getLabel() + ".1");
				tmpNode.setName(tmpNode.getLabel());
				tmpNode.setType(TYPE_START);
				tmpNode.getFather_of().add(tmpFather);
				model.getNodes().add(tmpNode);
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(tmpFather.getLabel() + ".2");
				String insideTerm = term.substring(0, term.length() - 1);
				tmpNode.setName(this.computeNodeName(insideTerm));
				tmpNode.getFather_of().add(tmpFather);
				model.getNodes().add(tmpNode);
				basicTermMatcher = patternBasicTerm.matcher(insideTerm);
				if (basicTermMatcher.find()
						&& (basicTermMatcher.group().length() == insideTerm
								.length())) {
					this.handleBasicTerm(insideTerm, tmpNode);
				} else {
					this.createStatechart(insideTerm, tmpNode);
				}
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".1")));
				tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".2")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".2")));
				tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".2")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
			} else if (oneOrMoreTimesTermMatcher.find()
					&& (oneOrMoreTimesTermMatcher.group().length() == term
							.length())) {
				Node tmpFather = this.getNode(father, term);
				if (tmpFather == null) tmpFather = father;
				tmpFather.setType(TYPE_OR);
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(tmpFather.getLabel() + ".1");
				tmpNode.setName(tmpNode.getLabel());
				tmpNode.setType(TYPE_START);
				tmpNode.getFather_of().add(tmpFather);
				model.getNodes().add(tmpNode);
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(tmpFather.getLabel() + ".2");
				String insideTerm = term.substring(0, term.length() - 1);
				tmpNode.setName(this.computeNodeName(insideTerm));
				tmpNode.getFather_of().add(tmpFather);
				model.getNodes().add(tmpNode);
				basicTermMatcher = patternBasicTerm.matcher(insideTerm);
				if (basicTermMatcher.find()
						&& (basicTermMatcher.group().length() == insideTerm
								.length())) {
					this.handleBasicTerm(insideTerm, tmpNode);
				} else {
					this.createStatechart(insideTerm, tmpNode);
				}
				tmpNode = IACFactory.eINSTANCE.createNode();
				tmpNode.setLabel(tmpFather.getLabel() + ".3");
				tmpNode.setName(tmpNode.getLabel());
				tmpNode.setType(TYPE_END);
				tmpNode.getFather_of().add(tmpFather);
				model.getNodes().add(tmpNode);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".1")));
				tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".2")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".2")));
				tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".2")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);
				tmpTransition = IACFactory.eINSTANCE.createTransition();
				tmpTransition.setSource(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".2")));
				tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
						.getLabel()
						+ ".3")));
				tmpTransition.setName(tmpTransition.getSource().getName()
						+ "TO" + tmpTransition.getTarget().getName());
				model.getTransitions().add(tmpTransition);

			} else if (parallelManyTimesTermMatcher.find()
					&& (parallelManyTimesTermMatcher.group().length() == term
							.length())) {
				Node tmpFather = this.getNode(father, term);
				if (tmpFather == null) tmpFather = father;
				tmpFather.setType(TYPE_AND);
				for (int k = 1; k <= Integer.parseInt(term.substring(term
						.lastIndexOf("|") + 1, term.length())); k++) {
					tmpNode = IACFactory.eINSTANCE.createNode();
					tmpNode.setLabel(tmpFather.getLabel() + "." + k);
					tmpNode.setName(tmpNode.getLabel());
					tmpNode.setType(TYPE_OR);
					tmpNode.getFather_of().add(tmpFather);
					model.getNodes().add(tmpNode);
					tmpNode = IACFactory.eINSTANCE.createNode();
					tmpNode.setLabel(tmpFather.getLabel() + "." + k + ".1");
					tmpNode.setName(tmpNode.getLabel());
					tmpNode.setType(TYPE_START);
					tmpNode.getFather_of().add(
							getNodeByLabel(tmpFather.getLabel() + "." + k));
					model.getNodes().add(tmpNode);
					tmpNode = IACFactory.eINSTANCE.createNode();
					tmpNode.setLabel(tmpFather.getLabel() + "." + k + ".2");
					String insideTerm = term.substring(term.indexOf("|") + 1,
							term.lastIndexOf("ω"));
					tmpNode.setName(this.computeNodeName(insideTerm));
					tmpNode.getFather_of().add(
							getNodeByLabel(tmpFather.getLabel() + "." + k));
					model.getNodes().add(tmpNode);
					basicTermMatcher = patternBasicTerm.matcher(insideTerm);
					if (basicTermMatcher.find()
							&& (basicTermMatcher.group().length() == insideTerm
									.length())) {
						this.handleBasicTerm(insideTerm, tmpNode);
						this.handleBasicTerm(insideTerm,
								getNodeByLabel(getNode(father, term).getLabel()
										+ "." + k + ".2"));
					} else {
						this.createStatechart(insideTerm,
								getNodeByLabel(getNode(father, term).getLabel()
										+ "." + k + ".2"));
					}
					tmpNode = IACFactory.eINSTANCE.createNode();
					tmpNode.setLabel(tmpFather.getLabel() + "." + k + ".3");
					tmpNode.setName(tmpNode.getLabel());
					tmpNode.setType(TYPE_END);
					tmpNode.getFather_of().add(
							getNodeByLabel(tmpFather.getLabel() + "." + k));
					model.getNodes().add(tmpNode);

					tmpTransition = IACFactory.eINSTANCE.createTransition();
					tmpTransition.setSource(getNodeByLabel(new String(tmpFather
							.getLabel()
							+ "." + k + ".1")));
					tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
							.getLabel()
							+ "." + k + ".2")));
					tmpTransition.setName(tmpTransition.getSource().getName()
							+ "TO" + tmpTransition.getTarget().getName());
					model.getTransitions().add(tmpTransition);
					tmpTransition = IACFactory.eINSTANCE.createTransition();
					tmpTransition.setSource(getNodeByLabel(new String(tmpFather
							.getLabel()
							+ "." + k + ".2")));
					tmpTransition.setTarget(getNodeByLabel(new String(tmpFather
							.getLabel()
							+ "." + k + ".3")));
					tmpTransition.setName(tmpTransition.getSource().getName()
							+ "TO" + tmpTransition.getTarget().getName());
					model.getTransitions().add(tmpTransition);
				}
			} else if (basicTermMatcher.find()
					&& (basicTermMatcher.group().length() == term.length())) {
				this.handleBasicTerm(term, getNode(father, term));
			}

		}
	}

	public String parent(String node) {
		return (node.substring(0, node.lastIndexOf(".")));
	}

	public void handleBasicTerm(String term, Node node) {
		boolean isBasic = true;
		if (formulas.containsKey(term)) {
			node.setType(TYPE_OR);
			this.createStatechart(formulas.get(term), node);
			isBasic = false;
		}
		if (isBasic) {
			node.setType(TYPE_BASIC);
		}
	}

	public String computeNodeName(String term) {
		String nodeName = new String(term);
		nodeName = nodeName.replaceAll("\\|\\|", "_parallel_");
		nodeName = nodeName.replaceAll("ω", "_forever_");
		nodeName = nodeName.replaceAll("\\.", "_sequence_");
		nodeName = nodeName.replaceAll("\\|", "_or_");
		nodeName = nodeName.replaceAll("\\*", "_zero_or_more_times_");
		nodeName = nodeName.replaceAll("\\+", "_one_or_more_times_");
		nodeName = nodeName.replaceAll("\\(", "_open_group_");
		nodeName = nodeName.replaceAll("\\)", "_close_group_");
		nodeName = nodeName.replaceAll("\\[", "_open_option_");
		nodeName = nodeName.replaceAll("\\]", "_close_option_");
		return nodeName;
	}

	public Node getNodeByLabel(String label) {
		for (Iterator<Node> iterator = model.getNodes().iterator(); iterator
				.hasNext();) {
			Node tmpNode = iterator.next();
			if (tmpNode.getLabel().equalsIgnoreCase(label))
				return tmpNode;
		}
		return null;
	}

	public Node getNode(Node father, String term) {
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.addLast(father);
		while (queue.size() > 0) {
			Node first = queue.removeFirst();
			if (first.getName().equalsIgnoreCase(this.computeNodeName(term))) {
				return first;
			} else {
				for (Iterator<Node> iterator = model.getNodes().iterator(); iterator
						.hasNext();) {
					Node prospectiveSon = iterator.next();
					if ((prospectiveSon.getFather_of().size() > 0)
							&& (prospectiveSon.getFather_of().get(0).getLabel()
									.equalsIgnoreCase(first.getLabel())))
						queue.addLast(prospectiveSon);
				}
			}
		}
		return null;
	}

	public List<String> findTermsInExpression(String expression,
			String connector) {
		List<String> foundTerms = new LinkedList<String>();
		StringTokenizer t = new StringTokenizer(expression, connector);
		String currentTerm = new String();
		while (t.hasMoreTokens()) {
			int parenthesis = 0;
			int brackets = 0;
			currentTerm = currentTerm + t.nextToken();
			for (int i = 0; i < currentTerm.length(); i++) {
				if (currentTerm.regionMatches(i, "(", 0, 1))
					parenthesis++;
				if (currentTerm.regionMatches(i, ")", 0, 1))
					parenthesis--;
				if (currentTerm.regionMatches(i, "[", 0, 1))
					brackets++;
				if (currentTerm.regionMatches(i, "]", 0, 1))
					brackets--;
			}
			if ((parenthesis == 0) && (brackets == 0)) {
				foundTerms.add(currentTerm);
				System.out.print("found term: " + currentTerm + "\n");
				currentTerm = new String();
			} else
				currentTerm = currentTerm + connector;
		}
		return foundTerms;
	}
	
}
