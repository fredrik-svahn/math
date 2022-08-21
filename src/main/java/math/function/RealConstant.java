package math.function;


import org.junit.jupiter.api.Test;

import java.util.Objects;

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
    public void bind(String variableName,
                     Expression value) {
        /**
         * Binding to a constant does nothing
         */
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
