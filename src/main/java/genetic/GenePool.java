package genetic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenePool {
    private Collection<Chromosome> genes;
    private double mutation;
    private double cross;

    public GenePool(Chromosome template, int size, double mutation, double cross) {
        genes = new ArrayList<>();
        this.mutation = mutation;
        this.cross = cross;

        for (int i = 0; i < size; i++) {
            Chromosome gene = template.clone();
            gene.mutate(mutation);
            genes.add(gene);
        }
    }

    public GenePool newPool(Function<Chromosome, Double> selection) {
        return new GenePool(template(selection), genes.size(), mutation, cross);
    }

    public Chromosome template(Function<Chromosome, Double> selection) {
        List<Chromosome> newGenes = scoreSorted(selection);
        Chromosome template1 = newGenes.get(newGenes.size() - 1);
        Chromosome template2 = newGenes.get(newGenes.size() - 2);
        Chromosome template = template1.cross(template2, cross);
        return template;
    }

    public List<Chromosome> scoreSorted(Function<Chromosome, Double> selection) {
        return genes
                .stream()
                .sorted(Comparator.comparing(selection))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return genes.stream().map(Chromosome::toString).collect(Collectors.joining(System.lineSeparator()));
    }
}
