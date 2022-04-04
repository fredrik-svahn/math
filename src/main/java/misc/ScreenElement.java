package misc;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class ScreenElement {
    public abstract void draw(Graphics g);
    public abstract double z();
    public double angle() { return 0; }
    public Point origin() { return new Point(0, 0); }
}
