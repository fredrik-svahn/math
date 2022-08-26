package components;

import javax.swing.*;

public abstract class EventHandler {
    void init() {}
    void update() {}
    void update(double ms) {}
    void targetFrameRateSet(int fps) {}
    void windowSizeSet(int w, int h) {}
    void windowInit(JFrame frame) {}
}
