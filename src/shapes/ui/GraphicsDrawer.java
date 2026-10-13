package shapes.ui;

import shapes.interfaces.IDrawer;
import java.awt.Graphics;

public class GraphicsDrawer implements IDrawer{

    private final Graphics graphics;
    private final int originX;
    private final int originY;
    private final double pixelsPerUnit;

    public GraphicsDrawer(Graphics graphics, int originX, int originY, double pixelsPerUnit) {
        this.graphics = graphics;
        this.originX = originX;
        this.originY = originY;
        this.pixelsPerUnit = pixelsPerUnit;
    }

    @Override
    public void drawCircle(double centerX, double centerY, double radius) {
        int radiusInPixels = toPixels(radius);
        graphics.drawOval(toScreenX(centerX) - radiusInPixels, toScreenY(centerY) - radiusInPixels, 2*radiusInPixels,2*radiusInPixels);
    }

    @Override
    public void drawPolygon(double[] xPoints, double[] yPoints) {
        int count = xPoints.length;
        int[] screenX = new int[count];
        int[] screenY = new int[count];
        for(int i=0; i<count;i++){
            screenX[i]= toScreenX(xPoints[i]);
            screenY[i]= toScreenY(yPoints[i]);
        }
        graphics.drawPolygon(screenX, screenY, count);
    }

    private int toPixels(double units) {
        return (int) Math.round(units * pixelsPerUnit);
    }

    private  int toScreenX(double x) {
        return originX + toPixels(x);
    }

    private int toScreenY(double y) {
        return originY - toPixels(y);
    }
}
