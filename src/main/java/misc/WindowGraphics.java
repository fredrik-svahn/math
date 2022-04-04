package misc;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class WindowGraphics extends Cell {
    private Set<ScreenElement> elements = new HashSet<>();

    @Override
    void ScreenInit(JFrame screen) {

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                Paint(g);
                repaint();
            }
        };
        screen.add(panel);
        screen.setVisible(true);
    }


    @Override
    void ScreenElementRegistered(ScreenElement element) {
        elements.add(element);
    }

    @Override
    void ScreenElementUnregistered(ScreenElement element) {
        elements.remove(element);
    }

    private void Paint(Graphics g) {
        PriorityQueue<ScreenElement> queue = new PriorityQueue<>(Comparator.comparing(ScreenElement::z));
        queue.addAll(elements);

        for (ScreenElement screenElement : queue) {
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform transform = g2d.getTransform();

            g2d.translate(
                    screenElement.origin().getX(),
                    screenElement.origin().getY()
            );

            g2d.rotate(screenElement.angle());
            screenElement.draw(g);
            g2d.setTransform(transform);
        }
    }
}
