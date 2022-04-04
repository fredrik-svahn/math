package misc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Sprite extends Cell {
    private final String file;
    private final Object o;
    private int z;
    private int x;
    private int y;
    private int h;
    private int w;
    private int dx;
    private int dy;
    private int dw;
    private int dh;
    private double angle;


    public Sprite(String file, Object o) {
        this.file = file;
        this.o = o;
    }

    @Override
    void Init() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedImage finalImage = image;
        emit(c -> c.ScreenElementRegistered(new ScreenElement() {

            @Override
            public void draw(Graphics g) {
                g.drawImage(
                        finalImage,
                        0,
                        0,
                        w,
                        h,
                        dx,
                        dy,
                        dx + dw,
                        dy + dh,
                        null
                );
            }

            @Override
            public double z() {
                return z;
            }

            @Override
            public double angle() {
                return angle;
            }

            @Override
            public Point origin() {
                return new Point(x + w/2, y + h/2);
            }
        }));
    }

    @Override
    void SizeChanged(Object object, double w, double h) {
        if (object != o) return;

        this.w = (int) w;
        this.h = (int) h;
    }

    @Override
    void MovedTo(Object object, double x, double y) {
        if (object != o) return;
        this.x = (int) x;
        this.y = (int) y;
    }

    @Override
    void ZChanged(Object object, int z) {
        if (object != o) return;
        this.z = z;
    }

    @Override
    void ImageCropChanged(Object object, int dx, int dy, int dw, int dh) {
        if (object != o) return;
        this.dx = dx;
        this.dy = dy;
        this.dw = dw;
        this.dh = dh;
    }

    @Override
    void AngleChanged(Object object, double angle) {
        if(object != o) return;

        this.angle = angle;
    }
}
