package asemedashboardview.views;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class HyperlinkFigure extends Label {

	private ASEMEAction action;

	private boolean underlined;

	public HyperlinkFigure(ASEMEAction action) {
		this.action = action;
		hookMouse();
	}

	protected void paintFigure(Graphics graphics) {
		Color color = graphics.getForegroundColor();
		if (action.isEnabled()) {
			graphics.setForegroundColor(ColorConstants.blue);
		} else {
			graphics.setForegroundColor(ColorConstants.gray);
		}
		super.paintFigure(graphics);
		if (underlined) {
			Rectangle bounds = getBounds();
			int y = bounds.y + bounds.height - 1;
			graphics.drawLine(bounds.x, y, bounds.x + bounds.width, y);
		}
		graphics.setForegroundColor(color);
	}

	protected void hookMouse() {
		addMouseListener(new MouseListener() {

			public void mouseDoubleClicked(MouseEvent me) {
			}

			public void mousePressed(MouseEvent me) {
				if (action.isEnabled()) {
					action.run();
				}
			}

			public void mouseReleased(MouseEvent me) {
			}

		});
		addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent me) {
			}

			public void mouseEntered(MouseEvent me) {
				setCursor(Cursors.HAND);
				underlined = true;
				repaint();
			}

			public void mouseExited(MouseEvent me) {
				underlined = false;
				setCursor(null);
				repaint();
			}

			public void mouseHover(MouseEvent me) {
			}

			public void mouseMoved(MouseEvent me) {
			}
		});
	}
}
