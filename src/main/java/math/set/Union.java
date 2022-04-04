package math.set;
public class Union<T> implements Set<T> {
    private Set<T> a;
    private Set<T> b;

    public Union(Set<T> a, Set<T> b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean contains(T value) {
        return a.contains(value) || b.contains(value);
    }
}
