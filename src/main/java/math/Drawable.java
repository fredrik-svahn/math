package math;

import javax.swing.*;
import java.awt.*;

public interface Drawable {
    Integer layer();
    void draw(JFrame frame, Surface screen, Graphics g);
}
