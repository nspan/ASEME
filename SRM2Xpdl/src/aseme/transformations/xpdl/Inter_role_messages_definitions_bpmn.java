package aseme.transformations.xpdl;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.ItemSelectable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.UIManager;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.InteractionNode;
import org.camunda.bpm.model.bpmn.instance.MessageFlow;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnEdge;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.di.Waypoint;
import org.camunda.bpm.model.xml.ModelInstance;
import org.camunda.bpm.model.xml.impl.instance.ModelElementInstanceImpl;

public class Inter_role_messages_definitions_bpmn {

	private static String sendername;
	private JPanel contentPane;
	private JList<String> list;
	int associationid;
	List<String> senderList = new LinkedList<String>();
	List<String> senderid = new LinkedList<String>();
	List<String> receiverList = new LinkedList<String>();
	List<String> receiverid = new LinkedList<String>();
	List<String> tempsenderlist = new LinkedList<String>();
	private JFrame frame;
	static DefaultListModel<String> model = new DefaultListModel<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					Inter_role_messages_definitions_bpmn window = new Inter_role_messages_definitions_bpmn();
					window.frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inter_role_messages_definitions_bpmn() {
		initialize();

		frame.setBackground(Color.white);
		frame.setFont(UIManager.getFont("List.font"));
		frame.setTitle("Inter-role Messages Definition for bpmn");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		contentPane.setLayout(new BorderLayout(10, 0));
		frame.setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblAgentactivity = new JLabel("Agent:Task");
		panel_2.add(lblAgentactivity, BorderLayout.NORTH);
		lblAgentactivity.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_2.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));

		JComboBox<String> comboBox = new JComboBox<String>();

		panel_6.add(comboBox, BorderLayout.NORTH);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));

		JTextArea txtrWelcomeToThe = new JTextArea();
		panel_3.add(txtrWelcomeToThe, BorderLayout.CENTER);
		txtrWelcomeToThe
				.setText("Welcome to the messager.\r\nHere you can create the messages between the roles.\r\nFirst choose a sending activity from the combo box.\r\nThen choose one or more possible receiving activities.\r\nThen click add "
						+ "message receiving activities.");

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("possible receivers");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_1, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane);

		list = new JList<String>();
		panel_4.add(list, BorderLayout.CENTER);

		JButton btnNewButton = new JButton("add message receiving activities");
		panel_4.add(btnNewButton, BorderLayout.SOUTH);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_1.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton_1 = new JButton("save & exit");
		panel_5.add(btnNewButton_1);
		
	
		int i = 0;
		int k = 0;
		String roleterm = "";

		System.out.println("Print sender list" + Live2BPMN.senders);
		System.out.println("Print sender id" + Live2BPMN.senderids);
		System.out.println("Print receiver list" + Live2BPMN.receivers);
		System.out.println("Print receiver id" + Live2BPMN.receiverids);
		if (!(Live2BPMN.senders.isEmpty())) {
			int n = 0;
			for (n = 0; n < Live2BPMN.senders.size(); n++) {
				comboBox.addItem(Live2BPMN.senders.get(n));
			}
			ActionListener actionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					model = new DefaultListModel<String>();
					list.setModel(model);
					ItemSelectable is = (ItemSelectable) actionEvent.getSource();
					String name = selectedString(is);
					JOptionPane.showMessageDialog(null, "You have Selected: "+ name);
					StringTokenizer t1 = new StringTokenizer(name, ":");
					t1.nextToken();
					String currentTerm1 = t1.nextToken();
					setsendername(name);				
					int m = 0;
					for (m = 0; m < Live2BPMN.receivers.size(); m++) {
						String tempname;
						try {
							tempname = Live2BPMN.receivers.get(m);
							StringTokenizer t = new StringTokenizer(tempname,
									":");
							String currentTerm = new String();
							t.nextToken();
							currentTerm = t.nextToken();
							if (currentTerm1.substring(4).equals(
									currentTerm.substring(7))) {
								model.addElement(tempname);
								list.setModel(model);
							}
						} catch (Exception e) {
						}
					}
				}
				//
				private String selectedString(ItemSelectable is) {
					Object selected[] = is.getSelectedObjects();
					return ((selected.length == 0) ? "null"
							: (String) selected[0]);
				}
			};
			comboBox.addActionListener(actionListener);
			btnNewButton_1.addMouseListener(new MouseListener() {
				// @Override
				public void mouseClicked(MouseEvent e) {
					String newfile = "";
					File sFile = null;		
						try {
							JFileChooser fc;// = null; //new JFileChooser();
							
							if(Liveness2XPDLApp.getArgs().length>0){	
								String path = Liveness2XPDLApp.getArgs()[0].substring(0,Liveness2XPDLApp.getArgs()[0].lastIndexOf("/")+1);
								fc = new JFileChooser(path);
								//fc.setCurrentDirectory(new File(path));
							}
							else{
								fc = new JFileChooser();
							}
							
							int returnval = fc.showSaveDialog(frame);
							if (returnval == JFileChooser.APPROVE_OPTION) {
								sFile = new File(fc.getSelectedFile()+".bpmn");
								newfile = sFile.toString();
							}
							Bpmn.writeModelToFile(sFile, Liveness2XPDLApp.liveness2bpmn.modelInstance);
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							System.out.println("Error writing file");
						}			
					System.out.println("\nWritting BPMN model into file \""
							+ newfile + "\".");
					frame.dispose();
					Live2BPMN.senders = new LinkedList<String>();
					Live2BPMN.senderids = new LinkedList<String>();
					Live2BPMN.sendershapes = new LinkedList<BpmnShape>();
					Live2BPMN.sendernodes = new LinkedList<FlowNode>();
					Live2BPMN.receivers = new LinkedList<String>();
					Live2BPMN.receiverids = new LinkedList<String>();
					Live2BPMN.receivernodes = new LinkedList<FlowNode>();
					Live2BPMN.receivershapes = new LinkedList<BpmnShape>();
					}

				// @Override
				public void mouseEntered(MouseEvent e) {
					// // TODO Auto-generated method stub
					//
				}

				//
				// @Override
				public void mouseExited(MouseEvent e) {
					// // TODO Auto-generated method stub
					//
				}

				//
				// @Override
				public void mousePressed(MouseEvent e) {
					// // TODO Auto-generated method stub
					//
				}

				//
				// @Override
				public void mouseReleased(MouseEvent e) {
					// // TODO Auto-generated method stub
					//
				}
			});

			btnNewButton.addMouseListener(new MouseListener() {
				private FlowNode sendernode = null;
				private FlowNode receivernode = null;
				private BpmnShape sendershape = null;
				private BpmnShape receivershape = null;
				// @Override
				public void mouseClicked(MouseEvent e) {
					JOptionPane.showMessageDialog(null, "You will create a message please select a sender and one or more receivers.");
					String sendername = getsendername();
					System.out.println("The sender is:" + sendername);
					String tempsender = "";
					String tempsenderid = "";
					int i = 0;
					for (i = 0; i < Live2BPMN.senders.size(); i++) {
						tempsender = Live2BPMN.senders.get(i);
						if (tempsender == sendername) {
							tempsenderid = Live2BPMN.senderids.get(i);
							sendernode = Live2BPMN.sendernodes.get(i);
							sendershape = Live2BPMN.sendershapes.get(i);
						}
					}
					String tempreceiver = "";
					String tempreceiverid = "";
					int z = 0;
					int k = 0;
					for (z = 0; z < Live2BPMN.receivers.size(); z++) {
						tempreceiver = Live2BPMN.receivers.get(z);
						System.out.println(tempreceiver);
						for (k = 0; k < tempsenderlist.size(); k++)
							if (tempsenderlist.get(k).equals(tempreceiver)) {
								receivernode = Live2BPMN.receivernodes.get(z);
								associationid = associationid + 1;
								MessageFlow flow = Liveness2XPDLApp.liveness2bpmn.modelInstance.newInstance(MessageFlow.class);
								 Calendar cal2 = Calendar.getInstance();
								 long d = cal2.getTimeInMillis();
								Liveness2XPDLApp.liveness2bpmn.collaboration.addChildElement(flow);
								flow.setId("association"+z+d);
								flow.setSource((InteractionNode) sendernode);
								flow.setTarget((InteractionNode) receivernode);
								
								BpmnEdge flowEdge = Liveness2XPDLApp.liveness2bpmn.modelInstance.newInstance(BpmnEdge.class);
								flowEdge.setId("flowEdge"+associationid+1);
							    flowEdge.setBpmnElement(flow);
							    Liveness2XPDLApp.liveness2bpmn.processPlane.getDiagramElements().add(flowEdge);
							    
							    Waypoint startWaypoint = Liveness2XPDLApp.liveness2bpmn.modelInstance.newInstance(Waypoint.class);
								 startWaypoint.setX(668.0);
								 startWaypoint.setY(330.0);
								 flowEdge.getWaypoints().add(startWaypoint);

								Waypoint endWaypoint = Liveness2XPDLApp.liveness2bpmn.modelInstance.newInstance(Waypoint.class);
								endWaypoint.setX(718.0);
								endWaypoint.setY(330.0);
								flowEdge.getWaypoints().add(endWaypoint);	
							}
					}
					JOptionPane.showMessageDialog(null,
							"Messages created and submitted");
					tempsenderlist = new LinkedList<String>();
				}


				//
				// @Override
				public void mouseEntered(MouseEvent e) {
					// // TODO Auto-generated method stub
					//
				}

				//
				// @Override
				public void mouseExited(MouseEvent e) {
					// // TODO Auto-generated method stub
					//
				}

				//
				// @Override
				public void mousePressed(MouseEvent e) {
					// // TODO Auto-generated method stub
					//
				}

				//
				// @Override
				public void mouseReleased(MouseEvent e) {
					// // TODO Auto-generated method stub
					//
				}
				//
				  public MessageFlow createMessageFlow(ModelInstance modelInstance, FlowNode from, FlowNode to) {
					    MessageFlow sequenceFlow = createElement(Liveness2XPDLApp.liveness2bpmn.modelInstance, from.getId() + "-" + to.getId(), MessageFlow.class);
					    ((ModelElementInstanceImpl) Liveness2XPDLApp.liveness2bpmn.modelInstance).addChildElement(sequenceFlow);
					    sequenceFlow.setSource((InteractionNode) from);
					    from.getOutgoing().add((SequenceFlow) sequenceFlow);
					    sequenceFlow.setTarget((InteractionNode) to);
					    to.getIncoming().add((SequenceFlow) sequenceFlow);
					    return sequenceFlow;
					  }
				  
				  public <T extends BpmnModelElementInstance> T createElement(BpmnModelInstance modelInstance, String id, Class<MessageFlow> class1) {
					    T element = (T) Liveness2XPDLApp.liveness2bpmn.modelInstance.newInstance(class1);
					    element.setAttributeValue("id", id, true);
					    ((ModelElementInstanceImpl) modelInstance).addChildElement(element);
					    return element;
					  }
			});

			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent evt) {
					if (evt.getValueIsAdjusting())
						return;
					tempsenderlist = list.getSelectedValuesList();
					System.out.println("The minirolelist is:" + tempsenderlist);
				}

			});

		}
	
	}

	protected void setsendername(String name) {
		// TODO Auto-generated method stub
		sendername = name;
	}

	protected String getsendername() {
		// TODO Auto-generated method stub
		return sendername;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 800, 400);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
