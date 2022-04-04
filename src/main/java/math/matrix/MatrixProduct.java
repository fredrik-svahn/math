package math.matrix;

import math.exception.MatrixDimensionMismatch;

class MatrixProduct {
    private final Matrix m1;
    private Matrix m2;

    public MatrixProduct(Matrix m1,
                         Matrix m2) {
        this.m1 = m1;
        this.m2 = m2;}

    public Matrix invoke() throws MatrixDimensionMismatch {
        if (m1.dimension().width != m2.dimension().height) {
            throw new MatrixDimensionMismatch();
        }

        Matrix result = m1.empty();
        result.set(m2.dimension().width - 1,
                   m1.dimension().height - 1,
                   0d);

        result = result.map((p, v) -> {
            Matrix row = m1.row(p.y).transpose();
            Matrix column = m2.column(p.x);
            return row.dotProduct(column);
        });

        return result;
    }
}
