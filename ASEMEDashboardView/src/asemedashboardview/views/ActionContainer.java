package asemedashboardview.views;

import org.eclipse.draw2d.IFigure;

public interface ActionContainer {

	public void addAction(IFigure actionFigure);

	public void addAction(IFigure actionFigure, boolean std);

	public void removeAction(IFigure actionFigure, boolean std);
}
