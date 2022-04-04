package math;
import math.exception.NoIntersectionFound;
import org.junit.jupiter.api.Test;

class IntervalTest {
    @Test
    void intersection() throws NoIntersectionFound {
        Interval a = new Interval(0.3, 0.8);
        Interval b = new Interval(-1, 1);
    }
}