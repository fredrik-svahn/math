package components;

import misc.Main;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public abstract class GameSystem extends EventHandler {
    private static BlockingQueue<Consumer<EventHandler> > eventQueue = new LinkedBlockingQueue<>();
    private static Collection<GameSystem> systems = new ArrayList<>();
    private static long storedDeltaTime = 0;
    private static long lastUpdate = System.nanoTime();
    private static long deltaTimeLimit = 2;

    public static void main(String[] args) throws
            InterruptedException {
        new BasicRendering();
        new PlayerSpawn();

        run();
    }

    public static void run() throws
            InterruptedException {
        send(EventHandler::init);

        new Thread(() -> {
            lastUpdate = System.nanoTime();

            while(true) {
                try {
                    storeUpdates();
                    Consumer<EventHandler> event = eventQueue.take();
                    systems.forEach(event);
                    runStoredUpdates();
                    Thread.sleep(deltaTimeLimit);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private static void storeUpdates() {
        double timeElapsed = (System.nanoTime() - lastUpdate) / 1e6;
        storedDeltaTime += timeElapsed;
    }

    private static void runStoredUpdates() {
        while(storedDeltaTime > deltaTimeLimit) {
            systems.forEach(EventHandler::update);
            storedDeltaTime -= deltaTimeLimit;
        }
        lastUpdate = System.nanoTime();
    }

    public GameSystem() {
        systems.add(this);
    }

    public static void send(Consumer<EventHandler> event) {
        eventQueue.offer(event);
    }
}
