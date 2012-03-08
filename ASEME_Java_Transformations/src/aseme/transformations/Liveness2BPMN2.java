package aseme.transformations;

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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import bpmn.Activity;
import bpmn.ActivityType;
import bpmn.BpmnFactory;
import bpmn.BpmnPackage;
import bpmn.Graph;
import bpmn.Pool;
import bpmn.SequenceEdge;
import bpmn.SubProcess;
import bpmn.Vertex;

public class Liveness2BPMN2 {

	String liveness = null;
	Hashtable<String, String> formulas = new Hashtable<String, String>();

	bpmn.BpmnDiagram model = null;

	public static void main(String[] args) {
		Liveness2BPMN2 liveness2BPMN = new Liveness2BPMN2();
		if (args.length == 0) {
			liveness2BPMN.liveness = new String(
//					"PersonalAssistant = SR\n"
//				+"SR = SendRequestMessage.ReceiveResponseMessage");
					
//			"Broker=SPù\n"
//+"SP=ReceiveRequestMessage.ProcessRequest.SendResponseMessage\n"
//+"ProcessRequest=ServiceMatch.[(InvokeDataManagement|SR)]\n"
//+"SR=SendRequestMessage.ReceiveResponseMessage"

"ComplexProvider=SPù\n"
+"SP=ReceiveRequestMessage.ProcessRequest.SendResponseMessage\n"
+"ProcessRequest=(DecideRouteType.SR.SortRoutes)|(DecidePOITypes.SR.DecidePois. SR)\n"
+"SR=SendRequestMessage.ReceiveResponseMessage"


);

		} else {
			liveness2BPMN.liveness = args[0];
		}
		liveness2BPMN.liveness = liveness2BPMN.liveness.replaceAll(" ", "");
		liveness2BPMN.transform();
	}

	public void transform() {
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
		resourceSet
				.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());

		// Register the package to ensure it is available during loading.
		//
		resourceSet.getPackageRegistry().put(BpmnPackage.eNS_URI,
				BpmnPackage.eINSTANCE);

		Resource resource = resourceSet.createResource(URI
				.createURI("http:///My.bpmn"));
		model = BpmnFactory.eINSTANCE.createBpmnDiagram();
		addToResourceAndAssignXmlId(model, resource);
		Pool pool = BpmnFactory.eINSTANCE.createPool();
		addToResourceAndAssignXmlId(pool, resource);
		model.getPools().add(pool);

		// call the createStatechart recursive process
		this.createProcess(formulas.get(leftHandSide), pool, resource);
		//resource.getContents().add(model);
		// create output
		try {
			FileOutputStream out = new FileOutputStream(new File(
					"initialModel.bpmn"));
			Map<String, Object> options = new HashMap<String, Object>();
		    options.put( XMLResource.OPTION_ENCODING, "UTF8" );
	        options.put( XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
	        options.put( XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION , Boolean.TRUE);
			resource.save(out, options);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void createProcess(String expression, Graph subProcess, Resource resource) {
		// this integer will be used for selecting the connector for getting the
		// terms after the if section
		int expressionType = 0;
		// pattern for parallelExpression : expressionType=1
		Pattern patternParallelExpression = Pattern
				.compile("(((\\(.+\\))|([\\w&&[^()]])+)([ù+*]?)\\|\\|)+((\\(.+\\))|([\\w&&[^()]])+)([ù+*]?)");
		// .compile("(((\\(.+\\))|([\\w&&[^()]])+)(ù?)\\|\\|)+((\\(.+\\))|([\\w&&[^()]])+)(ù?)");
		// original
		Matcher parallelMatcher = patternParallelExpression.matcher(expression);
		// pattern for orExpression : expressionType=2
		Pattern patternOrExpression = Pattern
				.compile("(((\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([ù+*]?)\\|)+((\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([ù+*]?)");
		Matcher orMatcher = patternOrExpression.matcher(expression);
		// pattern for sequentialExpression : expressionType=3
		Pattern patternSequentialExpression = Pattern
				.compile("(((\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([ù+*]?)\\.)+((\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([ù+*]?)");
		// .compile("(((\\(.+\\))|([\\w&&[^()]])+)(ù?)\\.)+((\\(.+\\))|([\\w&&[^()]])+)(ù?)");
		// original
		Matcher sequentialMatcher = patternSequentialExpression
				.matcher(expression);
		Activity tmpActivity = null;
		SequenceEdge tmpEdge = null;
		if (sequentialMatcher.find()
				&& (sequentialMatcher.group().length() == expression.length())) {
			expressionType = 3;
			System.out.print("a sequential expression processed: " + expression
					+ "\n");

			tmpActivity = BpmnFactory.eINSTANCE.createActivity();
			addToResourceAndAssignXmlId(tmpActivity, resource);
			tmpActivity.setActivityType(ActivityType.EVENT_START_EMPTY);
			tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
			addToResourceAndAssignXmlId(tmpEdge, resource);
			tmpActivity.getOutgoingEdges().add(tmpEdge);
			subProcess.getSequenceEdges().add(tmpEdge);
			subProcess.getVertices().add(tmpActivity);
			int k = 2;
			for (Iterator<String> iterator = this.findTermsInExpression(
					expression, ".").iterator(); iterator.hasNext();) {
				String term = iterator.next();
				tmpActivity = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity, resource);
				tmpActivity.getIncomingEdges().add(tmpEdge);
				tmpActivity.setName(this.computeNodeName(term));
				tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge, resource);
				tmpActivity.getOutgoingEdges().add(tmpEdge);
				subProcess.getSequenceEdges().add(tmpEdge);
				subProcess.getVertices().add(tmpActivity);
				k = k + 1;
			}
			tmpActivity = BpmnFactory.eINSTANCE.createActivity();
			addToResourceAndAssignXmlId(tmpActivity, resource);
			tmpActivity.getIncomingEdges().add(tmpEdge);
			tmpActivity.setActivityType(ActivityType.EVENT_END_EMPTY);
			subProcess.getVertices().add(tmpActivity);
		} else if (orMatcher.find()
				&& (orMatcher.group().length() == expression.length())) {
			expressionType = 2;
			System.out
					.print("an or expression processed: " + expression + "\n");

			Activity tmpActivity1 = BpmnFactory.eINSTANCE.createActivity();
			addToResourceAndAssignXmlId(tmpActivity1, resource);
			tmpActivity1.setActivityType(ActivityType.EVENT_START_EMPTY);
			tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
			addToResourceAndAssignXmlId(tmpEdge, resource);
			tmpActivity1.getOutgoingEdges().add(tmpEdge);
			subProcess.getSequenceEdges().add(tmpEdge);
			subProcess.getVertices().add(tmpActivity1);
			tmpActivity1 = BpmnFactory.eINSTANCE.createActivity();
			addToResourceAndAssignXmlId(tmpActivity1, resource);
			tmpActivity1
					.setActivityType(ActivityType.GATEWAY_DATA_BASED_EXCLUSIVE);
			tmpActivity1.getIncomingEdges().add(tmpEdge);
			subProcess.getVertices().add(tmpActivity1);

			Activity tmpActivity2 = BpmnFactory.eINSTANCE.createActivity();
			addToResourceAndAssignXmlId(tmpActivity2, resource);
			tmpActivity2.setActivityType(ActivityType.EVENT_END_EMPTY);
			tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
			addToResourceAndAssignXmlId(tmpEdge, resource);
			tmpActivity2.getIncomingEdges().add(tmpEdge);
			subProcess.getSequenceEdges().add(tmpEdge);
			subProcess.getVertices().add(tmpActivity2);
			tmpActivity2 = BpmnFactory.eINSTANCE.createActivity();
			addToResourceAndAssignXmlId(tmpActivity2, resource);
			tmpActivity2
					.setActivityType(ActivityType.GATEWAY_DATA_BASED_EXCLUSIVE);
			tmpActivity2.getOutgoingEdges().add(tmpEdge);
			subProcess.getVertices().add(tmpActivity2);

			int k = 2;
			for (Iterator<String> iterator = this.findTermsInExpression(
					expression, "|").iterator(); iterator.hasNext();) {
				String term = iterator.next();

				tmpActivity = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity, resource);
				tmpActivity.setName(this.computeNodeName(term));
				subProcess.getVertices().add(tmpActivity);

				tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge, resource);
				tmpActivity.getIncomingEdges().add(tmpEdge);
				tmpActivity1.getOutgoingEdges().add(tmpEdge);
				subProcess.getSequenceEdges().add(tmpEdge);

				tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge, resource);
				tmpActivity.getOutgoingEdges().add(tmpEdge);
				tmpActivity2.getIncomingEdges().add(tmpEdge);
				subProcess.getSequenceEdges().add(tmpEdge);
				k = k + 1;
			}
		} else if (parallelMatcher.find()
				&& (parallelMatcher.group().length() == expression.length())) {
			expressionType = 1;
			System.out.print("a parallel expression processed: " + expression
					+ "\n");

			Activity tmpActivity1 = BpmnFactory.eINSTANCE.createActivity();
			addToResourceAndAssignXmlId(tmpActivity1, resource);
			tmpActivity1.setActivityType(ActivityType.EVENT_START_EMPTY);
			tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
			addToResourceAndAssignXmlId(tmpEdge, resource);
			tmpActivity1.getOutgoingEdges().add(tmpEdge);
			subProcess.getSequenceEdges().add(tmpEdge);
			subProcess.getVertices().add(tmpActivity1);
			tmpActivity1 = BpmnFactory.eINSTANCE.createActivity();
			addToResourceAndAssignXmlId(tmpActivity1, resource);
			tmpActivity1.setActivityType(ActivityType.GATEWAY_PARALLEL);
			tmpActivity1.getIncomingEdges().add(tmpEdge);
			subProcess.getVertices().add(tmpActivity1);

			Activity tmpActivity2 = BpmnFactory.eINSTANCE.createActivity();
			addToResourceAndAssignXmlId(tmpActivity2, resource);
			tmpActivity2.setActivityType(ActivityType.EVENT_END_EMPTY);
			tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
			addToResourceAndAssignXmlId(tmpEdge, resource);
			tmpActivity2.getIncomingEdges().add(tmpEdge);
			subProcess.getSequenceEdges().add(tmpEdge);
			subProcess.getVertices().add(tmpActivity2);
			tmpActivity2 = BpmnFactory.eINSTANCE.createActivity();
			addToResourceAndAssignXmlId(tmpActivity2, resource);
			tmpActivity2.setActivityType(ActivityType.GATEWAY_PARALLEL);
			tmpActivity2.getOutgoingEdges().add(tmpEdge);
			subProcess.getVertices().add(tmpActivity2);

			int k = 2;
			for (Iterator<String> iterator = this.findTermsInExpression(
					expression, "|").iterator(); iterator.hasNext();) {
				String term = iterator.next();

				tmpActivity = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity, resource);
				tmpActivity.setName(this.computeNodeName(term));
				subProcess.getVertices().add(tmpActivity);

				tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge, resource);
				tmpActivity.getIncomingEdges().add(tmpEdge);
				tmpActivity1.getOutgoingEdges().add(tmpEdge);
				subProcess.getSequenceEdges().add(tmpEdge);

				tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge, resource);
				tmpActivity.getOutgoingEdges().add(tmpEdge);
				tmpActivity2.getIncomingEdges().add(tmpEdge);
				subProcess.getSequenceEdges().add(tmpEdge);
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
			// pattern for termù
			Pattern patternForeverTerm = Pattern.compile(".+ù$");
			Matcher foreverTermMatcher = patternForeverTerm.matcher(term);
			// pattern for term+
			Pattern patternOneOrMoreTimesTerm = Pattern.compile(".+\\+$");
			Matcher oneOrMoreTimesTermMatcher = patternOneOrMoreTimesTerm
					.matcher(term);
			// pattern for term*
			Pattern patternZeroOrMoreTimesTerm = Pattern.compile(".+\\*$");
			Matcher zeroOrMoreTimesTermMatcher = patternZeroOrMoreTimesTerm
					.matcher(term);
			// pattern for |termù|n
			Pattern patternParallelManyTimesTerm = Pattern
					.compile("^\\|.+(ù\\|(\\d)+)$");
			Matcher parallelManyTimesTermMatcher = patternParallelManyTimesTerm
					.matcher(term);
			if (complexParenthesisTermMatcher.find()
					&& (complexParenthesisTermMatcher.group().length() == term
							.length())) {
				this.createProcess(term.substring(1, term.length() - 1),
						substituteActivityWithSubProcess(subProcess, term, resource),resource);
			} else if (complexOptionalTermMatcher.find()
					&& (complexOptionalTermMatcher.group().length() == term
							.length())) {

				Graph tmpSub = substituteActivityWithSubProcess(subProcess, term, resource);
				tmpSub.setName(computeNodeName(term));
				if (tmpSub instanceof SubProcess)  ((SubProcess) tmpSub).setActivityType(ActivityType.SUB_PROCESS);

				Activity tmpActivity1 = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity1, resource);
				tmpActivity1.setActivityType(ActivityType.EVENT_START_EMPTY);
				tmpSub.getVertices().add(tmpActivity1);
				
				tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge, resource);
				tmpActivity1.getOutgoingEdges().add(tmpEdge);
				tmpSub.getSequenceEdges().add(tmpEdge);
				
				tmpActivity1 = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity1, resource);
				tmpActivity1
						.setActivityType(ActivityType.GATEWAY_DATA_BASED_EXCLUSIVE);
				tmpActivity1.getIncomingEdges().add(tmpEdge);
				tmpSub.getVertices().add(tmpActivity1);
				
				tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge, resource);
				tmpActivity1.getOutgoingEdges().add(tmpEdge);
				tmpSub.getSequenceEdges().add(tmpEdge);

				SequenceEdge tmpEdge1 = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge1, resource);
				tmpActivity1.getOutgoingEdges().add(tmpEdge1);
				tmpSub.getSequenceEdges().add(tmpEdge1);

				tmpActivity1 = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity1, resource);
				tmpActivity1
						.setActivityType(ActivityType.GATEWAY_DATA_BASED_EXCLUSIVE);
				tmpActivity1.getIncomingEdges().add(tmpEdge);
				tmpSub.getVertices().add(tmpActivity1);

				tmpActivity = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity, resource);
				tmpActivity.getIncomingEdges().add(tmpEdge1);
				String insideTerm = term.substring(1, term.length() - 1);
				tmpActivity.setName(computeNodeName(insideTerm));
				tmpSub.getVertices().add(tmpActivity);
				
				tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge, resource);
				tmpActivity.getOutgoingEdges().add(tmpEdge);
				tmpActivity1.getIncomingEdges().add(tmpEdge);
				tmpSub.getSequenceEdges().add(tmpEdge);

				tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge, resource);
				tmpActivity1.getOutgoingEdges().add(tmpEdge);
				tmpSub.getSequenceEdges().add(tmpEdge);

				tmpActivity1 = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity1, resource);
				tmpActivity1.setActivityType(ActivityType.EVENT_END_EMPTY);
				tmpActivity1.getIncomingEdges().add(tmpEdge);
				tmpSub.getVertices().add(tmpActivity1);

				basicTermMatcher = patternBasicTerm.matcher(insideTerm);
				if (basicTermMatcher.find()
						&& (basicTermMatcher.group().length() == insideTerm
								.length())) {
					this.handleBasicTerm(insideTerm, tmpSub, resource);
				} else {
					this.createProcess(insideTerm,
							substituteActivityWithSubProcess(tmpSub, insideTerm, resource), resource);
				}
			} else if (zeroOrMoreTimesTermMatcher.find()
					&& (zeroOrMoreTimesTermMatcher.group().length() == term
							.length())) {
				Graph tmpSub = substituteActivityWithSubProcess(subProcess, term, resource);
				tmpSub.setName(computeNodeName(term));
				if (tmpSub instanceof SubProcess) ((SubProcess) tmpSub).setActivityType(ActivityType.SUB_PROCESS);

				Activity aStart = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(aStart, resource);
				aStart.setActivityType(ActivityType.EVENT_START_EMPTY);
				tmpSub.getVertices().add(aStart);
				
				SequenceEdge eStartToGate = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(eStartToGate, resource);
				aStart.getOutgoingEdges().add(eStartToGate);
				tmpSub.getSequenceEdges().add(eStartToGate);
				
				Activity aGate1 = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(aGate1, resource);
				aGate1.setActivityType(ActivityType.GATEWAY_DATA_BASED_EXCLUSIVE);
				aGate1.getIncomingEdges().add(eStartToGate);
				tmpSub.getVertices().add(aGate1);
				
				SequenceEdge eGate1ToActivity = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(eGate1ToActivity, resource);
				aGate1.getOutgoingEdges().add(eGate1ToActivity);
				tmpSub.getSequenceEdges().add(eGate1ToActivity);

				SequenceEdge eGate1ToGate2 = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(eGate1ToGate2, resource);
				aGate1.getOutgoingEdges().add(eGate1ToGate2);
				tmpSub.getSequenceEdges().add(eGate1ToGate2);

				Activity aGate2 = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(aGate2, resource);
				aGate2.setActivityType(ActivityType.GATEWAY_DATA_BASED_EXCLUSIVE);
				aGate2.getIncomingEdges().add(eGate1ToGate2);
				tmpSub.getVertices().add(aGate2);

				tmpActivity = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity, resource);
				tmpActivity.getIncomingEdges().add(eGate1ToActivity);
				String insideTerm = term.substring(1, term.length() - 1);
				tmpActivity.setName(computeNodeName(insideTerm));
				tmpSub.getVertices().add(tmpActivity);
				
				SequenceEdge eActivityToGate3 = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(eActivityToGate3, resource);
				tmpActivity.getOutgoingEdges().add(eActivityToGate3);
				tmpSub.getSequenceEdges().add(eActivityToGate3);

				SequenceEdge eGate3ToGate2 = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(eGate3ToGate2, resource);
				aGate2.getIncomingEdges().add(eGate3ToGate2);
				tmpSub.getSequenceEdges().add(eGate3ToGate2);

				Activity aGate3 = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(aGate3, resource);
				aGate3.setActivityType(ActivityType.GATEWAY_DATA_BASED_EXCLUSIVE);
				aGate3.getIncomingEdges().add(eActivityToGate3);
				aGate3.getOutgoingEdges().add(eGate3ToGate2);
				subProcess.getVertices().add(aGate3);

				SequenceEdge eGate2ToEnd = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(eGate2ToEnd, resource);
				aGate2.getOutgoingEdges().add(eGate2ToEnd);
				tmpSub.getSequenceEdges().add(eGate2ToEnd);

				Activity aEnd = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(aEnd, resource);
				aEnd.setActivityType(ActivityType.EVENT_END_EMPTY);
				aEnd.getIncomingEdges().add(eGate2ToEnd);
				subProcess.getVertices().add(aEnd);

				basicTermMatcher = patternBasicTerm.matcher(insideTerm);
				if (basicTermMatcher.find()
						&& (basicTermMatcher.group().length() == insideTerm
								.length())) {
					this.handleBasicTerm(insideTerm, tmpSub, resource);
				} else {
					this.createProcess(insideTerm,
							substituteActivityWithSubProcess(tmpSub, insideTerm, resource), resource);
				}
			} else if (foreverTermMatcher.find()
					&& (foreverTermMatcher.group().length() == term.length())) {
				
				Graph tmpSub = substituteActivityWithSubProcess(subProcess, term, resource);
				tmpSub.setName(computeNodeName(term));
				if (tmpSub instanceof SubProcess) ((SubProcess) tmpSub).setActivityType(ActivityType.SUB_PROCESS);

				tmpActivity = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity, resource);
				tmpActivity.setActivityType(ActivityType.EVENT_START_EMPTY);
				tmpSub.getVertices().add(tmpActivity);
				
				tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge, resource);
				tmpActivity.getOutgoingEdges().add(tmpEdge);
				tmpSub.getSequenceEdges().add(tmpEdge);
				
				tmpActivity = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity, resource);
				tmpActivity.getIncomingEdges().add(tmpEdge);
				String insideTerm = term.substring(0, term.length() - 1);
				tmpActivity.setName(computeNodeName(insideTerm));
				tmpSub.getVertices().add(tmpActivity);
				
				tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge, resource);
				tmpActivity.getIncomingEdges().add(tmpEdge);
				tmpActivity.getOutgoingEdges().add(tmpEdge);
				tmpSub.getSequenceEdges().add(tmpEdge);

				basicTermMatcher = patternBasicTerm.matcher(insideTerm);
				if (basicTermMatcher.find()
						&& (basicTermMatcher.group().length() == insideTerm
								.length())) {
					this.handleBasicTerm(insideTerm, tmpSub, resource);
				} else {
					this.createProcess(insideTerm,
							substituteActivityWithSubProcess(tmpSub, insideTerm, resource), resource);
				}
				
			} else if (oneOrMoreTimesTermMatcher.find()
					&& (oneOrMoreTimesTermMatcher.group().length() == term
							.length())) {
				
				Graph tmpSub = substituteActivityWithSubProcess(subProcess, term, resource);
				tmpSub.setName(computeNodeName(term));
				if (tmpSub instanceof SubProcess) ((SubProcess) tmpSub).setActivityType(ActivityType.SUB_PROCESS);

				tmpActivity = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity, resource);
				tmpActivity.setActivityType(ActivityType.EVENT_START_EMPTY);
				tmpSub.getVertices().add(tmpActivity);
				
				tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge, resource);
				tmpActivity.getOutgoingEdges().add(tmpEdge);
				tmpSub.getSequenceEdges().add(tmpEdge);
				
				tmpActivity = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity, resource);
				tmpActivity.getIncomingEdges().add(tmpEdge);
				String insideTerm = term.substring(0, term.length() - 1);
				tmpActivity.setName(computeNodeName(insideTerm));
				tmpSub.getVertices().add(tmpActivity);
				
				Activity tmpActivity1 = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity1, resource);
				tmpActivity1.setActivityType(ActivityType.EVENT_END_EMPTY);
				tmpSub.getVertices().add(tmpActivity1);

				tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge, resource);
				tmpActivity1.getIncomingEdges().add(tmpEdge);
				tmpActivity.getOutgoingEdges().add(tmpEdge);
				tmpSub.getSequenceEdges().add(tmpEdge);

				basicTermMatcher = patternBasicTerm.matcher(insideTerm);
				if (basicTermMatcher.find()
						&& (basicTermMatcher.group().length() == insideTerm
								.length())) {
					this.handleBasicTerm(insideTerm, tmpSub, resource);
				} else {
					this.createProcess(insideTerm,
							substituteActivityWithSubProcess(tmpSub, insideTerm, resource), resource);
				}
				
			} else if (parallelManyTimesTermMatcher.find()
					&& (parallelManyTimesTermMatcher.group().length() == term
							.length())) {
				Graph tmpSub = substituteActivityWithSubProcess(subProcess, term, resource);
				tmpSub.setName(computeNodeName(term));
				if (tmpSub instanceof SubProcess) ((SubProcess) tmpSub).setActivityType(ActivityType.SUB_PROCESS);

				tmpActivity = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity, resource);
				tmpActivity.setActivityType(ActivityType.EVENT_START_EMPTY);
				tmpSub.getVertices().add(tmpActivity);
				
				tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge, resource);
				tmpActivity.getOutgoingEdges().add(tmpEdge);
				tmpSub.getSequenceEdges().add(tmpEdge);
				
				tmpActivity = BpmnFactory.eINSTANCE.createActivity();
				addToResourceAndAssignXmlId(tmpActivity, resource);
				tmpActivity.getIncomingEdges().add(tmpEdge);
				String insideTerm = term.substring(0, term.length() - 1);
				tmpActivity.setName(computeNodeName(insideTerm));
				tmpSub.getVertices().add(tmpActivity);
				
				tmpEdge = BpmnFactory.eINSTANCE.createSequenceEdge();
				addToResourceAndAssignXmlId(tmpEdge, resource);
				tmpActivity.getIncomingEdges().add(tmpEdge);
				tmpActivity.getOutgoingEdges().add(tmpEdge);
				tmpSub.getSequenceEdges().add(tmpEdge);

				basicTermMatcher = patternBasicTerm.matcher(insideTerm);
				if (basicTermMatcher.find()
						&& (basicTermMatcher.group().length() == insideTerm
								.length())) {
					this.handleBasicTerm(insideTerm, tmpSub, resource);
				} else {
					this.createProcess(insideTerm,
							substituteActivityWithSubProcess(tmpSub, insideTerm, resource), resource);
				}
				
			} else if (basicTermMatcher.find()
					&& (basicTermMatcher.group().length() == term.length())) {
				this.handleBasicTerm(term, subProcess, resource);
			}

		}
	}

	public Graph substituteActivityWithSubProcess(Graph subProcess,
			String term, Resource resource) {
		if (this.getVertex(subProcess, term) instanceof Activity){
		Activity tmpActivity = (Activity) this.getVertex(subProcess, term);
		// create a sub process and replace the activity with the subprocess in
		// the containing graph
		SubProcess complexTerm = BpmnFactory.eINSTANCE.createSubProcess();
		addToResourceAndAssignXmlId(complexTerm, resource);
		complexTerm.setActivityType(ActivityType.SUB_PROCESS);

		if (tmpActivity.getName()!=null) complexTerm.setName(tmpActivity.getName());
		for (int i = 0; i < tmpActivity.getIncomingEdges().size(); i++) {
			complexTerm.getIncomingEdges().add(
					tmpActivity.getIncomingEdges().get(i));
		}
		for (int i = 0; i < tmpActivity.getOutgoingEdges().size(); i++) {
			complexTerm.getOutgoingEdges().add(
					tmpActivity.getOutgoingEdges().get(i));
		}
		subProcess.getVertices().remove(tmpActivity);
		subProcess.getVertices().add(complexTerm);
		return complexTerm;
		}
		else return subProcess;
	}

	public void handleBasicTerm(String term, Graph subProcess, Resource resource) {
		boolean isBasic = true;
		if (formulas.containsKey(term)) {
			isBasic=false;
			this.createProcess(formulas.get(term),
					substituteActivityWithSubProcess(subProcess, term, resource), resource);
		}
		if (isBasic) {
			Activity tmpActivity = (Activity) this.getVertex(subProcess, term);
			tmpActivity.setActivityType(ActivityType.TASK);
		}
	}

	public String computeNodeName(String term) {
		String nodeName = new String(term);
		nodeName = nodeName.replaceAll("\\|\\|", "_conc_");
		nodeName = nodeName.replaceAll("ù", "Forever");
		nodeName = nodeName.replaceAll("\\.", "_seq_");
		nodeName = nodeName.replaceAll("\\|", "_or_");
		nodeName = nodeName.replaceAll("\\*", "ZeroOrMoreTimes");
		nodeName = nodeName.replaceAll("\\+", "OneOrMoreTimes");
		nodeName = nodeName.replaceAll("\\(", "_group_");
		nodeName = nodeName.replaceAll("\\)", "_closeGroup_");
		nodeName = nodeName.replaceAll("\\[", "_optional_");
		nodeName = nodeName.replaceAll("\\]", "_closeOptional_");
		return nodeName;
	}

	public Vertex getVertex(Graph subProcess, String term) {
		for (int i = 0; i < subProcess.getVertices().size(); i++) {
			if (((Activity) subProcess.getVertices().get(i)).getName()!=null&&((Activity) subProcess.getVertices().get(i)).getName().equalsIgnoreCase(computeNodeName(term)))
				return subProcess.getVertices().get(i);
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
	
	static int xmiID=1;

    static void addToResourceAndAssignXmlId(EObject obj, Resource resource) {
		resource.getContents().add(obj);
		XMIHelper.setXmlId(obj, new String("bpmnElement"+xmiID));
		xmiID++;
    }

}
