package asemedashboardview.views;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Rectangle;

public class SeparatorFigure extends Shape {

	protected void outlineShape(Graphics graphics) {
		Rectangle r = getBounds();
		int x = r.x + lineWidth / 2;
		int y = r.y + lineWidth / 2;
		int w = r.width - Math.max(1, lineWidth);
		int h = r.height - Math.max(1, lineWidth);
		graphics.drawLine(x, y + h / 2, x + w, y + h / 2);
	}

	protected void fillShape(Graphics graphics) {
	}
}
