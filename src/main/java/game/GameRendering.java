package game;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.PriorityQueue;

class GameRendering extends JPanel {
    private final JFrame frame;

    public GameRendering(JFrame frame) {
        this.frame = frame;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Game.renderDeltaTime = System.currentTimeMillis() - Game.renderLastTime;
        g.clearRect(0, 0, frame.getWidth(), frame.getHeight());

        synchronized (Game.world) {
            PriorityQueue<Entity> queue = new PriorityQueue<>(1, Comparator.comparing(e -> e.layer));
            queue.addAll(Game.world);

            for (Entity entity : queue) {
                render(g, entity);
            }
        }

        Game.renderLastTime = System.currentTimeMillis();
        repaint();
    }

    private void render(Graphics g, Entity entity) {

    }
}
