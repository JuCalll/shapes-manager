package shapes.ui;

import shapes.model.Shape;

import javax.swing.*;
import java.util.List;
import java.util.function.Supplier;

public class OperationsPanel {

    private JPanel rootPanel;
    private JTextField dxField;
    private JTextField dyField;
    private JButton moveButton;
    private JTextField factorField;
    private JButton scaleButton;
    private JButton compareButton;

    private final Supplier<List<Shape>> selectedShapes;
    private final Runnable onShapesChanged;

    public OperationsPanel(Supplier<List<Shape>> selectedShapes, Runnable onShapesChanged) {
        this.selectedShapes = selectedShapes;
        this.onShapesChanged = onShapesChanged;
        moveButton.addActionListener(e -> FormSupport.runSafely(rootPanel, this::moveSelected));
        scaleButton.addActionListener(e -> FormSupport.runSafely(rootPanel, this::scaleSelected));
        compareButton.addActionListener(e -> FormSupport.runSafely(rootPanel,this::compareSelected));
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void moveSelected(){
        Shape shape = requireSelection(1).get(0);
        double dx = FormSupport.readDouble(dxField, "dx");
        double dy= FormSupport.readDouble(dyField, "dy");
        shape.move(dx, dy);
        onShapesChanged.run();
    }
    private void scaleSelected(){
        Shape shape = requireSelection(1).get(0);
        double factor = FormSupport.readDouble(factorField, "Scale factor");
        shape.scale(factor);
        onShapesChanged.run();
    }
    private void compareSelected(){
        List<Shape> selected= requireSelection(2);
        Shape first = selected.get(0);
        Shape second = selected.get(1);
        int result = first.compareTo(second);
        String message = String.format("First %s: %.2f%nSecond %s: %.2f%n%nResult: %s",first.getType(),first.calculateDimension(),second.getType(),second.calculateDimension(),describeComparison(result));
        JOptionPane.showMessageDialog(rootPanel,message,"Comparison",JOptionPane.INFORMATION_MESSAGE);
    }

    private List<Shape> requireSelection(int expected){
        List<Shape> selected = selectedShapes.get();
        if(selected.size() != expected) {
            throw new IllegalStateException("Select exactly " + expected + (expected == 1 ? " shape" : " shapes") + " in the list. Selected: " + selected.size());
        }
        return selected;
    }

    private String describeComparison(int result){
        if (result < 0){
            return "the first is smaller";
        } else if (result > 0) {
            return "the first is larger";
        }else{
            return "both are equal";
        }
    }
}

