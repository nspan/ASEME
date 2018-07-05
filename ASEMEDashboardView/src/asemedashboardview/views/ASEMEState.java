package asemedashboardview.views;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import aseme.transformations.AsemeModelSaveHelper;
import asemedashboardview.Activator;

public final class ASEMEState {

	private static final String PREF_KEY = "aseme_dashboard"; //$NON-NLS-1$

	private static final String OPTION_PREFIX = "option_"; //$NON-NLS-1$

	private static final String SAG_KEY = "sag"; //$NON-NLS-1$
	
	private static final String SUC_KEY = "suc"; //$NON-NLS-1$

	private static final String AIP_KEY = "aip"; //$NON-NLS-1$

	private static final String SRM_KEY = "srm"; //$NON-NLS-1$
	
	private static final String XPDL_KEY = "xpdl"; //$NON-NLS-1$

	private static final String EAC_KEY = "eac"; //$NON-NLS-1$

	private static final String IAC_KEY = "iac"; //$NON-NLS-1$

	private static final String JADE_KEY = "jade"; //$NON-NLS-1$
	
	private static final String GG_KEY = "gg"; //$NON-NLS-1$
	
	private URI sag;
	
	private URI suc;
	
	private URI aip;
	
	private URI srm;
	
	private URI xpdl;

	private URI eac;

	private URI iac;

	private URI jade;
	
	private URI gg;

	private Set<String> enabledOptions;

	private IProject project;

	public IProject getProject() {
		return project;
	}

	public ASEMEState() {
		enabledOptions = new HashSet<String>();
	}

	public ASEMEState(IProject project) {
		this();
		this.project = project;
		Preferences prefs = getPreferences();
		if (prefs != null) {
			readOptions(prefs);
			sag = read(prefs, SAG_KEY);
			suc = read(prefs, SUC_KEY);
			aip = read(prefs, AIP_KEY);
			srm = read(prefs, SRM_KEY);
			eac = read(prefs, EAC_KEY);
			iac = read(prefs, IAC_KEY);
			jade = read(prefs, JADE_KEY);
			gg = read(prefs, GG_KEY);
		}
		AsemeModelSaveHelper.init();
	}
	
	public URI getSAG() {
		return sag;
	}

	public URI getSUC() {
		return suc;
	}
	
	public URI getAIP() {
		return aip;
	}
	
	public URI getSRM() {
		return srm;
	}
	
	public URI getXPDL() {
		return xpdl;
	}
	
	public URI getEAC() {
		return eac;
	}
	
	public URI getIAC() {
		return iac;
	}

	public URI getJADE() {
		return jade;
	}
	
	public URI getGG() {
		return gg;
	}

	public void setSAG(URI uri) {
		sag = uri;
		write(SAG_KEY, sag);
	}

	public void setSUC(URI uri) {
		suc = uri;
		write(SUC_KEY, suc);
	}
	
	public void setAIP(URI uri) {
		aip = uri;
		write(AIP_KEY, aip);
	}

	public void setSRM(URI uri) {
		srm = uri;
		write(SRM_KEY, srm);
	}
	
	public void setXPDL(URI uri) {
		xpdl = uri;
		write(XPDL_KEY, xpdl);
		}

	public void setEAC(URI uri) {
		eac = uri;
		write(EAC_KEY, eac);
	}
	
	public void setIAC(URI uri) {
		iac = uri;
		write(IAC_KEY, iac);
	}

	public void setJADE(URI uri) {
		jade = uri;
		write(JADE_KEY, jade);
	}
	
	public void setGG(URI uri) {
		gg = uri;
		write(GG_KEY, gg);
	}

//	public void setSAG(IFile file) {
//		sag = getURI(file);
//		write(SAG_KEY, sag);
//	}
//
//	public void setSUC(IFile file) {
//		suc = getURI(file);
//		write(SUC_KEY, suc);
//	}
//	
//	public void setAIP(IFile file) {
//		aip = getURI(file);
//		write(AIP_KEY, aip);
//	}
//
//	public void setSRM(IFile file) {
//		srm = getURI(file);
//		write(SRM_KEY, srm);
//	}
//
//	public void setEAC(IFile file) {
//		eac = getURI(file);
//		write(EAC_KEY, eac);
//	}
//
//	public void setIAC(IFile file) {
//		iac = getURI(file);
//		write(IAC_KEY, iac);
//	}
//
//	public void setJADE(IFile file) {
//		jade = getURI(file);
//		write(JADE_KEY, jade);
//	}
//	
//	public void setGG(IFile file) {
//		gg = getURI(file);
//		write(GG_KEY, gg);
//	}

//	private static URI getURI(IFile file) {
//		if (file == null) {
//			return null;
//		}
//		return URI.createPlatformResourceURI(file.getFullPath().toString(), true);
//	}

	public int getModelsCount() {
		return 7;
	}

	public int getSpecifiedModelsCount() {
		int count = 0;
		if (sag != null) {
			count++;
		}
		if (suc != null) {
			count++;
		}
		if (aip != null) {
			count++;
		}
		if (srm != null) {
			count++;
		}
		if (eac != null) {
			count++;
		}
		if (iac != null) {
			count++;
		}
		if (jade != null) {
			count++;
		}
		if (gg != null) {
			count++;
		}
		return count;
	}

	public boolean getOption(String name) {
		return enabledOptions.contains(name);
	}

	public void setOption(String name, boolean value) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (value) {
			enabledOptions.add(name);
		} else {
			enabledOptions.remove(name);
		}
		Preferences prefs = getPreferences();
		if (prefs == null) {
			return;
		}
		String key = OPTION_PREFIX + name;
		if (value) {
			prefs.put(key, "*"); //$NON-NLS-1$
		} else {
			prefs.remove(key);
		}
		savePreferences(prefs);
	}

	private void readOptions(Preferences prefs) {
		try {
			for (String key : prefs.keys()) {
				if (key.startsWith(OPTION_PREFIX)) {
					enabledOptions.add(key.substring(OPTION_PREFIX.length()));
				}
			}
		} catch (BackingStoreException e) {
			IStatus status = Activator.createError("Unable to read options", e);
			Activator.getDefault().getLog().log(status);
		}
	}

	private URI read(Preferences prefs, String key) {
		String s = prefs.get(key, null);
		if (s == null) {
			return null;
		}
		try {
			return URI.createURI(s);
		} catch (IllegalArgumentException e) {
			IStatus status = Activator.createError("Invalid URI", e);
			Activator.getDefault().getLog().log(status);
		}
		return null;
	}

	private void write(String key, URI uri) {
		if (project == null) {
			return;
		}
		String s = null;
		if (uri != null) {
			s = uri.toString();
		}
		Preferences prefs = getPreferences();
		prefs.put(key, s);
		savePreferences(prefs);
	}

	private void savePreferences(Preferences prefs) {
		try {
			prefs.flush();
		} catch (BackingStoreException e) {
			IStatus status = Activator.createError("Unable to update state", e);
			Activator.getDefault().getLog().log(status);
		}
	}

	private Preferences getPreferences() {
		if (project == null) {
			return null;
		}
		Preferences node = getExistingPreferences();
		if (node != null) {
			return node;
		}
		return new ProjectScope(project).getNode(Activator.getPluginID()).node(PREF_KEY);
	}

	private Preferences getExistingPreferences() {
		if (project == null) {
			return null;
		}
		Preferences node = Platform.getPreferencesService().getRootNode().node(ProjectScope.SCOPE);
		try {
			if (!node.nodeExists(project.getName())) {
				return null;
			}
			node = node.node(project.getName());
			if (!node.nodeExists(Activator.getPluginID())) {
				return null;
			}
			node = node.node(Activator.getPluginID());
			if (!node.nodeExists(PREF_KEY)) {
				return null;
			}
			return node.node(PREF_KEY);
		} catch (BackingStoreException e) {
			IStatus status = Activator.createError("Unable to read state", e);
			Activator.getDefault().getLog().log(status);
		}
		return null;
	}
}
