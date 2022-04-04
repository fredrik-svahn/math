package math.set;

public class Tuple<A, B> {
    private A first;
    private A second;

    public Tuple(A first, A second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public A getSecond() {
        return second;
    }
}
