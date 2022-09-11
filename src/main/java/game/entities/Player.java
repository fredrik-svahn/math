package game.entities;

import game.Mesh;
import game.core.Entity;
import game.movement.BasicMovement;
import game.movement.TankStyleMovement;
import math.Vector3D;
import math.polar.Angle;

public class Player extends Entity
        implements TankStyleMovement.Entity,
                   BasicMovement.Entity {
    private BasicMovement basicMovement = new BasicMovement();
    private TankStyleMovement tankStyleMovement = new TankStyleMovement();


    private Vector3D position = new Vector3D();
    private Vector3D velocity = new Vector3D();
    private double speed;
    private Angle angle = Angle.fromDegrees(0);
    private Mesh mesh;

    public Player() {
        mesh = new Mesh(
                new Vector3D(-25, 0),
                new Vector3D(25, 0),
                new Vector3D(25, 25),
                new Vector3D(-25, 25)
        );
    }

    @Override
    public void update() {
        tankStyleMovement.update(this);
        basicMovement.update(this);
    }

    @Override
    public Mesh mesh() {
        return mesh.translated(position);
    }

    @Override
    public Vector3D position() {
        return position;
    }

    @Override
    public Vector3D velocity() {
        return velocity;
    }

    @Override
    public void setPosition(Vector3D position) {
        this.position = position;
    }

    @Override
    public Angle angle() {
        return angle;
    }

    @Override
    public double speed() {
        return speed;
    }

    @Override
    public void setVelocity(Vector3D velocity) {
        this.velocity = velocity;
    }
}
