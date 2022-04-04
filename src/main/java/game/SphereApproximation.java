package game;

import math.LineSegment;
import math.Vector3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SphereApproximation {
    private Collection<BoundingSphere> boundingSpheres = new ArrayList<>();

    public SphereApproximation(Vector3D... mesh) {
        List<LineSegment> segments = new ArrayList<>();

        Vector3D previous = mesh[0];

        for (int i = 1; i < mesh.length; i++) {
            Vector3D vector3D = mesh[i];
            LineSegment lineSegment = new LineSegment(previous, vector3D);
            segments.add(lineSegment);
            previous = vector3D;
        }

        segments.add(new LineSegment(previous, mesh[0]));

        for (LineSegment segment : segments) {
            double dt = .25d / mesh.length;
            for(double t = 0; t < 1; t+= dt) {
                double length = segment.length() * dt;
                Vector3D position = segment.interpolate(t);
                double x = position.x;
                double y = position.y;
                boundingSpheres.add(new BoundingSphere(x, y, length/2));
            }
        }
    }

    public boolean collides(SphereApproximation approximation) {
        return boundingSpheres.stream().anyMatch(sphere -> approximation.boundingSpheres.stream().anyMatch(sphere::intersects));
    }

    public void draw(Graphics g) {
        boundingSpheres.forEach(boundingSphere -> boundingSphere.draw(g));
    }
}
