package statechart2naoth;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.xpand2.XpandExecutionContextImpl;
import org.eclipse.xpand2.XpandFacade;
//import org.eclipse.xpand2.output.JavaBeautifier;
import org.eclipse.xpand2.output.Outlet;
import org.eclipse.xpand2.output.OutputImpl;
import org.eclipse.xtend.typesystem.emf.EmfMetaModel;

import statechart.Model;
import statechart.StatechartPackage;

public class Stct2Naoth {
	
	
public static void genarateNaoth(String srcGenPath, Model statechart){
		
		
	    OutputImpl out = new OutputImpl();
	    Outlet outlet = new Outlet(srcGenPath);	
	   // outlet.postprocessors.add(new JavaBeautifier());
	    outlet.setOverwrite(false);
	   // outlet.setAppend(false);
	    out.addOutlet(outlet);
	    
	    Outlet transitions_outlet = new Outlet(srcGenPath+"/transitions/");
	    transitions_outlet.setOverwrite(false);
	    
	    Outlet activities_outlet = new Outlet(srcGenPath+"/activities/");
	    activities_outlet.setOverwrite(false);
	    
	    Map<String, Outlet> outlets = new HashMap<String,Outlet>();
	    outlets.put("default", outlet);
	    outlets.put("activities_outlet", activities_outlet);
	    outlets.put("transitions_outlet", transitions_outlet);
	    
	    //OutputImpl.resolveOutlet(outlets, srcGenPath, "transitions_outlet");
	    OutputImpl.resolveOutlet(outlets, srcGenPath+"/activities/", "activities_outlet");
	    OutputImpl.resolveOutlet(outlets, srcGenPath+"/transitions/", "transitions_outlet");
	    
	    //out.addOutlet(transitions_outlet);
	    XpandExecutionContextImpl executionContext = new XpandExecutionContextImpl(out, null);
	    
	    // Configure the metamodels
	    EmfMetaModel emfMetaModel = new EmfMetaModel();
	    emfMetaModel.setMetaModelPackage(StatechartPackage.class.getName());
	    executionContext.registerMetaModel(emfMetaModel);
	    XpandFacade xpandFacade = XpandFacade.create(executionContext);
	    Object[] params = null;
	    
	    xpandFacade.evaluate("mainTemplate::model", statechart, params);
	    
				
	}

}
