package interfaces.main;


import javax.swing.*;

public class Application {

    private JFrame frame;

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 500);
        frame.add(new Window(frame));
        frame.setVisible(true);
    }
}
