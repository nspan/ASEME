package aseme.transformations.xpdl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.enhydra.jxpdl.XMLUtil;
import org.enhydra.jxpdl.XPDLRepositoryHandler;
import org.enhydra.jxpdl.elements.Lane;
import org.enhydra.jxpdl.elements.NodeGraphicsInfo;
import org.enhydra.jxpdl.elements.Package;
import org.enhydra.jxpdl.elements.Pool;
import org.enhydra.jxpdl.elements.WorkflowProcess;
import org.w3c.dom.Document;

import SRM.SRMPackage;
import SRM.SRMmodel;

public class Liveness2XPDL {

	Package pkg = null;
	Pool p = null;
	WorkflowProcess wp = null;
	Lane l1 = null;
	int xmiid = 0;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet
				.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new XMIResourceFactoryImpl());

		// Register the package to ensure it is available during loading.

		resourceSet.getPackageRegistry().put(SRMPackage.eNS_URI,
				SRMPackage.eINSTANCE);

		// load SRM model

		Resource r = null;
		if (args != null && args.length > 1) {
			r = resourceSet.getResource(URI.createFileURI(args[0]), true);
		} else {
			try {
				r = resourceSet.getResource(
						URI.createFileURI("thesis-Broker.srm"), true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String liveness = null;
		// mms-initial-MeetingsManager.srm
		SRMmodel srm = (SRMmodel) r.getContents().get(0);
		// Liveness2XPDL.liveness = srm.getRoles().get(0).getLiveness();
		// Liveness2XPDL.liveness = Liveness2XPDL.liveness.replaceAll(" ", "");
		liveness = srm.getRoles().get(0).getLiveness();
		liveness = liveness.replaceAll(" ", "");

		// System.out.println("What is the liveness?" + Liveness2XPDL.liveness);
		System.out.println("What is the liveness?" + liveness);

		String agent1 = "ComplexProvider=SP~\n"
				+ "SP=ReceiveRequestMessage.ProcessRequest.SendResponseMessage\n"
				+ "ProcessRequest=(DecideRouteType.SR.SortRoutes)|(DecidePOITypes.SR.DecidePois.SR)\n"
				+ "SR=SendRequestMessage.ReceiveResponseMessage";

		String agent2 = "Testmessager1=ReceiveRequestMessage.ProcessRequest.SendResponseMessage\n"
				+ "testreceiver2=SendRequestMessage.ReceiveResponseMessage";

		List<String> roles = new LinkedList<String>();
		roles.add(liveness);
		roles.add(agent1);
		roles.add(agent2);

		String filename = "C:/Users/nek/Desktop/Myfirst.xpdl";

		File f = new File(filename);
		try {
			filename = f.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = f.getName().substring(0,
				((File) f).getName().lastIndexOf("."));
		System.out.println("Creating XPDL Model.\n");

		String id = name;
		if (!XMLUtil.isIdValid(id)) {
			id = "test";
		}

		System.out.println("...creating Package [Id=" + id + ",Name=" + name
				+ ",Script-type=text/javascript]");
		Package pkg = new Package();
		pkg.setId(name);
		pkg.setName(name);
		pkg.getPackageHeader().setXPDLVersion("2.1");
		pkg.getPackageHeader().setVendor("TUC");
		pkg.getPackageHeader().setCreated(XMLUtil.getCurrentDateAndTime());

		// pkg.getScript().setType("text/javascript");
		pkg.getScript().setType("http://www.w3.org/1999/XPath");

		pkg = Liveness2XPDL.createRoles(roles, pkg, filename);

		writeToFile(filename, pkg);
		System.out.println("\nWritting XPDL model into file \"" + filename
				+ "\".");
	}

	public static Package createRoles(List<String> roles, Package pkg,
			String filename) throws Exception {
		Live2xpdl live2xpdl = new Live2xpdl();

		System.out.println("What are the roles" + roles);

		StringTokenizer line2 = new StringTokenizer(roles.get(0), "=");
		String poolname = line2.nextToken();

		System.out.println("What is the pool name:" + poolname);

		System.out.println("......creating WorkflowProcess [Id=" + poolname
				+ ",Name=" + poolname + "]");
		WorkflowProcess wp = (WorkflowProcess) pkg.getWorkflowProcesses()
				.generateNewElement();
		wp.setId(poolname);
		wp.setName(poolname);

		pkg.getWorkflowProcesses().add(wp);
		
		for (int i = 0; i < roles.size(); i++) {
			
			System.out.println("What is the pool name:" + poolname);
			// to pool mas
			System.out.println("......creating Pool [Id=" + poolname + ",Name="
					+ poolname + ",Process=" + poolname + "]");
			Pool p = (Pool) pkg.getPools().generateNewElement();
			p.setId(poolname + i);
			p.setName(poolname);
			NodeGraphicsInfo ngip = (NodeGraphicsInfo) p.getNodeGraphicsInfos()
					.generateNewElement();
			ngip.setWidth(350);
			ngip.setHeight(250);
			ngip.getCoordinates().setXCoordinate("0");
			ngip.getCoordinates().setYCoordinate("0");
			p.getNodeGraphicsInfos().add(ngip);

			pkg.getPools().add(p);

			p = live2xpdl.transform(roles.get(i), pkg, filename);
		}

		return pkg;
	}

	public static void writeToFile(String outputFile, Package pkg)
			throws Exception {
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
		os = new FileOutputStream(outputFile, false);

		// Here we get all document elements set
		XPDLRepositoryHandler repH = new XPDLRepositoryHandler();
		repH.setXPDLPrefixEnabled(true);
		repH.toXML(document, pkg);

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

	public Package getPackage() {
		return pkg;
	}

}
