package game;

import math.Vector3D;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BoundingSphere {
    private Vector3D origin;
    private Vector3D offset;
    private double radius;

    public BoundingSphere(Vector3D origin,
                          Vector3D offset,
                          double radius) {
        this.origin = origin;
        this.offset = offset;
        this.radius = radius;
    }

    public BoundingSphere(double x, double y, double r) {
        offset = new Vector3D(x, y);
        origin = new Vector3D(0 ,0);
        radius = r;
    }

    public boolean intersects(BoundingSphere sphere) {
        double x1 = origin.x + offset.x;
        double y1 = origin.y + offset.y;
        double r1 = radius;

        double x2 = sphere.origin.x + sphere.offset.x;
        double y2 = sphere.origin.y + sphere.offset.y;
        double r2 = sphere.radius;

        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) < (r1 + r2) * (r1 + r2);
    }

    public boolean coarseIntersect(Rectangle2D.Double rectangle) {
        double x = origin.x + offset.x;
        double y = origin.y + offset.y;
        double r = radius;

        Rectangle2D.Double bounds = new Rectangle2D.Double(x - r, y - r, r, r);
        return rectangle.intersects(bounds);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawOval((int)offset.add(origin).x, (int)offset.add(origin).y, (int)radius*2, (int)radius*2);
    }
}
