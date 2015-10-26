package aseme.transformations.xpdl;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.ImageIcon;
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
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.UIManager;

import org.enhydra.jxpdl.elements.Activity;
import org.enhydra.jxpdl.elements.Association;
import org.enhydra.jxpdl.elements.Package;
import org.enhydra.jxpdl.elements.WorkflowProcess;

public class Inter_role_messages_definition extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5657667412211602793L;
	private static Package pkg1;
	private static String sendername;
	private JPanel contentPane;
	private JList<String> list;

	List<String> senderList = new LinkedList<String>();
	List<String> senderid = new LinkedList<String>();
	List<String> receiverList = new LinkedList<String>();
	List<String> receiverid = new LinkedList<String>();
	List<String> tempsenderlist = new LinkedList<String>();

	private static JFrame frame2;
	static DefaultListModel<String> model = new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inter_role_messages_definition frame = new Inter_role_messages_definition();
					frame.setBounds(0, 0, 800, 400);
					frame.setVisible(true);
					setFrame(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inter_role_messages_definition() {

		setBackground(Color.white);
		setFont(UIManager.getFont("List.font"));
		setTitle("Inter-role Messages Definition");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		contentPane.setLayout(new BorderLayout(10, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblAgentactivity = new JLabel("Agent:Activity");
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

		JLabel lblNewLabel = new JLabel("");
		panel_3.add(lblNewLabel, BorderLayout.SOUTH);
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\nek\\Desktop\\Capture.PNG"));

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

		pkg1 = getthePackage();
		WorkflowProcess wp = (WorkflowProcess) pkg1.getWorkflowProcesses().get(
				0);

		int i = 0;
		int k = 0;
		String roleterm = "";
		for (i = 0; i < wp.getActivities().size() - 1; i++)
			;
		{
			while (i == wp.getActivities().size() - 1) {
				Activity temp = (Activity) wp.getActivities().get(i - k);
				k = k + 1;
				if (temp.getName().startsWith("Send")) {
					roleterm = temp.getId().replaceAll("[^a-zA-Z]+", "");
					senderList.add(roleterm + ":" + temp.getName());
					senderid.add(temp.getId());
				} else if (temp.getName().startsWith("Receive")) {
					roleterm = temp.getId().replaceAll("[^a-zA-Z]+", "");
					receiverList.add(roleterm + ":" + temp.getName());
					receiverid.add(temp.getId());
				}
				if (k == wp.getActivities().size() - 1) {
					break;
				}
			}

		}

		System.out.println("Print sender list" + senderList);
		System.out.println("Print sender id" + senderid);
		System.out.println("Print receiver list" + receiverList);
		System.out.println("Print receiver id" + receiverid);

		if (!(senderList.isEmpty())) {

			int n = 0;
			for (n = 0; n < senderList.size(); n++) {
				comboBox.addItem(senderList.get(n));
			}

			ActionListener actionListener = new ActionListener() {

				public void actionPerformed(ActionEvent actionEvent) {
					model = new DefaultListModel<String>();
					list.setModel(model);
					ItemSelectable is = (ItemSelectable) actionEvent
							.getSource();
					String name = selectedString(is);
					setsendername(name);
					JOptionPane.showMessageDialog(null, "You have Selected: "
							+ name);
					StringTokenizer t1 = new StringTokenizer(name, ":");
					t1.nextToken();
					String currentTerm1 = t1.nextToken();

					System.out.println("What is the sendername?" + name);
					int m = 0;
					for (m = 0; m < receiverList.size(); m++) {
						String tempname;
						try {
							tempname = receiverList.get(m);
							StringTokenizer t = new StringTokenizer(tempname,
									":");
							String currentTerm = new String();
							t.nextToken();
							currentTerm = t.nextToken();
							if (currentTerm1.substring(4).equals(
									currentTerm.substring(7))) {
								model.addElement(receiverList.get(m));
								list.setModel(model);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
					try {
						JFileChooser fc = new JFileChooser();
						int returnval = fc.showSaveDialog(frame2);
						if (returnval == JFileChooser.APPROVE_OPTION) {
							File file = fc.getSelectedFile();
							newfile = file.toString();
						}
						Liveness2XPDL.writeToFile(newfile, pkg1);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
					}
					System.out.println("\nWritting XPDL model into file \""
							+ newfile + "\".");

					frame2.dispose();
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
			});

			btnNewButton.addMouseListener(new MouseListener() {
				// @Override
				public void mouseClicked(MouseEvent e) {
					// // TODO Auto-generated method stub
					JOptionPane
							.showMessageDialog(null,
									"You will create a message please select a sender and one or more receivers.");
					String sendername = getsendername();
					System.out.println("The sender is:" + sendername);
					String tempsender = "";
					String tempsenderid = "";
					int i = 0;
					for (i = 0; i < senderList.size(); i++) {
						tempsender = senderList.get(i);
						if (tempsender == sendername) {
							tempsenderid = senderid.get(i);
						}
					}
					System.out.println("What is the tempsenderid"
							+ tempsenderid);

					String tempreceiver = "";
					String tempreceiverid = "";
					int z = 0;
					int k = 0;
					int associationid = 1;
					for (z = 0; z < receiverList.size(); z++) {
						tempreceiver = receiverList.get(z);
						for (k = 0; k < tempsenderlist.size(); k++)
							if (tempsenderlist.get(k).equals(tempreceiver)) {
								tempreceiverid = receiverid.get(z);
								associationid = associationid + 1;
								System.out
										.println("What is the tempreceiver id"
												+ tempreceiverid);
								Association asoc = (Association) pkg1
										.getAssociations().generateNewElement();
								asoc.setId("" + associationid);
								asoc.setSource(tempsenderid);
								asoc.setTarget(tempreceiverid);
								asoc.setAssociationDirectionFROM();
								pkg1.getAssociations().add(asoc);
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

	protected static void setsendername(String name) {
		sendername = name;
	}

	protected static String getsendername() {
		// TODO Auto-generated method stub
		return sendername;
	}

	public static Package getthePackage() {
		// TODO Auto-generated method stub
		return pkg1;
	}

	public static void setthePackage(Package pkg) {
		pkg1 = pkg;
	}

	public static void setFrame(JFrame frame1) {

		frame2 = frame1;
	}

	public static JFrame getFrame() {
		return frame2;
	}

}
