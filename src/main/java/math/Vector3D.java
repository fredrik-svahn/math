package math;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Vector3D implements Drawable {
    public double x;
    public double y;
    public double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }



    public Vector3D(double x, double y) {
        this(x, y, 0);
    }

    public Vector3D() {
        this(0,0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Vector3D add(Vector3D point) {
        return new Vector3D(x+point.x, y+point.y, z+point.z);
    }

    public Vector3D multiply(double k) {
        return new Vector3D(x*k, y*k, z*z);
    }

    public Vector3D subtract(Vector3D v) {
        return add(v.multiply(-1));
    }



    public Vector3D crossProduct(Vector3D v) {
        return new Vector3D(
                y * v.z - z * v.y,
                z * v.x - x * v.z,
                x * v.y - y * v.x
        );
    }

    public Vector3D projection(Vector3D v) {
        double a = dotProduct(v);
        double b = v.dotProduct(v);
        double k = a/b;
        return v.multiply(k);
    }

    public Vector3D projection(Surface surface) {
        return subtract(projection(surface.normal()));
    }

    public double dotProduct(Vector3D v) {
        return (
                y * v.y +
                z * v.z +
                x * v.x
        );
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public double distance(Vector3D vector3D) {
        return subtract(vector3D).norm();
    }

    public double norm() {
        return Math.sqrt(dotProduct(this));
    }

    public double norm2() {
        return dotProduct(this);
    }

    @Override
    public Integer layer() {
        return 0;
    }

    @Override
    public void draw(JFrame frame, Surface screen, Graphics g) {
        g.drawLine(0, 0, (int)x, (int) y);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Vector3D vector3D = (Vector3D) object;
        return Double.compare(vector3D.x, x) == 0 && Double.compare(vector3D.y, y) == 0 && Double.compare(vector3D.z,
                                                                                                          z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
