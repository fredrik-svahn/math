package continuous;

class Update16ms
        extends GameSystem {
    private double dt = 0;

    @Override
    void Update(double milliSeconds) {

        dt += milliSeconds;

        while(dt >= 16) {
            emit(EventHandler::Update16);
            dt -= 16;
        }
    }
}
