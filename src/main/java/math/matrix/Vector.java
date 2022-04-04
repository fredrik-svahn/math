package math.matrix;

import java.util.List;

public class Vector extends ArrayListMatrix {
    public Vector(Matrix matrix) {
        if(matrix.dimension().height >= matrix.dimension().width) {
            consumeList(matrix.getColumn(0));
        }
        else {
            consumeList(matrix.getRow(0));
        }
    }

    public Vector(double... values) {
        consumeArray(values);
    }

    private void consumeArray(double[] values) {
        for (int i = 0; i < values.length; i++) {
            set(0, i, values[i]);
        }
    }

    private void consumeList(List<Double> list) {
        for (int i = 0; i < list.size(); i++) {
            set(0, i, list.get(i));
        }
    }

    public Vector(List<Double> list) {
        consumeList(list);
    }

    public int rows() {
        return dimension().height;
    }

    public double dotProduct(Vector vector) {
        int rowCount = Math.max(rows(), vector.rows());

        double sum = 0;

        for (int row = 0; row < rowCount; row++) {
            double first = row <= rows() ? get(0, row) : 0;
            double second = row <= vector.rows() ? vector.get(0, row) : 0;

            sum += first * second;
        }

        return sum;
    }

    public double norm() {
        return Math.sqrt(dotProduct(this));
    }

    public Vector normalized() {
        return multiply(1 / norm());
    }

    public Vector multiply(double k) {
        return new Vector(super.multiply(k));
    }

    public Vector projection(Vector vector) {
        double a = dotProduct(vector);
        double b = vector.dotProduct(vector);
        double k = a/b;
        return vector.multiply(k);
    }

}
