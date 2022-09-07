package game;

import math.Vector3D;
import math.polar.Angle;

import java.awt.*;

public class EntityFactory {
    static Entity Player(double x,
                         double y) {
        Entity o = new Entity();

        o.addTags("type_player",
                   "faction_player",
                   "render_rectangle");

        o.layer = 1;
        o.color = Color.BLUE;
        o.position = new Vector3D(0,
                                   0);
        o.dx = 0;
        o.dy = 0;
        o.resize(50,
                  50);
        o.maxSpeed = 10;
        o.acceleration = 1 / 10d;
        o.boundingBoxes.put("hull", new BoundingBox(o.position, new Vector3D(0, 0), new Vector3D(50, 50), o.facingAngle));

        return o;
    }

    static Entity Enemy(double x,
                        double y) {
        Entity o = new Entity();

        o.addTags("type_enemy",
                  "faction_enemy",
                  "render_rectangle");

        o.layer = -1;
        o.position = new Vector3D(150,
                                  150);
        o.resize(50,
                 50);
        o.color = Color.RED;
        o.maxSpeed = 5;
        o.boundingBoxes.put("hull", new BoundingBox(o.position, new Vector3D(0, 0), new Vector3D(50, 50), o.facingAngle));

        return o;
    }
    public static Entity PlayerProjectile(Entity player) {
        Entity projectile = new Entity();

        projectile.addTags("type_projectile",
                           "faction_player",
                           "render_rectangle");

        projectile.position = new Vector3D(player.position.x + player.width / 2,
                                           player.position.y + player.height / 2);

        projectile.resize(5,
                          5);
        projectile.maxSpeed = 10;
        projectile.facingAngle = player.facingAngle.clone();
        projectile.forwardSpeed = 10;
        projectile.color = player.color;
        projectile.layer = player.layer;

        projectile.boundingBoxes.put("hull", new BoundingBox(projectile.position, new Vector3D(0, 0), new Vector3D(50, 50), projectile.facingAngle));

        return projectile;
    }

    public static Entity PlayerProjectileSpread(Entity player,
                                                double angleSpread) {
        Entity projectile = PlayerProjectile(player);
        projectile.facingAngle = projectile.facingAngle.add(Angle.fromDegrees(randomBetween(-angleSpread,
                                                                                            angleSpread)));
        return projectile;
    }

    public static double randomBetween(double min,
                                       double max) {
        return Math.random() * (max - min) + min;
    }
}
