package genetic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IndividualPopulation {
    private Collection<Individual> individuals;
    private int iterations;

    public IndividualPopulation(Individual template, int size, int iterations) {
        individuals = new ArrayList<>();
        this.iterations = iterations;

        for (int i = 0; i < size; i++) {
            Individual individual = template.clone();
            individual.mutate(iterations);
            individuals.add(individual);
        }
    }

    public IndividualPopulation newPool(Function<Individual, Double> selection) {
        return new IndividualPopulation(template(selection), individuals.size(), iterations);
    }

    public Individual template(Function<Individual, Double> selection) {
        List<Individual> newGenes = scoreSorted(selection);
        Individual template1 = newGenes.get(newGenes.size() - 1);
        Individual template2 = newGenes.get(newGenes.size() - 2);
        Individual template = template1.cross(template2);
        return template;
    }

    public List<Individual> scoreSorted(Function<Individual, Double> selection) {
        return individuals
                .stream()
                .sorted(Comparator.comparing(selection))
                .collect(Collectors.toList());
    }
}
