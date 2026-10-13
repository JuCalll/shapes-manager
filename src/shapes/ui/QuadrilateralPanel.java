package shapes.ui;
import shapes.model.Quadrilateral;
import shapes.model.Point;
import shapes.model.Shape;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.function.Consumer;


public class QuadrilateralPanel {
    private JPanel rootPanel;
    private JTextField aXField;
    private JTextField aYField;
    private JTextField bXField;
    private JTextField bYField;
    private JTextField cXField;
    private JTextField cYField;
    private JTextField dXField;
    private JTextField dYField;
    private JButton createButton;

    private final Consumer<Shape> onShapeCreated;

    public QuadrilateralPanel(Consumer<Shape> onShapeCreated) {
        this.onShapeCreated = onShapeCreated;
        createButton.addActionListener(e -> FormSupport.runSafely(rootPanel, this::createQuadrilateral));

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createQuadrilateral() {
        Point a = FormSupport.readPoint(aXField, aYField, "Vertex A");
        Point b = FormSupport.readPoint(bXField, bYField, "Vertex B");
        Point c = FormSupport.readPoint(cXField, cYField, "Vertex C");
        Point d = FormSupport.readPoint(dXField, dYField, "Vertex D");

        onShapeCreated.accept(new Quadrilateral(a, b, c, d));
    }
}
