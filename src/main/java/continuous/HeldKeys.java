package continuous;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

class HeldKeys
        extends GameSystem {
    private Set<Integer> keysHeld = new HashSet<>();

    @Override
    void KeyPressed(KeyEvent event) {
        keysHeld.add(event.getKeyCode());
    }

    @Override
    void KeyReleased(KeyEvent event) {
        keysHeld.remove(event.getKeyCode());
    }

    @Override
    void Update16() {
        for (Integer integer : keysHeld) {
            emit(s -> s.KeyHeld(integer));
        }
    }
}
