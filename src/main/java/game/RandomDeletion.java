package game;

public class RandomDeletion {
    private static void randomDeletion(Entity entity) {
        if (!entity.hasTag("projectile"))
            return;

        if (Math.random() > 0.95) {
            entity.garbage = true;
        }
    }
}