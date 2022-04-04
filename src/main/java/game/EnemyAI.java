package game;

import math.polar.Angle;

public class EnemyAI {
    /**
     * Super advanced AI using machine learning
     *
     * @param entity
     */
    public static void enemyAI(Entity entity) {
        if (!entity.hasTag("type_enemy"))
            return;

        entity.facingAngle = entity.facingAngle.add(Angle.fromDegrees(5));
        entity.forwardSpeed = 0;
    }
}