package misc;

import javax.swing.*;
import java.awt.*;

class Background extends Cell {

    private JFrame screen;

    @Override
    void ScreenInit(JFrame screen) {
        this.screen = screen;
    }

    @Override
    void Init() {
        emit(c -> c.ScreenElementRegistered(new ScreenElement() {
            @Override
            public void draw(Graphics g) {
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
            }

            @Override
            public double z() {
                return 0;
            }
        }));
    }
}
