package components;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerSpawn extends GameSystem {
    @Override
    void init() {
        BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.RED);
        g.fillRect(0, 0, 50, 50);
        System.out.println(Color.RED);
        send(s -> s.imageCreated("player", image));
    }
}
