package continuous;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class BasicInput extends GameSystem {
    @Override
    void FrameCreated(JFrame frame) {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                emit(s -> s.KeyPressed(e));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                emit(s -> s.KeyReleased(e));
            }
        });
    }
}
