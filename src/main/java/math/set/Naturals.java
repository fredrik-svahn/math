package math.set;

public class Naturals implements Set<Integer> {
    @Override
    public boolean contains(Integer value) {
        return value > 0;
    }
}
