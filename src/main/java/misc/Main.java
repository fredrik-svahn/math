package misc;


import math.polar.Angle;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new EnemySpawn();
        new PlayerSpawn();
        new ScreenInitializer();
        new WindowGraphics();
        new DeltaTime();
        new Background();
        new DeltaUpdate();
        new Input();
        new HeldKeys();

        Object o = new Object();
        String file = "guy2.png";
        new Physics(o);
        new Sprite(file, o);
        new KeyboardMovement(o);
        new Friction(o);

        new Cell() {
            private double angle;

            @Override
            void UpdateDelta() {
                angle += 1;
                Cell.emit(cell -> cell.AngleChanged(o, angle));
                Cell.emit(cell -> cell.MovedDirection(o, 1, 1));
            }
        };

        Cell.emit(c -> {
            c.SizeChanged(o, 100, 100);
            c.ImageCropChanged(o, 0, 0, 100, 100);
            c.MovedTo(o, 25, 25);
            c.MassSet(o, 1);
            c.AngleChanged(o, Angle.fromDegrees(5).radians());
        });

        Cell.emit(Cell::Init);
        Cell.emit(c -> c.ScreenSizeChanged(500, 500));
        while (true) Cell.process();
    }

    private static class KeyboardMovement extends Cell {
        private final Object o;

        public KeyboardMovement(Object o) {
            this.o = o;
        }

        @Override
        void KeyHeld(Integer key) {
            switch (key) {
                case java.awt.event.KeyEvent.VK_LEFT:
                    emit(c -> c.ForceApplied(o, -1, 0));
                    break;
                case java.awt.event.KeyEvent.VK_RIGHT:
                    emit(c -> c.ForceApplied(o, 1, 0));
                    break;
                case java.awt.event.KeyEvent.VK_UP:
                    emit(c -> c.ForceApplied(o, 0, -1));
                    break;
                case java.awt.event.KeyEvent.VK_DOWN:
                    emit(c -> c.ForceApplied(o, 0, 1));
                    break;
            }
        }
    }
}
