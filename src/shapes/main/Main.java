package shapes.main;

import shapes.ui.MainForm;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::openMainWindow);
    }

    private static void openMainWindow() {
        JFrame frame = new JFrame("Shapes Manager");
        frame.setContentPane(new MainForm().getRootPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}