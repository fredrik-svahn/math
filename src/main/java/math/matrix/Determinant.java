package math.matrix;

import math.exception.DeterminantOfEmptyMatrix;
import math.exception.NonSquareDeterminant;

class Determinant {
    private final Matrix matrixElements;

    public Determinant(Matrix matrixElements) {this.matrixElements = matrixElements;}

    public Double recursive() throws NonSquareDeterminant, DeterminantOfEmptyMatrix {
        if (matrixElements.dimension().width != matrixElements.dimension().height) {
            throw new NonSquareDeterminant();
        }
        if (matrixElements.dimension().width == 0) {
            throw new DeterminantOfEmptyMatrix();
        }

        if (matrixElements.dimension().width == 2) {
            Double positive = matrixElements.get(0,
                                                 0) * matrixElements.get(1,
                                                                         1);
            Double negative = matrixElements.get(1,
                                                 0) * matrixElements.get(0,
                                                                         1);

            return positive - negative;
        } else if (matrixElements.dimension().width == 1) {
            return matrixElements.get(0,
                                      0);
        } else {

            int w = matrixElements.dimension().width;
            int mult = 1;
            double sum = 0d;

            for (int i = 0; i < w; i++) {
                sum += matrixElements.get(i,
                                          0) * mult * matrixElements.excludeRowAndColumn(i,
                                                                                         0).determinant();
                mult *= -1;
            }

            return sum;
        }
    }
}
