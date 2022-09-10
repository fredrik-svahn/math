package game.movement;

public class LinearMovement implements Movement {

    @Override
    public void update(Entity entity) {
        entity.setPosition(entity.position().add(entity.velocity()));
    }
}
