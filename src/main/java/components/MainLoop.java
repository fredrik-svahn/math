package components;

public class MainLoop extends GameSystem {
    private long storedDeltaTime = 0;
    private long lastUpdate = System.nanoTime();
    private long deltaTimeLimit = 16;

    @Override
    void init() {
        send(EventHandler::updateTriggered);
    }

    @Override
    void updateTriggered() {
        storedDeltaTime += System.nanoTime() - lastUpdate;

        while(storedDeltaTime >= deltaTimeLimit) {
            storedDeltaTime -= deltaTimeLimit;
            send(EventHandler::update);
        }

        send(EventHandler::updateTriggered);
        lastUpdate = System.nanoTime();
    }
}
