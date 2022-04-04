package graphics.grapher;

import graphics.scaler.RectangleScale;

import java.awt.*;

public class Grapher {
    private GraphSettings settings;
    private FunctionValues values;

    public Grapher(GraphSettings settings,FunctionValues values) {
        this.settings = settings;
        this.values = values;
    }

    public void draw(DrawSurface surface) {
        double i = values.xMin();

        double width = (values.xMax() - values.xMin());
        double height = (values.yMax() - values.yMin());

        double lastX = i;
        double lastY = values.y(i);

        while(i < values.xMax()) {
            double x = i;
            double y = values.y(x);

            RectangleScale.Rectangle screen = new RectangleScale.Rectangle() {
                @Override
                public double x() {
                    return 0;
                }

                @Override
                public double y() {
                    return 0;
                }

                @Override
                public double w() {
                    return settings.windowWidth();
                }

                @Override
                public double h() {
                    return settings.windowHeight();
                }
            };

            RectangleScale.Point point = new RectangleScale.Point() {
                @Override
                public double x() {
                    return x;
                }

                @Override
                public double y() {
                    return y;
                }
            };

            RectangleScale.Rectangle limits = new RectangleScale.Rectangle() {
                @Override
                public double x() {
                    return values.xMin();
                }

                @Override
                public double y() {
                    return values.yMin();
                }

                @Override
                public double w() {
                    return values.xMax() - values.xMin();
                }

                @Override
                public double h() {
                    return values.yMax() - values.yMin();
                }
            };
            RectangleScale scale = new RectangleScale(limits);
            RectangleScale.Point newPoint = scale.scale(point, screen);




            Point a = new Point((int)lastX, (int)lastY);
            Point b = new Point((int)newPoint.x(), (int)newPoint.y());

            surface.setThickness((int) settings.thickness());
            if(a.y >= 0 && a.y < settings.windowHeight() && b.y >= 0 && b.y < settings.windowHeight()) {
                surface.drawLine(a, b);
                // surface.drawPoint(b);
            }

            i += settings.stepSize();
            lastX = b.x;
            lastY = b.y;
        }

    }
}
