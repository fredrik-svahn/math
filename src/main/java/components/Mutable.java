package components;

import java.util.function.Function;

public class Mutable<T> {
    private T value;

    public Mutable(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }

    public void update(Function<T, T> function) {
        set(function.apply(get()));
    }
}
