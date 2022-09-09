package performance;

import java.util.function.Consumer;

public abstract class Component extends EventHandler {
    public GameObject object;

    public void emit(Consumer<EventHandler> event) {
        object.send(event);
    }
}
