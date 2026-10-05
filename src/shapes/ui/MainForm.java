package shapes.ui;

import shapes.model.Shape;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import java.awt.Component;
import java.util.List;
import java.util.stream.Collectors;

public class MainForm {

    private JPanel rootPanel;
    private JTabbedPane creationTabs;
    private JList<Shape> shapeList;
    private JTextArea infoArea;
    private JPanel operationsContainer;

    private final DefaultListModel<Shape> shapes = new DefaultListModel<>();

    public MainForm() {
        configureShapeList();
        registerCreationPanels();
        registerOperationsPanel();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void configureShapeList() {
        shapeList.setModel(shapes);
        shapeList.setCellRenderer(new ShapeRenderer());
        shapeList.addListSelectionListener(e -> refreshInfo());
    }

    private void registerCreationPanels() {
        // Each creation panel registers its tab here.
    }

    private void registerOperationsPanel() {
        // The operations panel is registered here.
    }

    private void addShape(Shape shape) {
        shapes.addElement(shape);
        shapeList.setSelectedIndex(shapes.size() - 1);
    }

    private List<Shape> getSelectedShapes() {
        return shapeList.getSelectedValuesList();
    }

    private void refreshInfo() {
        infoArea.setText(getSelectedShapes().stream()
                .map(Shape::displayInfo)
                .collect(Collectors.joining("\n\n")));
        shapeList.repaint();
    }

    private static class ShapeRenderer extends DefaultListCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Shape shape = (Shape) value;
            setText((index + 1) + ". " + shape.getType());
            return this;
        }
    }
}