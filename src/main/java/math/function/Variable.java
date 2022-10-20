package math.function;

import java.util.Objects;
import java.util.stream.Stream;

public class Variable extends Expression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public Expression evaluate() {
        return this;
    }

    @Override
    public Expression bind(String variableName, Expression value) {
        if (variableName.equals(name)) {
            return value.clone();
        }
        else {
            return new Variable(name);
        }
    }

    @Override
    public Stream<Variable> variables() {
        return Stream.of(this);
    }

    @Override
    public Expression clone() {
        return new Variable(name);
    }

    @Override
    public String toString() {
        return name;
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
