package game.movement;

import math.Vector3D;

public class BasicMovement {
    public interface Entity {
        Vector3D position();
        Vector3D velocity();
        void setPosition(Vector3D position);
    }

    public void update(Entity entity) {
        Vector3D currentPosition = entity.position();
        Vector3D newPosition = currentPosition.add(entity.velocity());
        entity.setPosition(newPosition);
    }
}
