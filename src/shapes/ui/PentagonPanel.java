package shapes.ui;

import shapes.model.Pentagon;
import shapes.model.Point;
import shapes.model.Shape;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.function.Consumer;

public class PentagonPanel {

    private JPanel rootPanel;
    private JTextField centerXField;
    private JTextField centerYField;
    private JTextField sideField;
    private JButton createButton;

    private final Consumer<Shape> onShapeCreated;

    public PentagonPanel(Consumer<Shape> onShapeCreated) {
        this.onShapeCreated = onShapeCreated;
        createButton.addActionListener(e->FormSupport.runSafely(rootPanel,this::createPentagon));
    }
    public JPanel getRootPanel() {
        return rootPanel;
    }
    private void createPentagon(){
        Point center = FormSupport.readPoint(centerXField,centerYField,"Center");
        double side = FormSupport.readDouble(sideField,"Side");
        onShapeCreated.accept(new Pentagon(center,side));
    }
}
