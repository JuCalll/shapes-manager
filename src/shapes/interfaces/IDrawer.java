package shapes.interfaces;

public interface IDrawer {

    void drawCircle(double centerX, double centerY, double radius);

    void drawPolygon(double[] xPoints, double[] yPoints);
}