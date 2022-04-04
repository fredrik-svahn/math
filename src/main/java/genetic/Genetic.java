package genetic;

import java.util.function.Function;

public class Genetic {
    public static void main(String[] args) {
        IndividualPopulation population = new IndividualPopulation(new Individual(3), 10, 8);

        Function<Individual, Double> scoring = individual -> {
            double error = 0;
            int i;
            for (i = 0; i < 10; i++) {
                double sample = Math.random() * 20;
                double expected = Math.sqrt(sample);
                double actual = approximation(individual, sample);
                error += Math.abs(expected - actual);
            }

            return -error/i;
        };

        for (int i = 0; i < 100000; i++) {
            population = population.newPool(scoring);
        }

        Individual best = population.template(scoring);
        System.out.println(approximation(best, 9));
    }

    private static double approximation(Individual individual, double x) {
        double sum = 0;
        for (int i = 0; i < individual.genes.length; i++) {
            sum += Double.longBitsToDouble(individual.genes[i]) * Math.pow(x, i);
        }
        return sum;
    }
}
