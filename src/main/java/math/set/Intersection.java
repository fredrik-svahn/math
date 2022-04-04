package math.set;

public class Intersection<T> implements Set<T> {
    private Set<T> a;
    private Set<T> b;

    public Intersection(Set<T> a, Set<T> b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean contains(T value) {
        return a.contains(value) && b.contains(value);
    }
}
