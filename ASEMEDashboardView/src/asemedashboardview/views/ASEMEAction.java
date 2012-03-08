package asemedashboardview.views;

public interface ASEMEAction {

	public void init(ASEMEFacade context);

	public boolean isEnabled();

	public void run();
}
