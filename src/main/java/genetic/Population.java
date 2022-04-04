package genetic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Population {
    public Collection<Network> specimen = new ArrayList<>();

    public Population(Network template, int size) {
        for (int i = 0; i < size; i++) {
            Network network = template.cross(template);
            network.mutate();
            specimen.add(network);
        }
    }

    public List<Double> scores(Function<Network, Double> criteria) {
        return specimen
                .stream()
                .map(criteria)
                .collect(Collectors.toList());
    }

    public Population nextGeneration(Function<Network, Double> criteria) {
        List<Network> networks = specimen
                .stream()
                .sorted(Comparator.comparing(criteria))
                .collect(Collectors.toList());

        Network parent1 = networks.get(networks.size() - 1);
        Network parent2 = networks.get(networks.size() - 2);

        Network child = parent1.cross(parent2);

        System.out.println(child.weightsToString());

        return new Population(child, networks.size());
    }

    public void forEach(Consumer<Network> consumer) {
        specimen.forEach(consumer);
    }
}
