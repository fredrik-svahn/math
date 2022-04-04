package math.matrix;

class ArrayListMatrixTest extends MatrixTest {

    @Override


    protected Matrix getMatrix() {
        return new ArrayListMatrix();
    }
}