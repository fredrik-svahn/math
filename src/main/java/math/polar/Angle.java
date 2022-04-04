package math.polar;
public class Angle {
    private final static double PI = 3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148086513282306647093844609550582231725359408128481117450284102701938521105559644622948954930381964428810975665933446128475648233786783165271201909145648566923460348610454326648213393607260249141273724587006;

    private double radians;

    public Angle(double radians) {
        while(radians >= PI * 2) {
            radians -= Math.PI * 2;
        }

        while(radians < 0) {
            radians += PI * 2;
        }

        this.radians = radians;
    }

    public static Angle fromRadians(double radians) {
        return new Angle(radians);
    }

    public static Angle fromDegrees(double degrees) {
        return new Angle(degrees * PI / 180);
    }

    public double degrees() {
        return radians * 180 / PI;
    }

    public double radians() {
        return radians;
    }

    public Angle add(Angle angle) {
        return new Angle(radians() + angle.radians());
    }

    public Angle subtract(Angle angle) {
        return add(angle.reverse());
    }

    public Angle reverse() {
        return new Angle(radians() + Math.PI);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Angle)) return false;
        return radians() == ((Angle) obj).radians();
    }

    @Override
    public Angle clone() {
        return Angle.fromDegrees(degrees());
    }

    @Override
    public String toString() {
        return "radians: " + radians() + ", degrees: " + degrees();
    }
}
