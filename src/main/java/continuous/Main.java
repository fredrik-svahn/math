package continuous;

public class Main {
    public static void main(String[] args) {
        new WorldSpawner();
        new UpdateTimer();
        new Movement();
        new RenderEngine();
        new Window();
        new RectangleRenderEngine();
        new BasicInput();
        new Friction();
        new Update16ms();
        new HeldKeys();
        new PlayerMovement();
        new WeaponSystem();

        GameSystem.emit(EventHandler::Init);
    }

}
