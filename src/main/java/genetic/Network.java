package genetic;

import math.matrix.ArrayListMatrix;
import math.matrix.Matrix;

public class Network {
    private double[][] weights;
    private double[][] state;
    private double[][] initialState;
    private double loss = 0.1;
    private double threshold = 0.1;
    private double mutationRate = 0.1;


    public Network(int n, double threshold, double loss, double mutationRate) {
        weights = new double[n][n];
        state = new double[n][n];
        initialState = new double[n][n];
        this.threshold = threshold;
        this.loss = loss;
        this.mutationRate = mutationRate;
    }

    public void mutate() {
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                weights[i][j] += -mutationRate + Math.random() * 2 * mutationRate;
            }
        }
    }

    public void pulse(int i, double charge) {
        double[] targets = state[i];

        for (int j = 0; j < targets.length; j++) {
            if(j == i) continue;
            if(charge < weights[i][j] * state[i][j]) continue;

            targets[j] += weights[i][j] * charge;

            double nextCharge = 1d / targets.length * weights[i][j] * state[i][j] * charge - loss;
            pulse(j, nextCharge);
        }
    }

    public Network cross(Network network) {
        Network newNetwork = new Network(size(), threshold, loss, mutationRate);

        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                if (Math.random() <= .5) {
                    newNetwork.weights[i][j] = weights[i][j];
                }
                else {
                    newNetwork.weights[i][j] = network.weights[i][j];
                }
            }
        }

        return newNetwork;
    }

    public void set(int neuron, int connection, double value) {
        state[neuron][connection] = value;
    }

    public double get(int neuron, int connection) {
        return state[neuron][connection];
    }

    public String toString() {
        Matrix matrix = new ArrayListMatrix();

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                matrix.set(j, i, state[i][j]);
            }
        }

        return matrix.toString();
    }

    public String weightsToString() {
        Matrix matrix = new ArrayListMatrix();

        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                matrix.set(j, i, weights[i][j]);
            }
        }

        return matrix.toString();
    }

    public int size() {
        return state.length;
    }


    public double state(int neuron) {
        double sum = 0;

        for (int i = 0; i < state[neuron].length; i++) {
            sum += state[neuron][i];
        }

        return sum;
    }
}
