package math.matrix;

import math.matrix.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class VectorTest {
    @Test
    void vectorDotProductForLength3VectorIsCorrect() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4,5,6);


        double actual = v1.dotProduct(v2);
        double expected = 4+10+18;
        assertEquals(expected, actual);
    }

    @Test
    void vectorNormForLength4VectorIsCorrect() {
        Vector v1 = new Vector(1,2,3,4);
        double actual = v1.norm();
        double expected = Math.sqrt(1+4+9+16);

        assertEquals(expected, actual, 0.01);
    }

    @Test
    void vectorProjectionOnAxesIsComponent() {
        Vector v1 = new Vector(1, 2, 3);
        Vector e1 = new Vector(1,0,0);
        Vector e2 = new Vector(0,1,0);
        Vector e3 = new Vector(0,0,1);

        assertEquals(new Vector(1, 0, 0),
                     v1.projection(e1));

        assertEquals(new Vector(0, 2, 0),
                     v1.projection(e2));

        assertEquals(new Vector(0, 0, 3),
                     v1.projection(e3));
    }

    @Test
    void projectionOnItselfIsItself() {
        Vector v1 = new Vector(1,2,3,4);

        Vector actual = v1.projection(v1);
        Vector expected = new Vector(1,2,3,4);

        assertEquals(expected, actual);
    }

    @Test
    void normalizedVectorIsCorrect() {
        Vector v1 = new Vector(1,1,1);

        Vector expected = new Vector(1/Math.sqrt(3), 1/Math.sqrt(3), 1/Math.sqrt(3));
        Vector actual = v1.normalized();

        assertEquals(expected, actual);
    }

    
}