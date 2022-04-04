package continuous;

import math.Vector3D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Friction
        extends GameSystem {
    private Set<Object> objects = new HashSet<>();
    private Map<Object, Vector3D> velocities = new HashMap<>();


    @Override
    void Update16() {
        for (Object object : objects) {
            Vector3D velocity = velocities.get(object);
            Vector3D friction = velocity.multiply(0.05);
            emit(s -> s.Accelerated(object, friction.multiply(-1)));
        }
    }

    @Override
    void VelocityChanged(Object object,
                         Vector3D velocity) {
        velocities.put(object, velocity);
    }

    @Override
    void FrictionEnabled(Object object) {
        objects.add(object);
    }

    @Override
    void FrictionDisabled(Object object) {
        objects.remove(object);
    }
}
