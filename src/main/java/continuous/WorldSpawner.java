package continuous;

import math.Vector3D;

import java.awt.*;

class WorldSpawner extends GameSystem {
    @Override
    void Init() {
        Object object = new Object();
        Vector3D position = new Vector3D();
        Vector3D velocity = new Vector3D(0, 0);
        Vector3D size = new Vector3D(50, 50);
        Color color = Color.RED;

        emit(s -> {
            s.Created(object);
            s.PositionChanged(object, position);
            s.VelocityChanged(object, velocity);
            s.SizeChanged(object, size);
            s.ColorChanged(object, color);
            s.RenderRectangleEnabled(object);
            s.PlayerEnabled(object);
            s.MovementEnabled(object);
            s.FrictionEnabled(object);
            s.WeaponEnabled(object);
        });
    }
}
