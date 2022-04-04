package game;

public class Expiration {
    static void expiry(Entity entity) {
        if (!entity.expires)
            return;

        entity.lifetime -= 17;

        if (entity.lifetime <= 0) {
            entity.garbage = true;
        }
    }
}