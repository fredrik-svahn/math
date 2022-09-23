package eventsourcing;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class EventLog {
    private List<Event> events = new ArrayList<>();

    public interface EventHandler {

    }

    public void emit(Event event) {
        events.add(event);
    }

    public void handle(EventHandler handler) {
        events.forEach(event -> {
            try {
                handler.getClass().getDeclaredMethod(
                        event.getClass().getSimpleName(),
                        event.getClass()
                ).invoke(
                        handler,
                        event
                );
            } catch(Exception ignored) {

            }
        });
    }
}
