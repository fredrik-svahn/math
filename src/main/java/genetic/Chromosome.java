package genetic;

import java.util.BitSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Chromosome implements Cloneable {
    private BitSet bits;
    private int size;

    public Chromosome(int size) {
        bits = new BitSet(size);
        bits.set(0, size-1, false);
        this.size = size;
    }

    public Chromosome cross(Chromosome chromosome, double ratioKept) {
        Chromosome copy = clone();
        for (int i = 0; i < size; i++) {
            if(Math.random() <= ratioKept) {
                copy.bits.set(i, chromosome.bits.get(i));
            }
        }
        return copy;
    }

    public void mutate(double chance) {
        for (int i = 0; i < size; i++) {
            if(Math.random() <= chance) {
                bits.flip(i);
            }
        }
    }

    public BitSet bits() {
        return bits;
    }

    public Chromosome clone() {
        Chromosome copy = new Chromosome(size);
        copy.bits = (BitSet) bits.clone();
        return copy;
    }

    @Override
    public String toString() {
        return toBinaryString();
    }

    private String toBinaryString() {
        return IntStream.range(0, bits.size())
                .mapToObj(b -> String.valueOf(bits.get(b) ? 1 : 0))
                .collect(Collectors.joining());
    }
}
