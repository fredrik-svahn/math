package math.matrix;

public class HashMapMatrixTest extends MatrixTest {
    @Override
    protected Matrix getMatrix() {
        return new HashMapMatrix();
    }
}
