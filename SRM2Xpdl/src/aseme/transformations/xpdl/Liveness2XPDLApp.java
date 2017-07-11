package aseme.transformations.xpdl;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.SwingConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.IOUtils;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.enhydra.jxpdl.XPDLRepositoryHandler;
import org.enhydra.jxpdl.elements.Activity;
import org.enhydra.jxpdl.elements.Package;
import org.enhydra.jxpdl.elements.WorkflowProcess;
import org.w3c.dom.Document;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import SRM.Role;
import SRM.SRMPackage;
import SRM.SRMmodel;

public class Liveness2XPDLApp extends JFrame implements MouseListener,
		ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7396635470457189928L;
	private static JFrame frame2;
	private static String[] arguments;
	private JPanel contentPane;
	private String input;
	static ResourceSet resourceSet = new ResourceSetImpl();
	static List<String> listofroles = new LinkedList<String>();
	static DefaultListModel<String> model = new DefaultListModel<String>();
	public static JList<String> rolelist = new JList<String>();
	private static List<String> miniroles = new LinkedList<String>();
	private static List<String> formulalist = new LinkedList<String>();
	//static String filename = "C:/Users/nek/Desktop/Myfirst.xpdl";
	Live2BPMN live2bpmn = new Live2BPMN();
	public static Liveness2BPMN liveness2bpmn = new Liveness2BPMN();
	Package Applicationpkg =null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		setArgs(args);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Liveness2XPDLApp frame = new Liveness2XPDLApp();
					setFrame(frame);

//					rolelist = new JList<String>();
//					miniroles = new LinkedList<String>();
//					formulalist = new LinkedList<String>();
//					String path = arguments[0].substring(0,arguments[0].lastIndexOf("/")+1);
//					System.out.println(path);
					
					//openSrm();
					
					frame.setVisible(true);
					
				} catch (Exception e) {
					
					
					
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Liveness2XPDLApp() {
		setTitle("Liveness to XPDL or to BPMN Transformation Application");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 307);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Open SRM");
		mntmNewMenuItem_1.setActionCommand("open");
		mntmNewMenuItem_1.addActionListener(this);
		mnFile.add(mntmNewMenuItem_1);

		JMenuItem mntmEditFormula = new JMenuItem("Edit Gaia Formula");
		mntmEditFormula.setActionCommand("edit");
		mntmEditFormula.addActionListener(this);
		mnFile.add(mntmEditFormula);

		JMenuItem mntmDeleteSelectedRoles = new JMenuItem(
				"Delete Selected Role(s)");
		mntmDeleteSelectedRoles.setActionCommand("delete");
		mntmDeleteSelectedRoles.addActionListener(this);
		mnFile.add(mntmDeleteSelectedRoles);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setActionCommand("exit");
		mntmExit.addActionListener(this);
		mnFile.add(mntmExit);

		JMenu mnTransform = new JMenu("Transform");
		menuBar.add(mnTransform);

		JMenuItem mntmSingleRoleTransformation = new JMenuItem(
				"Single Role Transformation into XPDL");
		mntmSingleRoleTransformation.setActionCommand("single");
		mntmSingleRoleTransformation.addActionListener(this);
		mnTransform.add(mntmSingleRoleTransformation);

		JMenuItem mntmMultipleRoleTransformation = new JMenuItem(
				"Multiple Role Transformation into XPDL");
		mntmMultipleRoleTransformation.setActionCommand("multi");
		mntmMultipleRoleTransformation.addActionListener(this);
		mnTransform.add(mntmMultipleRoleTransformation);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Single Role Transformation into BPMN");
		mntmNewMenuItem.setActionCommand("singlebpmn");
		mntmNewMenuItem.addActionListener(this);
		mnTransform.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Multiple Role Transformation into BPMN");
		mntmNewMenuItem_2.setActionCommand("multibpmn");
		mntmNewMenuItem_2.addActionListener(this);
		mnTransform.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("BPMN multiple roles into Lanes Option a");
		mntmNewMenuItem_3.setActionCommand("multilanesa");
		mntmNewMenuItem_3.addActionListener(this);
		mnTransform.add(mntmNewMenuItem_3);
		
		
		JMenuItem mntmBpmnMultipleRoles = new JMenuItem("BPMN multiple roles into Lanes Option b");
		mntmBpmnMultipleRoles.setActionCommand("multilanes");
		mntmBpmnMultipleRoles.addActionListener(this);
		mnTransform.add(mntmBpmnMultipleRoles);
		
		JMenu mnNewModel = new JMenu("CreateNew");
		menuBar.add(mnNewModel);
		
		JMenuItem mntmNewXpdlModel = new JMenuItem("New XPDL model");
		mntmNewXpdlModel.setActionCommand("newxpdl");
		mntmNewXpdlModel.addActionListener(this);
		mnNewModel.add(mntmNewXpdlModel);
		
		JMenuItem mntmNewBpmnModel = new JMenuItem("New BPMN model");
		mntmNewBpmnModel.setActionCommand("newbpmn");
		mntmNewBpmnModel.addActionListener(this);
		mnNewModel.add(mntmNewBpmnModel);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setActionCommand("about");
		mntmAbout.addActionListener(this);
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblListOfRoles = new JLabel("List of Roles");
		contentPane.add(lblListOfRoles, BorderLayout.NORTH);
		lblListOfRoles.setVerticalAlignment(SwingConstants.TOP);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		rolelist = new JList<String>();
		rolelist.setModel(model);
		scrollPane.setViewportView(rolelist);

		rolelist.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getValueIsAdjusting())
					return;
				miniroles = rolelist.getSelectedValuesList();
				System.out.println("The minirolelist is:" + miniroles);
			}
		});

		rolelist.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					System.out.println("Delete pressed");
					int count = rolelist.getSelectedIndices().length;
					for (int i = 0; i < count; i++) {
						model.removeElementAt(rolelist.getSelectedIndex());
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("open".equals(e.getActionCommand())) {

			JFileChooser fc;// = null;	//new JFileChooser();
			if(arguments.length>0){	
				String path = arguments[0].substring(0,arguments[0].lastIndexOf("/")+1);
				fc = new JFileChooser(path);
				//fc.setCurrentDirectory(new File(path));
			}
			else{
				fc = new JFileChooser();
			}
			
			int returnVal = fc.showOpenDialog(getFrame());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				input = file.toString();
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			    String content = null;
				try {
					content = IOUtils.toString(fis, Charset.defaultCharset());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			    content = content.replaceAll("&#xD;", "");
			    FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			    try {
					IOUtils.write(content, new FileOutputStream(file), Charset.defaultCharset());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			    try {
					fis.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			    try {
					fos.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				};
			}
			input = input.replaceAll("&#xD;", "");
			System.out.println(input);
			String agent = null;
			try {
				resourceSet
						.getResourceFactoryRegistry()
						.getExtensionToFactoryMap()
						.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
								new XMIResourceFactoryImpl());

				resourceSet.getPackageRegistry().put(SRMPackage.eNS_URI,
						SRMPackage.eINSTANCE);
				// load SRM model
				Resource r = null;
				if (arguments != null && arguments.length > 1) {
					r = resourceSet.getResource(
							URI.createFileURI(arguments[0]), true);
				} else {
					r = resourceSet.getResource(URI.createFileURI(input), true);
				}
			
				SRMmodel srm = (SRMmodel) r.getContents().get(0);
				
				for(Iterator<Role> roles = srm.getRoles().iterator(); roles.hasNext();){
					
					Role tmp = roles.next();
					
					String liveness = tmp.getLiveness().replaceAll("/r", "");
					System.out.println(liveness);
					liveness = liveness.replaceAll("/r/n", "");
					liveness = liveness.replaceAll(" ", "");
					listofroles.add(liveness);

					StringTokenizer temp = new StringTokenizer(liveness, "=");
					String leftHandSide = temp.nextToken();
					agent = "Role:" + leftHandSide;
					model.addElement(agent);
					rolelist.setModel(model);
					
				}	
				//}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			}
		} else if ("edit".equals(e.getActionCommand())) {
			String input = JOptionPane.showInputDialog(null,
					"Please insert your liveness formula.");
			listofroles.add(input);
			try {
				StringTokenizer temp = new StringTokenizer(input, "=");
				String leftHandSide = temp.nextToken();
				String agent = "Role:" + leftHandSide;
				model.addElement(agent);
				rolelist.setModel(model);
				//}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			}

		} else if ("exit".equals(e.getActionCommand())) {
			getFrame().dispose();
		} else if ("about".equals(e.getActionCommand())) {
			JOptionPane
					.showMessageDialog(
							null,
							"Welcome to the Liveness2XPDL transformation application\n"
									+ "You can open a SRM file to be transformed from File-->Open\n"
									+ "You can write your own liveness formula from File-->Edit Gaia Formula\n"
									+ "You can delete one or more roles from File--> Delete Selected Roles\n"
									+ "Or by selecting one or more roles in the list and pressing the delete key\n"
									+ "If you want to transform one role to its xpdl model Transform-->Single Role into XPDL Transformation\n"
									+ "If you want to transform multiple roles to their xpdl model Transform -->Multiple Roles into XPDL Transformation\n"
									+ "If you want to transform one role to its bpmn model Transform--> Single Role into BPMN Transformation\n"
									+ "If you want to transform multiple roles to their bpmn model Transform--> Multiple Roles into BPMN Transformation\n"
									+ "If you want to write the xpdl model to a certain file press the 'write to target file' button.\n");
		} else if ("single".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(null,"You will create a single xpdl role model.");	
			Applicationpkg = new Package();
			if (miniroles.isEmpty()) {
				JOptionPane.showMessageDialog(getFrame(),
						"Please select a role.");
			} else {
				String temp = miniroles.get(0);
				String temp1 = temp.substring(5);
				for (int k = 0; k < listofroles.size(); k++) {
					if (listofroles.get(k).startsWith(temp1)) {
						formulalist.add(listofroles.get(k));
					}
				}
				try {
					Applicationpkg = Liveness2XPDL.createRoles(formulalist,
							Applicationpkg, arguments[0]);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
				}
				
				this.saveXpdl(Applicationpkg);
				miniroles = new LinkedList<String>();
				formulalist = new LinkedList<String>();
			}
		} else if ("multi".equals(e.getActionCommand())) {
			Applicationpkg = new Package();
			JOptionPane.showMessageDialog(null, "You will create a multi role xpdl model.");
			if (miniroles.isEmpty()) {
				JOptionPane.showMessageDialog(getFrame(),
						"Please select one or more roles.");
			} else {
				System.out.println("What is the miniroles list?" + miniroles);
				for (int i = 0; i < miniroles.size(); i++) {
					String temp1 = "";
					try {
						String temp = miniroles.get(i);
						temp1 = temp.substring(5);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println("Listener exception");
					}
					for (int k = 0; k < listofroles.size(); k++) {
						if (listofroles.get(k).startsWith(temp1)) {
							formulalist.add(listofroles.get(k));
						}
					}
				}
				try {
					System.out
							.println("What is the formulalist:" + formulalist);
					Applicationpkg = Liveness2XPDL.createRoles(formulalist,
							Applicationpkg, arguments[0]);
				} catch (Exception e2) {
				}
				WorkflowProcess wp = (WorkflowProcess) Applicationpkg.getWorkflowProcesses().get(0);
				int flag=0;
				for (int i = 0; i < wp.getActivities().size(); i++) {
					try {
						Activity temp = (Activity) wp.getActivities().get(i);
						if (temp.getName().startsWith("Send")) {
							System.out.println("Execution of messager");
							Inter_role_messages_definition.setthePackage(Applicationpkg);
							Inter_role_messages_definition.main(getArgs());
							Applicationpkg = Inter_role_messages_definition.getthePackage();
							flag=1;
							break;
						}
					} catch (Exception e1) {
					}
				}
				if(flag==0){
				this.saveXpdl(Applicationpkg);
				}
				else{}
				miniroles = new LinkedList<String>();
				formulalist = new LinkedList<String>();
			}
		} else if ("delete".equals(e.getActionCommand())) {
			int count = rolelist.getSelectedIndices().length;
			for (int i = 0; i < count; i++) {
				model.removeElementAt(rolelist.getSelectedIndex());
			}
		}else if("newxpdl".equals(e.getActionCommand())){
			Applicationpkg = new Package();
		}else if("newbpmn".equals(e.getActionCommand())){
			liveness2bpmn.modelInstance = null;
		} else if("singlebpmn".equals(e.getActionCommand())){
			JOptionPane.showMessageDialog(null, "You will create a single bpmn role model.");
			if (miniroles.isEmpty()) {
				JOptionPane.showMessageDialog(getFrame(),
						"Please select a role.");
			} else {
				String temp = miniroles.get(0);
				String temp1 = temp.substring(5);
				for (int k = 0; k < listofroles.size(); k++) {
					if (listofroles.get(k).startsWith(temp1)) {
						formulalist.add(listofroles.get(k));
					}
				}
				Liveness2BPMN liveness2bpmn = new Liveness2BPMN();
				try {
					liveness2bpmn.createRoles(formulalist);
				} catch (Exception e2) {
				}
				String newfile = "";
				try {
					JFileChooser fc = new JFileChooser();
					File sFile =null;
					int returnval = fc.showSaveDialog(getFrame());
					if (returnval == JFileChooser.APPROVE_OPTION) {
						sFile = new File(fc.getSelectedFile()+".bpmn");
						newfile = sFile.toString();
					}
					Bpmn.writeModelToFile(sFile, liveness2bpmn.modelInstance);
				} catch (Exception e1) {
				}
				System.out.println("\nWritting BPMN model into this file \""+ newfile + "\".");
				miniroles = new LinkedList<String>();
				formulalist = new LinkedList<String>();
			}	
		}else if("multibpmn".equals(e.getActionCommand())){
			JOptionPane.showMessageDialog(null, "You will create a multi role bpmn model.");
			if (miniroles.isEmpty()) {
				JOptionPane.showMessageDialog(getFrame(), "Please select one or more roles.");
			} else {
				for (int i = 0; i < miniroles.size(); i++) {
					String temp1 = "";
					try {
						String temp = miniroles.get(i);
						temp1 = temp.substring(5);
					} catch (Exception e1) {
						System.out.println("Listener exception");
					}
					for (int k = 0; k < listofroles.size(); k++) {
						if (listofroles.get(k).startsWith(temp1)) {
							formulalist.add(listofroles.get(k));
						}
					}
				}
				try {
					liveness2bpmn.createRoles(formulalist);
				} catch (Exception e2) {
				}
				int flag=0;
				for (int i = 0; i < formulalist.size(); i++) {
						if (formulalist.get(i).contains("Send")) {
							System.out.println("Execution of bpmn messager");
							Inter_role_messages_definitions_bpmn.main(null);
							flag=1;
							break;
						}
				}
				if (flag==0){
				saveBpmn();
				}
				else{
				}
					
				miniroles = new LinkedList<String>();
				formulalist = new LinkedList<String>();
			}
		}else if("multilanesa".equals(e.getActionCommand())){
			JOptionPane.showMessageDialog(null, "You will create a multi lanes bpmn model.");
			if (miniroles.isEmpty()) {
				JOptionPane.showMessageDialog(getFrame(), "Please select one or more roles.");
			} else {
				for (int i = 0; i < miniroles.size(); i++) {
					String temp1 = "";
					try {
						String temp = miniroles.get(i);
						temp1 = temp.substring(5);
					} catch (Exception e1) {
						System.out.println("Listener exception");
					}

					for (int k = 0; k < listofroles.size(); k++) {
						try {
							if (listofroles.get(k).startsWith(temp1)) {
								formulalist.add(listofroles.get(k));
							}
						} catch (Exception e1) {
						}
					}
				}
				try {
					liveness2bpmn.createOptionACollaborationLanes(formulalist);
				} catch (Exception e2) {
					System.out.println("formula list explosion");
				}
				int flag = 0;
				for (int i = 0; i < formulalist.size(); i++) {
						if (formulalist.get(i).contains("Send")) {
							System.out.println("Execution of bpmn messager");
							Inter_messages_lanes_bpmn.main(null);
							flag=1;
							break;
						}
					}
				if (flag==0){
				saveBpmn();
				}
				else{
				}
				miniroles = new LinkedList<String>();
				formulalist = new LinkedList<String>();
				liveness2bpmn.lanecounter = 0;
			}	
		}else if("multilanes".equals(e.getActionCommand())){
			JOptionPane.showMessageDialog(null, "You will create a multi lanes bpmn model.");
			if (miniroles.isEmpty()) {
				JOptionPane.showMessageDialog(getFrame(), "Please select one or more roles.");
			} else {
				for (int i = 0; i < miniroles.size(); i++) {
					String temp1 = "";
					try {
						String temp = miniroles.get(i);
						temp1 = temp.substring(5);
					} catch (Exception e1) {
						System.out.println("Listener exception");
					}

					for (int k = 0; k < listofroles.size(); k++) {
						try {
							if (listofroles.get(k).startsWith(temp1)) {
								formulalist.add(listofroles.get(k));
							}
						} catch (Exception e1) {
						}
					}
				}
				try {
					liveness2bpmn.createCollaborationLanes(formulalist);
				} catch (Exception e2) {
					System.out.println("formula list explosion");
				}
				int flag = 0;
				for (int i = 0; i < formulalist.size(); i++) {
						if (formulalist.get(i).contains("Send")) {
							System.out.println("Execution of bpmn messager");
							Inter_messages_lanes_bpmn.main(null);
							flag=1;
							break;
						}
					}
				if (flag==0){
				saveBpmn();
				}
				else{
				}
				miniroles = new LinkedList<String>();
				formulalist = new LinkedList<String>();
				liveness2bpmn.lanecounter = 0;
			}
		}	
	}

	private void writeToFile(String outputFile, Package applicationpkg) throws Exception {
		Document document = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbuilder = dbf.newDocumentBuilder();
		document = dbuilder.newDocument();
		// output stream will either be the FileOutputStream in the
		// case of save as, or the ByteArrayOutputStream if we are
		// saving an existing file
		FileOutputStream os;
		// try to open random access file as rw, if it fails
		// the saving shouldn't occur
		os = new FileOutputStream(outputFile, true);

		// Here we get all document elements set
		XPDLRepositoryHandler repH = new XPDLRepositoryHandler();
		repH.setXPDLPrefixEnabled(true);
		repH.toXML(document, applicationpkg);

		// Use a Transformer for output
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		transformer.setOutputProperty("indent", "yes");
		transformer.setOutputProperty(
				"{http://xml.apache.org/xslt}indent-amount", "4");
		transformer.setOutputProperty("encoding", "UTF8");
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(os);
		transformer.transform(source, result);

		os.close();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void setFrame(JFrame frame1) {

		frame2 = frame1;
	}

	public static JFrame getFrame() {
		return frame2;
	}

	public static void setArgs(String[] args) {
		// TODO Auto-generated method stub
		arguments = args;
	}

	public static String[] getArgs() {
		return arguments;
	}
	
	
//	private static void openSrm(){
//		
//
//		if (rolelist.countComponents()>0)
//			rolelist = new JList<String>();
//		
//		
//		if (arguments.length>0){
//			String agent = new String();
//			
//			try {
//					resourceSet
//							.getResourceFactoryRegistry()
//							.getExtensionToFactoryMap()
//							.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
//									new XMIResourceFactoryImpl());
//
//					resourceSet.getPackageRegistry().put(SRMPackage.eNS_URI,
//							SRMPackage.eINSTANCE);
//					// load SRM model
//					Resource r = null;
//					//if (arguments != null && arguments.length > 1) {
//						r = resourceSet.getResource(
//								URI.createFileURI(arguments[0]), true);
////					} else {
////						r = resourceSet.getResource(URI.createFileURI(input), true);
////					}
//
//					SRMmodel srm = (SRMmodel) r.getContents().get(0);
//					
//					for(Iterator<Role> roles = srm.getRoles().iterator(); roles.hasNext();){
//						
//						Role tmp = roles.next();
//						
//						//String liveness = srm.getRoles().get(0).getLiveness();
//						String liveness = tmp.getLiveness();
//						liveness = liveness.replace(" ", "");
//						listofroles.add(liveness);
//
//						StringTokenizer temp = new StringTokenizer(liveness, "=");
//						String leftHandSide = temp.nextToken();
//						//if(expression.contains(leftHandSide)){
//							//JOptionPane.showMessageDialog(null, "The liveness property of the inserted SRM model has circulaer reference from the right hand side expression terms to the left hand side term");
//							//JOptionPane.showMessageDialog(null, "Please try to correct your SRM file and try to reinsert it.");
//						//}
//						//else{
//						agent = "Role:" + leftHandSide;
//						//if(!model.contains(agent)){
//							model.addElement(agent);
//						//}
//						rolelist.setModel(model);
//						
//						
//					}	
//					//}
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					System.err.println("what the flying fuck??");
//				}
//		}
//		
//		
//		
//	}
	

	
	private static void saveBpmn(){
		
		
		//String newfile = "";
		try {
			JFileChooser fc;// = new JFileChooser();
			
			if(arguments.length>0){	
				String path = arguments[0].substring(0,arguments[0].lastIndexOf("/")+1);
				fc = new JFileChooser(path);
				//fc.setCurrentDirectory(new File(path));
			}
			else{
				fc = new JFileChooser();
			}
			
//			fc.setFileFilter(new FileFilter() {
//				
//				@Override
//				public String getDescription() {
//					
//					return "Bpmn files (*.bpmn)";
//				}
//				
//				@Override
//				public boolean accept(File f) {
//					
//				String filename = f.getName().toLowerCase();
//				return filename.endsWith(".bpmn") ;
//					
//				}
//			});
			
			File sFile =null;
			int returnval = fc.showSaveDialog(getFrame());
			if (returnval == JFileChooser.APPROVE_OPTION) {
					sFile = new File(fc.getSelectedFile()+".bpmn");
					Bpmn.writeModelToFile(sFile, liveness2bpmn.modelInstance);
			}
			
		} catch (Exception e1) {
		}
	}
	
	private void saveXpdl(Package ApplicationPkg ){
		
		String newfile = "";
		try {
			JFileChooser fc;// = new JFileChooser();
			
			if(arguments.length>0){	
				String path = arguments[0].substring(0,arguments[0].lastIndexOf("/")+1);
				fc = new JFileChooser(path);
				//fc.setCurrentDirectory(new File(path));
			}
			else{
				fc = new JFileChooser();
			}
			
//			fc.setFileFilter(new FileFilter() {
//			
//			@Override
//			public String getDescription() {
//				
//				return "Xpdl files (*.xpdl)";
//			}
//			
//			@Override
//			public boolean accept(File f) {
//				
//			String filename = f.getName().toLowerCase();
//			return filename.endsWith(".xpdl") ;
//				
//			}
//		});
			

			int returnval = fc.showSaveDialog(getFrame());					
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File sFile = new File(fc.getSelectedFile()+".xpdl");
				newfile = sFile.toString();
			}
			writeToFile(newfile, ApplicationPkg);
		} catch (Exception e1) {
		}
		
	}
	
}
