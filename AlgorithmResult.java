package application;

import java.util.ArrayList;

public class AlgorithmResult {

    private String algorithmName;
    private double fitness;
    private int hardViolations;
    private int softViolations;
    private int iterations;
    private long runtime;

    private ArrayList<Double> fitnessHistory;

    public AlgorithmResult(
            String algorithmName,
            double fitness,
            int hardViolations,
            int softViolations,
            int iterations,
            long runtime,
            ArrayList<Double> fitnessHistory) {

        this.algorithmName = algorithmName;
        this.fitness = fitness;
        this.hardViolations = hardViolations;
        this.softViolations = softViolations;
        this.iterations = iterations;
        this.runtime = runtime;
        this.fitnessHistory = fitnessHistory;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public double getFitness() {
        return fitness;
    }

    public int getHardViolations() {
        return hardViolations;
    }

    public int getSoftViolations() {
        return softViolations;
    }

    public int getIterations() {
        return iterations;
    }

    public long getRuntime() {
        return runtime;
    }

    public ArrayList<Double> getFitnessHistory() {
        return fitnessHistory;
    }
}