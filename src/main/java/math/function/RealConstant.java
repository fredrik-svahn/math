package math.function;



import java.util.Objects;
import java.util.stream.Stream;

public class RealConstant extends Expression {
    public double value;

    public RealConstant(double value) {
        this.value = value;
    }

    @Override
    public Expression evaluate() {
        return this;
    }

    @Override
    public Expression bind(String variableName, Expression value) {
        return new RealConstant(this.value);
    }


    @Override
    public Stream<Variable> variables() {
        return Stream.of();
    }

    @Override
    public Expression clone() {
        return new RealConstant(value);
    }

    @Override
    public String toString() {
        return String.format("%f", value);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        RealConstant that = (RealConstant) object;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
