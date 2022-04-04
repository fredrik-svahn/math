package game;


import math.LineSegment;
import math.Vector3D;
import math.polar.Angle;



import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class Entity {
    public boolean garbage = false;

    /**
     * Bounds and position
     */
    public double width;
    public double height;
    public Vector3D position = new Vector3D();

    public Map<String, Collider> boundingBoxes = new HashMap<>();


    /**
     * Physics
     */
    public double dx;
    public double dy;
    public Angle facingAngle = Angle.fromDegrees(0);
    public double forwardSpeed = 0;
    public double acceleration = 0;
    public double maxSpeed = 0;

    /**
     * Generic
     */
    public Set<Object> tags = new HashSet<>();
    public Object parent;

    /**
     * Collision
     */
    public Map<String, Collection<Entity>> collisions = new HashMap<>();
    public BiConsumer<String, Entity> collisionAction = (s, e) -> { };
    public boolean solid = true;

    /**
     * Time
     */
    public double storedDeltaTime;
    public double lifetime = 0;
    public double storedTicks;
    public boolean expires = false;


    /**
     * Render
     */
    public Integer layer = 0;
    public Color color;
    public String renderString = "";
    public boolean drawFacing = true;
    public Color tempColor;
    public double tempColorLifetime;
    public BufferedImage image;
    public Graphics2D graphics;

    /**
     * Input
     */
    public Consumer<MouseEvent> mousePressed = e -> {};
    public Consumer<MouseEvent> mouseReleased = e -> {};
    public Consumer<MouseEvent> mouseClicked = e -> {};
    public Consumer<MouseEvent> mouseEntered = e -> {};
    public Consumer<MouseEvent> mouseExited = e -> {};

    public Consumer<KeyEvent> keyPressed = e -> {};
    public Consumer<KeyEvent> keyReleased = e -> {};


    public boolean hasTag(Object tag) {
        return tags.contains(tag);
    }

    public void addTag(Object tag) {
        tags.add(tag);
    }

    public void removeTag(Object tag) {
        tags.remove(tag);
    }

    public void addTags(Object... tags) {
        this.tags.addAll(List.of(tags));
    }

    public void removeTags(Object... tags) {
        this.tags.removeAll(List.of(tags));
    }

    public Angle headingAngle() {
        double radians = Math.atan2(-dy, -dx);
        return Angle.fromRadians(radians + Math.PI);
    }

    public Collider crudeBox() {
        Collection<LineSegment> lines = new ArrayList<>();

        for (Collider value : boundingBoxes.values()) {
            lines.addAll(value.lines());
        }

        if(lines.isEmpty()) {
            return new BoundingBox(position.x, position.y, 0, 0);
        }

        double minX = lines.stream().map(l -> l.start().x).min(Double::compareTo).get();
        double minY = lines.stream().map(l -> l.start().y).min(Double::compareTo).get();
        double maxX = lines.stream().map(l -> l.end().x).min(Double::compareTo).get();
        double maxY = lines.stream().map(l -> l.end().y).min(Double::compareTo).get();

        return new BoundingBox(minX, minY, maxX - minX, maxY - minY);
    }

    public void resize(double width, double height) {
        this.width = width;
        this.height = height;
    }


}
