package components;

import misc.Main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public abstract class GameSystem extends EventHandler {
    private static BlockingQueue<Consumer<EventHandler> > eventQueue = new LinkedBlockingQueue<>();
    private static Collection<GameSystem> systems = new ArrayList<>();

    public static void main(String[] args) throws
            InterruptedException {
        new MainLoop();
        new BasicRendering();
        new PlayerSpawn();

        run();
    }

    public static void run() throws
            InterruptedException {
        send(EventHandler::init);

        while(true) {
            var event = eventQueue.take();
            systems.parallelStream().forEach(event);
            Thread.sleep(16);
        }
    }

    public GameSystem() {
        systems.add(this);
    }

    public static void send(Consumer<EventHandler> event) {
        eventQueue.offer(event);
    }
}
