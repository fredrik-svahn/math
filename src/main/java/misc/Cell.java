package misc;


import javax.swing.*;
import java.awt.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public class Cell {
    private static List<Cell> cells = new LinkedList<>();
    private static Collection<Cell> spawnList = new ArrayList<>();
    private static Queue<Consumer<Cell>> messageQueue = new LinkedBlockingQueue<>();
    protected boolean delete = false;
    public Set<String> tags = new HashSet<>();

    public Cell() {
        spawnList.add(this);
    }

    public void delete() {
        delete = true;
    }

    public static void emit(Consumer<Cell> consumer) {
        messageQueue.add(consumer);
    }

    public static void process() {
        cells.addAll(spawnList);
        spawnList.clear();

        while (!messageQueue.isEmpty()) {
            Consumer<Cell> consumer = messageQueue.remove();
            Iterator<Cell> iterator = cells.iterator();

            while (iterator.hasNext()) {
                Cell cell = iterator.next();

                if (cell.delete) {
                    iterator.remove();
                } else {
                    consumer.accept(cell);
                }
            }
        }
    }

    void Init() {
    }

    void Update(double ms) { }

    void Kill(Object object) {
    }

    void ScreenInit(JFrame screen) {}
    void Draw(Graphics g) {}
    void ScreenElementRegistered(ScreenElement element) {}
    void ScreenElementUnregistered(ScreenElement element) {}
    void ScreenSizeChanged(int width, int height) {}
    void MovedTo(Object object, double x, double y) {}
    void ZChanged(Object object, int z) {}
    void MovedDirection(Object object, double dx, double dy) {}
    void SizeChanged(Object object, double w, double h) {}
    void ImageCropChanged(Object object, int dx, int dy, int dw, int dh) {}
    void VelocitySet(Object object, double dx, double dy) {}
    void ForceApplied(Object object, double fx, double fy) {}
    void MassSet(Object object, double mass) {}
    void AccelerationAdded(Object object, double ddx, double ddy) {}
    void VelocityAdded(Object object, double dx, double dy) {}
    void AccelerationSet(Object object, double ddx, double ddy) {}
    void ForceClear(Object object) {}
    void KeyPressed(java.awt.event.KeyEvent keyEvent) {}
    void KeyReleased(java.awt.event.KeyEvent keyEvent) {}
    void UpdateDelta() { }
    void KeyHeld(Integer key) {}
    void AngleChanged(Object object, double angle) {}
}
