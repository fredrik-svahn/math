package continuous;

import math.Vector3D;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class RectangleRenderEngine extends GameSystem {
    private Map<Object, Color> colors = new HashMap<>();
    private Map<Object, Vector3D> positions = new HashMap<>();
    private Map<Object, Vector3D> sizes = new HashMap<>();
    private Set<Object> objects = new HashSet<>();

    @Override
    void Update(double milliSeconds) {
        for (Object object : objects) {
            Vector3D position = positions.get(object);
            Vector3D size = sizes.get(object);
            Color color = colors.get(object);

            emit(s -> s.RenderHandlerRegistered(object, position.z, g -> {
                g.setColor(color);
                g.fillRect((int) position.x, (int) position.y, (int) size.x, (int) size.y);
            }));
        }
    }

    @Override
    void ColorChanged(Object object, Color color) {
        colors.put(object, color);
    }

    @Override
    void SizeChanged(Object object, Vector3D size) {
        sizes.put(object, size);
    }

    @Override
    void PositionChanged(Object object, Vector3D position) {
        positions.put(object, position);
    }

    @Override
    void RenderRectangleEnabled(Object object) {
        objects.add(object);
    }

    @Override
    void RenderRectangleDisabled(Object object) {
        objects.remove(object);
    }

    @Override
    void Destroyed(Object object) {
        objects.remove(object);
        positions.remove(object);
        colors.remove(object);
        sizes.remove(object);
    }
}
