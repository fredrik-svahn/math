package math;

import math.exception.NoIntersectionFound;
import math.matrix.ArrayListMatrix;
import math.matrix.Matrix;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Line implements Drawable {
    protected Vector3D start;
    protected Vector3D end;

    public Line(Vector3D start, Vector3D end) {
        this.start = start;
        this.end = end;
    }

    public Line(double x0, double y0, double x1, double y1) {
        this(new Vector3D(x0, y0, 0), new Vector3D(x1, y1, 0));
    }

    public Line(double x0, double y0, double z0, double x1, double y1, double z1) {
        this(new Vector3D(x0, y0, z0), new Vector3D(x1, y1, z1));
    }

    public Vector3D intersection(Line line) throws NoIntersectionFound {
        // p + t*r = q + u*s
        Double px = start.getX();
        Double py = start.getY();
        Double pz = start.getZ();
        Double rx = end.getX() - start.getX();
        Double ry = end.getY() - start.getY();
        Double rz = end.getZ() - start.getZ();

        Double qx = line.start.getX();
        Double qy = line.start.getY();
        Double qz = line.start.getZ();
        Double sx = line.end.getX() - line.start.getX();
        Double sy = line.end.getY() - line.start.getY();
        Double sz = line.end.getZ() - line.start.getZ();

        // t*r - u*s = q - p
        Matrix equation = new ArrayListMatrix();
        equation.set(0, 0, rx);
        equation.set(0, 1, ry);
        equation.set(1, 0, -sx);
        equation.set(1, 1, -sy);

        Matrix answer = new ArrayListMatrix();
        answer.set(0, 0, qx - px);
        answer.set(0, 1, qy - py);

        try {
            equation.reduce(answer);

            Double t = answer.get(0, 0);
            Double u = answer.get(0, 1);

            if (t * rz - u * sz != qz - pz) {
                throw new NoIntersectionFound();
            }

            if (isFinite() && (t < 0 || t > 1)) {
                throw new NoIntersectionFound();
            }

            if (line.isFinite() && (u < 0 || u > 1)) {
                throw new NoIntersectionFound();
            }

            double dx = end.getX() - start.getX();
            double dy = end.getY() - start.getY();
            double dz = end.getZ() - start.getZ();

            return new Vector3D(px + dx * t, py + dy * t, pz + dz * t);
        } catch (Throwable e) {
            throw new NoIntersectionFound();
        }
    }

    protected boolean isFinite() {
        return false;
    }

    public Line copy() {
        return new Line(start, end);
    }

    @Override
    public String toString() {
        return "math.Line{" + "start=" + start + ", end=" + end + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Line line = (Line) o;
        return Objects.equals(start, line.start) && Objects.equals(end, line.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public Integer layer() {
        return 0;
    }

    @Override
    public void draw(JFrame frame, Surface screen, Graphics g) {
        g.drawLine((int) start.x, (int) start.y, (int) end.x, (int) end.y);
    }
}
