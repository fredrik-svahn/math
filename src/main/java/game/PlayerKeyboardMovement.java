package game;

import math.polar.Angle;

import java.awt.event.KeyEvent;

public class PlayerKeyboardMovement {
    public static void playerKeyboardMovement(Entity entity) {
        if(!entity.hasTag("type_player")) return;

        if (Keyboard.key(KeyEvent.VK_RIGHT)) {
            entity.facingAngle = entity.facingAngle.add(Angle.fromDegrees(5));
        }

        if (Keyboard.key(KeyEvent.VK_LEFT)) {
            entity.facingAngle = entity.facingAngle.add(Angle.fromDegrees(-5));
        }

        if (Keyboard.key(KeyEvent.VK_DOWN)) {
            entity.forwardSpeed -= entity.acceleration;
        }

        if (Keyboard.key(KeyEvent.VK_UP)) {
            entity.forwardSpeed += entity.acceleration;
        }

        if (Keyboard.key(KeyEvent.VK_SPACE)) {
            for (int i = 0; i < 5; i++) {
                Entity projectile = EntityFactory.PlayerProjectileSpread(entity, 5);
                projectile.maxSpeed = 10;
                projectile.forwardSpeed = 10;
                projectile.lifetime = EntityFactory.randomBetween(2000, 3000);
                projectile.drawFacing = false;
                projectile.expires = true;
                projectile.width = 3;
                projectile.height = 3;
                Game.spawn(projectile);
            }
        }
    }
}