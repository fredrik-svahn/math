package game.movement;

import math.Vector3D;
import math.matrix.Vector;

public interface Entity {
    Vector3D position();
    void setPosition(Vector3D position);

    Vector3D velocity();
    void setVelocity(Vector3D velocity);


}
