package misc;

class Physics extends Cell {
    private final Object o;
    private double ddy;
    private double ddx;
    private double mass;
    private double fy;
    private double fx;
    private double dy;
    private double dx;
    private double w;
    private double y;
    private double x;

    public Physics(Object o) {
        this.o = o;
    }

    @Override
    void MovedTo(Object object, double x, double y) {
        if (object != o) return;
        this.x = x;
        this.y = y;
    }

    @Override
    void MovedDirection(Object object, double dx, double dy) {
        if (object != o) return;
        emit(cell -> cell.MovedTo(o, x + dx, y + dy));
    }

    @Override
    void VelocitySet(Object object, double dx, double dy) {
        if (object != o) return;

        this.dx = dx;
        this.dy = dy;
    }

    @Override
    void ForceApplied(Object object, double fx, double fy) {
        if (object != o) return;

        this.fx += fx;
        this.fy += fy;
    }

    @Override
    void ForceClear(Object object) {
        if (object != o) return;

        this.fx = 0;
        this.fy = 0;
    }

    @Override
    void MassSet(Object object, double mass) {
        if (object != o) return;

        this.mass = mass;
    }

    @Override
    void AccelerationAdded(Object object, double ddx, double ddy) {
        if (object != o) return;
        emit(cell -> cell.AccelerationSet(o, this.ddx + ddx, this.ddy + ddy));
    }

    @Override
    void VelocityAdded(Object object, double dx, double dy) {
        if (object != o) return;

        emit(cell -> cell.VelocitySet(o, this.dx + dx, this.dy + dy));
    }

    @Override
    void AccelerationSet(Object object, double ddx, double ddy) {
        if (object != o) return;

        this.ddx = ddx;
        this.ddy = ddy;
    }

    @Override
    void UpdateDelta() {
        emit(cell -> {
            cell.AccelerationAdded(o, fx / mass, fy / mass);
            cell.ForceClear(o);
            cell.VelocityAdded(o, ddx, ddy);
            cell.AccelerationSet(o, 0, 0);
            cell.MovedDirection(o, dx, dy);
        });
    }
}
