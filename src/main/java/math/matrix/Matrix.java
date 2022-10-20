package math.matrix;

import math.exception.*;
import math.set.Set;

import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

public abstract class Matrix implements Set<Double>, Iterable<MatrixElement> {
    private Dimension dimension = new Dimension();


    public Double[] toArray() {
        Double[] arr = new Double[dimension.width * dimension.height];

        for (int y = 0; y < dimension.height; y++) {
            for (int x = 0; x < dimension.width; x++) {
                int index = y * dimension.width + x;
                arr[index] = get(x, y);
            }
        }

        return arr;
    }

    public void set(int x, int y, Double value) {
        if (x >= dimension.width) dimension.width = x + 1;
        if (y >= dimension.height) dimension.height = y + 1;
        setValue(x, y, value);
    }

    public void set(Point point, Double value) {
        set(point.x, point.y, value);
    }

    public Matrix() {
    }

    public Matrix fromRows(Vector[] rows) {
        Matrix copy = copy();

        for (int y = 0; y < rows.length; y++) {
            for (int x = 0; x < rows[y].rows(); x++) {
                copy.set(x, y, rows[y].get(0, x));
            }
        }

        return copy;
    }

    public Matrix transpose() {
        return new Transpose(this).invoke();
    }

    public Matrix subMatrix(Point topLeft, Point bottomRight) {
        return filter(((point, aDouble) -> point.x <= bottomRight.x && point.x >= topLeft.x && point.y >= topLeft.y && point.y <= bottomRight.y));
    }

    public Matrix filter(BiFunction<Point, Double, Boolean> filter) {
        Matrix matrix = empty();

        int realY = 0;

        for (int y = 0; y < dimension().height; y++) {
            boolean emptyRow = true;
            int realX = 0;

            for (int x = 0; x < dimension().width; x++) {
                Point point = new Point(x, y);

                if (filter.apply(point, get(point))) {
                    matrix.set(realX, realY, get(point));
                    emptyRow = false;
                    realX++;
                }
            }

            if (!emptyRow) {
                realY++;
            }
        }


        return matrix;
    }

    public void forEach(BiConsumer<Point, Double> action) {
        for (int y = 0; y < dimension().height; y++) {
            for (int x = 0; x < dimension().width; x++) {
                Point point = new Point(x, y);
                action.accept(point, get(point));
            }
        }
    }

    public Matrix excludeRowAndColumn(int x, int y) {
        return filter((point, aDouble) -> point.x != x && point.y != y);
    }

    public Matrix identity(int n) {
        Matrix matrix = empty();

        for (int i = 0; i < n; i++) {
            matrix.set(i, i, 1d);
        }

        return matrix;
    }

    public Matrix identity() {
        return identity(Math.min(dimension().width, dimension().height));
    }

    @Deprecated
    public Double recursiveDeterminant() throws NonSquareDeterminant, DeterminantOfEmptyMatrix {
        return new Determinant(this).recursive();
    }

    public Matrix multiply(Matrix m) throws MatrixDimensionMismatch {
        return new MatrixProduct(this, m).invoke();
    }

    public double dotProduct(Matrix m) {
        if (dimension().width != 1) throw new IllegalArgumentException();
        if (m.dimension().width != 1) throw new IllegalArgumentException();

        double sum = 0;
        for (MatrixElement matrixElement : this) {
            sum += matrixElement.value * m.get(matrixElement.index);
        }
        return sum;
    }

    public Matrix dotProductMatrix(Matrix m) {
        Matrix result = empty();
        result.set(0, 0, dotProduct(m));
        return result;
    }

    public Matrix add(Matrix m) throws MatrixDimensionMismatch {
        if (!dimension().equals(m.dimension())) {
            throw new MatrixDimensionMismatch();
        }

        return map((p, v) -> v + m.get(p));
    }

    public Matrix multiply(Double k) {
        return map((p, v) -> v * k);
    }

    public Matrix multiplyElements(Matrix matrix) throws MatrixDimensionMismatch {
        if (!dimension().equals(matrix.dimension())) {
            throw new MatrixDimensionMismatch();
        }
        return map((point, aDouble) -> aDouble * matrix.get(point));
    }

    public Matrix map(BiFunction<Point, Double, Double> f) {
        Matrix matrix = empty();

        forEach((point, aDouble) -> {
            matrix.set(point.x, point.y, f.apply(point, aDouble));
        });

        return matrix;
    }

    public Matrix copy() {
        return map((p, v) -> v);
    }

    public Double get(Point point) {
        return get(point.x, point.y);
    }

    public Matrix column(int column) {
        return subMatrix(new Point(column, 0), new Point(column, dimension().height));
    }

    public Matrix row(int row) {
        return subMatrix(new Point(0, row), new Point(dimension().width, row));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Matrix)) {
            return false;
        }

        Matrix m = (Matrix) obj;

        if (!(dimension().equals(m.dimension()))) {
            return false;
        }

        return Arrays.equals(toArray(), m.toArray());
    }

    public boolean approximateEquals(Matrix matrix, double delta) {
        if (!(dimension().equals(matrix.dimension()))) {
            return false;
        }

        Double[] arr1 = toArray();
        Double[] arr2 = matrix.toArray();

        for (int i = 0; i < arr1.length; i++) {
            double difference = arr1[i] - arr2[i];

            if (Math.abs(difference) > delta) {
                return false;
            }
        }

        return true;
    }

    public double determinant() throws NonSquareDeterminant {
        if (dimension().width != dimension().height) {
            throw new NonSquareDeterminant();
        }

        Matrix copy = copy();

        int swaps = 0;
        // Swap to create nonzero diagonal
        for (int i = 0; i < copy.dimension().height; i++) {
            int j;
            try {
                j = copy.getFirstNonZeroDiagonal(i);
            } catch (NoSuchRow noSuchRow) {
                return 0;
            }

            copy.swapRows(i, j);
            swaps++;
        }

        // Forwards
        for (int i = 0; i < copy.dimension().height; i++) {
            int finalI = i;
            IntStream.range(i + 1, copy.dimension().height).forEach(k -> {
                double a = copy.get(finalI, finalI);
                double b = copy.get(finalI, k);

                copy.addRowMultiple(finalI, k, -b / a);
            });
        }

        double product = 1;

        for (int i = 0; i < dimension().height; i++) {
            product *= copy.getDiagonal(i);
        }

        return product * Math.pow((-1), swaps);
    }

    public void reduce(Matrix augment) {

        // Swap to create nonzero diagonal
        for (int i = 0; i < dimension().height; i++) {
            try {
                int j = getFirstNonZeroDiagonal(i);
                swapRows(i, j);
                augment.swapRows(i, j);
            } catch (Throwable ignored) {
            }
        }

        // Forwards
        for (int i = 0; i < dimension().height; i++) {
            double a = get(pivot(i), i);

            for (int j = i + 1; j < dimension().height; j++) {
                double b = get(pivot(i), j);

                if (a == 0) return;

                addRowMultiple(i, j, -b / a);
                augment.addRowMultiple(i, j, -b / a);
            }
        }


        // Backwards
        for (int i = dimension().height - 1; i >= 0; i--) {
            double a = get(rPivot(i), i);

            for (int j = i - 1; j >= 0; j--) {
                double b = get(rPivot(i), j);


                if (a == 0) return;

                addRowMultiple(i, j, -b / a);
                augment.addRowMultiple(i, j, -b / a);
            }
        }

        IntStream.range(0, dimension().height).parallel().forEach(y -> {
            double diagonal = getDiagonal(y);
            multiplyRow(y, 1 / diagonal);
            augment.multiplyRow(y, 1 / diagonal);
        });
    }


    public static Matrix solve(Matrix leftSide, Matrix rightSide) throws NoSolutionFound {
        Matrix leftCopy = leftSide.copy();
        Matrix rightCopy = rightSide.copy();
        leftCopy.reduce(rightCopy);
        return rightCopy;
    }

    public Double getDiagonal(int row) {
        return get(row, row);
    }

    public void multiplyRow(int y, double factor) {
        IntStream.range(0, dimension().width).parallel().forEach(x -> {
            set(x, y, get(x, y) * factor);
        });
    }

    private int getFirstNonZeroDiagonal(int i) throws NoSuchRow {
        int j;
        for (j = 0; j < dimension().height; j++) {
            if (get(i, j) != 0) {
                return j;
            }
        }

        throw new NoSuchRow();
    }

    public void addRowMultiple(int a, int b, double mult) {
        for (int x = 0; x < dimension().width; x++) {
            set(x, b, get(x, b) + mult * get(x, a));
        }
    }

    public Matrix inverse() throws NoInverseExists {
        try {
            if (determinant() == 0) {
                throw new NoInverseExists();
            }
        } catch (NonSquareDeterminant nonSquareDeterminant) {
            throw new NoInverseExists();
        }

        Matrix matrix = copy();
        Matrix id = matrix.identity();
        matrix.reduce(id);


        return id;
    }

    public Matrix gramSchmidt() {
        Matrix copy = copy();

        for (int i = 1; i < copy.dimension().height; i++) {
            for (int j = i - 1; j >= 0; j--) {
                Vector baseRow = copy.getRowVector(j);
                Vector row = copy.getRowVector(i);
                Vector projection = row.projection(baseRow);

                copy.addRow(i, projection.transpose().multiply(-1d));
            }
        }


        for (int i = 0; i < copy.dimension().height; i++) {
            copy.setRow(i, copy.getRowVector(i).normalized().transpose());
        }

        return copy;
    }

    public void addRow(int y, Matrix row) {
        for (int i = 0; i < row.dimension().width; i++) {
            set(i, y, get(i, y) + row.get(i, 0));
        }
    }

    public void swapRows(int a, int b) {
        var temp = getRow(a);
        setRow(a, getRow(b));
        setRow(b, temp);
    }

    public void setRow(int y, ArrayList<Double> row) {
        for (int i = 0; i < row.size(); i++) {
            set(i, y, row.get(i));
        }
    }

    public void setRow(int y, Matrix row) {
        setRow(y, row.getRow(0));
    }

    public ArrayList<Double> getRow(int y) {
        ArrayList<Double> row = new ArrayList<>();

        for (int i = 0; i < dimension().width; i++) {
            row.add(get(i, y));
        }

        return row;
    }

    public Matrix getRowMatrix(int y) {
        java.util.List<Double> row = getRow(y);

        Matrix m = empty();

        for (int i = 0; i < row.size(); i++) {
            m.set(i, 0, row.get(i));
        }

        return m;
    }

    public Vector getRowVector(int y) {
        return new Vector(getRowMatrix(y).transpose());
    }


    public ArrayList<Double> getColumn(int x) {
        ArrayList<Double> col = new ArrayList<>();

        for (int i = 0; i < dimension().height; i++) {
            col.add(get(x, i));
        }

        return col;
    }

    protected abstract void setValue(int x, int y, Double value);


    protected abstract Matrix empty();

    public Dimension dimension() {
        return dimension;
    }

    public abstract Double get(int x, int y);

    public boolean contains(Double value) {
        for (MatrixElement matrixElement : this) {
            if (matrixElement.value.equals(value)) return true;
        }

        return false;
    }

    @Override
    public Iterator<MatrixElement> iterator() {
        Iterator<MatrixElement> iterator = new Iterator<>() {
            int x = 0;
            int y = 0;

            @Override
            public boolean hasNext() {
                return x < dimension().width && y < dimension().height;
            }

            @Override
            public MatrixElement next() {
                MatrixElement element = new MatrixElement();
                element.index = new Point(x, y);
                element.value = get(x, y);

                x++;

                if (x >= dimension().width) {
                    x = 0;
                    y++;
                }

                return element;
            }
        };

        return iterator;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < dimension().height; i++) {
            var row = getRow(i);
            builder.append(row.toString()).append("\n");
        }

        return builder.toString();
    }

    public Matrix random() {
        return map((p, v) -> Math.random());
    }

    public Matrix round(int decimals) {
        DecimalFormat decimalFormat = new DecimalFormat("#." + "#".repeat(decimals));
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return map((p, v) -> Double.parseDouble(decimalFormat.format(v)));
    }

    public Matrix round() {
        return round(0);
    }

    public Matrix random(double k) {
        return random().multiply(k);
    }

    public Matrix cross(Matrix m, double chance) {
        return map((p, v) -> {
            if (Math.random() <= chance) return m.get(p);
            else return v;
        });
    }

    public Matrix cross(Matrix m) {
        return cross(m, 0.5);
    }

    public Matrix merge(Matrix m) {
        Matrix copy = copy();
        forEach(copy::set);
        m.forEach(copy::set);
        return copy;
    }

    public void setRow(int y, double... row) {
        for (int x = 0; x < row.length; x++) {
            set(x, y, row[x]);
        }
    }

    public void setColumn(int x, double... col) {
        for (int y = 0; y < col.length; y++) {
            set(x, y, col[y]);
        }
    }

    public int pivot(int row) {
        for (int i = 0; i < dimension().width; i++) {
            if (get(i, row) != 0) return i;
        }

        return getRow(row).size() - 1;
    }

    public int rPivot(int row) {

        for (int i = dimension().width - 1; i >= 0; i--) {
            if (get(i, row) != 0) return i;
        }

        return 0;
    }
}
