package asemedashboardview.views;

import org.eclipse.draw2d.ArrowLocator;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.DelegatingLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

public class FlowFigure extends Polyline implements Connection {

	private RotatableDecoration startArrow, endArrow;

	public FlowFigure() {
		setLayoutManager(new DelegatingLayout());
		addPoint(new Point(0, 0));
		addPoint(new Point(100, 100));
	}

	/**
	 * Returns the bounds which holds all the points in this polyline connection. Returns any previously existing bounds, else calculates by unioning all the children's dimensions.
	 * 
	 * @return the bounds
	 */
	public Rectangle getBounds() {
		if (bounds == null) {
			super.getBounds();
			for (int i = 0; i < getChildren().size(); i++) {
				IFigure child = (IFigure) getChildren().get(i);
				bounds.union(child.getBounds());
			}
		}
		return bounds;
	}

	/**
	 * @return the source decoration (may be null)
	 */
	protected RotatableDecoration getSourceDecoration() {
		return startArrow;
	}

	/**
	 * @return the target decoration (may be null)
	 */
	protected RotatableDecoration getTargetDecoration() {
		return endArrow;
	}

	public void setPoints(PointList points) {
		super.setPoints(points);
		layout(); // update arrows
	}

	public void layout() {
		Rectangle oldBounds = bounds;
		super.layout();
		bounds = null;
		if (!getBounds().contains(oldBounds)) {
			getParent().translateToParent(oldBounds);
			getUpdateManager().addDirtyRegion(getParent(), oldBounds);
		}
		repaint();
		fireFigureMoved();
	}

	public void setDashed() {
		super.setLineStyle(SWT.LINE_DASH);		
	}
	/**
	 * Sets the decoration to be used at the start of the {@link Connection}.
	 * 
	 * @param dec
	 *            the new source decoration
	 */
	public void setSourceDecoration(RotatableDecoration dec) {
		if (startArrow == dec) {
			return;
		}
		if (startArrow != null) {
			remove(startArrow);
		}
		startArrow = dec;
		if (startArrow != null) {
			add(startArrow, new ArrowLocator(this, ConnectionLocator.SOURCE));
		}
	}

	/**
	 * Sets the decoration to be used at the end of the {@link Connection}.
	 * 
	 * @param dec
	 *            the new target decoration
	 */
	public void setTargetDecoration(RotatableDecoration dec) {
		if (endArrow == dec) {
			return;
		}
		if (endArrow != null) {
			remove(endArrow);
		}
		endArrow = dec;
		if (endArrow != null) {
			add(endArrow, new ArrowLocator(this, ConnectionLocator.TARGET));
		}
	}

	// fake connection methods

	public ConnectionRouter getConnectionRouter() {
		return null;
	}

	public void setConnectionRouter(ConnectionRouter router) {
	}

	public Object getRoutingConstraint() {
		return null;
	}

	public void setRoutingConstraint(Object cons) {
	}

	public ConnectionAnchor getSourceAnchor() {
		return null;
	}

	public void setSourceAnchor(ConnectionAnchor anchor) {
	}

	public ConnectionAnchor getTargetAnchor() {
		return null;
	}

	public void setTargetAnchor(ConnectionAnchor anchor) {
	}


}
