package game;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class RenderFillRectangle {
    static void fillRectangleEntity(Entity entity,
                                    Graphics g) {
        if (!entity.hasTag("render_rectangle"))
            return;

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform transform = g2d.getTransform();

        g2d.rotate(entity.facingAngle.radians(),
                   entity.position.x + entity.width / 2,
                   entity.position.y + entity.height / 2);

        g2d.setColor(entity.color);
        g2d.fillRect((int) entity.position.x,
                     (int) entity.position.y,
                     (int) entity.width,
                     (int) entity.height);

        g2d.setTransform(transform);
    }
}