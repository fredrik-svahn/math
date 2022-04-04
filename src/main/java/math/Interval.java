package math;

import math.exception.NoIntersectionFound;
import math.set.Set;

public class Interval implements Set<Double> {
    public double min;
    public double max;

    public Interval(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean contains(Double value) {
        return value >= min && value <= max;
    }

    public boolean contains(Interval interval) {
        return min <= interval.min && max >= interval.max;
    }

    public Interval add(Interval interval) {
        double min = Math.min(this.min, interval.min);
        double max = Math.max(this.max, interval.max);
        return new Interval(min, max);
    }


    public double length() {
        return max - min;
    }

    public Interval intersection(Interval interval) throws NoIntersectionFound {
        if (!intersects(interval))
            throw new NoIntersectionFound();

        double min = Math.max(this.min, interval.min);
        double max = Math.min(this.max, interval.max);

        return new Interval(min, max);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Interval))
            return false;
        Interval interval = (Interval) obj;

        return min == interval.min && max == interval.max;
    }

    public boolean intersects(Interval interval) {
        return (interval.length() + length()) >= add(interval).length();
    }

    @Override
    public String toString() {
        return String.format("[%f, %f]", min, max);
    }
}
