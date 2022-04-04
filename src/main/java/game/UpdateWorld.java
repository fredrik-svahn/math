package game;

import java.util.TimerTask;

/**
 * Dumb workaround to use TimerTask
 */
class UpdateWorld extends TimerTask {
    @Override
    public void run() {
        long current = System.currentTimeMillis();
        Game.updateDeltaTime = current - Game.updateLastTime;
        Game.world.removeIf(e -> e.garbage);
        Game.world.addAll(Game.spawnQueue);
        Game.spawnQueue.clear();

        QuadTreeMaker.collisions();
        Game.world.forEach(Game::update);
        Keyboard.updateKeys();
        Game.updateLastTime = current;
    }
}
