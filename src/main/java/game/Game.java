package game;

import math.Vector3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Game {
    public static Collection<Entity> world = new ConcurrentLinkedQueue<>();
    public static Collection<Entity> spawnQueue = new ArrayList<>();
    public static Set<Integer> releasedKeys = new HashSet<>();
    public static double renderLastTime;
    public static double renderDeltaTime;

    public static double updateLastTime;
    public static double updateDeltaTime;

    public static boolean drawTree;
    public static boolean mouseMove;

    public static QuadTree tree = new QuadTree(0, 0, 0, 0);
    private static JFrame frame;

    public static int mouseX;
    public static int mouseY;
    public static int ms = 17;

    public static void main(String[] args) {
        initScreen();
        initWorld();
        initSystems();

        renderLastTime = System.currentTimeMillis();
        updateLastTime = System.currentTimeMillis();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new UpdateWorld(), 0, ms);
    }

    private static void initScreen() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        JPanel panel = new GameRendering(frame);
        frame.add(panel);
        frame.addKeyListener(new Keyboard());
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                mouseX = mouseEvent.getX();
                mouseY = mouseEvent.getY();
            }
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                for (Entity entity : world) {
                    entity.mouseClicked.accept(mouseEvent);
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                for (Entity entity : world) {
                    entity.mousePressed.accept(mouseEvent);
                }
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                for (Entity entity : world) {
                    entity.mouseReleased.accept(mouseEvent);
                }
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                for (Entity entity : world) {
                    entity.mouseEntered.accept(mouseEvent);
                }
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                for (Entity entity : world) {
                    entity.mouseExited.accept(mouseEvent);
                }
            }
        });
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                for (Entity entity : world) {
                    entity.keyPressed.accept(keyEvent);
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                for (Entity entity : world) {
                    entity.keyPressed.accept(keyEvent);
                }
            }
        });
        frame.setVisible(true);
    }

    private static void initSystems() {
        Entity fpsCounter = new Entity();
        fpsCounter.position = new Vector3D(0, 0);
        fpsCounter.color = Color.GREEN;
        fpsCounter.layer = 9999;

        fpsCounter.addTags("render_fps", "not_solid");

        fpsCounter.resize(50, 50);
        spawn(fpsCounter);

        Entity quadTreeDebugger = new Entity();
        quadTreeDebugger.keyPressed = keyEvent -> {
            if (keyEvent.getKeyCode() == KeyEvent.VK_F5)
                drawTree = true;
            if (keyEvent.getKeyCode() == KeyEvent.VK_F6)
                drawTree = false;
        };
        spawn(quadTreeDebugger);

        Entity mouseMoveDebugger = new Entity();
        mouseMoveDebugger.keyPressed = keyEvent -> {
            if (keyEvent.getKeyCode() == KeyEvent.VK_F7)
                mouseMove = true;
            if (keyEvent.getKeyCode() == KeyEvent.VK_F8)
                mouseMove = false;
        };
        spawn(mouseMoveDebugger);
    }

    private static void initWorld() {
        Entity player = EntityFactory.Player(100, 100);
        Collider v = new BoundingBox(0,
                                     0,
                                     50,
                                     50);

        player.collisionAction = (s, e) -> {
            System.out.println(s);
        };

        spawn(player);
        spawn(EntityFactory.Enemy(300, 300));
    }

    public static void spawn(Entity object) {
        spawnQueue.add(object);
    }

    /**
     * Update an entity
     *
     * @param entity
     */
    public static void update(Entity entity) {
        Movement.movement(entity);
        EnemyAI.enemyAI(entity);
        Expiration.expiry(entity);
        PlayerKeyboardMovement.playerKeyboardMovement(entity);
        TemporaryColor.tempColor(entity);
        MouseMoveDebug.mouseMoveDebug(entity);
    }
}
