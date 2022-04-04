package math;

import math.exception.NoIntersectionFound;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LineSegmentTest {


    @Test
    void testIntersectionOfBaseVectorsIsOrigin() throws NoIntersectionFound {
        LineSegment base1 = new LineSegment(new Vector3D(0, 0), new Vector3D(1, 0));
        LineSegment base2 = new LineSegment(new Vector3D(0, 0), new Vector3D(0, 1));


        Vector3D i = base1.intersection(base2);
        assertEquals(i, new Vector3D(0, 0, 0));
    }

    @Test
    void intersectionOfTwoLinesIsCorrect() throws NoIntersectionFound {
        Vector3D A = new Vector3D(352.0837610209865,347.7259738836005);
        Vector3D B = new Vector3D(302.2740261163995,352.0837610209865);
        Vector3D C = new Vector3D(320.964778541475,355.37538487244194);
        Vector3D D = new Vector3D(303.8637713751917,308.3907538331465);

        LineSegment line1 = new LineSegment(A, B);
        LineSegment line2 = new LineSegment(C, D);

        Vector3D intersection = line1.intersection(line2);
    }
}