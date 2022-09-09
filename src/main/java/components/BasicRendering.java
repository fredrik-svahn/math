package components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BasicRendering
        extends GameSystem {
    private JFrame frame;

    private Map<String, RenderInformation> images = new ConcurrentHashMap<>();
    private long ms;

    private static class RenderInformation {
        public double angle;
        public int x;
        public int y;
        public Image image;
        public int originX;
        public int originY;

        public RenderInformation(double angle,
                                 int x,
                                 int y,
                                 Image image) {
            this.angle = angle;
            this.x = x;
            this.y = y;
            this.image = image;
        }
    }

    @Override
    void init() {
        frame = new JFrame();
        frame.add(new Window());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        send(EventHandler::windowInit);
    }

    @Override
    void targetFrameRateSet(int fps) {
        this.ms = 1000 / fps;
    }

    @Override
    void imageAngleSet(String name,
                       double angle) {
        images.get(name).angle = angle;
    }

    @Override
    void imageCreated(String name,
                      Image image) {
        images.put(name, new RenderInformation(0, 0, 0, image));
    }

    @Override
    void imageDeleted(String name) {
        images.remove(name);
    }

    @Override
    void imageMoved(String name,
                    int x,
                    int y) {
        RenderInformation renderInformation = images.get(name);
        renderInformation.x = x;
        renderInformation.y = y;
    }

    @Override
    void imageOriginSet(String name,
                        int x,
                        int y) {
        images.get(name).originX = x;
        images.get(name).originY = y;
    }

    private class Window
            extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            g.clearRect(0, 0, frame.getWidth(), frame.getHeight());

            images.forEach((name, image) -> {
                Graphics2D g2d = (Graphics2D) g;
                AffineTransform transform = g2d.getTransform();
                g2d.translate(image.x + image.originX, image.y + image.originY);
                g2d.rotate(Math.toRadians(image.angle));
                g2d.translate(-image.originX, -image.originY);
                g.drawImage(image.image, 0, 0, null);
                g2d.transform(transform);
            });

            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            repaint();
        }
    }
}
