package components;

import java.util.function.Consumer;

public class Event {
    public Consumer<EventHandler> process;

    public Event(Consumer<EventHandler> process) {
        this.process = process;
    }
}
