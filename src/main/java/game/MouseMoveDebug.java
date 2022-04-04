package game;

public class MouseMoveDebug {
    public static void mouseMoveDebug(Entity entity) {
        if (!Game.mouseMove)
            return;
        if (!entity.hasTag("player"))
            return;

        entity.position.x = Game.mouseX - entity.width / 2;
        entity.position.y = Game.mouseY - entity.height;
    }
}