package components;

import math.Vector3D;

public class Movement extends Component {
    public Mutable<Vector3D> position;
    public Mutable<Vector3D> velocity;

    @Override
    void update() {
        position.update(p -> p.add(velocity.get()));
    }
}
