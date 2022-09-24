package game.world;

import java.util.Collection;
import java.util.HashSet;

public class World {
    public interface Entity {
        void update();
        void killed();
        void spawned();
    }

    private Collection<Entity> entities = new HashSet<>();

    public void spawn(Entity entity) {
        entities.add(entity);
        entity.spawned();
    }

    public void kill(Entity entity) {
        entity.killed();
        entities.remove(entity);
    }

    public void update() {
        for (Entity entity : entities) {
            entity.update();
        }
    }
}
