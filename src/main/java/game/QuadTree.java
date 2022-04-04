package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.BiConsumer;

public class QuadTree {
    private double x;
    private double y;
    private double width;
    private double height;
    private Collection<Entity> entities = new LinkedBlockingQueue<>();
    private Collection<QuadTree> children = new LinkedBlockingQueue<>();

    private static int limit = 10;
    private int level;
    private int levelLimit = 5;

    private double minWidth = 10;
    private double minHeight = 10;

    public QuadTree(double x, double y, double width, double height, int level) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.level = level;
    }

    public QuadTree(double x, double y, double width, double height) {
        this(x, y, width, height, 0);
    }

    public void add(Entity entity) {
        Collider quadrant = new BoundingBox(x, y, width, height);
        Collider crude = entity.crudeBox();

        if (quadrant.intersects(crude)) {
            entities.add(entity);
        }
    }

    public void subdivide() {
        if (width <= minWidth)
            return;
        if (height <= minHeight)
            return;
        if (entities.size() <= limit)
            return;

        children.add(new QuadTree(x, y, width / 2, height / 2, level + 1));
        children.add(new QuadTree(x + width / 2, y, width / 2, height / 2, level + 1));
        children.add(new QuadTree(x, y + height / 2, width / 2, height / 2, level + 1));
        children.add(new QuadTree(x + width / 2, y + height / 2, width / 2, height / 2, level + 1));

        for (Entity entity : entities) {
            for (QuadTree child : children) {
                child.add(entity);
            }
        }

        entities.clear();

        for (QuadTree child : children) {
            child.subdivide();
        }
    }

    public void makeCollisions() {
        Collection<Collection<Entity>> buckets = new ArrayList<>();
        buckets(buckets);

        for (Collection<Entity> bucket : buckets) {
            for (Entity entity : bucket) {
                for (Entity entity1 : bucket) {
                    if (entity == entity1)
                        continue;
                }
            }
        }
    }

    public void buckets(Collection<Collection<Entity>> collected) {
        if (children.size() == 0 && entities.size() > 1) {
            collected.add(entities);
        } else {
            for (QuadTree child : children) {
                child.buckets(collected);
            }
        }
    }

    public void pairs(BiConsumer<Entity, Entity> consumer) {
        Collection<Collection<Entity>> buckets = new LinkedList<>();
        buckets(buckets);

        for (Collection<Entity> bucket : buckets) {
            for (Entity entity : bucket) {
                for (Entity entity1 : bucket) {
                    if (entity != entity1)
                        consumer.accept(entity,
                                        entity1);
                }
            }
        }
    }

    public synchronized void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect((int) x, (int) y, (int) width, (int) height);

        if (entities.size() > 0) {
            g.drawString("" + entities.size(), (int) x, (int) y + 20);
        }

        for (Entity entity : entities) {
            if (entity.hasTag("player")) {
                g.setColor(new Color(255, 0, 0, 50));
                g.fillRect((int) x, (int) y, (int) width, (int) height);
            }
        }

        synchronized (children) {
            for (QuadTree child : children) {
                child.draw(g);
            }
        }
    }

    public int treeCount() {
        int count = 1;

        for (QuadTree child : children) {
            count += child.treeCount();
        }

        return count;
    }
}
