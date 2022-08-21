package math.function;

import java.util.Objects;

public class Variable extends Expression {
    private String name;
    private Expression value;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public Expression evaluate() {
        if(value == null) return this;

        return value.evaluate();
    }

    @Override
    public void bind(String variableName,
                     Expression value) {
        if(variableName.equals(name)) {
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Variable variable = (Variable) object;

        return Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
