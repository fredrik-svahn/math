package math.matrix;

import math.Vector3D;
import math.exception.MatrixDimensionMismatch;

public class Transformation {
    private Matrix matrix;

    private Transformation(Matrix matrix) {
        this.matrix = matrix;
    }

    public Transformation apply(Transformation transformation) {
        try {
            return new Transformation(transformation.matrix.multiply(matrix));
        } catch (MatrixDimensionMismatch matrixDimensionMismatch) {
            return null;
        }
    }

    public static Transformation identity() {
        return new Transformation(new ArrayListMatrix().identity(4));
    }

    public static Transformation translation(double x, double y, double z) {
        Matrix m = new ArrayListMatrix().identity(4);
        m.set(3, 0, x);
        m.set(3, 1, y);
        m.set(3, 2, z);
        m.set(3, 3, 1d);

        return new Transformation(m);
    }

    public static Transformation rotationZ(double angle) {
        Matrix m = new ArrayListMatrix().identity(4);
        m.set(0,0, Math.cos(angle));
        m.set(1,0, -Math.sin(angle));
        m.set(0,1, Math.sin(angle));
        m.set(1,1, Math.cos(angle));

        return new Transformation(m);
    }

    public static Transformation rotationY(double angle) {
        Matrix m = new ArrayListMatrix().identity(4);
        m.set(0,0, Math.cos(angle));
        m.set(2,0, Math.sin(angle));
        m.set(0,2, -Math.sin(angle));
        m.set(2,2, Math.cos(angle));

        return new Transformation(m);
    }

    public static Transformation rotationX(double angle) {
        Matrix m = new ArrayListMatrix().identity(4);
        m.set(1,1, Math.cos(angle));
        m.set(2,1, -Math.sin(angle));
        m.set(1,2, Math.sin(angle));
        m.set(2,2, Math.cos(angle));

        return new Transformation(m);
    }

    public static Transformation rotation(double angle) {
        return rotationZ(angle);
    }

    public Matrix matrix() {
        return matrix;
    }

    public Vector3D transform(Vector3D vector3D) {
        Matrix temp = new ArrayListMatrix(vector3D);
        temp.set(0,3,1d);

        Matrix result = temp.identity();
        try {
            result = matrix.multiply(temp);
        } catch (MatrixDimensionMismatch ignored) {
        }

        Vector3D v = new Vector3D();
        v.setX(result.get(0,0));
        v.setY(result.get(0,1));
        v.setZ(result.get(0,2));

        return v;
    }
}
