package graphics;

import graphics.grapher.DrawSurface;
import graphics.grapher.FunctionValues;
import graphics.grapher.GraphSettings;
import graphics.grapher.Grapher;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Grapher grapher = new Grapher(
                new GraphSettings() {
                    @Override
                    public double thickness() {
                        return 2;
                    }

                    @Override
                    public int windowWidth() {
                        return frame.getWidth();
                    }

                    @Override
                    public int windowHeight() {
                        return frame.getHeight();
                    }

                    @Override
                    public double stepSize() {
                        return 0.05;
                    }
                },
                new FunctionValues() {

                    @Override
                    public double y(double x) {
                        return Math.log(x);
                    }

                    @Override
                    public double yMax() {
                        return 10;
                    }

                    @Override
                    public double yMin() {
                        return -10;
                    }

                    @Override
                    public double xMax() {
                        return 10;
                    }

                    @Override
                    public double xMin() {
                        return -10;
                    }
                }
        );


        JPanel jPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                grapher.draw(new DrawSurface() {
                    @Override
                    public void setColor(String color) {
                        g.setColor(Color.decode(color));
                    }

                    @Override
                    public void drawPoint(Point point) {
                        g.drawRect(point.x, getHeight() - point.y, 1, 1);
                    }

                    @Override
                    public void drawLine(Point start, Point end) {
                        g.drawLine(start.x, getHeight() - start.y, end.x, getHeight() - end.y);
                    }



                    @Override
                    public void drawImage(Image image, Point point) {
                        g.drawImage(
                                image,
                                point.x,
                                point.y,
                                frame.getWidth(),
                                frame.getHeight(),
                                null,
                                null
                        );
                    }

                    @Override
                    public void setThickness(int thickness) {
                        Graphics2D g2d = (Graphics2D) g;
                        g2d.setStroke(new BasicStroke(thickness));
                    }
                });
            }
        };


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(jPanel);
        frame.setVisible(true);
        frame.repaint();
    }
}
