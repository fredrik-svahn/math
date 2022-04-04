package math;

import math.exception.MatrixDimensionMismatch;
import math.exception.NoIntersectionFound;
import math.exception.NoInverseExists;
import org.junit.jupiter.api.Test;

class LineTest {
    @Test
    void testIntersection() throws NoIntersectionFound, NoInverseExists, MatrixDimensionMismatch {
        Surface surface = new Surface(
                new Vector3D(0, 0, 0),
                new Vector3D(0, 1, 0),
                new Vector3D(1, 0, 0));
        LineSegment line = new LineSegment(0.33, 0.25, -1, 0.25, 0.25, 1);
    }
}