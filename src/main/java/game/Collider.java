package game;

import math.LineSegment;

import java.awt.*;
import java.util.Collection;

public interface Collider {
    Collection<LineSegment> lines();

    boolean intersects(Collider box);
    void drawOutline(Graphics g);
}
