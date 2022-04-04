package math.matrix;

import math.Vector3D;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListMatrix extends Matrix {
    private ArrayList<ArrayList<Double>> elements = new ArrayList<>();

    public ArrayListMatrix(Vector3D... columns) {
        for (int i = 0; i < columns.length; i++) {
            Vector3D column = columns[i];

            set(i, 0, column.getX());
            set(i, 1, column.getY());
            set(i, 2, column.getZ());
        }
    }


    @Override
    public Double get(int x, int y) {
        return elements.get(y).get(x);
    }


    @Override
    protected void setValue(int x, int y, Double value) {
        while(y >= elements.size()) {
            elements.add(new ArrayList<>());
        }

        for (ArrayList<Double> element : elements) {
            while(x >= element.size()) {
                element.add(0d);
            }
        }

        elements.get(y).set(x, value);
    }


    @Override
    public Matrix empty() {
        return new ArrayListMatrix();
    }

    @Override
    public String toString() {
        List<String> rows = elements.stream().map(AbstractCollection::toString).collect(Collectors.toList());
        return String.join("\n", rows);
    }
}
