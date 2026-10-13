package shapes.ui;

import shapes.model.Circle;
import shapes.model.Point;
import shapes.model.Shape;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.function.Consumer;


public class CirclePanel {

    private JPanel rootPanel;
    private JTextField centerXField;
    private JTextField centerYField;
    private JTextField radiusField;
    private JButton createButton;

    private final Consumer<Shape> onShapeCreated;

    public CirclePanel(Consumer<Shape> onShapeCreated) {
        this.onShapeCreated = onShapeCreated;
        createButton.addActionListener(e -> FormSupport.runSafely(rootPanel, this::createCircle));
    }

    public JPanel getRootPanel(){
        return rootPanel;
    }

    private void createCircle(){
        Point center= FormSupport.readPoint(centerXField, centerYField, "Center");
        double radius = FormSupport.readDouble(radiusField, "Radius");
        onShapeCreated.accept(new Circle(center, radius));
    }

}
