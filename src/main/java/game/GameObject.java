package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;


public abstract class GameObject {
    public static List<GameObject> objects = new ArrayList<>();

    public boolean delete;

    public GameObject() {
        objects.add(this);
    }

    public void dispose() { delete = true; }
    public void draw(Graphics g) {};

    public static void broadcast(Consumer<GameObject> consumer) {
        objects.removeIf(object -> {
            if(!object.delete) consumer.accept(object);
            return object.delete;
        });
    }

    void Init() {}
    void Update() {}
    void KeyDown(Integer key) {}
    void KeyUp(Integer key) {}
    void KeyHeld(Integer key) {}
    void MovedTo(Object object, double x, double y) {}
    void MovedDirection(Object object, double dx, double dy) {}
    void VelocitySet(Object object, double dx, double dy) {}
    void VelocityAdded(Object object, double dx, double dy) {}
    void SizeSet(Object object, double width, double height) {}
    void ColorSet(Object object, Color color) {}

    void MovementSpeedSed(Object object, double speed) {}


    void FrictionSet(Object object, double friction) {}


    void LeftKeyHeld() {}
    void RightKeyHeld() {}
    void UpKeyHeld() {}
    void DownKeyHeld() {}
    void SpaceKeyHeld() {}
    void ShiftKeyHeld() {}

    void LeftKeyUp() {}
    void RightKeyUp() {}
    void UpKeyUp() {}
    void DownKeyUp() {}
    void SpaceKeyUp() {}
    void ShiftKeyUp() {}


    void LeftKeyDown() {}
    void RightKeyDown() {}
    void UpKeyDown() {}
    void DownKeyDown() {}
    void SpaceKeyDown() {}
    void ShiftKeyDown() {}
}
