package continuous;

import math.Vector3D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Movement extends GameSystem {
    private Map<Object, Vector3D> velocities = new HashMap<>();
    private Map<Object, Vector3D> positions = new HashMap<>();
    private Set<Object> objects = new HashSet<>();

    @Override
    void Update16() {
        for (Object object : objects) {
            Vector3D velocity = velocities.get(object);

            if(velocity.norm() < 0.01) continue;

            emit(s -> s.MovedDirection(object, velocity));
        }
    }

    @Override
    void MovementEnabled(Object object) {
        objects.add(object);
    }

    @Override
    void MovementDisabled(Object object) {
        objects.remove(object);
    }

    @Override
    void Destroyed(Object object) {
        velocities.remove(object);
        positions.remove(object);
        objects.remove(object);
    }

    @Override
    void VelocityChanged(Object object, Vector3D velocity) {
        velocities.put(object, velocity);
    }

    @Override
    void Accelerated(Object object, Vector3D acceleration) {

        emit(s -> s.VelocityChanged(object, velocities.get(object).add(acceleration)));
    }


    @Override
    void MovedDirection(Object object, Vector3D direction) {
        emit(s -> s.PositionChanged(object, positions.get(object).add(direction)));
    }

    @Override
    void PositionChanged(Object object, Vector3D position) {
        positions.put(object, position);
    }
}
