package game;

public class TemporaryColor {
    static void tempColor(Entity entity) {
        if (entity.tempColorLifetime > 0) {
            entity.tempColorLifetime -= 17;
        }
    }
}