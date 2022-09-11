package game.movement;

import math.Vector3D;
import math.polar.Angle;

public class TankStyleMovement {
    public interface Entity {
        Vector3D velocity();
        Angle angle();
        double speed();
        void setVelocity(Vector3D velocity);
    }

    public void update(Entity entity) {
        Vector3D currentVelocity = entity.velocity();
        Vector3D direction = new Vector3D(
                Math.cos(entity.angle().radians()),
                Math.sin(entity.angle().radians())
        );

        Vector3D newVelocity = currentVelocity.add(direction.multiply(entity.speed()));
        entity.setVelocity(newVelocity);
    }
}
