package asemedashboardview.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;

public class ModelFigure extends RectangleFigure implements ActionContainer {

	private IFigure labelsPlate;

	private IFigure actionsPlate;

	private IFigure stdActionsPlate;

	private List<SeparatorFigure> separators;

	public ModelFigure() {
		separators = new ArrayList<SeparatorFigure>();
		ToolbarLayout layout = new ToolbarLayout();
		layout.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);
		setLayoutManager(layout);

		labelsPlate = new Figure();
		ToolbarLayout labelsLayout = new ToolbarLayout();
		labelsPlate.setLayoutManager(labelsLayout);
		add(labelsPlate);

		SeparatorFigure s1 = new SeparatorFigure();
		separators.add(s1);
		add(s1);

		actionsPlate = new Figure();
		ToolbarLayout actionsLayout = new ToolbarLayout();
		actionsLayout.setStretchMinorAxis(false);
		actionsPlate.setLayoutManager(actionsLayout);
		add(actionsPlate);

		stdActionsPlate = new Figure();
		ToolbarLayout stdActionsLayout = new ToolbarLayout(true);
		stdActionsLayout.setSpacing(2);
		stdActionsPlate.setLayoutManager(stdActionsLayout);
		actionsPlate.add(stdActionsPlate);

		Label descriptionFigure = new Label();
		descriptionFigure.setFont(JFaceResources.getBannerFont());
		addLabel(descriptionFigure);
		Label nameFigure = new Label();
		
		ToolbarLayout nameLayout = new ToolbarLayout();
		nameLayout.setStretchMinorAxis(false);
		nameFigure.setLayoutManager(nameLayout);
		
		addLabel(nameFigure);
		setName(null); // init
		
		
		
	}

	public void setSpacing(int spacing) {
		setBorder(new MarginBorder(spacing, 0, spacing, 0));
		for (SeparatorFigure separator : separators) {
			separator.setPreferredSize(new Dimension(0, spacing + separator.getLineWidth()));
		}
		labelsPlate.setBorder(new MarginBorder(0, spacing, 0, spacing));
		actionsPlate.setBorder(new MarginBorder(0, spacing, 0, spacing));
	}

	public void addLabel(IFigure labelFigure) {
		labelsPlate.add(labelFigure);
	}

	public void removeLabel(IFigure labelFigure) {
		labelsPlate.remove(labelFigure);
	}

	public final void addAction(IFigure actionFigure) {
		addAction(actionFigure, true);
	}

	public void addAction(IFigure actionFigure, boolean std) {
		Label bullet = new Label();
		// bullet.setText("-");
		if (std) {
			if (!stdActionsPlate.getChildren().isEmpty()) {
				bullet.setText("/"); //$NON-NLS-1$
			}
			stdActionsPlate.add(bullet);
			stdActionsPlate.add(actionFigure);
		} else {
			IFigure plate = new Figure();
			ToolbarLayout layout = new ToolbarLayout(true);
			layout.setSpacing(3);
			plate.setLayoutManager(layout);
			plate.add(bullet);
			plate.add(actionFigure);
			actionsPlate.add(plate);
		}
	}

	public void removeAction(IFigure actionFigure, boolean std) {
		if (std) {
			int ix = stdActionsPlate.getChildren().indexOf(actionFigure);
			IFigure bullet = (IFigure) stdActionsPlate.getChildren().get(ix);
			stdActionsPlate.remove(actionFigure);
			stdActionsPlate.remove(bullet);
		} else {
			actionsPlate.remove(actionFigure.getParent());
		}
	}

	protected Label getLabel(int i) {
		return (Label) labelsPlate.getChildren().get(i);
	}

	public String getDescription() {
		return getLabel(0).getText();
	}

	public void setDescription(String description) {
		getLabel(0).setText(description);
	}

	public void setIcon(Image icon) {
		getLabel(0).setIcon(icon);
	}

	public void setName(String name) {
		if (name == null || name.trim().length() == 0) {
			name = Messages.ModelFigure_NoName;
		}
			getLabel(1).setText(name);
	}

	public void setFullName(String name) {
		if (name == null || name.trim().length() == 0) {
			setToolTip(null);
		} else {
			Label tooltip = new Label(name);
			setToolTip(tooltip);
		}
	}
}
