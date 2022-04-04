package misc;

class Friction extends Cell {
    private final Object o;
    private double dy;
    private double dx;

    public Friction(Object o) {
        this.o = o;
    }

    @Override
    void VelocitySet(Object object, double dx, double dy) {
        if(object != o) return;

        this.dx = dx;
        this.dy = dy;
    }

    @Override
    void UpdateDelta() {
        emit(c -> c.VelocitySet(o, dx * 0.5, dy * 0.5));
    }
}
