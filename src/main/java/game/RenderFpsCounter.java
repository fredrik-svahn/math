package game;

import java.awt.*;

public class RenderFpsCounter {
    public static void fpsCounterDraw(Entity entity, Graphics g) {
        if (!entity.hasTag("render_fps"))
            return;

        entity.storedDeltaTime += Game.renderDeltaTime;
        entity.storedTicks++;

        if (entity.storedTicks >= 10) {
            double avg = entity.storedDeltaTime / entity.storedTicks;
            entity.renderString = "FPS: " + 1000 / avg;
            entity.storedTicks = 0;
            entity.storedDeltaTime = 0;
        }
    }
}