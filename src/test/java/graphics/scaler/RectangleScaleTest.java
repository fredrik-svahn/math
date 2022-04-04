package graphics.scaler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleScaleTest {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    @Test
    void topLeftRemainsTopLeft() {
        RectangleScale scale = new RectangleScale(new RectangleScale.Rectangle() {
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
                return WIDTH;
            }

            @Override
            public double h() {
                return HEIGHT;
            }
        });

        RectangleScale.Point point = scale.scale(new RectangleScale.Point() {
            @Override
            public double x() {
                return 0;
            }

            @Override
            public double y() {
                return 0;
            }
        }, new RectangleScale.Rectangle() {
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
                return WIDTH/2d;
            }

            @Override
            public double h() {
                return HEIGHT/2d;
            }
        });


        assertEquals(point.x(), 0);
        assertEquals(point.y(), 0);
    }
}