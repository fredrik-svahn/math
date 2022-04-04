package continuous;

import math.Vector3D;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

class PlayerMovement
        extends GameSystem {
    private Set<Object> players = new HashSet<>();

    @Override
    void PlayerEnabled(Object object) {
        players.add(object);
    }

    @Override
    void PlayerDisabled(Object object) {
        players.remove(object);
    }

    @Override
    void Destroyed(Object object) {
        players.remove(object);
    }

    @Override
    void KeyHeld(int key) {

        for (Object player : players) {
            int amount = 1;
            double ddx = 0;
            double ddy = 0;
            switch (key) {
                case KeyEvent.VK_RIGHT:
                    ddx = amount;
                    break;
                case KeyEvent.VK_LEFT:
                    ddx = -amount;
                    break;
                case KeyEvent.VK_DOWN:
                    ddy = amount;
                    break;
                case KeyEvent.VK_UP:
                    ddy = -amount;
                    break;
                case KeyEvent.VK_SPACE:
                    break;
            }

            Vector3D acceleration = new Vector3D(ddx, ddy);
            emit(s -> s.Accelerated(player,acceleration));
        }
    }
}
