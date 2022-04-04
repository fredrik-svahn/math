package continuous;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public class GameSystem extends EventHandler {
    private static Collection<GameSystem> systems = new ArrayList<>();
    private static BlockingQueue<Consumer<EventHandler>> events = new LinkedBlockingQueue<>();

    static {
        new Thread(() -> {
            while (true) {
                try {
                    var event = events.take();

                    for (GameSystem gameSystem : systems) {
                        event.accept(gameSystem);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void emit(Consumer<EventHandler> event) {
        events.add(event);
    }

    public GameSystem() {
        systems.add(this);
    }
}
