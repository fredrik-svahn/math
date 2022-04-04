package misc;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

class HeldKeys extends Cell {
    private Set<Integer> pressed = new HashSet<>();

    @Override
    void KeyPressed(KeyEvent keyEvent) {
        pressed.add(keyEvent.getKeyCode());
    }

    @Override
    void KeyReleased(KeyEvent keyEvent) {
        pressed.remove(keyEvent.getKeyCode());
    }

    @Override
    void UpdateDelta() {
        for (Integer integer : pressed) {
            emit(c -> c.KeyHeld(integer));
        }
    }
}
