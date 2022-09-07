package components;

import javax.swing.*;
import java.awt.*;
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

        frame.add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.clearRect(0, 0, frame.getWidth(), frame.getHeight());

                images.forEach((name, image) -> {
                    g.drawImage(image.image, image.x, image.y, null);
                });

                try {
                    Thread.sleep(ms);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                repaint();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
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
}
