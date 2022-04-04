package continuous;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

class RenderEngine extends GameSystem {
    private Map<Object, RenderInformation> renderInformation = new ConcurrentHashMap<>();
    private JPanel panel;

    class RenderInformation {
        private double z;
        private Consumer<Graphics> handler;

        public RenderInformation(double z, Consumer<Graphics> handler) {
            this.z = z;
            this.handler = handler;
        }
    }

    @Override
    void FrameCreated(JFrame frame) {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.clearRect(0, 0, frame.getWidth(), frame.getHeight());

                synchronized (renderInformation) {
                    PriorityQueue<RenderInformation> queue = new PriorityQueue<>(Comparator.comparing(o -> o.z));
                    queue.addAll(renderInformation.values());

                    for (RenderInformation renderInformation : queue) {
                        renderInformation.handler.accept(g);
                    }
                }
                repaint();
            }
        };

        frame.add(panel);
        panel.repaint();

        emit(EventHandler::WindowVisible);
    }

    @Override
    void Update(double milliSeconds) {
        if (panel != null) panel.repaint();
    }

    @Override
    void RenderHandlerRegistered(Object object, double z, Consumer<Graphics> handler) {
        renderInformation.put(object, new RenderInformation(z, handler));
    }

    @Override
    void Destroyed(Object object) {
        renderInformation.remove(object);
    }
}
