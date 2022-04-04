package misc;

class PlayerSpawn extends Cell {
    @Override
    void Init() {
        Object player = new Object();
        new PlayerBehaviour(player);
    }
}
