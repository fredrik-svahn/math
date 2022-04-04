package components;

import java.util.ArrayList;
import java.util.Collection;

public class Program {
    private static Collection<Entity> entities = new ArrayList<>();

    public static void main(String[] args) {
        
    }

    public static void spawn(Entity entity) {
        entities.add(entity);
    }

    public static void kill(Entity entity) {
        entities.remove(entity);
    }

}
