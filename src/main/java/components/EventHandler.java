package components;

import javax.swing.*;
import java.awt.*;

public abstract class EventHandler {
    void init() {}
    void updateTriggered() {}
    void update() {}
    void targetFrameRateSet(int fps) {}
    void windowSizeSet(int w, int h) {}
    void windowInit() {}
    void imageCreated(String name,  Image image) {}
    void imageDeleted(String name) {}
    void imageMoved(String name, int x, int y) {}
    void imageAngleSet(String name, double angle) {}
    void imageOriginSet(String name, int x, int y) {}
}
