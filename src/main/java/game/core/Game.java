package game.core;

import game.entities.Player;
import math.LineSegment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Game
        implements KeyListener {
    private static Collection<Entity> entities = new LinkedList<>();
    private static JFrame frame;

    private static Set<Integer> keys = new HashSet<>();

    public static void main(String[] args) throws
            InterruptedException {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 500);

        frame.add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                for (Entity entity : entities) {
                    java.util.List<LineSegment> lines = entity.mesh().lines();

                    for (LineSegment line : lines) {
                        g.setColor(Color.RED);
                        g.drawLine((int) line.start().x, (int) line.start().y, (int) line.end().x, (int) line.end().y);
                    }
                }
            }
        });

        frame.setVisible(true);
        frame.addKeyListener(new Game());

        spawn(new Player());


        while(true) {
            for (Entity entity : entities) {
                entity.update();
            }

            Thread.sleep(1);
        }
    }

    public static boolean key(int keyCode) {
        return keys.contains(keyCode);
    }

    public static void spawn(Entity entity) {
        entities.add(entity);
    }

    public static void kill(Entity entity) {
        entities.remove(entity);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys.remove(e.getKeyCode());
    }
}
