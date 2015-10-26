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
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

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
	public static JList<String> rolelist = null;
	private static List<String> miniroles = new LinkedList<String>();
	private static List<String> formulalist = new LinkedList<String>();
	static String filename = null ; //"C:/Users/nek/Desktop/Myfirst.xpdl";

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

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public static void setFilename( String file){
		filename = file;
		
	}
	
	/**
	 * Create the frame.
	 */
	public Liveness2XPDLApp() {
		setTitle("Liveness2XPDL Transformation Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				"Single Role Transformation");
		mntmSingleRoleTransformation.setActionCommand("single");
		mntmSingleRoleTransformation.addActionListener(this);
		mnTransform.add(mntmSingleRoleTransformation);

		JMenuItem mntmMultipleRoleTransformation = new JMenuItem(
				"Multiple Role Transformation");
		mntmMultipleRoleTransformation.setActionCommand("multi");
		mntmMultipleRoleTransformation.addActionListener(this);
		mnTransform.add(mntmMultipleRoleTransformation);

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

			///
			JFileChooser fc;
			
			if (filename != null){
				fc = new JFileChooser(filename);
			}
			else{
				fc = new JFileChooser();
			}
			
			//JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(getFrame());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				input = file.toString();
			}

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
					
					//String liveness = srm.getRoles().get(0).getLiveness();
					String liveness = tmp.getLiveness();
					liveness = liveness.replace(" ", "");
					listofroles.add(liveness);

					StringTokenizer temp = new StringTokenizer(liveness, "=");
					String leftHandSide = temp.nextToken();
					String expression = temp.nextToken();
					//if(expression.contains(leftHandSide)){
						//JOptionPane.showMessageDialog(null, "The liveness property of the inserted SRM model has circulaer reference from the right hand side expression terms to the left hand side term");
						//JOptionPane.showMessageDialog(null, "Please try to correct your SRM file and try to reinsert it.");
					//}
					//else{
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
				String expression = temp.nextToken();
				if(expression.contains(leftHandSide)){
					JOptionPane.showMessageDialog(null, "The liveness property of the inserted formula has circular reference from the right hand side expression terms to the left hand side term");
					JOptionPane.showMessageDialog(null, "Please try to correct your formula and reinsert it");
				}else{
				String agent = "Role:" + leftHandSide;
				model.addElement(agent);
				rolelist.setModel(model);
				}
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
									+ "If you want to transform one role Transform-->Single Role Transformation\n"
									+ "If you want to transform multiple roles Transform -->Multiple Roles Transformation\n"
									+ "If you want to write the xpdl model to a certain file press the 'write to target file' button.");
		} else if ("single".equals(e.getActionCommand())) {

			JOptionPane.showMessageDialog(null,
					"You will create a single role model.");
			Package Applicationpkg =null;
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
							Applicationpkg, filename);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
				}

				String newfile = "";
				try {
					
					///
					JFileChooser fc;
					
					if (filename != null){
						fc = new JFileChooser(filename);
					}
					else{
						fc = new JFileChooser();
					}
					//JFileChooser fc = new JFileChooser();
					int returnval = fc.showSaveDialog(getFrame());
					if (returnval == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						newfile = file.toString();
					}
					writeToFile(newfile, Applicationpkg);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("\nWritting XPDL model into file this \""
						+ newfile + "\".");

				miniroles = new LinkedList<String>();
				formulalist = new LinkedList<String>();
			}
		} else if ("multi".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(null,
					"You will create a multi role model.");

			Package Applicationpkg =null;
			Applicationpkg = new Package();

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
							Applicationpkg, filename);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					//JOptionPane.showMessageDialog(getFrame(), "formulalist : " + formulalist.size()+ "\tlistofroles :" + listofroles.size());
				}

				WorkflowProcess wp = (WorkflowProcess) Applicationpkg
						.getWorkflowProcesses().get(0);

				for (int i = 0; i < wp.getActivities().size(); i++) {
					try {

						Activity temp = (Activity) wp.getActivities().get(i);
						if (temp.getName().startsWith("Send")) {
							System.out.println("Execution of messager");
							Inter_role_messages_definition
									.setthePackage(Applicationpkg);
							Inter_role_messages_definition.main(getArgs());
							Applicationpkg = Inter_role_messages_definition
									.getthePackage();
							break;
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
					}
				}
				String newfile = "";
				try {
					
					///
					JFileChooser fc;
					
					if (filename != null){
						fc = new JFileChooser(filename);
					}
					else{
						fc = new JFileChooser();
					}
					
					//JFileChooser fc = new JFileChooser();
					int returnval = fc.showSaveDialog(getFrame());
					if (returnval == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						newfile = file.toString();
					}
					writeToFile(newfile, Applicationpkg);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				}
				System.out.println("\nWritting XPDL model into file \""
						+ newfile + "\".");

				miniroles = new LinkedList<String>();
				formulalist = new LinkedList<String>();
			}
		} else if ("delete".equals(e.getActionCommand())) {
			int count = rolelist.getSelectedIndices().length;
			for (int i = 0; i < count; i++) {
				model.removeElementAt(rolelist.getSelectedIndex());
			}
		}
	}

	private void writeToFile(String outputFile, Package applicationpkg) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		// System.out.println("PKGEPS=" + pkg.getExternalPackageIds());
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

}
