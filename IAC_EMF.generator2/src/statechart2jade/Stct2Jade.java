package statechart2jade;

import org.eclipse.xpand2.XpandExecutionContextImpl;
import org.eclipse.xpand2.XpandFacade;
import org.eclipse.xpand2.output.JavaBeautifier;
import org.eclipse.xpand2.output.Outlet;
import org.eclipse.xpand2.output.OutputImpl;
import org.eclipse.xtend.typesystem.emf.EmfMetaModel;

import statechart.Model;
import statechart.StatechartPackage;

public class Stct2Jade {
	
	//public Stct2Jade(){
		
	//}
	
	public static void genarateJade(String srcGenPath, Model statechart){
		
		
	    OutputImpl out = new OutputImpl();
	    Outlet outlet = new Outlet(srcGenPath);	
	    outlet.postprocessors.add(new JavaBeautifier());
	    outlet.setOverwrite(false);
	   // outlet.setAppend(false);    
	    out.addOutlet(outlet);
	    XpandExecutionContextImpl executionContext = new XpandExecutionContextImpl(out, null);

	    // Configure the metamodels
	    EmfMetaModel emfMetaModel = new EmfMetaModel();
	    emfMetaModel.setMetaModelPackage(StatechartPackage.class.getName());
	    executionContext.registerMetaModel(emfMetaModel);
	    XpandFacade xpandFacade = XpandFacade.create(executionContext);
	    Object[] params = null;
	    
	    xpandFacade.evaluate("templates::Agent::javaClass", statechart, params);
	    
				
	}

}
