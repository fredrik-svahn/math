package game;

public class Movement {
    static void movement(Entity entity) {
        if (entity.forwardSpeed > 0) {
            if (entity.forwardSpeed > entity.maxSpeed) {
                entity.forwardSpeed = entity.maxSpeed;
            }
        } else if (entity.forwardSpeed < 0) {
            if (entity.forwardSpeed < -entity.maxSpeed) {
                entity.forwardSpeed = -entity.maxSpeed;
            }
        }

        entity.dx = Math.cos(entity.facingAngle.radians()) * entity.forwardSpeed;
        entity.dy = Math.sin(entity.facingAngle.radians()) * entity.forwardSpeed;

        entity.position.x += entity.dx;
        entity.position.y += entity.dy;

    }
}