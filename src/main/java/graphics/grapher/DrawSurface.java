package graphics.grapher;

import java.awt.*;

public interface DrawSurface {
    void setColor(String color);
    void drawPoint(Point point);
    void drawLine(Point start, Point end);
    void drawImage(Image image, Point point);
    void setThickness(int thickness);
}
