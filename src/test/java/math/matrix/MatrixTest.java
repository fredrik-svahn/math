package math.matrix;

import math.exception.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class MatrixTest {
    protected abstract Matrix getMatrix();

    @Test
    void newlyCreatedMatrixHasDimensionZero() {
        Matrix m = getMatrix();
        assertEquals(m.dimension(), new Dimension(0, 0));
    }

    @Test
    void addingElementOutsideMatrixChangesSize() {
        Matrix m = getMatrix();
        m.set(1, 1, 3d);


        assertEquals(m.dimension(), new Dimension(2, 2));
    }

    @Test
    void transposeIsCorrectFor2x2() {
        Matrix m = getMatrix();
        m.set(0, 0, 1d);
        m.set(1, 0, 2d);
        m.set(0, 1, 1d);
        m.set(1, 1, 2d);

        Matrix m1 = getMatrix();
        m1.set(0, 0, 1d);
        m1.set(0, 1, 2d);
        m1.set(1, 0, 1d);
        m1.set(1, 1, 2d);

        assertEquals(m1, m.transpose());
    }

    @Test
    void transposeTwiceGivesBackOriginal() {
        Matrix m1 = getMatrix();

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                m1.set(x, y, Math.random());
            }
        }

        assertEquals(m1, m1.transpose().transpose());
    }

    @Test
    void determinant3x3Correct() throws NonSquareDeterminant, DeterminantOfEmptyMatrix, NoSolutionFound, NoInverseExists, MatrixDimensionMismatch {
        Matrix m = getMatrix();

        m.set(0, 0, 0d);
        m.set(1, 0, 1d);
        m.set(2, 0, 1d);
        m.set(0, 1, 4d);
        m.set(1, 1, -2d);
        m.set(2, 1, 5d);
        m.set(0, 2, 2d);
        m.set(1, 2, 8d);
        m.set(2, 2, 7d);

        assertEquals(18, m.determinant(), 1e-5);
    }

    @Test
    void matrixMultiplicationIsCorrectFor2x2() throws MatrixDimensionMismatch {
        Matrix m = getMatrix();
        m.set(0, 0, 2d);
        m.set(1, 0, 4d);
        m.set(0, 1, 6d);
        m.set(1, 1, 8d);


        Matrix r = getMatrix();
        r.set(0, 0, 28d);
        r.set(1, 0, 40d);
        r.set(0, 1, 60d);
        r.set(1, 1, 88d);


        assertEquals(r, m.multiply(m));
    }

    @Test
    void inverseDoesNotExistForDeterminantZero() throws NonSquareDeterminant, DeterminantOfEmptyMatrix {
        Matrix matrix = getMatrix();

        matrix.set(0, 0, 4d);
        matrix.set(1, 0, 2d);
        matrix.set(0, 1, 4d);
        matrix.set(1, 1, 2d);
    }

    @Test
    void inverseOfIdentityIsIdentity() throws NoInverseExists {
        Matrix m = getMatrix().identity(3);
        Matrix inverse = m.inverse();
        assertEquals(m, inverse);
    }

    @Test
    void rowReductionOfLinearlyDependentMatrixGivesZeroedRows() {
        Matrix m = getMatrix().identity(3).random();

        m.setRow(1, m.getRowVector(0).multiply(2).transpose());
        m.setRow(2, m.getRowVector(0).multiply(3).transpose());

        m.reduce(m.identity(3));

        assertEquals(m.getRowVector(1), new Vector(0, 0, 0));
        assertEquals(m.getRowVector(2), new Vector(0, 0, 0));
    }

    @Test
    void rowReductionOf3x3MatrixIsCorrect() {

    }

    @Test
    void inverseMatrixSolvesLinearEquation() throws MatrixDimensionMismatch, NoInverseExists {
        Matrix m = getMatrix();
        m.set(0, 0, 4d);
        m.set(1, 0, 6d);
        m.set(0, 1, 9d);
        m.set(1, 1, 8d);

        Matrix b = getMatrix();
        b.set(0, 0, 30d);
        b.set(0, 1, 20d);


        Matrix solution = m.inverse().multiply(b);
        Double x = solution.get(0, 0);
        Double y = solution.get(0, 1);

        assertEquals(30, 4 * x + 6 * y, 1E-6);
        assertEquals(20, 9 * x + 8 * y, 1E-6);
    }

    @Test
    void solvingGivesSameAnswerAsInverseMultiplication() throws NoSolutionFound, NoInverseExists, MatrixDimensionMismatch {
        Matrix A = getMatrix().fromRows(new Vector[]{new Vector(1, 0, -2), new Vector(3, 1, -2), new Vector(-5,
                                                                                                            -1,
                                                                                                            9)});

        Matrix b = new Vector(2, 3, 5);

        Matrix solveSolution = Matrix.solve(A, b);
        Matrix inverseSolution = A.inverse().multiply(b);

        assertEquals(solveSolution, inverseSolution);
    }

    @Test
    void rowVectorIsCorrect() {
        Matrix matrix = getMatrix();

        matrix.set(0, 0, 1d);
        matrix.set(1, 0, 2d);
        matrix.set(2, 0, 3d);
        matrix.set(3, 0, 4d);

        Vector actual = matrix.getRowVector(0);

        Matrix matrix1 = getMatrix();
        matrix1.set(0, 0, 1d);
        matrix1.set(0, 1, 2d);
        matrix1.set(0, 2, 3d);
        matrix1.set(0, 3, 4d);

        Vector expected = new Vector(matrix1);
        assertEquals(expected, actual);
    }

    @Disabled
    public void performance() throws NoInverseExists, NonSquareDeterminant {
        Matrix m = getMatrix().identity(3);
        m.random();

        int n = 10000;
        for (int i = 0; i < n; i++) {
            m.inverse();
        }

        long t0 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            m.inverse();
        }
        long t1 = System.nanoTime();

        System.out.printf("Performance %d x %d %s inversion\n",
                          m.dimension().width,
                          m.dimension().height,
                          m.getClass().getName());
        System.out.printf("%d inversions: %d ns\n", n, t1 - t0);
        System.out.printf("%d inversions: %f ms\n", n, (t1 - t0) / 1E6);
        System.out.printf("Average: %f ms\n", (t1 - t0) / 1E6 / n);


        Matrix m1 = getMatrix().identity(5).random();

        for (int i = 0; i < n; i++) {
            m1.determinant();
        }

        long t2 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            m1.determinant();
        }
        long t3 = System.nanoTime();

        System.out.printf("Performance %d x %d %s determinant\n",
                          m1.dimension().width,
                          m1.dimension().height,
                          m1.getClass().getName());
        System.out.printf("%d determinants: %d ns\n", n, t3 - t2);
        System.out.printf("%d determinants: %f ms\n", n, (t3 - t2) / 1E6);
        System.out.printf("Average: %f ms\n", (t3 - t2) / 1E6 / n);
    }
}