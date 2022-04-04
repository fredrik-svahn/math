package math;

import math.exception.NoIntersectionFound;
import math.matrix.ArrayListMatrix;
import math.matrix.Matrix;
import math.set.Set;

public class Surface {
    private Vector3D p0;
    private Vector3D p1;
    private Vector3D p2;

    public Surface(Vector3D p0, Vector3D p1, Vector3D p2) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
    }

    public Line intersection(Surface surface) {
        return null;
    }

    public Vector3D normal() {
        return p1.crossProduct(p2);
    }

    public Vector3D intersection(Line line) throws NoIntersectionFound {
        Matrix solution = new ArrayListMatrix(line.start.subtract(p0));
        Matrix system = new ArrayListMatrix(line.start.subtract(line.end), p1.subtract(p0), p2.subtract(p0));

        try {
            system.reduce(solution);
            double t = solution.get(0, 0);
            double u = solution.get(0, 1);
            double v = solution.get(0, 2);

            Set<Double> norm = new Interval(0, 1);

            if (line.isFinite() && !norm.contains(t)) {
                throw new NoIntersectionFound();
            }

            if (!norm.contains(u)) {
                throw new NoIntersectionFound();
            }

            if (!norm.contains(v)) {
                throw new NoIntersectionFound();
            }

            if (u + v > 1) {
                throw new NoIntersectionFound();
            }

            return line.start.add(line.end.subtract(line.start).multiply(t));
        } catch (Throwable t) {
            throw new NoIntersectionFound();
        }
    }
}
