package game;

import java.awt.*;

public class RenderDrawFacingAngle {
    static void drawFacingAngle(Entity entity, Graphics g) {
        int centerX = (int) (entity.position.x + entity.width / 2);
        int centerY = (int) (entity.position.y + entity.height / 2);
        int endPointX = (int) (centerX + Math.cos(entity.facingAngle.radians()) * entity.width);
        int endPointY = (int) (centerY + Math.sin(entity.facingAngle.radians()) * entity.height);

        g.setColor(Color.GREEN);
        g.drawLine(centerX, centerY, endPointX, endPointY);
    }
}