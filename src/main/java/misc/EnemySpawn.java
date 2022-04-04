package misc;

class EnemySpawn extends Cell {
    @Override
    void Init() {
        Object enemy = new Object();

        for (int i = 0; i < 1; i++) {
            new EnemyAI(enemy);
        }
    }
}
