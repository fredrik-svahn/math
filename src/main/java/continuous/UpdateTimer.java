package continuous;

class UpdateTimer extends GameSystem {
    private long past = System.nanoTime();

    @Override
    void Init() {
        emit(EventHandler::Update);
    }

    @Override
    void Update() {
        long current = System.nanoTime();
        long dt = current - past;

        emit(s -> s.Update(dt));

        past = current;
    }

    @Override
    void Update(long deltaTime) {
        emit(EventHandler::Update);
        emit(s -> s.Update(deltaTime / 1E6));
    }
}
