package shapes.ui;

import shapes.model.Point;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Component;

final class FormSupport {

    private FormSupport() {
    }

    static double readDouble(JTextField field, String label) {
        String text = field.getText().trim().replace(',', '.');
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    label + " must be a number. Received: \"" + field.getText() + "\"");
        }
    }

    static Point readPoint(JTextField xField, JTextField yField, String label) {
        double x = readDouble(xField, label + " X");
        double y = readDouble(yField, label + " Y");
        return new Point(x, y);
    }

    static void runSafely(Component parent, Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            JOptionPane.showMessageDialog(parent, e.getMessage(),
                    "Invalid operation", JOptionPane.ERROR_MESSAGE);
        }
    }
}