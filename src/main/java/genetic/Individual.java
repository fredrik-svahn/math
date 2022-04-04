package genetic;

import java.util.Arrays;

public class Individual {
    public long[] genes;

    public Individual(int geneCount) {
        genes = new long[geneCount];
        Arrays.fill(genes, 0);
    }

    public void mutate(int iterations) {
        for (int i = 0; i < genes.length; i++) {
            long base = Long.MAX_VALUE;

            for (int j = 0; j < iterations; j++) {
                long val = (long) (Math.random() * Long.MAX_VALUE);
                base = base & val;
            }

            genes[i] ^= base;
        }
    }

    public void print() {
        for (int i = 0; i < genes.length; i++) {
            String string = String
                    .format("%64s", Long.toBinaryString(genes[i]))
                    .replace(' ', '0');
            System.out.println(string);
        }
    }

    public Individual clone() {
        Individual individual = new Individual(genes.length);
        individual.genes = Arrays.copyOf(genes, genes.length);
        return individual;
    }

    public Individual cross(Individual individual) {
        Individual offspring = clone();

        for (int i = 0; i < offspring.genes.length; i++) {
            long pattern = (long) (Math.random() * Long.MAX_VALUE);
            offspring.genes[i] ^= individual.genes[i] & pattern;
        }

        return offspring;
    }
}
