package game;

public class QuadTreeMaker {
    public static void collisions() {
        double minX = 0;
        double minY = 0;
        double maxX = 0;
        double maxY = 0;


        for (Entity entity : Game.world) {
            if (entity.position.x < minX)
                minX = entity.position.x;
            if (entity.position.y < minY)
                minY = entity.position.y;

            if (entity.position.x + entity.width > maxX)
                maxX = entity.position.x + entity.width;
            if (entity.position.y + entity.height > maxY)
                maxY = entity.position.y + entity.height;
        }

        Game.tree = new QuadTree(minX, minY, maxX - minX, maxY - minY);

        for (Entity entity : Game.world) {
            Game.tree.add(entity);
        }


        Game.tree.subdivide();
        Game.tree.makeCollisions();
    }
}