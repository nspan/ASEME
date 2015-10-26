package asemedashboardview.views;


import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import asemedashboardview.Activator;

public class ASEMEFigure extends RectangleFigure {

	private static final int LINE_WIDTH = 6;

	private static final int TEXT_GAP = LINE_WIDTH + 3;

	private static final int ARROW_LENGTH = 15;

	private static final Color DASHBOARD_BG = new Color(null, 241, 213, 204);

	private static final Color DASHBOARD_FG = new Color(null, 184, 46, 0);

	private static final Color MODEL_BG = ColorConstants.white;

	private ImageFigure logoFigure;

	private IFigure statusFigure;

	// models
	
	private ModelFigure sagFigure;
	
	private ModelFigure sucFigure;
	
	private ModelFigure aipFigure;

	private ModelFigure srmFigure;
	
	private ModelFigure xpdlFigure;

	private ModelFigure eacFigure;

	private ModelFigure iacFigure;

	private ModelFigure jadeFigure;
	
	private ModelFigure ggFigure;

	// flows
	
	private FlowFigure sag2sucFlow;
	
	private FlowFigure suc2aipFlow;

	private FlowFigure suc2srmFlow;

	private FlowFigure aip2eacFlow;

	private FlowFigure eac2iacFlow;

	private FlowFigure srm2iacFlow;
	
	private FlowFigure srm2xpdlFlow;

	private FlowFigure aip2srmFlow;

	private FlowFigure iac2jadeFlow;
	
	private FlowFigure iac2ggFlow;
	

	// flow actions
	
	private FlowActionFigure sag2sucFigure;
	
	private FlowActionFigure suc2aipFigure;

	private FlowActionFigure suc2srmFigure;

	private FlowActionFigure aip2eacFigure;
	
	private FlowActionFigure srm2iacFigure;
	
	private FlowActionFigure srm2xpdlFigure;

	private FlowActionFigure iac2jadeFigure;
	
	private FlowActionFigure iac2ggFigure;

	public ASEMEFigure() {
		add(logoFigure = new ImageFigure() {

			protected void paintFigure(Graphics graphics) {
				if (getImage() != null) {
					graphics.drawImage(getImage(), new Rectangle(getImage().getBounds()), getBounds());
				}
			}
		});
		Image logoImage = Activator.getDefault().getImageRegistry().get(Activator.GMF_LOGO_IMAGE);
		if (logoImage != null) {
			logoFigure.setImage(logoImage);
		}
		//Image logoImage = Activator.getDefault().getImageRegistry().get(Activator.GMF_LOGO_IMAGE);
		//add(logoFigure = new ImageFigure(logoImage));
		add(sagFigure = createModelFigure(Messages.ASEMEFigure_SAG, Activator.SAG_ICON));
		add(sucFigure = createModelFigure(Messages.ASEMEFigure_SUC, Activator.SUC_ICON));
		add(aipFigure = createModelFigure(Messages.ASEMEFigure_AIP, Activator.AIP_ICON));
		add(srmFigure = createModelFigure(Messages.ASEMEFigure_SRM, Activator.SRM_ICON));
		add(xpdlFigure = createModelFigure(Messages.ASEMEFigure_XPDL, Activator.XPDL_ICON));
		add(eacFigure = createModelFigure(Messages.ASEMEFigure_EAC, Activator.EAC_ICON));
		add(iacFigure = createModelFigure(Messages.ASEMEFigure_IAC, Activator.IAC_ICON));
		add(jadeFigure = createModelFigure(Messages.ASEMEFigure_JADE, Activator.JADE_ICON));
		add(ggFigure = createModelFigure(Messages.ASEMEFigure_GG, Activator.GG_ICON));
		add(sag2sucFlow = createFlowFigure(true, false));
		add(suc2aipFlow = createFlowFigure(true, true));
		add(suc2srmFlow = createFlowFigure(true, false));
		add(aip2srmFlow = createFlowFigure(true, true));
		add(aip2eacFlow = createFlowFigure(true, false));
		add(eac2iacFlow = createFlowFigure(true, true));
		add(srm2iacFlow = createFlowFigure(true, false));
		add(srm2xpdlFlow=  createFlowFigure(true, false)); ///
		add(iac2jadeFlow = createFlowFigure(true, false));
		add(iac2ggFlow = createFlowFigure(true, false));
		add(sag2sucFigure = createFlowActionFigure());
		add(suc2aipFigure = createFlowActionFigure());
		add(suc2srmFigure = createFlowActionFigure());
		add(aip2eacFigure = createFlowActionFigure());
		add(srm2iacFigure = createFlowActionFigure());
		add(srm2xpdlFigure = createFlowActionFigure());		///
		add(iac2jadeFigure = createFlowActionFigure());
		add(iac2ggFigure = createFlowActionFigure());
		add(statusFigure = new Figure());
		statusFigure.setFont(JFaceResources.getBannerFont());
		ToolbarLayout statusLayout = new ToolbarLayout();
		statusLayout.setStretchMinorAxis(false);
		statusFigure.setLayoutManager(statusLayout);
		statusFigure.add(new Label());
		//statusFigure.add(new Label());
		setLayoutManager(new DashboardLayout());
		setBorder(new MarginBorder(10));
		setBackgroundColor(DASHBOARD_BG);
		setForegroundColor(DASHBOARD_FG);
	}

	public ModelFigure getSAGFigure() {
		return sagFigure;
	}	

	public ModelFigure getSUCFigure() {
		return sucFigure;
	}

	public ModelFigure getAIPFigure() {
		return aipFigure;
	}
	
	public ModelFigure getSRMFigure() {
		return srmFigure;
	}
	
	public ModelFigure getXPDLFigure() {
		return xpdlFigure;
	}

	public ModelFigure getEACFigure() {
		return eacFigure;
	}

	public ModelFigure getIACFigure() {
		return iacFigure;
	}

	public ModelFigure getJADEFigure() {
		return jadeFigure;
	}
	
	public ModelFigure getGGFigure() {
		return ggFigure;
	}

	public FlowActionFigure getSAG2SUCFigure() {
		return sag2sucFigure;
	}
	
	public FlowActionFigure getSUC2AIPFigure() {
		return suc2aipFigure;
	}
	
	public FlowActionFigure getSUC2SRMFigure() {
		return suc2srmFigure;
	}

	public FlowActionFigure getAIP2EACFigure() {
		return aip2eacFigure;
	}

		
	public FlowActionFigure getSRM2IACFigure() {
			return srm2iacFigure;
	}
	
	public FlowActionFigure getSRM2XPDLFigure(){	///
		return srm2xpdlFigure;
	}

	public FlowActionFigure getIAC2JADEFigure() {
		return iac2jadeFigure;
	}
	
	public FlowActionFigure getIAC2GGFigure() {
		return iac2ggFigure;
	}

	public Label getStatusLine(int i) {
		return (Label) statusFigure.getChildren().get(i);
	}

	protected ModelFigure createModelFigure(String description, String iconKey) {
		ModelFigure modelFigure = new ModelFigure();
		modelFigure.setDescription(description);
		Image image = Activator.getDefault().getImageRegistry().get(iconKey);
		if (image != null) {
			modelFigure.setIcon(image);
		}
		modelFigure.setBackgroundColor(MODEL_BG);
		modelFigure.setForegroundColor(DASHBOARD_FG);
		modelFigure.setLineWidth(LINE_WIDTH);
		modelFigure.setSpacing(TEXT_GAP);
		return modelFigure;
	}

	protected FlowFigure createFlowFigure(boolean directed, boolean dashed) {
		FlowFigure flowFigure = new FlowFigure();
		if (directed) {
			PolygonDecoration decoration = new PolygonDecoration();
			PointList template = new PointList();
			template.addPoint(0, -LINE_WIDTH / 2);
			template.addPoint(0, LINE_WIDTH / 2);
			template.addPoint(-ARROW_LENGTH, LINE_WIDTH / 2 + 10);
			template.addPoint(-ARROW_LENGTH, -(LINE_WIDTH / 2 + 10));
			decoration.setTemplate(template);
			decoration.setScale(1, 1);
			flowFigure.setTargetDecoration(decoration);
		}
		flowFigure.setForegroundColor(DASHBOARD_FG);
		flowFigure.setLineWidth(LINE_WIDTH);
		if(dashed) {
		flowFigure.setDashed();
		}
		return flowFigure;
	}

	protected FlowActionFigure createFlowActionFigure() {
		FlowActionFigure flowActionFigure = new FlowActionFigure();
		ToolbarLayout flowActionLayout = new ToolbarLayout();
		flowActionLayout.setStretchMinorAxis(false);
		flowActionLayout.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);
		flowActionFigure.setLayoutManager(flowActionLayout);
		flowActionFigure.setBackgroundColor(MODEL_BG);
		flowActionFigure.setForegroundColor(DASHBOARD_FG);
		flowActionFigure.setLineWidth(LINE_WIDTH / 3);
		flowActionFigure.setBorder(new MarginBorder(TEXT_GAP / 2));
		return flowActionFigure;
	}

	protected void outlineShape(Graphics graphics) {
	}

	private class DashboardLayout extends AbstractLayout {

		private static final int MAX_BOX_WIDTH = 300;

		private static final int BOX_SPACING = 100;

		protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
			Dimension d = getLayoutData().getSize();
			Insets insets = container.getInsets();
			d = new Dimension(d.width + insets.getWidth(), d.height + insets.getHeight());
			return d.union(getBorderPreferredSize(container));
		}

		public void layout(IFigure container) {
			getLayoutData().apply(container.getClientArea().getLocation());
		}

		protected LayoutData getLayoutData() {
			Dimension sagSize = sagFigure.getPreferredSize();
			sagSize.width = Math.min(sagSize.width, MAX_BOX_WIDTH);
			Dimension sucSize = sucFigure.getPreferredSize();
			sucSize.width = Math.min(sucSize.width, MAX_BOX_WIDTH);
			Dimension aipSize = aipFigure.getPreferredSize();
			aipSize.width = Math.min(aipSize.width, MAX_BOX_WIDTH);
			Dimension srmSize = srmFigure.getPreferredSize();
			srmSize.width = Math.min(srmSize.width, MAX_BOX_WIDTH);
			Dimension eacSize = eacFigure.getPreferredSize();
			
			Dimension xpdlSize = xpdlFigure.getPreferredSize();
			xpdlSize.width = Math.min(xpdlSize.width, MAX_BOX_WIDTH);
			
			eacSize.width = Math.min(eacSize.width, MAX_BOX_WIDTH);;
			Dimension iacSize = iacFigure.getPreferredSize();
			iacSize.width = Math.min(iacSize.width, MAX_BOX_WIDTH);
			Dimension jadeSize = jadeFigure.getPreferredSize();
			jadeSize.width = Math.min(jadeSize.width, MAX_BOX_WIDTH);
			
			Dimension ggSize = ggFigure.getPreferredSize();
			ggSize.width = Math.min(ggSize.width, MAX_BOX_WIDTH);
			
			Dimension sag2sucSize = sag2sucFigure.getPreferredSize();
			Dimension suc2aipSize = suc2aipFigure.getPreferredSize();
			Dimension suc2srmSize = suc2srmFigure.getPreferredSize();
			Dimension aip2eacSize = aip2eacFigure.getPreferredSize();
			Dimension srm2iacSize = srm2iacFigure.getPreferredSize();
			Dimension srm2xpdlSize = srm2xpdlFigure.getPreferredSize();
			Dimension iac2jadeSize = iac2jadeFigure.getPreferredSize();
			Dimension iac2ggSize = iac2jadeFigure.getPreferredSize();
			LayoutData data = new LayoutData();

			// boxes
			
			Dimension logoSize = logoFigure.getPreferredSize();
			//if (logoSize.width > logoMaxWidth || logoSize.height > logoMaxHeight) {
				//double scale = Math.min((double) logoMaxWidth / logoSize.width, (double) logoMaxHeight / logoSize.height);
				//logoSize.width *= scale;
				//logoSize.height *= scale;
			//}
			data.logoBox = new Rectangle(0, 0, logoSize.width, logoSize.height);
			Dimension statusSize = statusFigure.getPreferredSize();
			int statusX = data.logoBox.getLeft().x + data.logoBox.width + BOX_SPACING;	//data.suc2aipBox.x + data.suc2aipBox.width + BOX_SPACING;
			int statusY = data.logoBox.y;		//data.sucBox.y;
			data.statusBox = new Rectangle(statusX, statusY, statusSize.width, statusSize.height);
			
			int sagX = data.logoBox.x;
			int sagY = data.logoBox.getBottom().y + BOX_SPACING/5;
			int sucX = sagX + sagSize.width + BOX_SPACING;
			int sucY = sagY;
			int srmX = sucX;
			int srmY = sucY + sucSize.height + BOX_SPACING ;
			
			int xpdlX = srmX -xpdlSize.width - BOX_SPACING;
			int xpdlY = srmY;
			
			int aipX = sucX + sucSize.width + BOX_SPACING;
			int aipY = sucY + sucSize.height + (BOX_SPACING - aipSize.height)/2 ;
			int eacX = aipX;
			int eacY = aipY + aipSize.height + BOX_SPACING;
			int iacX = srmX;
			int iacY = srmY + srmSize.height + BOX_SPACING;
			int jadeX = iacX - (jadeSize.width + BOX_SPACING);
			int jadeY = iacY;
			
			int ggX = jadeX;
			int ggY = jadeY + BOX_SPACING;
			
			data.sagBox = new Rectangle(sagX, sagY, sagSize.width, sagSize.height);
			data.sucBox = new Rectangle(sucX, sucY, sucSize.width, sucSize.height);
			data.aipBox = new Rectangle(aipX, aipY, aipSize.width, aipSize.height);
			data.srmBox = new Rectangle(srmX, srmY, srmSize.width, srmSize.height);
			
			data.xpdlBox = new Rectangle(xpdlX, xpdlY, xpdlSize.width, xpdlSize.height);
			
			data.eacBox = new Rectangle(eacX, eacY, eacSize.width, eacSize.height);
			data.iacBox = new Rectangle(iacX, iacY, iacSize.width, iacSize.height);
			data.jadeBox = new Rectangle(jadeX, jadeY, jadeSize.width, jadeSize.height);
			
			data.ggBox = new Rectangle(ggX, ggY, ggSize.width, ggSize.height);
			
//			int dmY = gdmSize.height + BOX_SPACING;
//			int dgmY = dmY + dmSize.height + BOX_SPACING;
//			data.dgmBox = new Rectangle(0, dgmY, dgmSize.width, dgmSize.height);
//			data.dm2dgmBox = new Rectangle((data.dgmBox.width - dm2dgmSize.width) / 2, dmY + (dmSize.height - dm2dgmSize.height) / 2, dm2dgmSize.width, dm2dgmSize.height);
//			data.dmBox = new Rectangle(data.dm2dgmBox.x + data.dm2dgmBox.width + BOX_SPACING, dmY, dmSize.width, dmSize.height);
//			int dm2tdmX = data.dmBox.x + (data.dmBox.width - dm2tdmSize.width) / 2;
//			int gap1 = dm2tdmX - (data.dgmBox.x + data.dgmBox.width);
//			if (gap1 < BOX_SPACING) {
//				int delta = BOX_SPACING - gap1;
//				data.dmBox.x += delta;
//				dm2tdmX += delta;
//			}
//			data.dm2gdmBox = new Rectangle(data.dmBox.x + (data.dmBox.width - dm2gdmSize.width) / 2, (gdmSize.height - dm2gdmSize.height) / 2, dm2gdmSize.width, dm2gdmSize.height);
//			data.gdmBox = new Rectangle(data.dm2gdmBox.x + data.dm2gdmBox.width + BOX_SPACING + ARROW_LENGTH, 0, gdmSize.width, gdmSize.height);
//			int tdmY = data.dmBox.y + data.dmBox.height + BOX_SPACING;
//			data.dm2tdmBox = new Rectangle(dm2tdmX, tdmY + (tdmSize.height - dm2tdmSize.height) / 2, dm2tdmSize.width, dm2tdmSize.height);
//			data.tdmBox = new Rectangle(data.dm2tdmBox.x + data.dm2tdmBox.width + BOX_SPACING + ARROW_LENGTH, tdmY, tdmSize.width, tdmSize.height);
//			data.dm2mmBox = new Rectangle(data.dmBox.x + data.dmBox.width + BOX_SPACING, data.dmBox.y + (data.dmBox.height - dm2mmSize.height) / 2, dm2mmSize.width, dm2mmSize.height);
//			data.mmBox = new Rectangle(data.dm2mmBox.x + data.dm2mmBox.width + BOX_SPACING + ARROW_LENGTH, data.dmBox.y, mmSize.width, mmSize.height);
//			int gmY = data.mmBox.y + data.mmBox.height + BOX_SPACING;
//			data.mm2gmBox = new Rectangle(data.mmBox.x + data.mmBox.width + BOX_SPACING, data.mmBox.y + (data.mmBox.height - mm2gmSize.height) / 2, mm2gmSize.width, mm2gmSize.height);
			data.sag2sucBox = new Rectangle(data.sagBox.x + data.sagBox.width + BOX_SPACING/5,  data.sagBox.y + (data.sagBox.height - sag2sucSize.height) / 2, sag2sucSize.width, sag2sucSize.height);
			data.suc2aipBox = new Rectangle(data.aipBox.x + (data.aipBox.width - suc2aipSize.width)/2, data.sucBox.y + (data.sucBox.height - suc2aipSize.height)/2, suc2aipSize.width, suc2aipSize.height);
			data.suc2srmBox = new Rectangle(data.sucBox.x + (data.sucBox.width - suc2srmSize.width)/2, data.sucBox.y + data.sucBox.height + (BOX_SPACING - suc2srmSize.height)/2 , suc2srmSize.width, suc2srmSize.height);
			data.aip2eacBox = new Rectangle(data.aipBox.x + (data.aipBox.width - aip2eacSize.width)/2, data.aipBox.y + data.aipBox.height + (BOX_SPACING - aip2eacSize.height)/ 2, aip2eacSize.width, aip2eacSize.height);
			data.srm2iacBox = new Rectangle(data.srmBox.x + (data.srmBox.width - srm2iacSize.width)/2, data.srmBox.y + data.srmBox.height + (BOX_SPACING - srm2iacSize.height)/2, srm2iacSize.width, srm2iacSize.height);
			
			//data.srm2xpdlBox = new Rectangle(data.srmBox.getLeft().x()- 50, data.srmBox.getLeft().y(), srm2iacSize.width, srm2iacSize.height);
			data.srm2xpdlBox = new Rectangle(data.xpdlBox.x + data.xpdlBox.width + BOX_SPACING/5,  data.xpdlBox.y + (data.xpdlBox.height - srm2xpdlSize.height) / 2, srm2xpdlSize.width, srm2xpdlSize.height);
			
			data.iac2jadeBox = new Rectangle(data.jadeBox.x +data.jadeBox.width + BOX_SPACING/5,  data.iacBox.y + (data.iacBox.height - iac2jadeSize.height) / 2, iac2jadeSize.width, iac2jadeSize.height);
			
			data.iac2ggBox = new Rectangle(data.iacBox.getBottom().x - data.iac2jadeBox.width/2, data.ggBox.getRight().y- data.iac2jadeBox.height/2, iac2ggSize.width, iac2ggSize.height);
					//data.gmBox = new Rectangle(data.mm2gmBox.x - (gmSize.width - mm2gmSize.width) / 2, gmY, gmSize.width, gmSize.height);
//			int gap2 = data.gmBox.x - (data.tdmBox.x + data.tdmBox.width);
//			if (gap2 < BOX_SPACING) {
//				int delta = BOX_SPACING - gap2;
//				data.mm2gmBox.x += delta;
//				data.gmBox.x += delta;
//			}
//
//			// points
			int sagPointsX = data.sagBox.x + data.sagBox.width;
			int sagPointsY = data.sagBox.y + data.sagBox.height / 2;
			int sucPointsX = data.sucBox.x;
			int sucPointsY = sagPointsY;
			data.sag2sucPoints = new PointList(2);
			data.sag2sucPoints.addPoint(sagPointsX, sagPointsY);
			data.sag2sucPoints.addPoint(sucPointsX, sucPointsY);
			sucPointsX = data.sucBox.x + data.sucBox.width;
			sucPointsY = data.sucBox.y + data.sucBox.height/2;
			int suc2boxPointsX = data.suc2aipBox.x;
			int suc2boxPointsY = data.suc2aipBox.y + data.suc2aipBox.height/ 2;
			int box2aipPointsX = data.suc2aipBox.x + data.suc2aipBox.width/2;
			int box2aipPointsY = data.suc2aipBox.y + data.suc2aipBox.height;
			int aipPointsX = data.aipBox.x + data.aipBox.width / 2;
			int aipPointsY = data.aipBox.y;
			data.suc2aipPoints = new PointList(4);
			data.suc2aipPoints.addPoint(sucPointsX, sucPointsY);
			data.suc2aipPoints.addPoint(suc2boxPointsX, suc2boxPointsY);
			data.suc2aipPoints.addPoint(box2aipPointsX, box2aipPointsY);
			data.suc2aipPoints.addPoint(aipPointsX, aipPointsY);
			sucPointsX = data.sucBox.x + data.sucBox.width/2;
			sucPointsY = data.sucBox.y + data.sucBox.height;
			int srmPointsX = sucPointsX;
			int srmPointsY = data.srmBox.y;
			data.suc2srmPoints = new PointList(2);
			data.suc2srmPoints.addPoint(sucPointsX, sucPointsY);
			data.suc2srmPoints.addPoint(srmPointsX, srmPointsY);
			aipPointsX = data.aipBox.x;
			aipPointsY = data.aipBox.y + data.aipBox.height/2;
			int aip2boxPointsX = data.suc2srmBox.x + data.suc2srmBox.width;
			int aip2boxPointsY = data.suc2srmBox.y + data.suc2srmBox.height/2;
			data.aip2srmPoints = new PointList(2);
			data.aip2srmPoints.addPoint(aipPointsX, aipPointsY);
			data.aip2srmPoints.addPoint(aip2boxPointsX, aip2boxPointsY);
			aipPointsX = data.aipBox.x + data.aipBox.width/2;
			aipPointsY = data.aipBox.y + data.aipBox.height;
			int eacPointsX = aipPointsX;
			int eacPointsY = data.eacBox.y;
			data.aip2eacPoints = new PointList(2);
			data.aip2eacPoints.addPoint(aipPointsX, aipPointsY);
			data.aip2eacPoints.addPoint(eacPointsX, eacPointsY);
			eacPointsX = data.eacBox.x;
			eacPointsY = data.eacBox.y + data.eacBox.height/2;
			int eac2boxPointsX = data.srm2iacBox.x + data.srm2iacBox.width;
			int eac2boxPointsY = data.srm2iacBox.y + data.srm2iacBox.height/2;
			data.eac2iacPoints = new PointList(2);
			data.eac2iacPoints.addPoint(eacPointsX, eacPointsY);
			data.eac2iacPoints.addPoint(eac2boxPointsX, eac2boxPointsY);			
			srmPointsX = data.srmBox.x + data.srmBox.width/2;
			srmPointsY = data.srmBox.y + data.srmBox.height;
			int iacPointsX = srmPointsX;
			int iacPointsY = data.iacBox.y;
			data.srm2iacPoints = new PointList(2);
			data.srm2iacPoints.addPoint(srmPointsX, srmPointsY);
			data.srm2iacPoints.addPoint(iacPointsX, iacPointsY);
			
			int xpdlPointsX = data.xpdlBox.x + data.xpdlBox.width;					
			int xpdlPpointsY = data.xpdlBox.y + data.xpdlBox.height/2;
			data.srm2xpdlPoints = new PointList(2);
			data.srm2xpdlPoints.addPoint(data.srmBox.getLeft());//, srmPointsY);
			data.srm2xpdlPoints.addPoint(data.xpdlBox.getRight());
			//data.srm2xpdlPoints.addPoint(xpdlPointsX, xpdlPpointsY);
			//data.srm2xpdlPoints.addPoint(data.srmBox.getLeft().x()-100, data.srmBox.getLeft().y());
			
			iacPointsX = data.iacBox.x;
			iacPointsY = data.iacBox.y + data.iacBox.height / 2;
			int jadePointsX = data.jadeBox.x + data.jadeBox.width;
			int jadePointsY = data.jadeBox.y + data.jadeBox.height/2;
			data.iac2jadePoints = new PointList(2);
			data.iac2jadePoints.addPoint(iacPointsX, iacPointsY);
			data.iac2jadePoints.addPoint(jadePointsX, jadePointsY);
			
			
			
			
			data.iac2ggPoints = new PointList(4);
			data.iac2ggPoints.addPoint(data.iacBox.getBottom());
			data.iac2ggPoints.addPoint(data.iac2ggBox.getTop());
			data.iac2ggPoints.addPoint(data.iac2ggBox.getLeft());
			data.iac2ggPoints.addPoint(data.ggBox.getRight());
			
//			int pointsY = data.dmBox.y + data.dmBox.height / 2;
//			data.dm2dgmPoints = new PointList(3);
//			data.dm2dgmPoints.addPoint(data.dmBox.x, pointsY);
//			data.dm2dgmPoints.addPoint(data.dm2dgmBox.x + data.dm2dgmBox.width / 2, pointsY);
//			data.dm2dgmPoints.addPoint(data.dm2dgmBox.x + data.dm2dgmBox.width / 2, data.dgmBox.y);
//			int pointsX = data.dmBox.x + data.dmBox.width / 2;
//			data.dm2gdmPoints = new PointList(3);
//			data.dm2gdmPoints.addPoint(pointsX, data.dmBox.y);
//			data.dm2gdmPoints.addPoint(pointsX, data.dm2gdmBox.y + data.dm2gdmBox.height / 2);
//			data.dm2gdmPoints.addPoint(data.gdmBox.x, data.dm2gdmBox.y + data.dm2gdmBox.height / 2);
//			data.dm2tdmPoints = new PointList(3);
//			data.dm2tdmPoints.addPoint(pointsX, data.dmBox.y + data.dmBox.height);
//			data.dm2tdmPoints.addPoint(pointsX, data.dm2tdmBox.y + data.dm2tdmBox.height / 2);
//			data.dm2tdmPoints.addPoint(data.tdmBox.x, data.dm2tdmBox.y + data.dm2tdmBox.height / 2);
//			data.dm2mmPoints = new PointList(2);
//			data.dm2mmPoints.addPoint(data.dmBox.x + data.dmBox.width, pointsY);
//			data.dm2mmPoints.addPoint(data.mmBox.x, pointsY);
//			int crossX = data.dm2mmBox.x + data.dm2mmBox.width / 2;
//			data.gdm2mmPoints = new PointList(2);
//			data.gdm2mmPoints.addPoint(crossX, data.gdmBox.y + data.gdmBox.height);
//			data.gdm2mmPoints.addPoint(crossX, pointsY);
//			data.tdm2mmPoints = new PointList(2);
//			data.tdm2mmPoints.addPoint(crossX, data.tdmBox.y);
//			data.tdm2mmPoints.addPoint(crossX, pointsY);
//			data.mm2gmPoints = new PointList(3);
//			data.mm2gmPoints.addPoint(data.mmBox.x + data.mmBox.width, pointsY);
//			data.mm2gmPoints.addPoint(data.mm2gmBox.x + data.mm2gmBox.width / 2, pointsY);
//			data.mm2gmPoints.addPoint(data.mm2gmBox.x + data.mm2gmBox.width / 2, data.gmBox.y);
//			data.sag2sucPoints = new PointList(2);
//			data.sag2sucPoints.addPoint(data.sagBox.x + data.sagBox.width, pointsY);
//			data.sag2sucPoints.addPoint(data.sag2sucBox.x + data.sag2sucBox.width, pointsY);
			
			// logo and status
			//int logoMaxWidth = data.sag2sucBox.x - BOX_SPACING;
			//int logoMaxHeight = data.sagBox.y - BOX_SPACING;
//			int logoMaxWidth = data.dm2gdmBox.x - BOX_SPACING;
//			int logoMaxHeight = data.dmBox.y - BOX_SPACING;
			

			return data;
		}

		private class LayoutData {

			public Rectangle logoBox;

			public Rectangle statusBox;
			
			public Rectangle sagBox;
			
			public Rectangle sucBox;
			
			public Rectangle aipBox;
			
			public Rectangle srmBox;
			
			public Rectangle xpdlBox;

			public Rectangle eacBox;

			public Rectangle iacBox;

			public Rectangle jadeBox;
			
			public Rectangle ggBox;

			public PointList sag2sucPoints;
			
			public PointList suc2aipPoints;
			
			public PointList suc2srmPoints;
			
			public PointList aip2srmPoints;

			public PointList aip2eacPoints;

			public PointList eac2iacPoints;

			public PointList srm2iacPoints;
			
			public PointList srm2xpdlPoints; ///

			public PointList iac2jadePoints;
			
			public PointList iac2ggPoints;

			public Rectangle sag2sucBox;
			
			public Rectangle suc2aipBox;

			public Rectangle suc2srmBox;

			public Rectangle aip2eacBox;

			public Rectangle srm2iacBox;
			
			public Rectangle srm2xpdlBox; ///

			public Rectangle iac2jadeBox;
			
			public Rectangle iac2ggBox;

			public void apply(Point offset) {
				logoFigure.setBounds(logoBox.getTranslated(offset));
				statusFigure.setBounds(statusBox.getTranslated(offset));
				sagFigure.setBounds(sagBox.getTranslated(offset));
				sucFigure.setBounds(sucBox.getTranslated(offset));
				aipFigure.setBounds(aipBox.getTranslated(offset));
				srmFigure.setBounds(srmBox.getTranslated(offset));
				xpdlFigure.setBounds(xpdlBox.getTranslated(offset));
 				eacFigure.setBounds(eacBox.getTranslated(offset));
				iacFigure.setBounds(iacBox.getTranslated(offset));
				jadeFigure.setBounds(jadeBox.getTranslated(offset));
				ggFigure.setBounds(ggBox.getTranslated(offset));
				sag2sucFlow.setPoints(getTranslated(sag2sucPoints, offset));
				suc2aipFlow.setPoints(getTranslated(suc2aipPoints, offset));
				suc2srmFlow.setPoints(getTranslated(suc2srmPoints, offset));
				aip2srmFlow.setPoints(getTranslated(aip2srmPoints, offset));
				aip2eacFlow.setPoints(getTranslated(aip2eacPoints, offset));
				eac2iacFlow.setPoints(getTranslated(eac2iacPoints, offset));
				srm2iacFlow.setPoints(getTranslated(srm2iacPoints, offset));
				srm2xpdlFlow.setPoints(getTranslated(srm2xpdlPoints, offset));	///
				iac2jadeFlow.setPoints(getTranslated(iac2jadePoints, offset));
				iac2ggFlow.setPoints(getTranslated(iac2ggPoints, offset));
				sag2sucFigure.setBounds(sag2sucBox.getTranslated(offset));
				suc2aipFigure.setBounds(suc2aipBox.getTranslated(offset));
				suc2srmFigure.setBounds(suc2srmBox.getTranslated(offset));
				aip2eacFigure.setBounds(aip2eacBox.getTranslated(offset));
				srm2iacFigure.setBounds(srm2iacBox.getTranslated(offset));
				srm2xpdlFigure.setBounds(srm2xpdlBox.getTranslated(offset)); ///
				iac2jadeFigure.setBounds(iac2jadeBox.getTranslated(offset));
				iac2ggFigure.setBounds(iac2ggBox.getTranslated(offset));

			}

			private PointList getTranslated(PointList source, Point offset) {
				PointList target = new PointList(source.size());
				target.addAll(source);
				target.translate(offset);
				return target;
			}

			public Dimension getSize() {
				//Rectangle bounds = statusBox.getCopy();
				Rectangle bounds = logoBox.getCopy();
				bounds.union(statusBox);
				bounds.union(sagBox);
				bounds.union(sucBox);
				bounds.union(aipBox);
				bounds.union(srmBox);
				bounds.union(xpdlBox);
				bounds.union(eacBox);
				bounds.union(iacBox);
				bounds.union(jadeBox);
				bounds.union(ggBox);
				return bounds.getSize();
				
			}
		}
	}
}
