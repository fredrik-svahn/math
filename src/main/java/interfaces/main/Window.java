package interfaces.main;

import javax.swing.*;
import java.awt.*;

class Window extends JPanel {
    private final JFrame frame;

    public Window(JFrame frame) {
        this.frame = frame;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
        draw(g);
    }

    private void draw(Graphics g) {
        int x = 0;
        int y = 0;
        int width = 50;
        int height = 50;
        Color red = Color.RED;
        g.setColor(red);
        g.fillRect(x, y, width, height);
    }
}
