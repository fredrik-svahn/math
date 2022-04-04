package game;

import math.LineSegment;
import math.Vector3D;
import math.exception.NoIntersectionFound;
import math.matrix.Transformation;
import math.polar.Angle;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BoundingBox
        implements Collider {
    private Vector3D origin;
    private Vector3D dimensions;
    private Vector3D offset;
    private Angle angle;

    public BoundingBox(Vector3D origin, Vector3D offset, Vector3D dimensions, Angle angle) {
        this.origin = origin;
        this.offset = offset;
        this.dimensions = dimensions;
        this.angle = angle;
    }

    public BoundingBox(double x, double y, double w, double h) {
        origin = new Vector3D(0 ,0);
        offset = new Vector3D(x, y);
        dimensions = new Vector3D(w, h);
        angle = Angle.fromDegrees(0);
    }


    public Vector3D origin() {
        return origin;
    }

    public Vector3D dimensions() {
        return dimensions;
    }

    @Override
    public Collection<LineSegment> lines() {
        double width = dimensions().x;
        double height = dimensions().y;
        double x = origin.x + offset.x;
        double y = origin.y + offset.y;

        Vector3D v1 = new Vector3D(-width / 2, -height / 2);
        Vector3D v2 = new Vector3D(width / 2, -height / 2);
        Vector3D v3 = new Vector3D(-width / 2, height / 2);
        Vector3D v4 = new Vector3D(width / 2, height / 2);

        double tx = x + width / 2;
        double ty = y + width / 2;

        Transformation t = Transformation.identity();
        t = t.apply(Transformation.rotation(angle.radians()));
        t = t.apply(Transformation.translation(tx, ty, 0));

        v1 = t.transform(v1);
        v2 = t.transform(v2);
        v3 = t.transform(v3);
        v4 = t.transform(v4);

        LineSegment l1 = new LineSegment(v1, v2);
        LineSegment l2 = new LineSegment(v2, v4);
        LineSegment l3 = new LineSegment(v4, v3);
        LineSegment l4 = new LineSegment(v3, v1);

        return List.of(l1, l2, l3, l4);
    }

    @Override
    public boolean intersects(Collider other) {
        Optional<LineSegment> segment = lines().stream().filter(line -> {
            Optional<LineSegment> segment1 = other.lines().stream().filter(line1 -> {
                try {
                    line.intersection(line1);
                    return true;
                } catch (NoIntersectionFound noIntersectionFound) {
                    return false;
                }
            }).findFirst();

            return segment1.isPresent();
        }).findFirst();

        return segment.isPresent();
    }

    @Override
    public void drawOutline(Graphics g) {
        g.setColor(Color.RED);
        for (LineSegment line : lines()) {
            line.draw(null, null, g);
        }
    }

}
