package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

class Keyboard implements KeyListener {
    private static Set<Integer> keys = new HashSet<>();
    private static Set<Integer> pressedKeys = new HashSet<>();
    private static Set<Integer> previousKeys = new HashSet<>();

    static boolean keyPressed(int key) {
        return !previousKeys.contains(key) && keys.contains(key);
    }

    private static boolean keyReleased(int key) {
        return previousKeys.contains(key) && !keys.contains(key);
    }

    private static boolean keyPressedRepeat(int key) {
        return pressedKeys.contains(key);
    }

    public static boolean key(Integer key) {
        return keys.contains(key);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keys.add(keyEvent.getKeyCode());
        pressedKeys.add(keyEvent.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keys.remove(keyEvent.getKeyCode());
    }

    public static void updateKeys() {
        pressedKeys.clear();
        previousKeys = new HashSet<>(keys);
    }
}
