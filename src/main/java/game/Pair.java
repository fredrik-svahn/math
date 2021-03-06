package game;

public class Pair<T, T1> {
    private T first;
    private T1 second;

    Pair(T first, T1 second) {
        this.first = first;
        this.second = second;
    }

    public T getKey() {
        return first;
    }

    public T1 getValue() {
        return second;
    }
}
