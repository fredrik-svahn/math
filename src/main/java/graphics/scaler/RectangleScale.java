package graphics.scaler;

public class RectangleScale {
    private Rectangle rectangle;

    public RectangleScale(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Point scale(Point point, Rectangle rectangle1) {
        double newX = point.x();
        double newY = point.y();

        newX += (rectangle1.x() - rectangle.x());
        newY += (rectangle1.y() - rectangle.y());
        newX *= (rectangle1.w() / rectangle.w());
        newY *= (rectangle1.h() / rectangle.h());

        double x = newX;
        double y = newY;

        return new Point() {
            @Override
            public double x() {
                return x;
            }

            @Override
            public double y() {
                return y;
            }
        };
    }

    public static interface Point {
        double x();
        double y();
    }

    public static interface Rectangle {
        double x();
        double y();
        double w();
        double h();
    }
}
