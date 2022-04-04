package math.matrix;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HashMapMatrix extends Matrix {
    private Map<Point, Double> map = new HashMap<>();

    @Override
    protected void setValue(int x, int y, Double value) {
        map.put(new Point(x, y), value);
    }

    @Override
    protected Matrix empty() {
        return new HashMapMatrix();
    }


    @Override
    public Double get(int x, int y) {
        return map.getOrDefault(new Point(x, y), 0d);
    }
}
