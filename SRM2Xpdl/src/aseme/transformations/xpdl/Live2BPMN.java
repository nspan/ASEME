package aseme.transformations.xpdl;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;
import org.camunda.bpm.model.bpmn.instance.CatchEvent;
import org.camunda.bpm.model.bpmn.instance.Collaboration;
import org.camunda.bpm.model.bpmn.instance.Definitions;
import org.camunda.bpm.model.bpmn.instance.EndEvent;
import org.camunda.bpm.model.bpmn.instance.ExclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.Lane;
import org.camunda.bpm.model.bpmn.instance.LaneSet;
import org.camunda.bpm.model.bpmn.instance.ParallelGateway;
import org.camunda.bpm.model.bpmn.instance.Participant;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.ReceiveTask;
import org.camunda.bpm.model.bpmn.instance.SendTask;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnDiagram;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnEdge;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;
import org.camunda.bpm.model.bpmn.instance.di.Waypoint;


public class Live2BPMN {

	public String liveness;
	public Participant participant;
	public Collaboration collaboration;
	public BpmnModelInstance modelInstance;
	public Definitions definitions;
	public BpmnPlane processPlane;
	public Process process;
	public BpmnDiagram bpmnDiagram;
	public StartEvent startEvent;
	public BpmnShape startEventShape;
	public BpmnShape participantShape;
	public EndEvent endEvent;
	BpmnShape endEventShape;
	public LaneSet laneset;
	public Lane lane1;
	public double xaxis = 0.0;
	public double yaxis = 0.0;
	public LinkedList<BpmnModelElementInstance> firstacts;
	public Hashtable<String, String> formulas;
	private String lefthand;
	private int activityid;
	public List<String> foundTerms = new LinkedList<String>();
	List<String> findTerms = new LinkedList<String>();
	List<String> expressionList = new LinkedList<>();
	List<String> basicterms = new LinkedList<>();
	public static List<String> senders = new LinkedList<String>();
	public static List<String> receivers = new LinkedList<String>();
	public static List<String> senderids = new LinkedList<String>();
	public static List<String> receiverids = new LinkedList<String>();
	public static List<FlowNode> sendernodes = new LinkedList<FlowNode>();
	public static List<FlowNode> receivernodes = new LinkedList<FlowNode>();
	public static List<BpmnShape> sendershapes = new LinkedList<BpmnShape>();
	public static List<BpmnShape> receivershapes = new LinkedList<BpmnShape>();
	public static List<BpmnShape> messagesendershape = new LinkedList<BpmnShape>();
	public static List<BpmnShape> messagereceivershape = new LinkedList<BpmnShape>();
	int numberofpara = 0;
	int numberofseq = 0;
	int numberofors = 0;
	private int listpointer;
	private String connector;
	private int numberoforexpressions;
	private String term123;
	public BpmnShape previousshape;
	private CatchEvent messagetemp;
	Liveness2BPMN liveness2bpmn = new Liveness2BPMN();
	public int currentrole = 0;
	public int flag = 0;
	private BpmnShape xorshapebrackets;
	public int lanecounter;
	public BpmnModelElementInstance instance;
	
	public Live2BPMN() {
		// TODO Auto-generated constructor stub
	}

	public static <T> void main(String[] args) throws IOException {
		Live2BPMN live2bpmn = new Live2BPMN();
		if (args.length == 0) {
			live2bpmn.liveness = new String(
				//"ComplexProvider=SP~\n"
			 //+"SP=ReceiveRequestMessage.ProcessRequest.SendResponseMessage\n"
			//+"ProcessRequest=(DecideRouteType.SR.SortRoutes)|[DecidePOITypes.SR.DecidePois.SR]\n"
			// +"SR=SendRequestMessage.ReceiveResponseMessage"
			 //+"SR=SR.ReceiveResponseMessage"
			 "PersonalAssistant = SR\n"
			 +"SR=(ReceiveRequestMessage.A3)~||ProcessRequest~||SendResponseMessage~\n"
			 +"ProcessRequest=SendRequestMessage.ReceiveResponseMessage"
			 //"x = A1|(A2)+|A3*|A~"
			 //"x=[A1.A2.A3]"
				//"x = [A1.A2]~|[A3|A4*|A6+]*|A7~|[A8~|A9*|A10~]~"
				//"ComplexProvider = (ReceiveComplexServiceRequest.DecideRouteType.SendSimpleServiceRequest.ReceiveSimpleServiceResponse.SortRoutes.SendComplexServiceResponse)+"
			//"x = A*"
			);
		} else {
			live2bpmn.liveness = args[0];
		}
		live2bpmn.liveness = live2bpmn.liveness.replaceAll(" ", "");	
		String outputFile = "C:/Users/nek/Desktop/Myfirst.bpmn";
		File f = new File(outputFile);
		outputFile = f.getCanonicalPath();
		f.getName().substring(0,((File) f).getName().lastIndexOf("."));
		System.out.println("Create a new BPMN model");
		live2bpmn.createProcess(live2bpmn.liveness);
		Bpmn.writeModelToFile(f, live2bpmn.modelInstance);
		System.out.println("End of program");
	}

	  public void createProcess(String liveness) {
		    
		  firstacts = new LinkedList<BpmnModelElementInstance>();
		  formulas = new Hashtable<String, String>();
		  StringTokenizer line = new StringTokenizer(liveness, "\n");
		  while (line.hasMoreTokens()) {
				String tmp = line.nextToken();
				StringTokenizer formulaElements = new StringTokenizer(tmp, "=");
				String leftHandSide = formulaElements.nextToken();
				setleftHandSide(leftHandSide);
				String formula = formulaElements.nextToken();
//				if(formula.contains(leftHandSide)){
//					System.out.println("There is a self-reference in the formula and the program will exit.");
//					System.out.println("Check your formula.");
//					System.exit(0);
//				}
				//String formula1 = preprocessing(formula);
				//System.out.println("What is the formula after preprocessing:"
					//	+ formula1);
				formulas.put(leftHandSide, formula);
			}
		  System.out.println("What are the formulas?" + formulas);
		  line = new StringTokenizer(liveness, "\n");
		  StringTokenizer formulaElements = new StringTokenizer(line.nextToken(),
					"=");
		  String leftHandSide = formulaElements.nextToken();
		  
		  	modelInstance = Bpmn.createEmptyModel();
		  	definitions = modelInstance.newInstance(Definitions.class);
		  	definitions.setId("Idok");
		  	definitions.setTargetNamespace("http://www.jboss.org/drools");
			definitions.setExporter("org.eclipse.bpmn.modeler.core"); 
			definitions.setTypeLanguage("http://www.java.com/javaTypes");
			modelInstance.setDefinitions(definitions);
		    
		    bpmnDiagram = modelInstance.newInstance(BpmnDiagram.class);
		    bpmnDiagram.setId("diagram");
		    bpmnDiagram.setName("diagram");
		    bpmnDiagram.setDocumentation("bpmn diagram element");
		    bpmnDiagram.setResolution(120.0);
		    modelInstance.getDefinitions().addChildElement(bpmnDiagram);
		    //Calendar to create unique xmiids
			Calendar cal2 = Calendar.getInstance();
			long d = cal2.getTimeInMillis();
		    collaboration = modelInstance.newInstance(Collaboration.class);
		    collaboration.setId("collaborationid");
		    participant = modelInstance.newInstance(Participant.class);
		    participant.setId("anid00"+d);
		    participant.setName(leftHandSide);
		    collaboration.addChildElement(participant);
		    modelInstance.getDefinitions().addChildElement(collaboration);
		    
		    process = createElement(definitions, "transformedprocess", Process.class);
		    
		    participant.setProcess(process);
		    participantShape = modelInstance.newInstance(BpmnShape.class);
		    participantShape.setId("participantshape");
		    participantShape.setBpmnElement(participant);
		    
		    processPlane = modelInstance.newInstance(BpmnPlane.class);
		    processPlane.setId("plane");
		    processPlane.setBpmnElement(process);
		    bpmnDiagram.setBpmnPlane(processPlane);
		    processPlane.getDiagramElements().add(participantShape);
	   
		    laneset = createElement(process, "lanesetid", LaneSet.class);
		    lane1 = createElement(laneset, "lane1", Lane.class);
	    
		    xaxis=xaxis+70.0;
		    yaxis=yaxis+70.0;
		    startEvent = createElement(process, "start", StartEvent.class);
	    
			startEventShape = modelInstance.newInstance(BpmnShape.class);
		    startEventShape.setId("startShape");
		    startEventShape.setBpmnElement(startEvent);
		    processPlane.getDiagramElements().add(startEventShape);
		    setpreviousshape(startEventShape);
		    
		    Bounds startEventBounds = modelInstance.newInstance(Bounds.class);
		    startEventBounds.setHeight(36.0);
		    startEventBounds.setWidth(36.0);
		    startEventBounds.setX(632.0);
		    startEventBounds.setY(312.0);
		    startEventShape.setBounds(startEventBounds);

		    xaxis=xaxis+70.0;
		    yaxis=yaxis+70.0;
		    endEvent = createElement(process, "end", EndEvent.class);
		    endEventShape = modelInstance.newInstance(BpmnShape.class);
		    endEventShape.setId("endShape");
		    endEventShape.setBpmnElement(endEvent);
		    processPlane.getDiagramElements().add(endEventShape);
		    
		    Bounds endEventBounds = modelInstance.newInstance(Bounds.class);
		    endEventBounds.setHeight(36.0);
		    endEventBounds.setWidth(36.0);
		    endEventBounds.setX(96.0);
		    endEventBounds.setY(16.0);
		    endEventShape.setBounds(endEventBounds);
		    
			//create process Time for recursion
		    BpmnModelElementInstance task2 = createProcessrecursively(formulas.get(leftHandSide), process, (BpmnModelElementInstance) startEvent);
		    
		    SequenceFlow flow = createSequenceFlow(process, (FlowNode)task2, endEvent);
		    
		    BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);
		    flowEdge.setId("flowEdge");
		    flowEdge.setBpmnElement(flow);
		    flowEdge.setSourceElement(startEventShape);
		    flowEdge.setTargetElement(endEventShape);
		    processPlane.getDiagramElements().add(flowEdge);

		    // create waypoints for sequence flow edge
		    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
		    startWaypoint.setX(698.0);
		    startWaypoint.setY(330.0);
		    flowEdge.getWaypoints().add(startWaypoint);

		    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
		    endWaypoint.setX(718.0);
		    endWaypoint.setY(330.0);
		    flowEdge.getWaypoints().add(endWaypoint);
		    
		    Bounds participantBounds = modelInstance.newInstance(Bounds.class);
		    participantBounds.setHeight(xaxis);
		    participantBounds.setWidth(yaxis);
		    participantBounds.setX(30.0);
		    participantBounds.setY(30.0);
		    participantShape.setBounds(participantBounds);    
		  }
	  	
	  BpmnModelElementInstance createMessagePartners(String expression, Process process2, BpmnModelElementInstance actprevious){
		// TODO Auto-generated method stub
		
		  Calendar cal2 = Calendar.getInstance();
			long d = cal2.getTimeInMillis();
			int expressionType = 0;
			// pattern for parallelExpression
			Pattern patternParallelExpression = Pattern
					.compile("(((\\|.+~|(\\d))|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)\\|\\|)+((\\|.+~\\|(\\d))|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)");
			Matcher parallelMatcher = patternParallelExpression.matcher(expression);
			// pattern for orExpression : expressionType=2
			Pattern patternOrExpression = Pattern
					.compile("(((\\|.+~|(\\d))|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)\\|)+((\\|.+~\\|(\\d))|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)");
			Matcher orMatcher = patternOrExpression.matcher(expression);
			// pattern for sequentialExpression : expressionType=3
			Pattern patternSequentialExpression = Pattern
					.compile("(((\\|.+~\\|\\d)|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)\\.)+((\\|.+~\\|\\d)|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)");

			Matcher sequentialMatcher = patternSequentialExpression
					.matcher(expression);

			List<String> myTerms = null;

			switch (expressionType) {
			case 0:
				myTerms = new LinkedList<String>();
				myTerms.add(expression);
				break;
			}
			
			if (sequentialMatcher.find()
					&& (sequentialMatcher.group().length() == expression.length())) {
				expressionType = 3;

				System.out.print("a sequential expression processed: " + expression
						+ "\n");
				setconnector(".");
				List<String> seqterms = this.findTermsInExpression(expression, ".",
						process2);

				System.out.println("Seqterms are:" + seqterms);

				int listpointer2 = listpointer;
				int b = 0;
				for (b = 0; b < seqterms.size(); b++) {
					String currentterm = seqterms.get(b);
					BpmnModelElementInstance seqact = createMessagePartners(currentterm, process, actprevious);
					listpointer = listpointer + 1;
					actprevious = seqact;
				}
				int i = 0;
				for (i = 0; i < seqterms.size(); i++) {
					listpointer = listpointer - 1;
					if (listpointer == listpointer2) {
						break;
					}
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
				}
			}else if (orMatcher.find()
					&& (orMatcher.group().length() == expression.length())) {
				expressionType = 2;
				System.out.print("an or expression processed: " + expression + "\n");
				numberoforexpressions = numberoforexpressions + 1;
				System.out.println("The numberoforexpressions is:"+ numberoforexpressions);
				setconnector("|");
				d = cal2.getTimeInMillis();
				xaxis=xaxis+70.0;
			    yaxis=yaxis+70.0;
				ExclusiveGateway xor = createElement(process, "xor1"+d, ExclusiveGateway.class);
				modelInstance.getModelElementById("xor1"+d);
				xor.setName("decision");
				d=cal2.getTimeInMillis();
				BpmnShape xorshape = modelInstance.newInstance(BpmnShape.class);
				xorshape.setId("xor1Shape"+d);
				xorshape.setBpmnElement(xor);
				processPlane.getDiagramElements().add(xorshape);
				 
				 Bounds xorBounds = modelInstance.newInstance(Bounds.class);
				 xorBounds.setHeight(50.0);
				 xorBounds.setWidth(100.0);
				 xorBounds.setX(xaxis+70.0);
				 xaxis = xaxis+70.0;
				 xorBounds.setY(yaxis+70.0);
				 yaxis = yaxis+70.0;
				 xorshape.setBounds(xorBounds);
				 
				 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, xor);
				 BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow4Edge"+d);
					try {
						flowEdge.setBpmnElement(flow1);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
					}
				processPlane.getDiagramElements().add(flowEdge);
					
				Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
				startWaypoint.setX(668.0);
				startWaypoint.setY(330.0);
				flowEdge.getWaypoints().add(startWaypoint);

				Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
				endWaypoint.setX(718.0);
				endWaypoint.setY(330.0);
				flowEdge.getWaypoints().add(endWaypoint);	
				 
				d = cal2.getTimeInMillis();
				xaxis=xaxis+70.0;
			    yaxis=yaxis+70.0;
				ExclusiveGateway xor2 = createElement(process, "xor2"+d, ExclusiveGateway.class);
				xor2.setName("decisionexit");
				d=cal2.getTimeInMillis();
				BpmnShape xor2shape = modelInstance.newInstance(BpmnShape.class);
				xor2shape.setId("xor2Shape"+d);
				xor2shape.setBpmnElement(xor2);
				processPlane.getDiagramElements().add(xor2shape);
					 
				Bounds xor2Bounds = modelInstance.newInstance(Bounds.class);
				xor2Bounds.setHeight(50.0);
				xor2Bounds.setWidth(100.0);
				xor2Bounds.setX(xaxis+70.0);
				xaxis = xaxis+70.0;
				xor2Bounds.setY(yaxis+70.0);
				yaxis = yaxis+70.0;
				xor2shape.setBounds(xor2Bounds);
				
				int listpointer2 = listpointer;
				listpointer = listpointer + 1;
				firstacts.add(xor);
				
				List<String> orterms = this.findTermsInExpression(expression, "|",
						process2);
				System.out.println("the orterms are:" + orterms);

				int or = 0;
				for (or = 0; or < orterms.size(); or++) {
					String currentterm = orterms.get(or);
					System.out.println("The current term is:" + currentterm);
					actprevious = xor;
					BpmnModelElementInstance ortemp = createMessagePartners(currentterm, process, actprevious);
					listpointer = listpointer + 1;
					d = cal2.getTimeInMillis();
					SequenceFlow flow2= createSequenceFlow(process, (FlowNode) ortemp, xor2);
					
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow5Edge"+or+d);
					flowEdge.setBpmnElement(flow2);
					processPlane.getDiagramElements().add(flowEdge);
					
					 startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
				}

				int i = 0;
				for (i = 0; i < orterms.size() + 1; i++) {

					listpointer = listpointer - 1;
					if (listpointer == listpointer2) {
						break;
					}
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
				}
				actprevious = xor2;
				setpreviousshape(xor2shape);
			}else if (parallelMatcher.find()
					&& (parallelMatcher.group().length() == expression.length())) {
				expressionType = 1;
				System.out.print("a parallel expression processed: " + expression
						+ "\n");

				setconnector("||");
				d = cal2.getTimeInMillis();
				//ParallelGateway
				numberofpara = numberofpara + 1;
				System.out.println("The numberofparaexpressions is:"+ numberofpara);
				d = cal2.getTimeInMillis();
				xaxis=xaxis+70.0;
			    yaxis=yaxis+70.0;
				ParallelGateway para = createElement(process, "para1"+d, ParallelGateway.class);
				modelInstance.getModelElementById("para1"+d);
				para.setName("parallel");
				d=cal2.getTimeInMillis();
				BpmnShape parashape = modelInstance.newInstance(BpmnShape.class);
				parashape.setId("parashape"+d);
				parashape.setBpmnElement(para);
				processPlane.getDiagramElements().add(parashape);
				
				Bounds paraBounds = modelInstance.newInstance(Bounds.class);
				 paraBounds.setHeight(50.0);
				 paraBounds.setWidth(100.0);
				 paraBounds.setX(xaxis+70.0);
				 xaxis = xaxis+70.0;
				 paraBounds.setY(yaxis+70.0);
				 yaxis = yaxis+70.0;
				 parashape.setBounds(paraBounds);
				 
				 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, para);
				 BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
				d = cal2.getTimeInMillis();
				flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
				flowEdge.setId("flow4Edge"+d);
				flowEdge.setBpmnElement(flow1);
				processPlane.getDiagramElements().add(flowEdge);
				
				Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
				startWaypoint.setX(668.0);
				startWaypoint.setY(330.0);
				flowEdge.getWaypoints().add(startWaypoint);

				Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
				endWaypoint.setX(718.0);
				endWaypoint.setY(330.0);
				flowEdge.getWaypoints().add(endWaypoint);	
				 
				d = cal2.getTimeInMillis();
				xaxis=xaxis+70.0;
			    yaxis=yaxis+70.0;
				ParallelGateway para2 = createElement(process, "para2exit"+d, ParallelGateway.class);
				para2.setName("parallelexit");
				d=cal2.getTimeInMillis();
				BpmnShape para2shape = modelInstance.newInstance(BpmnShape.class);
				para2shape.setId("paraShape"+d);
				para2shape.setBpmnElement(para2);
				processPlane.getDiagramElements().add(para2shape);
					 
				Bounds para2Bounds = modelInstance.newInstance(Bounds.class);
				para2Bounds.setHeight(50.0);
				para2Bounds.setWidth(100.0);
				para2Bounds.setX(xaxis+70.0);
				xaxis = xaxis+70.0;
				para2Bounds.setY(yaxis+70.0);
				yaxis = yaxis+70.0;
				para2shape.setBounds(para2Bounds);
				
				int listpointer2 = listpointer;
				listpointer = listpointer + 1;
				firstacts.add(para);
				
				List<String> paraterms = this.findTermsInExpression(expression,
						"||", process2);
				System.out.println("the parallel terms are:" + paraterms);

				int par = 0;
				for (par = 0; par < paraterms.size(); par++) {
					String currentterm = paraterms.get(par);
					System.out.println("The current term is:" + currentterm);
					actprevious = para;
					
					BpmnModelElementInstance paratemp = createMessagePartners(currentterm, process, actprevious);
					listpointer = listpointer + 1;
					d = cal2.getTimeInMillis();
					
					SequenceFlow flow = createSequenceFlow(process, (FlowNode) paratemp, para2);
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow5Edge"+par+d);
					flowEdge.setBpmnElement(flow);
					processPlane.getDiagramElements().add(flowEdge);
					
					 startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
				}

				int i = 0;
				for (i = 0; i < paraterms.size() + 1; i++) {

					listpointer = listpointer - 1;
					if (listpointer == listpointer2) {
						break;
					}
					try {
						firstacts.remove(listpointer);
					} catch (Exception e){
					}
				}
				actprevious = para2;
				setpreviousshape(para2shape);
			}else {
				String term1 = expression;
				setTerm1(term1);
				System.out.println("term1 is:" + term1);
				Pattern patternBasicTerm = Pattern.compile("^\\w+$");
				Matcher basicTermMatcher = patternBasicTerm.matcher(term1);
				// pattern for (term)
				Pattern patternComplexParenthesisTerm = Pattern
						.compile("^\\(.+\\)$");
				Matcher complexParenthesisTermMatcher = patternComplexParenthesisTerm
						.matcher(term1);
				// pattern for [term]
				Pattern patternComplexOptionalTerm = Pattern.compile("^\\[.+\\]$");
				Matcher complexOptionalTermMatcher = patternComplexOptionalTerm
						.matcher(term1);
				// pattern for term~
				Pattern patternForeverTerm = Pattern.compile(".+~$");
				Matcher foreverTermMatcher = patternForeverTerm.matcher(term1);
				// pattern for term+
				Pattern patternOneOrMoreTimesTerm = Pattern.compile(".+\\+$");
				Matcher oneOrMoreTimesTermMatcher = patternOneOrMoreTimesTerm
						.matcher(term1);
				// pattern for term*
				Pattern patternZeroOrMoreTimesTerm = Pattern.compile(".+\\*$");
				Matcher zeroOrMoreTimesTermMatcher = patternZeroOrMoreTimesTerm
						.matcher(term1);
				
				if (complexParenthesisTermMatcher.find()
						&& (complexParenthesisTermMatcher.group().length() == term1
								.length())) {
					System.out.println("A parenthesis term is being processed");
					String insideTerm = term1.substring(1, term1.length() - 1);
					System.out.println("the inside term is:" + insideTerm);
					BpmnModelElementInstance parenth = createMessagePartners(insideTerm, process, actprevious);
					actprevious = parenth;
				} else if (complexOptionalTermMatcher.find()
						&& (complexOptionalTermMatcher.group().length() == term1
								.length())) {
					System.out.println("A brackets term is being processed.");
					// x optional [x]
					d = cal2.getTimeInMillis();
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorbrackets = createElement(process, "bracketsxor1"+d, ExclusiveGateway.class);
					xorbrackets.setName("bracketsdecision");
					d=cal2.getTimeInMillis();
					BpmnShape xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
					xorshapebrackets.setId("xor1Shape"+d);
					xorshapebrackets.setBpmnElement(xorbrackets);
					processPlane.getDiagramElements().add(xorshapebrackets);
					 
					 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
					 xorbracketsBounds.setHeight(50.0);
					 xorbracketsBounds.setWidth(100.0);
					 xorbracketsBounds.setX(xaxis+70.0);
					 xaxis = xaxis+70.0;
					 xorbracketsBounds.setY(yaxis+70.0);
					 yaxis = yaxis+70.0;
					 xorshapebrackets.setBounds(xorbracketsBounds);
					 
					 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, xorbrackets);

					 BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						d = cal2.getTimeInMillis();
						flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flow4Edge"+d);
						try {
							flowEdge.setBpmnElement(flow1);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						}
						processPlane.getDiagramElements().add(flowEdge);
						
					Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
						
					d = cal2.getTimeInMillis();
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorbrackets2 = createElement(process, "xorbrackets2"+d, ExclusiveGateway.class);
					xorbrackets2.setName("bracketsdecisionexit");
					d=cal2.getTimeInMillis();
					BpmnShape xor2shape = modelInstance.newInstance(BpmnShape.class);
					xor2shape.setId("xor2Shape"+d);
					xor2shape.setBpmnElement(xorbrackets2);
					processPlane.getDiagramElements().add(xor2shape);
						 
					Bounds xorbrackets2Bounds = modelInstance.newInstance(Bounds.class);
					xorbrackets2Bounds.setHeight(50.0);
					xorbrackets2Bounds.setWidth(100.0);
					xorbrackets2Bounds.setX(xaxis+70.0);
					xaxis = xaxis+70.0;
					xorbrackets2Bounds.setY(yaxis+70.0);
					yaxis = yaxis+70.0;
					xor2shape.setBounds(xorbrackets2Bounds);
					
					String insideTerm = term1.substring(1, term1.length() - 1);
					System.out.println("the inside term is:" + insideTerm);
					int listpointer2 = listpointer;

					listpointer = listpointer + 1;
					firstacts.add(xorbrackets);
					
					firstacts.add(xorbrackets);
					BpmnModelElementInstance xbrackets = createMessagePartners(insideTerm, process, xorbrackets);

					d = cal2.getTimeInMillis();
					SequenceFlow bracketstoendxor = createSequenceFlow(process, (FlowNode) xbrackets, xorbrackets2);
								    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow2Edge"+d);
					flowEdge.setBpmnElement(bracketstoendxor);
					processPlane.getDiagramElements().add(flowEdge);
					
					startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	

					SequenceFlow decisiontoexit = createSequenceFlow(process, (FlowNode) xorbrackets, xorbrackets2);
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow3Edge"+d);
					flowEdge.setBpmnElement(decisiontoexit);
					processPlane.getDiagramElements().add(flowEdge);
					
					 startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);
					
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
					listpointer = listpointer2;

					actprevious = xorbrackets2;
					setpreviousshape(xor2shape);
				} else if (foreverTermMatcher.find()
						&& (foreverTermMatcher.group().length() == term1.length())) {
					// activity x~
					d = cal2.getTimeInMillis();
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xortilda = createElement(process, "xor1tilda"+d, ExclusiveGateway.class);
					xortilda.setName("tildadecision");
				
					d=cal2.getTimeInMillis();
					BpmnShape xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
					xorshapebrackets.setId("xor1Shape"+d);
					xorshapebrackets.setBpmnElement(xortilda);
					processPlane.getDiagramElements().add(xorshapebrackets);
					 
					 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
					 xorbracketsBounds.setHeight(50.0);
					 xorbracketsBounds.setWidth(100.0);
					 xorbracketsBounds.setX(xaxis+70.0);
					 xaxis = xaxis+70.0;
					 xorbracketsBounds.setY(yaxis+70.0);
					 yaxis = yaxis+70.0;
					 xorshapebrackets.setBounds(xorbracketsBounds);
					 
					 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, xortilda);

					BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow2Edge"+d);
					try {
						flowEdge.setBpmnElement(flow1);
					} catch (Exception e1) {
					}
					processPlane.getDiagramElements().add(flowEdge);
					
					 Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
					
					d = cal2.getTimeInMillis();
					activityid = activityid + 1;
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xortilda2 = createElement(process, "xortilda2"+d, ExclusiveGateway.class);
					xortilda2.setName("tildadecisionexit");
					d=cal2.getTimeInMillis();
					BpmnShape xor2shape = modelInstance.newInstance(BpmnShape.class);
					xor2shape.setId("xor2Shape"+d);
					xor2shape.setBpmnElement(xortilda2);
					processPlane.getDiagramElements().add(xor2shape);
						 
					Bounds xorbrackets2Bounds = modelInstance.newInstance(Bounds.class);
					xorbrackets2Bounds.setHeight(50.0);
					xorbrackets2Bounds.setWidth(100.0);
					xorbrackets2Bounds.setX(xaxis+70.0);
					xaxis = xaxis+70.0;
					xorbrackets2Bounds.setY(yaxis+70.0);
					yaxis = yaxis+70.0;
					xor2shape.setBounds(xorbrackets2Bounds);

					d = cal2.getTimeInMillis();
					activityid = activityid + 1;

					System.out.println("A tilda term is being processed");
					String insideTerm = term1.substring(0, term1.length() - 1);
					int listpointer2 = listpointer;

					listpointer = listpointer + 1;
					firstacts.add(xortilda);
					
					BpmnModelElementInstance xtilda = createMessagePartners(insideTerm, process, xortilda);
					
					d = cal2.getTimeInMillis();
					SequenceFlow tildatoendxor = createSequenceFlow(process, (FlowNode) xtilda, xortilda2);
				
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow3Edge"+d);
					flowEdge.setBpmnElement(tildatoendxor);
					processPlane.getDiagramElements().add(flowEdge);
					
					startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	

					d = cal2.getTimeInMillis();
					SequenceFlow decisiontoexit = createSequenceFlow(process, xortilda2, xortilda);
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow4Edge"+d);
					flowEdge.setBpmnElement(decisiontoexit);
					processPlane.getDiagramElements().add(flowEdge);
					
					startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	

					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
					listpointer = listpointer2;

					actprevious = xortilda2;
					setpreviousshape(xor2shape);
				} else if (oneOrMoreTimesTermMatcher.find()
						&& (oneOrMoreTimesTermMatcher.group().length() == term1
								.length())) {
					// activity x+
					d = cal2.getTimeInMillis();
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorplus = createElement(process, "xor1plus"+d, ExclusiveGateway.class);
					xorplus.setName("plusdecision");
					d=cal2.getTimeInMillis();
					BpmnShape xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
					xorshapebrackets.setId("xor1Shape"+d);
					xorshapebrackets.setBpmnElement(xorplus);
					processPlane.getDiagramElements().add(xorshapebrackets);
					 
					 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
					 xorbracketsBounds.setHeight(50.0);
					 xorbracketsBounds.setWidth(100.0);
					 xorbracketsBounds.setX(xaxis+70.0);
					 xaxis = xaxis+70.0;
					 xorbracketsBounds.setY(yaxis+70.0);
					 yaxis = yaxis+70.0;
					 xorshapebrackets.setBounds(xorbracketsBounds);
					 
					 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, xorplus);

					 d = cal2.getTimeInMillis();
					BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow1Edge"+d);
					try {
						flowEdge.setBpmnElement(flow1);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}
					processPlane.getDiagramElements().add(flowEdge);
						
					Waypoint  startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					Waypoint  endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
					 
					d = cal2.getTimeInMillis();
					activityid = activityid + 1;
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorplus2 = createElement(process, "xorplus2"+d, ExclusiveGateway.class);
					xorplus2.setName("plusdecisionexit");
					d=cal2.getTimeInMillis();
					BpmnShape xor2shape = modelInstance.newInstance(BpmnShape.class);
					xor2shape.setId("xor2Shape"+d);
					xor2shape.setBpmnElement(xorplus2);
					processPlane.getDiagramElements().add(xor2shape);
						 
					Bounds xorbrackets2Bounds = modelInstance.newInstance(Bounds.class);
					xorbrackets2Bounds.setHeight(50.0);
					xorbrackets2Bounds.setWidth(100.0);
					xorbrackets2Bounds.setX(xaxis+70.0);
					xaxis = xaxis+70.0;
					xorbrackets2Bounds.setY(yaxis+70.0);
					yaxis = yaxis+70.0;
					xor2shape.setBounds(xorbrackets2Bounds);

					d = cal2.getTimeInMillis();
					activityid = activityid + 1;

					System.out.println("A plus term is being processed");
					String insideTerm = term1.substring(0, term1.length() - 1);
					int listpointer2 = listpointer;

					listpointer = listpointer + 1;
					firstacts.add(xorplus);
					
					BpmnModelElementInstance xtilda = createMessagePartners(insideTerm, process, xorplus);
					
					d = cal2.getTimeInMillis();
					SequenceFlow tildatoendxor = createSequenceFlow(process, (FlowNode) xtilda, xorplus2);
				
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow2Edge"+d);
					flowEdge.setBpmnElement(tildatoendxor);
				
					processPlane.getDiagramElements().add(flowEdge);
					
					startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	

					d = cal2.getTimeInMillis();
					SequenceFlow decisiontoexit = createSequenceFlow(process, xorplus2, xorplus);
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow3Edge"+d);
					flowEdge.setBpmnElement(decisiontoexit);
				
					processPlane.getDiagramElements().add(flowEdge);
					
					startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
					listpointer = listpointer2;
					actprevious = xorplus2;
					setpreviousshape(xor2shape);
				} else if ((zeroOrMoreTimesTermMatcher.find() && (zeroOrMoreTimesTermMatcher
						.group().length() == term1.length()))) {
					// activity x*
					d = cal2.getTimeInMillis();
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorstar = modelInstance.newInstance(ExclusiveGateway.class);
					xorstar.setName("stardecision");
					xorstar.setId("xor"+d);
					d=cal2.getTimeInMillis();
					process.addChildElement(xorstar);
					
					xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
					xorshapebrackets.setId("xor1Shape"+d);
					xorshapebrackets.setBpmnElement(xorstar);
					processPlane.addChildElement(xorshapebrackets);
					
					 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
					 xorbracketsBounds.setHeight(50.0);
					 xorbracketsBounds.setWidth(100.0);
					 xorbracketsBounds.setX(xaxis+70.0);
					 xaxis = xaxis+70.0;
					 xorbracketsBounds.setY(yaxis+70.0);
					 yaxis = yaxis+70.0;
					 xorshapebrackets.setBounds(xorbracketsBounds);
					 
					 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, xorstar);

					 d = cal2.getTimeInMillis();
					BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow1Edge"+d);
					try {
						flowEdge.setBpmnElement(flow1);
					} catch (Exception e1) {
					}
					try {
						flowEdge.setSourceElement(previousshape);
					} catch (Exception e1) {
					}
					flowEdge.setTargetElement(xorshapebrackets);
					processPlane.getDiagramElements().add(flowEdge);
					
					Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);
					 
					d = cal2.getTimeInMillis();
					activityid = activityid + 1;
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorstar2 = createElement(process, "xorstar2"+d, ExclusiveGateway.class);
					xorstar2.setName("stardecisionexit");
					d=cal2.getTimeInMillis();
					BpmnShape xor2shape = modelInstance.newInstance(BpmnShape.class);
					xor2shape.setId("xor2Shape"+d);
					xor2shape.setBpmnElement(xorstar2);
					processPlane.getDiagramElements().add(xor2shape);
						 
					Bounds xorbrackets2Bounds = modelInstance.newInstance(Bounds.class);
					xorbrackets2Bounds.setHeight(50.0);
					xorbrackets2Bounds.setWidth(100.0);
					xorbrackets2Bounds.setX(xaxis+70.0);
					xaxis = xaxis+70.0;
					xorbrackets2Bounds.setY(yaxis+70.0);
					yaxis = yaxis+70.0;
					xor2shape.setBounds(xorbrackets2Bounds);

					d = cal2.getTimeInMillis();
					activityid = activityid + 1;

					System.out.println("A star term is being processed");
					String insideTerm = term1.substring(0, term1.length() - 1);
					int listpointer2 = listpointer;

					listpointer = listpointer + 1;
					firstacts.add(xorstar);
					
					BpmnModelElementInstance xstar = createMessagePartners(insideTerm, process, xorstar);
					
					d = cal2.getTimeInMillis();
					SequenceFlow startoendxor = createSequenceFlow(process, (FlowNode) xstar, xorstar2);					
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow2Edge"+d);
					flowEdge.setBpmnElement(startoendxor);
				
					processPlane.getDiagramElements().add(flowEdge);
					
					startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);					
				    
					d = cal2.getTimeInMillis();
					SequenceFlow endtostartxor = createSequenceFlow(process, (FlowNode) xorstar, xorstar2);
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow3Edge"+d);
					flowEdge.setBpmnElement(endtostartxor);
	
					processPlane.getDiagramElements().add(flowEdge);
					
					startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	

					d = cal2.getTimeInMillis();
					SequenceFlow decisiontoexit = createSequenceFlow(process, xorstar2, xorstar);
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow4Edge"+d);
					flowEdge.setBpmnElement(decisiontoexit);
					processPlane.getDiagramElements().add(flowEdge);
					
					startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);
					    
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
					listpointer = listpointer2;
					actprevious = xorstar2;
					setpreviousshape(xor2shape);
				} else if (basicTermMatcher.find()) {
					// basic activity x
					if (formulas.keySet().contains(term1)) {
						BpmnModelElementInstance act = handleBasicTerm(term1, process2, actprevious);
						actprevious = act;
					}else if(term1.startsWith("ReceiveResponse")){
						//receive we made as receiveTask
						String rolename1 = Liveness2BPMN.getparticipantname();
						receivers.add(rolename1+":"+term1);
						d = cal2.getTimeInMillis();
						xaxis=xaxis+70.0;
					    yaxis=yaxis+70.0;
						ReceiveTask messagetemp = createElement(process2, term1+"task"+d, ReceiveTask.class);
						messagetemp.setName(term1);
						receiverids.add(term1+"task"+d);
						 BpmnShape taskshape = modelInstance.newInstance(BpmnShape.class);
						 taskshape.setId("taskShape"+term1+d);
						 taskshape.setBpmnElement(messagetemp);
						 processPlane.getDiagramElements().add(taskshape);
						
						 receivershapes.add(taskshape);
						 
						 Bounds taskBounds = modelInstance.newInstance(Bounds.class);
						 taskBounds.setHeight(50.0);
						 taskBounds.setWidth(100.0);
						 taskBounds.setX(xaxis+70.0);
						 xaxis = xaxis+70.0;
						 taskBounds.setY(yaxis+70.0);
						 yaxis = yaxis+70.0;
						 taskshape.setBounds(taskBounds);
						 
						//prosoxi edo  ksekiname
							xaxis=xaxis+70.0;
						    yaxis=yaxis+70.0;
							ParallelGateway xorstar = modelInstance.newInstance(ParallelGateway.class);
							xorstar.setName("stardecision");
							xorstar.setId("xor"+d);
							d=cal2.getTimeInMillis();
							process.addChildElement(xorstar);
							
							xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
							xorshapebrackets.setId("xor1Shape"+d);
							xorshapebrackets.setBpmnElement(xorstar);
							processPlane.addChildElement(xorshapebrackets);
							 receivernodes.add(xorstar);
							messagereceivershape.add(xorshapebrackets);
							
							 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
							 xorbracketsBounds.setHeight(50.0);
							 xorbracketsBounds.setWidth(100.0);
							 xorbracketsBounds.setX(xaxis+70.0);
							 xaxis = xaxis+70.0;
							 xorbracketsBounds.setY(yaxis+70.0);
							 yaxis = yaxis+70.0;
							 xorshapebrackets.setBounds(xorbracketsBounds);
							 
						SequenceFlow flow = createSequenceFlow(process2, (FlowNode) actprevious, xorstar);
					 
						d = cal2.getTimeInMillis();
						BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flowEdge"+d+"a");		    
						flowEdge.setBpmnElement(flow);
					    flowEdge.setSourceElement(previousshape);
					    flowEdge.setTargetElement(xorshapebrackets);
					    processPlane.getDiagramElements().add(flowEdge);
					
					    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					    
					    SequenceFlow xortoreceiveflow = createSequenceFlow(process2, (FlowNode) xorstar, messagetemp);
						 
						d = cal2.getTimeInMillis();
						flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flowEdge"+d+d);		    
						flowEdge.setBpmnElement(xortoreceiveflow);
					    flowEdge.setSourceElement(xorshapebrackets);
					    flowEdge.setTargetElement(taskshape);
					    processPlane.getDiagramElements().add(flowEdge);
					
					    startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					
						actprevious = messagetemp;
						setpreviousshape(taskshape);
					}else if(term1.startsWith("Receive")){
						//receive we made as receiveTask
						String rolename1 = Liveness2BPMN.getparticipantname();
						receivers.add(rolename1+":"+term1);
						d = cal2.getTimeInMillis();
						xaxis=xaxis+70.0;
					    yaxis=yaxis+70.0;
						ReceiveTask messagetemp = createElement(process2, term1+"task"+d, ReceiveTask.class);
						messagetemp.setName(term1);
						receiverids.add(term1+"task"+d);
						 BpmnShape taskshape = modelInstance.newInstance(BpmnShape.class);
						 taskshape.setId("taskShape"+term1+d);
						 taskshape.setBpmnElement(messagetemp);
						 processPlane.getDiagramElements().add(taskshape);
						 receivernodes.add(messagetemp);
						 receivershapes.add(taskshape);
						 
						messagereceivershape.add(taskshape);
						 
						 Bounds taskBounds = modelInstance.newInstance(Bounds.class);
						 taskBounds.setHeight(50.0);
						 taskBounds.setWidth(100.0);
						 taskBounds.setX(xaxis+70.0);
						 xaxis = xaxis+70.0;
						 taskBounds.setY(yaxis+70.0);
						 yaxis = yaxis+70.0;
						 taskshape.setBounds(taskBounds);
		
						SequenceFlow flow = createSequenceFlow(process2, (FlowNode) actprevious, (ReceiveTask) messagetemp);
												 
						d = cal2.getTimeInMillis();
						BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flowEdge"+d+"e");
					    try {
							flowEdge.setBpmnElement(flow);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						}
					    try {
							flowEdge.setSourceElement(previousshape);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
					    flowEdge.setTargetElement(taskshape);
					    processPlane.getDiagramElements().add(flowEdge);
					
					    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					
						actprevious = messagetemp;
						setpreviousshape(taskshape);	
					}else if (term1.startsWith("SendRequest")) {	
						//send we made as sendTask
						String rolename1 = liveness2bpmn.getparticipantname();
						senders.add(rolename1+":"+term1);
						d = cal2.getTimeInMillis();
						xaxis=xaxis+70.0;
					    yaxis=yaxis+70.0;
						 SendTask activitytemp = createElement(process2, term1+"task"+d, SendTask.class);
						activitytemp.setName(term1);
						senderids.add(term1+"task"+d);
						 BpmnShape taskshape = modelInstance.newInstance(BpmnShape.class);
						 taskshape.setId("taskShape"+term1+d);
						 taskshape.setBpmnElement(activitytemp);
						 processPlane.getDiagramElements().add(taskshape);
						 
						 sendershapes.add(taskshape);
						 
						 Bounds taskBounds = modelInstance.newInstance(Bounds.class);
						 taskBounds.setHeight(50.0);
						 taskBounds.setWidth(100.0);
						 taskBounds.setX(xaxis+70.0);
						 xaxis = xaxis+70.0;
						 taskBounds.setY(yaxis+70.0);
						 yaxis = yaxis+70.0;
						 taskshape.setBounds(taskBounds);
						 
						 //prosoxi edo  ksekiname
							xaxis=xaxis+70.0;
						    yaxis=yaxis+70.0;
							ParallelGateway xorstar = modelInstance.newInstance(ParallelGateway.class);
							xorstar.setName("stardecision");
							xorstar.setId("xor"+d);
							d=cal2.getTimeInMillis();
							process.addChildElement(xorstar);
							
							xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
							xorshapebrackets.setId("xor1Shape"+d);
							xorshapebrackets.setBpmnElement(xorstar);
							processPlane.addChildElement(xorshapebrackets);
							sendernodes.add(xorstar);
							messagesendershape.add(xorshapebrackets);
							
							 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
							 xorbracketsBounds.setHeight(50.0);
							 xorbracketsBounds.setWidth(100.0);
							 xorbracketsBounds.setX(xaxis+70.0);
							 xaxis = xaxis+70.0;
							 xorbracketsBounds.setY(yaxis+70.0);
							 yaxis = yaxis+70.0;
							 xorshapebrackets.setBounds(xorbracketsBounds);
							 
							 SequenceFlow flow1 = createSequenceFlow(process2, (FlowNode) actprevious, activitytemp);

							 d = cal2.getTimeInMillis();
							BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
							flowEdge.setId("flow1Edge"+d);
							try {
								flowEdge.setBpmnElement(flow1);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
							}
							try {
								flowEdge.setSourceElement(previousshape);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
							}
							flowEdge.setTargetElement(xorshapebrackets);
							processPlane.getDiagramElements().add(flowEdge);
							
							    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
							    startWaypoint.setX(668.0);
							    startWaypoint.setY(330.0);
							    flowEdge.getWaypoints().add(startWaypoint);

							    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
							    endWaypoint.setX(718.0);
							    endWaypoint.setY(330.0);
							    flowEdge.getWaypoints().add(endWaypoint);
							 
							 flow1 = createSequenceFlow(process2, (FlowNode) activitytemp, xorstar);

							 d = cal2.getTimeInMillis();
							flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
							flowEdge.setId("flow1Edge"+d+d);
							flowEdge.setBpmnElement(flow1);
							try {
								flowEdge.setSourceElement(previousshape);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
							}
							flowEdge.setTargetElement(xorshapebrackets);
							processPlane.getDiagramElements().add(flowEdge);
							
							    startWaypoint = modelInstance.newInstance(Waypoint.class);
							    startWaypoint.setX(668.0);
							    startWaypoint.setY(330.0);
							    flowEdge.getWaypoints().add(startWaypoint);

							    endWaypoint = modelInstance.newInstance(Waypoint.class);
							    endWaypoint.setX(718.0);
							    endWaypoint.setY(330.0);
							    flowEdge.getWaypoints().add(endWaypoint);
							 
						actprevious = xorstar;
						setpreviousshape(xorshapebrackets);	
					}else if(term1.startsWith("Send")){

						//send we made as sendTask
						String rolename1 = Liveness2BPMN.getparticipantname();
						senders.add(rolename1+":"+term1);
						d = cal2.getTimeInMillis();
						xaxis=xaxis+70.0;
					    yaxis=yaxis+70.0;
						 SendTask activitytemp = createElement(process2, term1+"task"+d, SendTask.class);
						activitytemp.setName(term1);
						senderids.add(term1+"task"+d);
						 BpmnShape taskshape = modelInstance.newInstance(BpmnShape.class);
						 taskshape.setId("taskShape"+term1+d);
						 taskshape.setBpmnElement(activitytemp);
						 processPlane.getDiagramElements().add(taskshape);
						 sendernodes.add(activitytemp);
						 sendershapes.add(taskshape);
						messagesendershape.add(taskshape);
						
						 Bounds taskBounds = modelInstance.newInstance(Bounds.class);
						 taskBounds.setHeight(50.0);
						 taskBounds.setWidth(100.0);
						 taskBounds.setX(xaxis+70.0);
						 xaxis = xaxis+70.0;
						 taskBounds.setY(yaxis+70.0);
						 yaxis = yaxis+70.0;
						 taskshape.setBounds(taskBounds);
		
						SequenceFlow flow = createSequenceFlow(process2, (FlowNode) actprevious, (SendTask) activitytemp);
												 
						d = cal2.getTimeInMillis();
						BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flowEdge"+d+"r");
					    flowEdge.setBpmnElement(flow);
					    try {
							flowEdge.setSourceElement(previousshape);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
					    flowEdge.setTargetElement(taskshape);
					    processPlane.getDiagramElements().add(flowEdge);
					
					    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					
						actprevious = activitytemp;
						setpreviousshape(taskshape);
					}else{
						//simple task for general
						d = cal2.getTimeInMillis();
						xaxis=xaxis+70.0;
					    yaxis=yaxis+70.0;
						Task activitytemp = createElement(process2, term1+"task"+d, Task.class);

						activitytemp.setName(term1);
						BpmnShape taskshape = modelInstance.newInstance(BpmnShape.class);
						taskshape.setId("taskShape"+term1+d);
						taskshape.setBpmnElement(activitytemp);
						processPlane.getDiagramElements().add(taskshape);
						 
						Bounds taskBounds = modelInstance.newInstance(Bounds.class);
						taskBounds.setHeight(50.0);
						taskBounds.setWidth(100.0);
						taskBounds.setX(xaxis+70.0);
						xaxis = xaxis+70.0;
						taskBounds.setY(yaxis+70.0);
						yaxis = yaxis+70.0;
						taskshape.setBounds(taskBounds);
		
						SequenceFlow flow = createSequenceFlow(process, (FlowNode) actprevious, (Task) activitytemp);
						
						d = cal2.getTimeInMillis();
						BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flowEdge"+d);
					    try {
							flowEdge.setBpmnElement(flow);
						} catch (Exception e1) {
						}
					    try {
							flowEdge.setSourceElement(previousshape);
						} catch (Exception e) {
						}
					    flowEdge.setTargetElement(taskshape);
					    processPlane.getDiagramElements().add(flowEdge);
					
					    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					    
						actprevious = activitytemp;
						setpreviousshape(taskshape);
					}
				}
			}
			return actprevious;
	}
	  
	  BpmnModelElementInstance createProcessrecursively(String expression, Process process2,
			  BpmnModelElementInstance actprevious) {
		// TODO Auto-generated method stub
		  Calendar cal2 = Calendar.getInstance();
			long d = cal2.getTimeInMillis();
			int expressionType = 0;
			// pattern for parallelExpression
			Pattern patternParallelExpression = Pattern
					.compile("(((\\|.+~|(\\d))|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)\\|\\|)+((\\|.+~\\|(\\d))|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)");
			Matcher parallelMatcher = patternParallelExpression.matcher(expression);
			// pattern for orExpression : expressionType=2
			Pattern patternOrExpression = Pattern
					.compile("(((\\|.+~|(\\d))|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)\\|)+((\\|.+~\\|(\\d))|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)");
			Matcher orMatcher = patternOrExpression.matcher(expression);
			// pattern for sequentialExpression : expressionType=3
			Pattern patternSequentialExpression = Pattern
					.compile("(((\\|.+~\\|\\d)|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)\\.)+((\\|.+~\\|\\d)|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)");

			Matcher sequentialMatcher = patternSequentialExpression
					.matcher(expression);

			List<String> myTerms = null;

			switch (expressionType) {
			case 0:
				myTerms = new LinkedList<String>();
				myTerms.add(expression);
				break;
			}
			
			if (sequentialMatcher.find()
					&& (sequentialMatcher.group().length() == expression.length())) {
				expressionType = 3;

				System.out.print("a sequential expression processed: " + expression
						+ "\n");
				setconnector(".");
				List<String> seqterms = this.findTermsInExpression(expression, ".",
						process2);

				System.out.println("Seqterms are:" + seqterms);

				int listpointer2 = listpointer;
				int b = 0;
				for (b = 0; b < seqterms.size(); b++) {
					String currentterm = seqterms.get(b);
					BpmnModelElementInstance seqact = createProcessrecursively(currentterm, process, actprevious);
					listpointer = listpointer + 1;
					actprevious = seqact;
				}
				int i = 0;
				for (i = 0; i < seqterms.size(); i++) {
					listpointer = listpointer - 1;
					if (listpointer == listpointer2) {
						break;
					}
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
				}
			}else if (orMatcher.find()
					&& (orMatcher.group().length() == expression.length())) {
				expressionType = 2;

				System.out.print("an or expression processed: " + expression + "\n");
				numberoforexpressions = numberoforexpressions + 1;
				System.out.println("The numberoforexpressions is:"+ numberoforexpressions);
				setconnector("|");
				d = cal2.getTimeInMillis();
				xaxis=xaxis+70.0;
			    yaxis=yaxis+70.0;
				ExclusiveGateway xor = createElement(process, "xor1"+d, ExclusiveGateway.class);
				modelInstance.getModelElementById("xor1"+d);
				xor.setName("decision");
				d=cal2.getTimeInMillis();
				BpmnShape xorshape = modelInstance.newInstance(BpmnShape.class);
				xorshape.setId("xor1Shape"+d);
				xorshape.setBpmnElement(xor);
				processPlane.getDiagramElements().add(xorshape);
				 
				 Bounds xorBounds = modelInstance.newInstance(Bounds.class);
				 xorBounds.setHeight(50.0);
				 xorBounds.setWidth(100.0);
				 xorBounds.setX(xaxis+70.0);
				 xaxis = xaxis+70.0;
				 xorBounds.setY(yaxis+70.0);
				 yaxis = yaxis+70.0;
				 xorshape.setBounds(xorBounds);
				 
				 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, xor);
				 BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
				 d = cal2.getTimeInMillis();
				flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
				flowEdge.setId("flow4Edge"+d);
				flowEdge.setBpmnElement(flow1);
				processPlane.getDiagramElements().add(flowEdge);
					
				Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
				startWaypoint.setX(668.0);
				startWaypoint.setY(330.0);
				flowEdge.getWaypoints().add(startWaypoint);

				Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
				endWaypoint.setX(718.0);
				endWaypoint.setY(330.0);
				flowEdge.getWaypoints().add(endWaypoint);	
				 
				d = cal2.getTimeInMillis();
				xaxis=xaxis+70.0;
			    yaxis=yaxis+70.0;
				ExclusiveGateway xor2 = createElement(process, "xor2"+d, ExclusiveGateway.class);
				xor2.setName("decisionexit");
				d=cal2.getTimeInMillis();
				BpmnShape xor2shape = modelInstance.newInstance(BpmnShape.class);
				xor2shape.setId("xor2Shape"+d);
				xor2shape.setBpmnElement(xor2);
				processPlane.getDiagramElements().add(xor2shape);
					 
				Bounds xor2Bounds = modelInstance.newInstance(Bounds.class);
				xor2Bounds.setHeight(50.0);
				xor2Bounds.setWidth(100.0);
				xor2Bounds.setX(xaxis+70.0);
				xaxis = xaxis+70.0;
				xor2Bounds.setY(yaxis+70.0);
				yaxis = yaxis+70.0;
				xor2shape.setBounds(xor2Bounds);
				
				int listpointer2 = listpointer;

				listpointer = listpointer + 1;
				firstacts.add(xor);
				
				List<String> orterms = this.findTermsInExpression(expression, "|",
						process2);
				System.out.println("the orterms are:" + orterms);

				int or = 0;
				for (or = 0; or < orterms.size(); or++) {
					String currentterm = orterms.get(or);
					System.out.println("The current term is:" + currentterm);
					actprevious = xor;
					BpmnModelElementInstance ortemp = createProcessrecursively(currentterm, process, actprevious);
					listpointer = listpointer + 1;
					d = cal2.getTimeInMillis();
					SequenceFlow flow2= createSequenceFlow(process, (FlowNode) ortemp, xor2);
					
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow5Edge"+or+d);
					flowEdge.setBpmnElement(flow2);
					processPlane.getDiagramElements().add(flowEdge);
					
					 startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
				}
				int i = 0;
				for (i = 0; i < orterms.size() + 1; i++) {

					listpointer = listpointer - 1;
					if (listpointer == listpointer2) {
						break;
					}
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
				}
				actprevious = xor2;
				setpreviousshape(xor2shape);
			}else if (parallelMatcher.find()
					&& (parallelMatcher.group().length() == expression.length())) {
				expressionType = 1;
				System.out.print("a parallel expression processed: " + expression
						+ "\n");
				setconnector("||");
				d = cal2.getTimeInMillis();
				//ParallelGateway 
				numberofpara = numberofpara + 1;
				System.out.println("The numberofparaexpressions is:"+ numberofpara);
				d = cal2.getTimeInMillis();
				xaxis=xaxis+70.0;
			    yaxis=yaxis+70.0;
				ParallelGateway para = createElement(process, "para1"+d, ParallelGateway.class);
				modelInstance.getModelElementById("para1"+d);
				para.setName("parallel");
				d=cal2.getTimeInMillis();
				BpmnShape parashape = modelInstance.newInstance(BpmnShape.class);
				parashape.setId("parashape"+d);
				parashape.setBpmnElement(para);
				processPlane.getDiagramElements().add(parashape);
				
				Bounds paraBounds = modelInstance.newInstance(Bounds.class);
				 paraBounds.setHeight(50.0);
				 paraBounds.setWidth(100.0);
				 paraBounds.setX(xaxis+70.0);
				 xaxis = xaxis+70.0;
				 paraBounds.setY(yaxis+70.0);
				 yaxis = yaxis+70.0;
				 parashape.setBounds(paraBounds);
				 
				 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, para);
				 BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow4Edge"+d);
					flowEdge.setBpmnElement(flow1);
					processPlane.getDiagramElements().add(flowEdge);
					
					 Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
				 
				d = cal2.getTimeInMillis();
				xaxis=xaxis+70.0;
			    yaxis=yaxis+70.0;
				ParallelGateway para2 = createElement(process, "para2exit"+d, ParallelGateway.class);
				para2.setName("parallelexit");
				d=cal2.getTimeInMillis();
				BpmnShape para2shape = modelInstance.newInstance(BpmnShape.class);
				para2shape.setId("paraShape"+d);
				para2shape.setBpmnElement(para2);
				processPlane.getDiagramElements().add(para2shape);
					 
				Bounds para2Bounds = modelInstance.newInstance(Bounds.class);
				para2Bounds.setHeight(50.0);
				para2Bounds.setWidth(100.0);
				para2Bounds.setX(xaxis+70.0);
				xaxis = xaxis+70.0;
				para2Bounds.setY(yaxis+70.0);
				yaxis = yaxis+70.0;
				para2shape.setBounds(para2Bounds);
				
				int listpointer2 = listpointer;

				listpointer = listpointer + 1;
				firstacts.add(para);
				
				List<String> paraterms = this.findTermsInExpression(expression,
						"||", process2);
				System.out.println("the parallel terms are:" + paraterms);

				int par = 0;
				for (par = 0; par < paraterms.size(); par++) {
					String currentterm = paraterms.get(par);
					System.out.println("The current term is:" + currentterm);
					actprevious = para;
					
					BpmnModelElementInstance paratemp = createProcessrecursively(currentterm, process, actprevious);
					listpointer = listpointer + 1;
					d = cal2.getTimeInMillis();
					
					SequenceFlow flow = createSequenceFlow(process, (FlowNode) paratemp, para2);
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow5Edge"+par+d);
					flowEdge.setBpmnElement(flow);
					processPlane.getDiagramElements().add(flowEdge);
					
					 startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
				}

				int i = 0;
				for (i = 0; i < paraterms.size() + 1; i++) {

					listpointer = listpointer - 1;
					if (listpointer == listpointer2) {
						break;
					}
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
				}
				actprevious = para2;
				setpreviousshape(para2shape);
			}else {
				String term1 = expression;
				setTerm1(term1);
				System.out.println("term1 is:" + term1);
				//basicterms.add(term1);
				Pattern patternBasicTerm = Pattern.compile("^\\w+$");
				Matcher basicTermMatcher = patternBasicTerm.matcher(term1);
				// pattern for (term)
				Pattern patternComplexParenthesisTerm = Pattern
						.compile("^\\(.+\\)$");
				Matcher complexParenthesisTermMatcher = patternComplexParenthesisTerm
						.matcher(term1);
				// pattern for [term]
				Pattern patternComplexOptionalTerm = Pattern.compile("^\\[.+\\]$");
				Matcher complexOptionalTermMatcher = patternComplexOptionalTerm
						.matcher(term1);
				// pattern for term~
				Pattern patternForeverTerm = Pattern.compile(".+~$");
				Matcher foreverTermMatcher = patternForeverTerm.matcher(term1);
				// pattern for term+
				Pattern patternOneOrMoreTimesTerm = Pattern.compile(".+\\+$");
				Matcher oneOrMoreTimesTermMatcher = patternOneOrMoreTimesTerm
						.matcher(term1);
				// pattern for term*
				Pattern patternZeroOrMoreTimesTerm = Pattern.compile(".+\\*$");
				Matcher zeroOrMoreTimesTermMatcher = patternZeroOrMoreTimesTerm
						.matcher(term1);
				
				if (complexParenthesisTermMatcher.find()
						&& (complexParenthesisTermMatcher.group().length() == term1
								.length())) {
					System.out.println("A parenthesis term is being processed");
					String insideTerm = term1.substring(1, term1.length() - 1);
					System.out.println("the inside term is:" + insideTerm);

					BpmnModelElementInstance parenth = createProcessrecursively(insideTerm, process, actprevious);
					actprevious = parenth;
				} else if (complexOptionalTermMatcher.find()
						&& (complexOptionalTermMatcher.group().length() == term1
								.length())) {
					System.out.println("A brackets term is being processed.");
					// x optional [x]
					d = cal2.getTimeInMillis();
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorbrackets = createElement(process, "bracketsxor1"+d, ExclusiveGateway.class);
					xorbrackets.setName("bracketsdecision");
					d=cal2.getTimeInMillis();
					BpmnShape xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
					xorshapebrackets.setId("xor1Shape"+d);
					xorshapebrackets.setBpmnElement(xorbrackets);
					processPlane.getDiagramElements().add(xorshapebrackets);
					 
					 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
					 xorbracketsBounds.setHeight(50.0);
					 xorbracketsBounds.setWidth(100.0);
					 xorbracketsBounds.setX(xaxis+70.0);
					 xaxis = xaxis+70.0;
					 xorbracketsBounds.setY(yaxis+70.0);
					 yaxis = yaxis+70.0;
					 xorshapebrackets.setBounds(xorbracketsBounds);
					 
					 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, xorbrackets);

					 BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						d = cal2.getTimeInMillis();
						flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flow4Edge"+d);
						flowEdge.setBpmnElement(flow1);
						processPlane.getDiagramElements().add(flowEdge);
						
						 Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
						 startWaypoint.setX(668.0);
						 startWaypoint.setY(330.0);
						 flowEdge.getWaypoints().add(startWaypoint);

						Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
						endWaypoint.setX(718.0);
						endWaypoint.setY(330.0);
						flowEdge.getWaypoints().add(endWaypoint);	
						
					d = cal2.getTimeInMillis();
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorbrackets2 = createElement(process, "xorbrackets2"+d, ExclusiveGateway.class);
					xorbrackets2.setName("bracketsdecisionexit");
					d=cal2.getTimeInMillis();
					BpmnShape xor2shape = modelInstance.newInstance(BpmnShape.class);
					xor2shape.setId("xor2Shape"+d);
					xor2shape.setBpmnElement(xorbrackets2);
					processPlane.getDiagramElements().add(xor2shape);
						 
					Bounds xorbrackets2Bounds = modelInstance.newInstance(Bounds.class);
					xorbrackets2Bounds.setHeight(50.0);
					xorbrackets2Bounds.setWidth(100.0);
					xorbrackets2Bounds.setX(xaxis+70.0);
					xaxis = xaxis+70.0;
					xorbrackets2Bounds.setY(yaxis+70.0);
					yaxis = yaxis+70.0;
					xor2shape.setBounds(xorbrackets2Bounds);
					
					String insideTerm = term1.substring(1, term1.length() - 1);
					System.out.println("the inside term is:" + insideTerm);
					int listpointer2 = listpointer;

					listpointer = listpointer + 1;
					firstacts.add(xorbrackets);
					
					firstacts.add(xorbrackets);
					BpmnModelElementInstance xbrackets = createProcessrecursively(insideTerm, process, xorbrackets);

					d = cal2.getTimeInMillis();
					SequenceFlow bracketstoendxor = createSequenceFlow(process, (FlowNode) xbrackets, xorbrackets2);
								    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow2Edge"+d);
					flowEdge.setBpmnElement(bracketstoendxor);
					processPlane.getDiagramElements().add(flowEdge);
					
					 startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	

					SequenceFlow decisiontoexit = createSequenceFlow(process, (FlowNode) xorbrackets, xorbrackets2);
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow3Edge"+d);
					flowEdge.setBpmnElement(decisiontoexit);
					processPlane.getDiagramElements().add(flowEdge);
					
					 startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);
					
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					listpointer = listpointer2;

					actprevious = xorbrackets2;
					setpreviousshape(xor2shape);
				} else if (foreverTermMatcher.find()
						&& (foreverTermMatcher.group().length() == term1.length())) {
					// activity x~
					d = cal2.getTimeInMillis();
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xortilda = createElement(process, "xor1tilda"+d, ExclusiveGateway.class);
					xortilda.setName("tildadecision");
				
					d=cal2.getTimeInMillis();
					BpmnShape xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
					xorshapebrackets.setId("xor1Shape"+d);
					xorshapebrackets.setBpmnElement(xortilda);
					processPlane.getDiagramElements().add(xorshapebrackets);
					 
					 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
					 xorbracketsBounds.setHeight(50.0);
					 xorbracketsBounds.setWidth(100.0);
					 xorbracketsBounds.setX(xaxis+70.0);
					 xaxis = xaxis+70.0;
					 xorbracketsBounds.setY(yaxis+70.0);
					 yaxis = yaxis+70.0;
					 xorshapebrackets.setBounds(xorbracketsBounds);
					 
					 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, xortilda);

					BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow2Edge"+d);
					flowEdge.setBpmnElement(flow1);
					processPlane.getDiagramElements().add(flowEdge);
					
					 Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
					
					d = cal2.getTimeInMillis();
					activityid = activityid + 1;
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xortilda2 = createElement(process, "xortilda2"+d, ExclusiveGateway.class);
					xortilda2.setName("tildadecisionexit");
					d=cal2.getTimeInMillis();
					BpmnShape xor2shape = modelInstance.newInstance(BpmnShape.class);
					xor2shape.setId("xor2Shape"+d);
					xor2shape.setBpmnElement(xortilda2);
					processPlane.getDiagramElements().add(xor2shape);
						 
					Bounds xorbrackets2Bounds = modelInstance.newInstance(Bounds.class);
					xorbrackets2Bounds.setHeight(50.0);
					xorbrackets2Bounds.setWidth(100.0);
					xorbrackets2Bounds.setX(xaxis+70.0);
					xaxis = xaxis+70.0;
					xorbrackets2Bounds.setY(yaxis+70.0);
					yaxis = yaxis+70.0;
					xor2shape.setBounds(xorbrackets2Bounds);

					d = cal2.getTimeInMillis();
					activityid = activityid + 1;

					System.out.println("A tilda term is being processed");
					String insideTerm = term1.substring(0, term1.length() - 1);
					int listpointer2 = listpointer;

					listpointer = listpointer + 1;
					firstacts.add(xortilda);
					
					BpmnModelElementInstance xtilda = createProcessrecursively(insideTerm, process, xortilda);
					
					d = cal2.getTimeInMillis();
					SequenceFlow tildatoendxor = createSequenceFlow(process, (FlowNode) xtilda, xortilda2);
				
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow3Edge"+d);
					flowEdge.setBpmnElement(tildatoendxor);
					processPlane.getDiagramElements().add(flowEdge);
					
					startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	

					d = cal2.getTimeInMillis();
					SequenceFlow decisiontoexit = createSequenceFlow(process, xortilda2, xortilda);
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow4Edge"+d);
					flowEdge.setBpmnElement(decisiontoexit);
					processPlane.getDiagramElements().add(flowEdge);
					
					startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
					listpointer = listpointer2;
					actprevious = xortilda2;
					setpreviousshape(xor2shape);
				} else if (oneOrMoreTimesTermMatcher.find()
						&& (oneOrMoreTimesTermMatcher.group().length() == term1
								.length())) {
					// activity x+
					d = cal2.getTimeInMillis();
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorplus = createElement(process, "xor1plus"+d, ExclusiveGateway.class);
					xorplus.setName("plusdecision");
					d=cal2.getTimeInMillis();
					BpmnShape xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
					xorshapebrackets.setId("xor1Shape"+d);
					xorshapebrackets.setBpmnElement(xorplus);
					processPlane.getDiagramElements().add(xorshapebrackets);
					 
					 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
					 xorbracketsBounds.setHeight(50.0);
					 xorbracketsBounds.setWidth(100.0);
					 xorbracketsBounds.setX(xaxis+70.0);
					 xaxis = xaxis+70.0;
					 xorbracketsBounds.setY(yaxis+70.0);
					 yaxis = yaxis+70.0;
					 xorshapebrackets.setBounds(xorbracketsBounds);
					 
					 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, xorplus);

					 d = cal2.getTimeInMillis();
					BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow1Edge"+d);
					flowEdge.setBpmnElement(flow1);
					
					processPlane.getDiagramElements().add(flowEdge);
						
					Waypoint  startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					Waypoint  endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
					 
					d = cal2.getTimeInMillis();
					activityid = activityid + 1;
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorplus2 = createElement(process, "xorplus2"+d, ExclusiveGateway.class);
					xorplus2.setName("plusdecisionexit");
					d=cal2.getTimeInMillis();
					BpmnShape xor2shape = modelInstance.newInstance(BpmnShape.class);
					xor2shape.setId("xor2Shape"+d);
					xor2shape.setBpmnElement(xorplus2);
					processPlane.getDiagramElements().add(xor2shape);
						 
					Bounds xorbrackets2Bounds = modelInstance.newInstance(Bounds.class);
					xorbrackets2Bounds.setHeight(50.0);
					xorbrackets2Bounds.setWidth(100.0);
					xorbrackets2Bounds.setX(xaxis+70.0);
					xaxis = xaxis+70.0;
					xorbrackets2Bounds.setY(yaxis+70.0);
					yaxis = yaxis+70.0;
					xor2shape.setBounds(xorbrackets2Bounds);

					d = cal2.getTimeInMillis();
					activityid = activityid + 1;

					System.out.println("A plus term is being processed");
					String insideTerm = term1.substring(0, term1.length() - 1);
					int listpointer2 = listpointer;

					listpointer = listpointer + 1;
					firstacts.add(xorplus);
					
					BpmnModelElementInstance xtilda = createProcessrecursively(insideTerm, process, xorplus);
					
					d = cal2.getTimeInMillis();
					SequenceFlow tildatoendxor = createSequenceFlow(process, (FlowNode) xtilda, xorplus2);
				
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow2Edge"+d);
					flowEdge.setBpmnElement(tildatoendxor);
				
					processPlane.getDiagramElements().add(flowEdge);
					
					startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	

					d = cal2.getTimeInMillis();
					SequenceFlow decisiontoexit = createSequenceFlow(process, xorplus2, xorplus);
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow3Edge"+d);
					flowEdge.setBpmnElement(decisiontoexit);
				
					processPlane.getDiagramElements().add(flowEdge);
					
					    startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);	
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					listpointer = listpointer2;
					actprevious = xorplus2;
					setpreviousshape(xor2shape);
				} else if ((zeroOrMoreTimesTermMatcher.find() && (zeroOrMoreTimesTermMatcher
						.group().length() == term1.length()))) {
					// activity x*
					d = cal2.getTimeInMillis();
					//ExclusiveGateway xorstar = createElement(process, "xor1star"+d, ExclusiveGateway.class);
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorstar = modelInstance.newInstance(ExclusiveGateway.class);
					xorstar.setName("stardecision");
					xorstar.setId("xor"+d);
					d=cal2.getTimeInMillis();
					process.addChildElement(xorstar);
					
					xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
					xorshapebrackets.setId("xor1Shape"+d);
					xorshapebrackets.setBpmnElement(xorstar);
					processPlane.addChildElement(xorshapebrackets);
					
					 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
					 xorbracketsBounds.setHeight(50.0);
					 xorbracketsBounds.setWidth(100.0);
					 xorbracketsBounds.setX(xaxis+70.0);
					 xaxis = xaxis+70.0;
					 xorbracketsBounds.setY(yaxis+70.0);
					 yaxis = yaxis+70.0;
					 xorshapebrackets.setBounds(xorbracketsBounds);
					 
					 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, xorstar);

					 d = cal2.getTimeInMillis();
					BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow1Edge"+d);
					flowEdge.setBpmnElement(flow1);
					flowEdge.setSourceElement(previousshape);
					flowEdge.setTargetElement(xorshapebrackets);
					processPlane.getDiagramElements().add(flowEdge);
					
					    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					 
					d = cal2.getTimeInMillis();
					activityid = activityid + 1;
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorstar2 = createElement(process, "xorstar2"+d, ExclusiveGateway.class);
					xorstar2.setName("stardecisionexit");
					d=cal2.getTimeInMillis();
					BpmnShape xor2shape = modelInstance.newInstance(BpmnShape.class);
					xor2shape.setId("xor2Shape"+d);
					xor2shape.setBpmnElement(xorstar2);
					processPlane.getDiagramElements().add(xor2shape);
						 
					Bounds xorbrackets2Bounds = modelInstance.newInstance(Bounds.class);
					xorbrackets2Bounds.setHeight(50.0);
					xorbrackets2Bounds.setWidth(100.0);
					xorbrackets2Bounds.setX(xaxis+70.0);
					xaxis = xaxis+70.0;
					xorbrackets2Bounds.setY(yaxis+70.0);
					yaxis = yaxis+70.0;
					xor2shape.setBounds(xorbrackets2Bounds);

					d = cal2.getTimeInMillis();
					activityid = activityid + 1;

					System.out.println("A star term is being processed");
					String insideTerm = term1.substring(0, term1.length() - 1);
					int listpointer2 = listpointer;

					listpointer = listpointer + 1;
					firstacts.add(xorstar);
					
					BpmnModelElementInstance xstar = createProcessrecursively(insideTerm, process, xorstar);
					
					d = cal2.getTimeInMillis();
					SequenceFlow startoendxor = createSequenceFlow(process, (FlowNode) xstar, xorstar2);
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow2Edge"+d);
					flowEdge.setBpmnElement(startoendxor);
				
					processPlane.getDiagramElements().add(flowEdge);
				
					    startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);					
				    
					d = cal2.getTimeInMillis();
					SequenceFlow endtostartxor = createSequenceFlow(process, (FlowNode) xorstar, xorstar2);
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow3Edge"+d);
					flowEdge.setBpmnElement(endtostartxor);
	
					processPlane.getDiagramElements().add(flowEdge);
					
					    startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);	

					d = cal2.getTimeInMillis();
					SequenceFlow decisiontoexit = createSequenceFlow(process, xorstar2, xorstar);
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow4Edge"+d);
					flowEdge.setBpmnElement(decisiontoexit);
					processPlane.getDiagramElements().add(flowEdge);
					
					    startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					    
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
//						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					listpointer = listpointer2;

					actprevious = xorstar2;
					setpreviousshape(xor2shape);
				} else if (basicTermMatcher.find()) {
					// basic activity x
					if (formulas.keySet().contains(term1)) {
						BpmnModelElementInstance act = handleBasicTerm(term1, process2, actprevious);
						actprevious = act;
					}else if(term1.startsWith("Receive")){
						//receive we made as receiveTask
						String rolename1 = Liveness2BPMN.getparticipantname();
						receivers.add(rolename1+":"+term1);
						d = cal2.getTimeInMillis();
						xaxis=xaxis+70.0;
					    yaxis=yaxis+70.0;
						ReceiveTask messagetemp = createElement(process2, term1+"task"+d, ReceiveTask.class);
						messagetemp.setName(term1);
						receiverids.add(term1+"task"+d);
						 BpmnShape taskshape = modelInstance.newInstance(BpmnShape.class);
						 taskshape.setId("taskShape"+term1+d);
						 taskshape.setBpmnElement(messagetemp);
						 processPlane.getDiagramElements().add(taskshape);
						 receivernodes.add(messagetemp);
						 receivershapes.add(taskshape);
						 
						 Bounds taskBounds = modelInstance.newInstance(Bounds.class);
						 taskBounds.setHeight(50.0);
						 taskBounds.setWidth(100.0);
						 taskBounds.setX(xaxis+70.0);
						 xaxis = xaxis+70.0;
						 taskBounds.setY(yaxis+70.0);
						 yaxis = yaxis+70.0;
						 taskshape.setBounds(taskBounds);
		
						SequenceFlow flow = createSequenceFlow(process, (FlowNode) actprevious, (ReceiveTask) messagetemp);
												 
						d = cal2.getTimeInMillis();
						BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flowEdge"+d+"e");
					    flowEdge.setBpmnElement(flow);
					    flowEdge.setSourceElement(previousshape);
					    flowEdge.setTargetElement(taskshape);
					    processPlane.getDiagramElements().add(flowEdge);
					
					    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					
						actprevious = messagetemp;
						setpreviousshape(taskshape);
					}else if (term1.startsWith("Send")) {
						//send we made as sendTask
						String rolename1 = Liveness2BPMN.getparticipantname();
						senders.add(rolename1+":"+term1);
						d = cal2.getTimeInMillis();
						xaxis=xaxis+70.0;
					    yaxis=yaxis+70.0;
						 SendTask activitytemp = createElement(process2, term1+"task"+d, SendTask.class);
						activitytemp.setName(term1);
						senderids.add(term1+"task"+d);
						 BpmnShape taskshape = modelInstance.newInstance(BpmnShape.class);
						 taskshape.setId("taskShape"+term1+d);
						 taskshape.setBpmnElement(activitytemp);
						 processPlane.getDiagramElements().add(taskshape);
						 sendernodes.add(activitytemp);
						 sendershapes.add(taskshape);
						 
						 Bounds taskBounds = modelInstance.newInstance(Bounds.class);
						 taskBounds.setHeight(50.0);
						 taskBounds.setWidth(100.0);
						 taskBounds.setX(xaxis+70.0);
						 xaxis = xaxis+70.0;
						 taskBounds.setY(yaxis+70.0);
						 yaxis = yaxis+70.0;
						 taskshape.setBounds(taskBounds);
		
						SequenceFlow flow = createSequenceFlow(process, (FlowNode) actprevious, (SendTask) activitytemp);
												 
						d = cal2.getTimeInMillis();
						BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flowEdge"+d+"r");
					    flowEdge.setBpmnElement(flow);
					    flowEdge.setSourceElement(previousshape);
					    flowEdge.setTargetElement(taskshape);
					    processPlane.getDiagramElements().add(flowEdge);
					
					    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					
						actprevious = activitytemp;
						setpreviousshape(taskshape);
					} else{
						//simple task for general
						d = cal2.getTimeInMillis();
						xaxis=xaxis+70.0;
					    yaxis=yaxis+70.0;
						Task activitytemp = createElement(process2, term1+"task"+d, Task.class);

						activitytemp.setName(term1);
						BpmnShape taskshape = modelInstance.newInstance(BpmnShape.class);
						taskshape.setId("taskShape"+term1+d);
						taskshape.setBpmnElement(activitytemp);
						processPlane.getDiagramElements().add(taskshape);
						 
						Bounds taskBounds = modelInstance.newInstance(Bounds.class);
						taskBounds.setHeight(50.0);
						taskBounds.setWidth(100.0);
						taskBounds.setX(xaxis+70.0);
						xaxis = xaxis+70.0;
						taskBounds.setY(yaxis+70.0);
						yaxis = yaxis+70.0;
						taskshape.setBounds(taskBounds);
		
						SequenceFlow flow = createSequenceFlow(process, (FlowNode) actprevious, (Task) activitytemp);
						
						d = cal2.getTimeInMillis();
						BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flowEdge"+d+"q");
					    flowEdge.setBpmnElement(flow);
					    flowEdge.setSourceElement(previousshape);
					    flowEdge.setTargetElement(taskshape);
					    processPlane.getDiagramElements().add(flowEdge);
					
					    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					    
						actprevious = activitytemp;
						setpreviousshape(taskshape);
					}
				}
			}
			return actprevious;
	}

	private BpmnModelElementInstance handleBasicTerm(String term1, Process process2,
			BpmnModelElementInstance actprevious) {
		// TODO Auto-generated method stub
		
		BpmnModelElementInstance tmp = createProcessrecursively(formulas.get(term1), process2, actprevious);
		return tmp;
	}

	private void setTerm1(String name) {
		// TODO Auto-generated method stub
		term123 = name;
	}

	private void setconnector(String string) {
		connector = string;
	}

	private List<String> findTermsInExpression(String expression,
			String string, Process process2) {

		System.out.println("The connector is:" + connector);
		List<String> paraTerms = new LinkedList<String>();
		List<String> orTerms = new LinkedList<String>();
		List<String> seqTerms = new LinkedList<String>();
		if (connector == ".") {
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
					findTerms.add(currentTerm);
					if (connector == ".") {
						numberofseq = numberofseq + 1;
						seqTerms.add(currentTerm);
						expressionList.add(currentTerm);
					} else if (connector == "||") {
						numberofpara = numberofpara + 1;
						System.out.println("Numberofparallel:" + numberofpara);
						paraTerms.add(currentTerm);
						expressionList.add(currentTerm);
						System.out.println("simpleTerm is:" + expressionList);
					} else if (connector == "|") {
						numberofors = numberofors + 1;
						System.out.println("Numberofors:" + numberofors);
						orTerms.add(currentTerm);
						expressionList.add(currentTerm);
						System.out.println("simpleTerm is:" + expressionList);
					} else {
						expressionList.add(currentTerm);
					}
					currentTerm = new String();
				} else
					currentTerm = currentTerm + connector;
			}
		} else if (connector == "|") {
			List<String> characterlist = new LinkedList<>();
			int parenthesis = 0;
			int brackets = 0;
			int i = 0;
			System.out.println("The expression is:" + expression);

			for (i = 0; i < expression.length(); i++) {
				String newterm = "";
				char character = expression.charAt(i);

				newterm = newterm + character;
				characterlist.add(newterm);
			}
			String tempterm = "";
			String tempop = "";
			int k = 0;
			for (k = 0; k < characterlist.size(); k++) {
				tempterm = tempterm + characterlist.get(k);
				try {
					tempop = characterlist.get(k + 1);
				} catch (Exception e) {
				}
				if (characterlist.get(k).equals("(")) {
					parenthesis = parenthesis + 1;
				} else if (characterlist.get(k).equals(")")) {
					parenthesis = parenthesis - 1;
					if (parenthesis == 0) {
						if (tempop.equals("~") | tempop.equals("+")
								| tempop.equals("*")) {
							orTerms.add(tempterm + tempop);
							tempterm = "";
						} else {
							orTerms.add(tempterm);
							tempterm = "";
						}
					}
				} else if (characterlist.get(k).equals("[")) {
					brackets = brackets + 1;
				} else if (characterlist.get(k).equals("]")) {
					brackets = brackets - 1;
					if (brackets == 0) {
						if (tempop.equals("~") | tempop.equals("+")
								| tempop.equals("*")) {
							orTerms.add(tempterm + tempop);
							tempterm = "";
						} else {
							orTerms.add(tempterm);
							tempterm = "";
						}
					}
				} else if (characterlist.get(k).equals("|")
						&& (parenthesis == 0 && brackets == 0)) {
					if (tempterm.equals("|")) {
						tempterm = "";
					} else {
						tempterm = tempterm.substring(0, tempterm.length() - 1);
						if (tempterm.equals("~") | tempterm.equals("+")
								| tempterm.equals("*")) {
							tempterm = "";
						} else {
							orTerms.add(tempterm);
							tempterm = "";
						}
					}

				} else if (k == characterlist.size() - 1) {
					if (tempterm.equals("~") | tempterm.equals("+")
							| tempterm.equals("*")) {
						tempterm = "";
					} else {
						orTerms.add(tempterm);
					}
				}

			}
			// parallel
		} else if (connector == "||") {

			List<String> characterlist = new LinkedList<>();
			int parenthesis = 0;
			int brackets = 0;
			int i = 0;
			System.out.println("The expression is:" + expression);

			for (i = 0; i < expression.length(); i++) {
				String newterm = "";
				char character = expression.charAt(i);
				newterm = newterm + character;
				characterlist.add(newterm);
			}
			String tempterm = "";
			int k = 0;
			String tempop = "";
			for (k = 0; k < characterlist.size(); k++) {

				tempterm = tempterm + characterlist.get(k);
				try {
					tempop = characterlist.get(k + 1);
				} catch (Exception e) {
				}
				if (characterlist.get(k).equals("(")) {
					parenthesis = parenthesis + 1;
				} else if (characterlist.get(k).equals(")")) {
					parenthesis = parenthesis - 1;
					if (parenthesis == 0) {
						if (tempterm.startsWith("|")) {
							tempterm = tempterm.substring(1, tempterm.length());

							if (tempop.equals("~") | tempop.equals("+")
									| tempop.equals("*")) {
								paraTerms.add(tempterm + tempop);
								tempterm = "";
							} else {
								paraTerms.add(tempterm);
								tempterm = "";
							}
						}
					}
				} else if (characterlist.get(k).equals("[")) {
					brackets = brackets + 1;
				} else if (characterlist.get(k).equals("]")) {
					brackets = brackets - 1;
					if (brackets == 0) {
						if (tempterm.startsWith("|")) {
							tempterm = tempterm.substring(1, tempterm.length());
							if (tempop.equals("~") | tempop.equals("+")
									| tempop.equals("*")) {
								paraTerms.add(tempterm + tempop);
								tempterm = "";
							} else {
								paraTerms.add(tempterm);
								tempterm = "";
							}
						}
					}
				} else if (characterlist.get(k).equals("|")
						&& characterlist.get(k + 1).equals("|")
						&& ((parenthesis == 0) && (brackets == 0))) {
					if (tempterm.equals("")) {
						tempterm = "";
					} else if (tempterm.equals("~") | tempterm.equals("+")
							| tempterm.equals("*")) {
						tempterm = "";
					} else {
						if (tempterm.startsWith("|") && tempterm.endsWith("|")
								&& !(tempterm == "|")) {
							if (tempterm.equals("~") | tempterm.equals("+")
									| tempterm.equals("*")) {
								tempterm = "";
							} else {
								if (tempterm.equals("|")) {
									tempterm = tempterm.substring(0);
									tempterm = "";
								} else {
									tempterm = tempterm.substring(1,
											tempterm.length() - 1);
									if (tempterm.equals("")) {
									} else {
										paraTerms.add(tempterm);
										tempterm = "";
									}
								}
							}
						} else if (!(tempterm.startsWith("|"))
								&& tempterm.endsWith("|")) {
							if (tempterm.equals("~") | tempterm.equals("+")
									| tempterm.equals("*")) {
								tempterm = "";
							} else {
								tempterm = tempterm.substring(0,
										tempterm.length() - 1);
								if (tempterm.endsWith("|")) {

									tempterm = tempterm.substring(0,
											tempterm.length() - 1);
									if (tempterm.equals("")) {
									} else {
										paraTerms.add(tempterm);
										tempterm = "";
									}
								} else {
									if (tempterm.equals("~")
											| tempterm.equals("+")
											| tempterm.equals("*")) {
										tempterm = "";
									}
									if (tempterm.endsWith("|")) {
										tempterm = tempterm.substring(0,
												tempterm.length() - 1);
										if (tempterm.equals("")) {
										} else {
											paraTerms.add(tempterm);
											tempterm = "";
										}
									} else {
										if (tempterm.equals("")) {
										} else {
											paraTerms.add(tempterm);
											tempterm = "";
										}
									}
								}
							}
						} else {
							if (tempterm.equals("~") | tempterm.equals("+")
									| tempterm.equals("*")) {
								tempterm = "";
							} else {
								paraTerms.add(tempterm);
							}
						}
						tempterm = "";
					}
				} else if (k == characterlist.size() - 1) {
					if (tempterm.equals("~") | tempterm.equals("+")
							| tempterm.equals("*")) {
						tempterm = "";
					} else {
						if (tempterm.startsWith("|")) {
							tempterm = tempterm.substring(1, tempterm.length());
							if (tempterm.equals("")) {
							} else {
								paraTerms.add(tempterm);
							}
						} else {
							if (tempterm.equals("~") | tempterm.equals("+")
									| tempterm.equals("*")) {
								tempterm = "";
							} else {
								paraTerms.add(tempterm);
								tempterm = "";
							}
						}
					}
				}
			}
		}
		if (connector == ".") {
			return seqTerms;
		} else if (connector == "|") {
			return orTerms;
		} else if (connector == "||") {
			return paraTerms;
		} else
			return foundTerms;
	}

	void setleftHandSide(String leftHandSide) {
		  	lefthand = leftHandSide;
	}

	public <T extends BpmnModelElementInstance> T createElement(BpmnModelElementInstance parentElement, String id, Class<T> elementClass) {
		    T element = modelInstance.newInstance(elementClass);
		    element.setAttributeValue("id", id, true);
		    parentElement.addChildElement(element);
		    return element;
		  }

	  
	  public SequenceFlow createSequenceFlow(Process process, FlowNode from, FlowNode to) {
		    SequenceFlow sequenceFlow=null;
			try {
				sequenceFlow = createElement(process, from.getId() + "-" + to.getId(), SequenceFlow.class);
			} catch (Exception e) {
			}
		    try {
				process.addChildElement(sequenceFlow);
			} catch (Exception e) {
			}
		    try {
				sequenceFlow.setSource(from);
			} catch (Exception e) {
			}
		    try {
				from.getOutgoing().add(sequenceFlow);
			} catch (Exception e) {
			}
		    try {
				sequenceFlow.setTarget(to);
			} catch (Exception e) {
			}
		    try {
				to.getIncoming().add(sequenceFlow);
			} catch (Exception e) {
			}
		    return sequenceFlow;
		  }
	  
	  public void setpreviousshape(BpmnShape shapeprevious){
		  previousshape = shapeprevious;
	  }
	  
	  public void validateModel() {
		    Bpmn.validateModel(modelInstance);
		  }

	  public static void writeModelToFile(File file, BpmnModelInstance modelInstance) {
		  Bpmn.writeModelToFile(file, modelInstance);
		  }

	public void createMessageLanes(String expression, Process process2) {
		//prosoxi edo
		BpmnModelElementInstance actprevious = null;
		  Calendar cal2 = Calendar.getInstance();
			long d = cal2.getTimeInMillis();
			int expressionType = 0;
			// pattern for parallelExpression
			Pattern patternParallelExpression = Pattern
					.compile("(((\\|.+~|(\\d))|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)\\|\\|)+((\\|.+~\\|(\\d))|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)");
			Matcher parallelMatcher = patternParallelExpression.matcher(expression);
			// pattern for orExpression : expressionType=2
			Pattern patternOrExpression = Pattern
					.compile("(((\\|.+~|(\\d))|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)\\|)+((\\|.+~\\|(\\d))|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)");
			Matcher orMatcher = patternOrExpression.matcher(expression);
			// pattern for sequentialExpression : expressionType=3
			Pattern patternSequentialExpression = Pattern
					.compile("(((\\|.+~\\|\\d)|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)\\.)+((\\|.+~\\|\\d)|(\\[.+\\])|(\\(.+\\))|([\\w&&[^()]])+)([~+*]?)");

			Matcher sequentialMatcher = patternSequentialExpression
					.matcher(expression);

			List<String> myTerms = null;

			switch (expressionType) {
			case 0:
				myTerms = new LinkedList<String>();
				myTerms.add(expression);
				break;
			}
			
			if (sequentialMatcher.find()
					&& (sequentialMatcher.group().length() == expression.length())) {
				expressionType = 3;

				System.out.print("a sequential expression processed: " + expression
						+ "\n");
				setconnector(".");
				List<String> seqterms = this.findTermsInExpression(expression, ".",
						process2);

				System.out.println("Seqterms are:" + seqterms);

				int listpointer2 = listpointer;
				int b = 0;
				for (b = 0; b < seqterms.size(); b++) {
					String currentterm = seqterms.get(b);
					BpmnModelElementInstance seqact = null;
				//	try {
						seqact = createMessagePartners(currentterm, process, actprevious);
					//} catch (Exception e) {
					//}
					listpointer = listpointer + 1;
					actprevious = seqact;
				}

				int i = 0;
				for (i = 0; i < seqterms.size(); i++) {
					listpointer = listpointer - 1;
					if (listpointer == listpointer2) {
						break;
					}
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
				}
			}else if (orMatcher.find()
					&& (orMatcher.group().length() == expression.length())) {
				expressionType = 2;

				System.out.print("an or expression processed: " + expression + "\n");
				numberoforexpressions = numberoforexpressions + 1;
				System.out.println("The numberoforexpressions is:"+ numberoforexpressions);
				setconnector("|");
				d = cal2.getTimeInMillis();
				xaxis=xaxis+70.0;
			    yaxis=yaxis+70.0;
				ExclusiveGateway xor = createElement(process, "xor1"+d, ExclusiveGateway.class);
				modelInstance.getModelElementById("xor1"+d);
				xor.setName("decision");
				d=cal2.getTimeInMillis();
				BpmnShape xorshape = modelInstance.newInstance(BpmnShape.class);
				xorshape.setId("xor1Shape"+d);
				xorshape.setBpmnElement(xor);
				processPlane.getDiagramElements().add(xorshape);
				 
				 Bounds xorBounds = modelInstance.newInstance(Bounds.class);
				 xorBounds.setHeight(50.0);
				 xorBounds.setWidth(100.0);
				 xorBounds.setX(xaxis+70.0);
				 xaxis = xaxis+70.0;
				 xorBounds.setY(yaxis+70.0);
				 yaxis = yaxis+70.0;
				 xorshape.setBounds(xorBounds);
				 
				 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, xor);
				 //actprevious = xor;
				 BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow4Edge"+d);
					try {
						flowEdge.setBpmnElement(flow1);
					} catch (Exception e1) {
					}
					processPlane.getDiagramElements().add(flowEdge);
					
					 Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
				 
				d = cal2.getTimeInMillis();
				xaxis=xaxis+70.0;
			    yaxis=yaxis+70.0;
				ExclusiveGateway xor2 = createElement(process, "xor2"+d, ExclusiveGateway.class);
				xor2.setName("decisionexit");
				d=cal2.getTimeInMillis();
				BpmnShape xor2shape = modelInstance.newInstance(BpmnShape.class);
				xor2shape.setId("xor2Shape"+d);
				xor2shape.setBpmnElement(xor2);
				processPlane.getDiagramElements().add(xor2shape);
					 
				Bounds xor2Bounds = modelInstance.newInstance(Bounds.class);
				xor2Bounds.setHeight(50.0);
				xor2Bounds.setWidth(100.0);
				xor2Bounds.setX(xaxis+70.0);
				xaxis = xaxis+70.0;
				xor2Bounds.setY(yaxis+70.0);
				yaxis = yaxis+70.0;
				xor2shape.setBounds(xor2Bounds);
				
				int listpointer2 = listpointer;

				listpointer = listpointer + 1;
				firstacts.add(xor);
				
				List<String> orterms = this.findTermsInExpression(expression, "|",
						process2);
				System.out.println("the orterms are:" + orterms);

				int or = 0;
				for (or = 0; or < orterms.size(); or++) {
					String currentterm = orterms.get(or);
					System.out.println("The current term is:" + currentterm);
					actprevious = xor;
					BpmnModelElementInstance ortemp = createMessagePartners(currentterm, process, actprevious);
					listpointer = listpointer + 1;
					d = cal2.getTimeInMillis();
					SequenceFlow flow2= createSequenceFlow(process, (FlowNode) ortemp, xor2);
					
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow5Edge"+or+d);
					flowEdge.setBpmnElement(flow2);
					processPlane.getDiagramElements().add(flowEdge);
					
					 startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
				}

				int i = 0;
				for (i = 0; i < orterms.size() + 1; i++) {

					listpointer = listpointer - 1;
					if (listpointer == listpointer2) {
						break;
					}
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
				}
				actprevious = xor2;
				setpreviousshape(xor2shape);
			}else if (parallelMatcher.find()
					&& (parallelMatcher.group().length() == expression.length())) {
				expressionType = 1;
				System.out.print("a parallel expression processed: " + expression
						+ "\n");

				setconnector("||");
				d = cal2.getTimeInMillis();
				//ParallelGateway 
				numberofpara = numberofpara + 1;
				System.out.println("The numberofparaexpressions is:"+ numberofpara);
				d = cal2.getTimeInMillis();
				xaxis=xaxis+70.0;
			    yaxis=yaxis+70.0;
				ParallelGateway para = createElement(process, "para1"+d, ParallelGateway.class);
				modelInstance.getModelElementById("para1"+d);
				para.setName("parallel");
				d=cal2.getTimeInMillis();
				BpmnShape parashape = modelInstance.newInstance(BpmnShape.class);
				parashape.setId("parashape"+d);
				parashape.setBpmnElement(para);
				processPlane.getDiagramElements().add(parashape);
				
				Bounds paraBounds = modelInstance.newInstance(Bounds.class);
				 paraBounds.setHeight(50.0);
				 paraBounds.setWidth(100.0);
				 paraBounds.setX(xaxis+70.0);
				 xaxis = xaxis+70.0;
				 paraBounds.setY(yaxis+70.0);
				 yaxis = yaxis+70.0;
				 parashape.setBounds(paraBounds);
				 
				 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, para);
				 BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow4Edge"+d);
					try {
						flowEdge.setBpmnElement(flow1);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}
					processPlane.getDiagramElements().add(flowEdge);
					
					 Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
				 
				d = cal2.getTimeInMillis();
				xaxis=xaxis+70.0;
			    yaxis=yaxis+70.0;
				ParallelGateway para2 = createElement(process, "para2exit"+d, ParallelGateway.class);
				para2.setName("parallelexit");
				d=cal2.getTimeInMillis();
				BpmnShape para2shape = modelInstance.newInstance(BpmnShape.class);
				para2shape.setId("paraShape"+d);
				para2shape.setBpmnElement(para2);
				processPlane.getDiagramElements().add(para2shape);
					 
				Bounds para2Bounds = modelInstance.newInstance(Bounds.class);
				para2Bounds.setHeight(50.0);
				para2Bounds.setWidth(100.0);
				para2Bounds.setX(xaxis+70.0);
				xaxis = xaxis+70.0;
				para2Bounds.setY(yaxis+70.0);
				yaxis = yaxis+70.0;
				para2shape.setBounds(para2Bounds);
				
				int listpointer2 = listpointer;

				listpointer = listpointer + 1;
				firstacts.add(para);
				
				List<String> paraterms = this.findTermsInExpression(expression,
						"||", process2);
				System.out.println("the parallel terms are:" + paraterms);

				int par = 0;
				for (par = 0; par < paraterms.size(); par++) {
					String currentterm = paraterms.get(par);
					System.out.println("The current term is:" + currentterm);
					actprevious = para;
					
					BpmnModelElementInstance paratemp = createMessagePartners(currentterm, process, actprevious);
					listpointer = listpointer + 1;
					d = cal2.getTimeInMillis();
					
					SequenceFlow flow = createSequenceFlow(process, (FlowNode) paratemp, para2);
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow5Edge"+par+d);
					flowEdge.setBpmnElement(flow);
					processPlane.getDiagramElements().add(flowEdge);
					
					 startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
				}
				int i = 0;
				for (i = 0; i < paraterms.size() + 1; i++) {
					listpointer = listpointer - 1;
					if (listpointer == listpointer2) {
						break;
					}
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
				}
				actprevious = para2;
				setpreviousshape(para2shape);
			}else {
				String term1 = expression;
				setTerm1(term1);
				System.out.println("term1 is:" + term1);
				//basicterms.add(term1);
				Pattern patternBasicTerm = Pattern.compile("^\\w+$");
				Matcher basicTermMatcher = patternBasicTerm.matcher(term1);
				// pattern for (term)
				Pattern patternComplexParenthesisTerm = Pattern
						.compile("^\\(.+\\)$");
				Matcher complexParenthesisTermMatcher = patternComplexParenthesisTerm
						.matcher(term1);
				// pattern for [term]
				Pattern patternComplexOptionalTerm = Pattern.compile("^\\[.+\\]$");
				Matcher complexOptionalTermMatcher = patternComplexOptionalTerm
						.matcher(term1);
				// pattern for term~
				Pattern patternForeverTerm = Pattern.compile(".+~$");
				Matcher foreverTermMatcher = patternForeverTerm.matcher(term1);
				// pattern for term+
				Pattern patternOneOrMoreTimesTerm = Pattern.compile(".+\\+$");
				Matcher oneOrMoreTimesTermMatcher = patternOneOrMoreTimesTerm
						.matcher(term1);
				// pattern for term*
				Pattern patternZeroOrMoreTimesTerm = Pattern.compile(".+\\*$");
				Matcher zeroOrMoreTimesTermMatcher = patternZeroOrMoreTimesTerm
						.matcher(term1);	

				if (complexParenthesisTermMatcher.find()
						&& (complexParenthesisTermMatcher.group().length() == term1
								.length())) {
					System.out.println("A parenthesis term is being processed");
					String insideTerm = term1.substring(1, term1.length() - 1);
					System.out.println("the inside term is:" + insideTerm);
					BpmnModelElementInstance parenth = createMessagePartners(insideTerm, process, actprevious);
					actprevious = parenth;
				} else if (complexOptionalTermMatcher.find()
						&& (complexOptionalTermMatcher.group().length() == term1
								.length())) {
					System.out.println("A brackets term is being processed.");
					// x optional [x]
					d = cal2.getTimeInMillis();
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorbrackets = createElement(process, "bracketsxor1"+d, ExclusiveGateway.class);
					xorbrackets.setName("bracketsdecision");
					d=cal2.getTimeInMillis();
					BpmnShape xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
					xorshapebrackets.setId("xor1Shape"+d);
					xorshapebrackets.setBpmnElement(xorbrackets);
					processPlane.getDiagramElements().add(xorshapebrackets);
					 
					 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
					 xorbracketsBounds.setHeight(50.0);
					 xorbracketsBounds.setWidth(100.0);
					 xorbracketsBounds.setX(xaxis+70.0);
					 xaxis = xaxis+70.0;
					 xorbracketsBounds.setY(yaxis+70.0);
					 yaxis = yaxis+70.0;
					 xorshapebrackets.setBounds(xorbracketsBounds);
					 
					 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, xorbrackets);

					 BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						d = cal2.getTimeInMillis();
						flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flow4Edge"+d);
						flowEdge.setBpmnElement(flow1);
						processPlane.getDiagramElements().add(flowEdge);
						
						 Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
						 startWaypoint.setX(668.0);
						 startWaypoint.setY(330.0);
						 flowEdge.getWaypoints().add(startWaypoint);

						Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
						endWaypoint.setX(718.0);
						endWaypoint.setY(330.0);
						flowEdge.getWaypoints().add(endWaypoint);	
						
					d = cal2.getTimeInMillis();
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorbrackets2 = createElement(process, "xorbrackets2"+d, ExclusiveGateway.class);
					xorbrackets2.setName("bracketsdecisionexit");
					d=cal2.getTimeInMillis();
					BpmnShape xor2shape = modelInstance.newInstance(BpmnShape.class);
					xor2shape.setId("xor2Shape"+d);
					xor2shape.setBpmnElement(xorbrackets2);
					processPlane.getDiagramElements().add(xor2shape);
						 
					Bounds xorbrackets2Bounds = modelInstance.newInstance(Bounds.class);
					xorbrackets2Bounds.setHeight(50.0);
					xorbrackets2Bounds.setWidth(100.0);
					xorbrackets2Bounds.setX(xaxis+70.0);
					xaxis = xaxis+70.0;
					xorbrackets2Bounds.setY(yaxis+70.0);
					yaxis = yaxis+70.0;
					xor2shape.setBounds(xorbrackets2Bounds);
					
					String insideTerm = term1.substring(1, term1.length() - 1);
					System.out.println("the inside term is:" + insideTerm);
					int listpointer2 = listpointer;

					listpointer = listpointer + 1;
					firstacts.add(xorbrackets);
					
					firstacts.add(xorbrackets);
					BpmnModelElementInstance xbrackets = createMessagePartners(insideTerm, process, xorbrackets);

					d = cal2.getTimeInMillis();
					SequenceFlow bracketstoendxor = createSequenceFlow(process, (FlowNode) xbrackets, xorbrackets2);
								    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow2Edge"+d);
					flowEdge.setBpmnElement(bracketstoendxor);
					processPlane.getDiagramElements().add(flowEdge);
					
					 startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	

					SequenceFlow decisiontoexit = createSequenceFlow(process, (FlowNode) xorbrackets, xorbrackets2);
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow3Edge"+d);
					flowEdge.setBpmnElement(decisiontoexit);
					processPlane.getDiagramElements().add(flowEdge);
					
					 startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);
					
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
					listpointer = listpointer2;
					actprevious = xorbrackets2;
					setpreviousshape(xor2shape);
				} else if (foreverTermMatcher.find()
						&& (foreverTermMatcher.group().length() == term1.length())) {
					// activity x~
					d = cal2.getTimeInMillis();
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xortilda = createElement(process, "xor1tilda"+d, ExclusiveGateway.class);
					xortilda.setName("tildadecision");
				
					d=cal2.getTimeInMillis();
					BpmnShape xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
					xorshapebrackets.setId("xor1Shape"+d);
					xorshapebrackets.setBpmnElement(xortilda);
					processPlane.getDiagramElements().add(xorshapebrackets);
					 
					 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
					 xorbracketsBounds.setHeight(50.0);
					 xorbracketsBounds.setWidth(100.0);
					 xorbracketsBounds.setX(xaxis+70.0);
					 xaxis = xaxis+70.0;
					 xorbracketsBounds.setY(yaxis+70.0);
					 yaxis = yaxis+70.0;
					 xorshapebrackets.setBounds(xorbracketsBounds);
					 
					 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, xortilda);

					BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow2Edge"+d);
					flowEdge.setBpmnElement(flow1);
					processPlane.getDiagramElements().add(flowEdge);
					
					 Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					 startWaypoint.setX(668.0);
					 startWaypoint.setY(330.0);
					 flowEdge.getWaypoints().add(startWaypoint);

					Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
					
					d = cal2.getTimeInMillis();
					activityid = activityid + 1;
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xortilda2 = createElement(process, "xortilda2"+d, ExclusiveGateway.class);
					xortilda2.setName("tildadecisionexit");
					d=cal2.getTimeInMillis();
					BpmnShape xor2shape = modelInstance.newInstance(BpmnShape.class);
					xor2shape.setId("xor2Shape"+d);
					xor2shape.setBpmnElement(xortilda2);
					processPlane.getDiagramElements().add(xor2shape);
						 
					Bounds xorbrackets2Bounds = modelInstance.newInstance(Bounds.class);
					xorbrackets2Bounds.setHeight(50.0);
					xorbrackets2Bounds.setWidth(100.0);
					xorbrackets2Bounds.setX(xaxis+70.0);
					xaxis = xaxis+70.0;
					xorbrackets2Bounds.setY(yaxis+70.0);
					yaxis = yaxis+70.0;
					xor2shape.setBounds(xorbrackets2Bounds);

					d = cal2.getTimeInMillis();
					activityid = activityid + 1;

					System.out.println("A tilda term is being processed");
					String insideTerm = term1.substring(0, term1.length() - 1);
					int listpointer2 = listpointer;

					listpointer = listpointer + 1;
					firstacts.add(xortilda);
					
					BpmnModelElementInstance xtilda = createMessagePartners(insideTerm, process, xortilda);
					
					d = cal2.getTimeInMillis();
					SequenceFlow tildatoendxor = createSequenceFlow(process, (FlowNode) xtilda, xortilda2);
				
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow3Edge"+d);
					flowEdge.setBpmnElement(tildatoendxor);
					processPlane.getDiagramElements().add(flowEdge);
					
					startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	

					d = cal2.getTimeInMillis();
					SequenceFlow decisiontoexit = createSequenceFlow(process, xortilda2, xortilda);
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow4Edge"+d);
					flowEdge.setBpmnElement(decisiontoexit);
					processPlane.getDiagramElements().add(flowEdge);
					
					startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	

					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
					listpointer = listpointer2;

					actprevious = xortilda2;
					setpreviousshape(xor2shape);
				} else if (oneOrMoreTimesTermMatcher.find()
						&& (oneOrMoreTimesTermMatcher.group().length() == term1
								.length())) {
					// activity x+
					d = cal2.getTimeInMillis();
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorplus = createElement(process, "xor1plus"+d, ExclusiveGateway.class);
					xorplus.setName("plusdecision");
					d=cal2.getTimeInMillis();
					BpmnShape xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
					xorshapebrackets.setId("xor1Shape"+d);
					xorshapebrackets.setBpmnElement(xorplus);
					processPlane.getDiagramElements().add(xorshapebrackets);
					 
					 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
					 xorbracketsBounds.setHeight(50.0);
					 xorbracketsBounds.setWidth(100.0);
					 xorbracketsBounds.setX(xaxis+70.0);
					 xaxis = xaxis+70.0;
					 xorbracketsBounds.setY(yaxis+70.0);
					 yaxis = yaxis+70.0;
					 xorshapebrackets.setBounds(xorbracketsBounds);
					 
					 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, xorplus);

					 d = cal2.getTimeInMillis();
					BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow1Edge"+d);
					flowEdge.setBpmnElement(flow1);
					
					processPlane.getDiagramElements().add(flowEdge);
						
					Waypoint  startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					Waypoint  endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	
					 
					d = cal2.getTimeInMillis();
					activityid = activityid + 1;
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorplus2 = createElement(process, "xorplus2"+d, ExclusiveGateway.class);
					xorplus2.setName("plusdecisionexit");
					d=cal2.getTimeInMillis();
					BpmnShape xor2shape = modelInstance.newInstance(BpmnShape.class);
					xor2shape.setId("xor2Shape"+d);
					xor2shape.setBpmnElement(xorplus2);
					processPlane.getDiagramElements().add(xor2shape);
						 
					Bounds xorbrackets2Bounds = modelInstance.newInstance(Bounds.class);
					xorbrackets2Bounds.setHeight(50.0);
					xorbrackets2Bounds.setWidth(100.0);
					xorbrackets2Bounds.setX(xaxis+70.0);
					xaxis = xaxis+70.0;
					xorbrackets2Bounds.setY(yaxis+70.0);
					yaxis = yaxis+70.0;
					xor2shape.setBounds(xorbrackets2Bounds);

					d = cal2.getTimeInMillis();
					activityid = activityid + 1;

					System.out.println("A plus term is being processed");
					String insideTerm = term1.substring(0, term1.length() - 1);
					int listpointer2 = listpointer;

					listpointer = listpointer + 1;
					firstacts.add(xorplus);
					
					BpmnModelElementInstance xtilda = createMessagePartners(insideTerm, process, xorplus);
					
					d = cal2.getTimeInMillis();
					SequenceFlow tildatoendxor = createSequenceFlow(process, (FlowNode) xtilda, xorplus2);
				
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow2Edge"+d);
					flowEdge.setBpmnElement(tildatoendxor);
				
					processPlane.getDiagramElements().add(flowEdge);
					
					startWaypoint = modelInstance.newInstance(Waypoint.class);
					startWaypoint.setX(668.0);
					startWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(startWaypoint);

					endWaypoint = modelInstance.newInstance(Waypoint.class);
					endWaypoint.setX(718.0);
					endWaypoint.setY(330.0);
					flowEdge.getWaypoints().add(endWaypoint);	

					d = cal2.getTimeInMillis();
					SequenceFlow decisiontoexit = createSequenceFlow(process, xorplus2, xorplus);
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow3Edge"+d);
					flowEdge.setBpmnElement(decisiontoexit);
				
					processPlane.getDiagramElements().add(flowEdge);
					
					    startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);	
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
					listpointer = listpointer2;

					actprevious = xorplus2;
					setpreviousshape(xor2shape);
				} else if ((zeroOrMoreTimesTermMatcher.find() && (zeroOrMoreTimesTermMatcher
						.group().length() == term1.length()))) {
					// activity x*
					d = cal2.getTimeInMillis();
					//ExclusiveGateway xorstar = createElement(process, "xor1star"+d, ExclusiveGateway.class);
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorstar = modelInstance.newInstance(ExclusiveGateway.class);
					xorstar.setName("stardecision");
					xorstar.setId("xor"+d);
					d=cal2.getTimeInMillis();
					process.addChildElement(xorstar);
					
					xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
					xorshapebrackets.setId("xor1Shape"+d);
					xorshapebrackets.setBpmnElement(xorstar);
					processPlane.addChildElement(xorshapebrackets);
					
					 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
					 xorbracketsBounds.setHeight(50.0);
					 xorbracketsBounds.setWidth(100.0);
					 xorbracketsBounds.setX(xaxis+70.0);
					 xaxis = xaxis+70.0;
					 xorbracketsBounds.setY(yaxis+70.0);
					 yaxis = yaxis+70.0;
					 xorshapebrackets.setBounds(xorbracketsBounds);
					 
					 SequenceFlow flow1 = createSequenceFlow(process, (FlowNode) actprevious, xorstar);

					 d = cal2.getTimeInMillis();
					BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow1Edge"+d);
					flowEdge.setBpmnElement(flow1);
					flowEdge.setSourceElement(previousshape);
					flowEdge.setTargetElement(xorshapebrackets);
					processPlane.getDiagramElements().add(flowEdge);
					
					    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					 
					d = cal2.getTimeInMillis();
					activityid = activityid + 1;
					xaxis=xaxis+70.0;
				    yaxis=yaxis+70.0;
					ExclusiveGateway xorstar2 = createElement(process, "xorstar2"+d, ExclusiveGateway.class);
					xorstar2.setName("stardecisionexit");
					d=cal2.getTimeInMillis();
					BpmnShape xor2shape = modelInstance.newInstance(BpmnShape.class);
					xor2shape.setId("xor2Shape"+d);
					xor2shape.setBpmnElement(xorstar2);
					processPlane.getDiagramElements().add(xor2shape);
						 
					Bounds xorbrackets2Bounds = modelInstance.newInstance(Bounds.class);
					xorbrackets2Bounds.setHeight(50.0);
					xorbrackets2Bounds.setWidth(100.0);
					xorbrackets2Bounds.setX(xaxis+70.0);
					xaxis = xaxis+70.0;
					xorbrackets2Bounds.setY(yaxis+70.0);
					yaxis = yaxis+70.0;
					xor2shape.setBounds(xorbrackets2Bounds);

					d = cal2.getTimeInMillis();
					activityid = activityid + 1;

					System.out.println("A star term is being processed");
					String insideTerm = term1.substring(0, term1.length() - 1);
					int listpointer2 = listpointer;

					listpointer = listpointer + 1;
					firstacts.add(xorstar);
					
					BpmnModelElementInstance xstar = createMessagePartners(insideTerm, process, xorstar);
					
					d = cal2.getTimeInMillis();
					SequenceFlow startoendxor = createSequenceFlow(process, (FlowNode) xstar, xorstar2);					
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow2Edge"+d);
					flowEdge.setBpmnElement(startoendxor);
				
					processPlane.getDiagramElements().add(flowEdge);
					
					    startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);					
				    
					d = cal2.getTimeInMillis();
					SequenceFlow endtostartxor = createSequenceFlow(process, (FlowNode) xorstar, xorstar2);
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow3Edge"+d);
					flowEdge.setBpmnElement(endtostartxor);
	
					processPlane.getDiagramElements().add(flowEdge);
					
					    startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);	

					d = cal2.getTimeInMillis();
					SequenceFlow decisiontoexit = createSequenceFlow(process, xorstar2, xorstar);
					d = cal2.getTimeInMillis();
					flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
					flowEdge.setId("flow4Edge"+d);
					flowEdge.setBpmnElement(decisiontoexit);
					processPlane.getDiagramElements().add(flowEdge);
					
					    startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					    
					try {
						firstacts.remove(listpointer);
					} catch (Exception e) {
					}
					listpointer = listpointer2;

					actprevious = xorstar2;
					setpreviousshape(xor2shape);
				} else if (basicTermMatcher.find()) {
					// basic activity x
					if (formulas.keySet().contains(term1)) {
						BpmnModelElementInstance act = null;
						try {
							act = handleBasicTerm(term1, process2, actprevious);
						} catch (Exception e) {
						}
						actprevious = act;
					}else if(term1.startsWith("ReceiveResponse")){
						//receive we made as receiveTask
						String rolename1 = Liveness2BPMN.getparticipantname();
						receivers.add(rolename1+":"+term1);
						d = cal2.getTimeInMillis();
						xaxis=xaxis+70.0;
					    yaxis=yaxis+70.0;
						ReceiveTask messagetemp = createElement(process2, term1+"task"+d, ReceiveTask.class);
						messagetemp.setName(term1);
						receiverids.add(term1+"task"+d);
						 BpmnShape taskshape = modelInstance.newInstance(BpmnShape.class);
						 taskshape.setId("taskShape"+term1+d);
						 taskshape.setBpmnElement(messagetemp);
						 processPlane.getDiagramElements().add(taskshape);
						
						 receivershapes.add(taskshape);
						 
						 Bounds taskBounds = modelInstance.newInstance(Bounds.class);
						 taskBounds.setHeight(50.0);
						 taskBounds.setWidth(100.0);
						 taskBounds.setX(xaxis+70.0);
						 xaxis = xaxis+70.0;
						 taskBounds.setY(yaxis+70.0);
						 yaxis = yaxis+70.0;
						 taskshape.setBounds(taskBounds);
						 
						//prosoxi edo  ksekiname
							xaxis=xaxis+70.0;
						    yaxis=yaxis+70.0;
							ParallelGateway xorstar = modelInstance.newInstance(ParallelGateway.class);
							xorstar.setName("stardecision");
							xorstar.setId("xor"+d);
							d=cal2.getTimeInMillis();
							process.addChildElement(xorstar);
							
							xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
							xorshapebrackets.setId("xor1Shape"+d);
							xorshapebrackets.setBpmnElement(xorstar);
							processPlane.addChildElement(xorshapebrackets);
							 receivernodes.add(xorstar);
							messagereceivershape.add(xorshapebrackets);
							
							 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
							 xorbracketsBounds.setHeight(50.0);
							 xorbracketsBounds.setWidth(100.0);
							 xorbracketsBounds.setX(xaxis+70.0);
							 xaxis = xaxis+70.0;
							 xorbracketsBounds.setY(yaxis+70.0);
							 yaxis = yaxis+70.0;
							 xorshapebrackets.setBounds(xorbracketsBounds);
							 
						SequenceFlow flow = createSequenceFlow(process2, (FlowNode) actprevious, xorstar);
					 
						d = cal2.getTimeInMillis();
						BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flowEdge"+d+"a");		    
						flowEdge.setBpmnElement(flow);
					    flowEdge.setSourceElement(previousshape);
					    flowEdge.setTargetElement(xorshapebrackets);
					    processPlane.getDiagramElements().add(flowEdge);
					
					    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					    
					    SequenceFlow xortoreceiveflow = createSequenceFlow(process2, (FlowNode) xorstar, messagetemp);
						 
						d = cal2.getTimeInMillis();
						flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flowEdge"+d+d);		    
						flowEdge.setBpmnElement(xortoreceiveflow);
					    flowEdge.setSourceElement(xorshapebrackets);
					    flowEdge.setTargetElement(taskshape);
					    processPlane.getDiagramElements().add(flowEdge);
					
					    startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					
						actprevious = messagetemp;
						setpreviousshape(taskshape);
					}else if(term1.startsWith("Receive")){
						//receive we made as receiveTask
						String rolename1 = Liveness2BPMN.getparticipantname();
						receivers.add(rolename1+":"+term1);
						d = cal2.getTimeInMillis();
						xaxis=xaxis+70.0;
					    yaxis=yaxis+70.0;
						ReceiveTask messagetemp = createElement(process2, term1+"task"+d, ReceiveTask.class);
						messagetemp.setName(term1);
						receiverids.add(term1+"task"+d);
						 BpmnShape taskshape = modelInstance.newInstance(BpmnShape.class);
						 taskshape.setId("taskShape"+term1+d);
						 taskshape.setBpmnElement(messagetemp);
						 processPlane.getDiagramElements().add(taskshape);
						 receivernodes.add(messagetemp);
						 receivershapes.add(taskshape);
						 
						messagereceivershape.add(taskshape);
						 
						 Bounds taskBounds = modelInstance.newInstance(Bounds.class);
						 taskBounds.setHeight(50.0);
						 taskBounds.setWidth(100.0);
						 taskBounds.setX(xaxis+70.0);
						 xaxis = xaxis+70.0;
						 taskBounds.setY(yaxis+70.0);
						 yaxis = yaxis+70.0;
						 taskshape.setBounds(taskBounds);
		
						SequenceFlow flow = createSequenceFlow(process2, (FlowNode) actprevious, (ReceiveTask) messagetemp);
												 
						d = cal2.getTimeInMillis();
						BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flowEdge"+d+"e");
					    flowEdge.setBpmnElement(flow);
					    flowEdge.setSourceElement(previousshape);
					    flowEdge.setTargetElement(taskshape);
					    processPlane.getDiagramElements().add(flowEdge);
					
					    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					
						actprevious = messagetemp;
						setpreviousshape(taskshape);	
					}else if (term1.startsWith("SendRequest")) {	
						//send we made as sendTask
						String rolename1 = liveness2bpmn.getparticipantname();
						senders.add(rolename1+":"+term1);
						d = cal2.getTimeInMillis();
						xaxis=xaxis+70.0;
					    yaxis=yaxis+70.0;
						 SendTask activitytemp = createElement(process2, term1+"task"+d, SendTask.class);
						activitytemp.setName(term1);
						senderids.add(term1+"task"+d);
						 BpmnShape taskshape = modelInstance.newInstance(BpmnShape.class);
						 taskshape.setId("taskShape"+term1+d);
						 taskshape.setBpmnElement(activitytemp);
						 processPlane.getDiagramElements().add(taskshape);
						 
						 sendershapes.add(taskshape);
						 
						 Bounds taskBounds = modelInstance.newInstance(Bounds.class);
						 taskBounds.setHeight(50.0);
						 taskBounds.setWidth(100.0);
						 taskBounds.setX(xaxis+70.0);
						 xaxis = xaxis+70.0;
						 taskBounds.setY(yaxis+70.0);
						 yaxis = yaxis+70.0;
						 taskshape.setBounds(taskBounds);
						 
						 //prosoxi edo  ksekiname
							xaxis=xaxis+70.0;
						    yaxis=yaxis+70.0;
							ParallelGateway xorstar = modelInstance.newInstance(ParallelGateway.class);
							xorstar.setName("stardecision");
							xorstar.setId("xor"+d);
							d=cal2.getTimeInMillis();
							process.addChildElement(xorstar);
							
							xorshapebrackets = modelInstance.newInstance(BpmnShape.class);
							xorshapebrackets.setId("xor1Shape"+d);
							xorshapebrackets.setBpmnElement(xorstar);
							processPlane.addChildElement(xorshapebrackets);
							sendernodes.add(xorstar);
							messagesendershape.add(xorshapebrackets);
							
							 Bounds xorbracketsBounds = modelInstance.newInstance(Bounds.class);
							 xorbracketsBounds.setHeight(50.0);
							 xorbracketsBounds.setWidth(100.0);
							 xorbracketsBounds.setX(xaxis+70.0);
							 xaxis = xaxis+70.0;
							 xorbracketsBounds.setY(yaxis+70.0);
							 yaxis = yaxis+70.0;
							 xorshapebrackets.setBounds(xorbracketsBounds);
							 
							 SequenceFlow flow1 = createSequenceFlow(process2, (FlowNode) actprevious, activitytemp);

							 d = cal2.getTimeInMillis();
							BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
							flowEdge.setId("flow1Edge"+d);
							flowEdge.setBpmnElement(flow1);
							flowEdge.setSourceElement(previousshape);
							flowEdge.setTargetElement(xorshapebrackets);
							processPlane.getDiagramElements().add(flowEdge);
							
							    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
							    startWaypoint.setX(668.0);
							    startWaypoint.setY(330.0);
							    flowEdge.getWaypoints().add(startWaypoint);

							    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
							    endWaypoint.setX(718.0);
							    endWaypoint.setY(330.0);
							    flowEdge.getWaypoints().add(endWaypoint);
							 
							 flow1 = createSequenceFlow(process2, (FlowNode) activitytemp, xorstar);

							 d = cal2.getTimeInMillis();
							flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
							flowEdge.setId("flow1Edge"+d+d);
							flowEdge.setBpmnElement(flow1);
							flowEdge.setSourceElement(previousshape);
							flowEdge.setTargetElement(xorshapebrackets);
							processPlane.getDiagramElements().add(flowEdge);
							
							    startWaypoint = modelInstance.newInstance(Waypoint.class);
							    startWaypoint.setX(668.0);
							    startWaypoint.setY(330.0);
							    flowEdge.getWaypoints().add(startWaypoint);

							    endWaypoint = modelInstance.newInstance(Waypoint.class);
							    endWaypoint.setX(718.0);
							    endWaypoint.setY(330.0);
							    flowEdge.getWaypoints().add(endWaypoint);
							 
						actprevious = xorstar;
						setpreviousshape(xorshapebrackets);	
					}else if(term1.startsWith("Send")){

						//send we made as sendTask
						String rolename1 = Liveness2BPMN.getparticipantname();
						senders.add(rolename1+":"+term1);
						d = cal2.getTimeInMillis();
						xaxis=xaxis+70.0;
					    yaxis=yaxis+70.0;
						 SendTask activitytemp = createElement(process2, term1+"task"+d, SendTask.class);
						activitytemp.setName(term1);
						senderids.add(term1+"task"+d);
						 BpmnShape taskshape = modelInstance.newInstance(BpmnShape.class);
						 taskshape.setId("taskShape"+term1+d);
						 taskshape.setBpmnElement(activitytemp);
						 processPlane.getDiagramElements().add(taskshape);
						 sendernodes.add(activitytemp);
						 sendershapes.add(taskshape);
						messagesendershape.add(taskshape);
						
						 Bounds taskBounds = modelInstance.newInstance(Bounds.class);
						 taskBounds.setHeight(50.0);
						 taskBounds.setWidth(100.0);
						 taskBounds.setX(xaxis+70.0);
						 xaxis = xaxis+70.0;
						 taskBounds.setY(yaxis+70.0);
						 yaxis = yaxis+70.0;
						 taskshape.setBounds(taskBounds);
		
						SequenceFlow flow = createSequenceFlow(process2, (FlowNode) actprevious, (SendTask) activitytemp);
												 
						d = cal2.getTimeInMillis();
						BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flowEdge"+d+"r");
					    flowEdge.setBpmnElement(flow);
					    flowEdge.setSourceElement(previousshape);
					    flowEdge.setTargetElement(taskshape);
					    processPlane.getDiagramElements().add(flowEdge);
					
					    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					
						actprevious = activitytemp;
						setpreviousshape(taskshape);
					}
					else{
						//simple task for general
						d = cal2.getTimeInMillis();
						xaxis=xaxis+70.0;
					    yaxis=yaxis+70.0;
						Task activitytemp = createElement(process2, term1+"task"+d, Task.class);

						activitytemp.setName(term1);
						BpmnShape taskshape = modelInstance.newInstance(BpmnShape.class);
						taskshape.setId("taskShape"+term1+d);
						taskshape.setBpmnElement(activitytemp);
						processPlane.getDiagramElements().add(taskshape);
						 
						Bounds taskBounds = modelInstance.newInstance(Bounds.class);
						taskBounds.setHeight(50.0);
						taskBounds.setWidth(100.0);
						taskBounds.setX(xaxis+70.0);
						xaxis = xaxis+70.0;
						taskBounds.setY(yaxis+70.0);
						yaxis = yaxis+70.0;
						taskshape.setBounds(taskBounds);
		
						SequenceFlow flow=null;
						try {
							flow = createSequenceFlow(process2, (FlowNode) actprevious, (Task) activitytemp);
						} catch (Exception e) {
						}
						
						d = cal2.getTimeInMillis();
						BpmnEdge flowEdge = modelInstance.newInstance(BpmnEdge.class);					    
						flowEdge.setId("flowEdge"+d+"b");
					    flowEdge.setBpmnElement(flow);
					    flowEdge.setSourceElement(previousshape);
					    flowEdge.setTargetElement(taskshape);
					    processPlane.getDiagramElements().add(flowEdge);
					
					    Waypoint startWaypoint = modelInstance.newInstance(Waypoint.class);
					    startWaypoint.setX(668.0);
					    startWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(startWaypoint);

					    Waypoint endWaypoint = modelInstance.newInstance(Waypoint.class);
					    endWaypoint.setX(718.0);
					    endWaypoint.setY(330.0);
					    flowEdge.getWaypoints().add(endWaypoint);
					    
						actprevious = activitytemp;
						setpreviousshape(taskshape);
					}
				}
			}
	}

}
