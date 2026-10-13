package shapes.ui;

import shapes.interfaces.IDrawer;
import shapes.model.Shape;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

public final class ShapeCanvas extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final double PIXELS_PER_UNIT = 25;
    private static final Color AXIS_COLOR = Color.LIGHT_GRAY;
    private static final Color SHAPE_COLOR = Color.DARK_GRAY;
    private static final Color SELECTED_COLOR = Color.RED;

    private transient List<Shape> shapes = List.of();
    private transient List<Shape> selectedShapes = List.of();

    public ShapeCanvas() {
        setPreferredSize(new Dimension(420, 420));
        setBackground(Color.WHITE);
    }

    public void showShapes(List<Shape> shapes, List<Shape> selectedShapes) {
        this.shapes = List.copyOf(shapes);
        this.selectedShapes = List.copyOf(selectedShapes);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int originX = getWidth() / 2;
        int originY = getHeight() / 2;
        drawAxes(g, originX, originY);

        IDrawer drawer = new GraphicsDrawer(g, originX, originY, PIXELS_PER_UNIT);
        for (Shape shape : shapes) {
            g.setColor(selectedShapes.contains(shape) ? SELECTED_COLOR : SHAPE_COLOR);
            shape.draw(drawer);
        }
    }

    private void drawAxes(Graphics g, int originX, int originY) {
        g.setColor(AXIS_COLOR);
        g.drawLine(0, originY, getWidth(), originY);
        g.drawLine(originX, 0, originX, getHeight());
    }
}