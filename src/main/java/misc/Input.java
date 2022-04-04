package misc;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Input extends Cell {
    @Override
    void ScreenInit(JFrame screen) {
        screen.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                Cell.emit(c -> c.KeyPressed(keyEvent));
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                Cell.emit(c -> c.KeyReleased(keyEvent));
            }
        });
    }


}
