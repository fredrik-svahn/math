package math.set;

public class Rationals implements Set<Tuple<Integer, Integer>> {
    @Override
    public boolean contains(Tuple<Integer, Integer> value) {
        return value.getSecond() != 0;
    }
}
