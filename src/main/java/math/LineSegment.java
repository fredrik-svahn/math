package math;

import math.exception.NoIntersectionFound;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class LineSegment extends Line {

    public LineSegment(Vector3D start, Vector3D end) {
        super(start, end);
    }
    public LineSegment(double x0, double y0, double x1, double y1) {
        super(x0, y0, x1, y1);
    }
    public LineSegment(double x0, double y0, double z0, double x1, double y1, double z1) {
        super(x0, y0, z0, x1, y1, z1);
    }
    public static Collection<Line> linesFrom(Polygon polygon) {
        Collection<Line> lines = new ArrayList<>();
        Vector3D start = new Vector3D(polygon.xpoints[0], polygon.ypoints[0], 0);

        for (int i = 1; i < polygon.npoints; i++) {
            Vector3D next = new Vector3D(polygon.xpoints[i], polygon.ypoints[i], 0);
            lines.add(new LineSegment(start, next));
            start = next;
        }

        return lines;
    }

    @Override
    protected boolean isFinite() {
        return true;
    }

    public LineSegment clipped(Line line) {
        try {
            Vector3D intersection = intersection(line);
            return new LineSegment(start, intersection);
        } catch (NoIntersectionFound noIntersectionFound) {
            return this;
        }
    }

    public LineSegment clipped(Surface surface) {
        try {
            Vector3D intersection = surface.intersection(this);
            return new LineSegment(start, intersection);
        } catch (NoIntersectionFound noIntersectionFound) {
            return this;
        }
    }

    public double length() {
        Vector3D difference = end.subtract(start);
        return Math.sqrt(difference.dotProduct(difference));
    }

    public Vector3D interpolate(double t) {
        return start().add(end().subtract(start).multiply(t));
    }

    public Vector3D start() {
        return start;
    }
    public Vector3D end() {
        return end;
    }
}
