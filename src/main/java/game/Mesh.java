package game;

import math.LineSegment;
import math.Vector3D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Mesh {
    public List<Vector3D> points;

    public Mesh(Vector3D... points) {
        this.points = List.of(points);
    }

    public Mesh(List<Vector3D> points) {
        this.points = points;
    }

    public Mesh translated(Vector3D translation) {
        List<Vector3D> newPoints = points.stream().map(p -> p.add(translation)).collect(Collectors.toList());
        return new Mesh(newPoints);
    }

    public List<LineSegment> lines() {
        List<LineSegment> lines = new ArrayList<>();

        for (int current = 0; current < points.size(); current++) {
            int next = (current + 1) % points.size();
            lines.add(new LineSegment(points.get(current), points.get(next)));
        }

        return lines;
    }
}
