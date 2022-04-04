package math.matrix;

class Transpose {
    private final Matrix matrixElements;

    public Transpose(Matrix matrixElements) {this.matrixElements = matrixElements;}

    public Matrix invoke() {
        Matrix matrix = matrixElements.empty();

        matrixElements.forEach((point, aDouble) -> {
            //noinspection SuspiciousNameCombination
            matrix.set(point.y,
                       point.x,
                       aDouble);
        });

        return matrix;
    }
}
