package shapes.ui;

import shapes.model.Triangle;
import shapes.model.Point;
import shapes.model.Shape;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.function.Consumer;

public class TrianglePanel {

    private JPanel rootPanel;
    private JTextField aXField;
    private JTextField aYField;
    private JTextField bXField;
    private JTextField bYField;
    private JTextField cXField;
    private JTextField cYField;
    private JButton createButton;

    private final Consumer<Shape> onShapeCreated;

    public TrianglePanel(Consumer<Shape> onShapeCreated) {
        this.onShapeCreated = onShapeCreated;
        createButton.addActionListener(e -> FormSupport.runSafely(rootPanel, this::createTriangle));

    }

    public JPanel getRootPanel(){
        return rootPanel;
    }

    private void createTriangle(){
        Point a = FormSupport.readPoint(aXField, aYField, "Vertex A");
        Point b = FormSupport.readPoint(bXField, bYField, "Vertex B");
        Point c = FormSupport.readPoint(cXField, cYField, "Vertex C");

        onShapeCreated.accept(new Triangle(a, b, c));

    }


}
