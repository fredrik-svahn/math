package math.set;

import java.util.Collection;
import java.util.stream.Collectors;

public class SetEnumeration<T> {
    private Set<T> set;

    public SetEnumeration(Set<T> set) {
        this.set = set;
    }

    public Collection<T> enumerate(Collection<T> values) {
        return values.stream().filter(v -> set.contains(v)).collect(Collectors.toSet());
    }
}
