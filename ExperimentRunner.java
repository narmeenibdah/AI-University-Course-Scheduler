package application;

import java.util.ArrayList;

public class ExperimentRunner {

	public ArrayList<ExperimentResult> runExperiments() {

		ArrayList<ExperimentResult> results = new ArrayList<>();

		// Population size experiments

		results.add(runSingleExperiment(10, 20, 0.05, 0.80, 500));

		results.add(runSingleExperiment(10, 50, 0.05, 0.80, 500));

		results.add(runSingleExperiment(10, 100, 0.05, 0.80, 500));

		results.add(runSingleExperiment(10, 200, 0.05, 0.80, 500));

		// Mutation rate experiments

		results.add(runSingleExperiment(10, 100, 0.01, 0.80, 500));

		results.add(runSingleExperiment(10, 100, 0.10, 0.80, 500));

		results.add(runSingleExperiment(10, 100, 0.20, 0.80, 500));

		// Crossover rate experiments

		results.add(runSingleExperiment(10, 100, 0.05, 0.60, 500));

		results.add(runSingleExperiment(10, 100, 0.05, 0.90, 500));

		// Dataset size experiments

		results.add(runSingleExperiment(20, 100, 0.05, 0.80, 500));

		results.add(runSingleExperiment(30, 100, 0.05, 0.80, 500));

		results.add(runSingleExperiment(40, 100, 0.05, 0.80, 500));

		results.add(runSingleExperiment(50, 100, 0.05, 0.80, 500));

		return results;
	}

	private ExperimentResult runSingleExperiment(int courseCount, int populationSize, double mutationRate,
			double crossoverRate, int maxGenerations) {

		UniversityData data = new UniversityData(courseCount);

		int elitismCount = Math.max(1, (int) (populationSize * 0.05));

		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(data, populationSize, maxGenerations, crossoverRate,
				mutationRate, elitismCount);

		long startTime = System.currentTimeMillis();

		Chromosome best = geneticAlgorithm.run();

		long endTime = System.currentTimeMillis();

		return new ExperimentResult(courseCount, populationSize, mutationRate, crossoverRate,
				geneticAlgorithm.getGenerationsUsed(), best.getFitness(), best.getHardViolations(),
				best.getSoftViolations(), endTime - startTime);
	}
}