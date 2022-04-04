package game;

import math.Vector3D;
import math.polar.Angle;

import javax.swing.*;
import java.awt.*;

public class TestProgram {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        Collider c1 = new BoundingBox(new Vector3D(), new Vector3D(50, 50), new Vector3D(50, 50),
                                      Angle.fromDegrees(26));
        Collider c2 = new BoundingBox(new Vector3D(), new Vector3D(30, 30), new Vector3D(50, 50),
                                      Angle.fromDegrees(37));

        frame.add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                c1.drawOutline(g);
                c2.drawOutline(g);
                c1.intersects(c2);
            }
        });

        frame.setVisible(true);

        while(true) {
            frame.repaint();
        }
    }
}
