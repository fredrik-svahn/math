package components;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerSpawn extends GameSystem {

    private double angle;

    @Override
    void init() {
        BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.RED);
        g.fillRect(0, 0, 50, 50);
        send(s -> s.imageCreated("player", image));
        send(s -> s.imageMoved("player", 25, 25));
        send(s -> s.imageAngleSet("player", 45));
        send(s -> s.imageOriginSet("player", 25, 25));
    }

    @Override
    void imageAngleSet(String name,
                       double angle) {
        if(!name.equals("player")) return;

        this.angle = angle;
    }

    @Override
    void update() {
        send(s -> s.imageAngleSet("player", angle + 1));
    }
}
