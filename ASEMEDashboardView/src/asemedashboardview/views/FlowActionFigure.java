package asemedashboardview.views;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;

public class FlowActionFigure extends RectangleFigure implements ActionContainer {

	public FlowActionFigure() {
		setLayoutManager(new ToolbarLayout());
	}

	public void addAction(IFigure actionFigure) {
		add(actionFigure);
	}

	public void addAction(IFigure actionFigure, boolean std) {
		add(actionFigure);
	}

	public void removeAction(IFigure actionFigure, boolean std) {
		remove(actionFigure);
	}
}
