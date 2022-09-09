package performance;

public abstract class EventHandler {
    void init() {}
    void update() {}
    void update(double ms){}
    void destroyed() {}
}
