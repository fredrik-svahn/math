package game;

import java.awt.*;

public class RenderDrawOutline {
    static void drawOutline(Entity entity, Graphics g, Color outlineColor) {
        g.setColor(Color.BLUE);
        g.drawRect((int)entity.position.x, (int)entity.position.y, (int) entity.width, (int) entity.height);
    }
}