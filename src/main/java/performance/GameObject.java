package performance;

import game.Game;

import java.util.function.Consumer;

public class GameObject {
    private Component[] components;

    public GameObject(Component... components) {
        this.components = components;

        for (Component component : components) {
            component.object = this;
        }
    }

    public void send(Consumer<EventHandler> event) {
        for (Component component : components) {
            event.accept(component);
        }
    }
}
