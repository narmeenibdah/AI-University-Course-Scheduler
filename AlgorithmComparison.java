package application;

import java.util.ArrayList;

public class AlgorithmComparison {

	public ArrayList<AlgorithmResult> compare(int courseCount, int populationSize, int maximumIterations,
			double crossoverRate, double mutationRate) {

		ArrayList<AlgorithmResult> results = new ArrayList<>();

		UniversityData geneticData = new UniversityData(courseCount);

		int elitismCount = Math.max(1, (int) (populationSize * 0.05));

		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(geneticData, populationSize, maximumIterations,
				crossoverRate, mutationRate, elitismCount);

		long geneticStart = System.currentTimeMillis();

		Chromosome geneticResult = geneticAlgorithm.run();

		long geneticEnd = System.currentTimeMillis();

		results.add(
				new AlgorithmResult("Genetic Algorithm", geneticResult.getFitness(), geneticResult.getHardViolations(),
						geneticResult.getSoftViolations(), geneticAlgorithm.getGenerationsUsed(),
						geneticEnd - geneticStart, new ArrayList<>(geneticAlgorithm.getFitnessHistory())));

		UniversityData hillData = new UniversityData(courseCount);

		HillClimbing hillClimbing = new HillClimbing(hillData, maximumIterations);

		long hillStart = System.currentTimeMillis();

		Chromosome hillResult = hillClimbing.run();

		long hillEnd = System.currentTimeMillis();

		results.add(new AlgorithmResult("Hill Climbing", hillResult.getFitness(), hillResult.getHardViolations(),
				hillResult.getSoftViolations(), hillClimbing.getIterationsUsed(), hillEnd - hillStart,
				new ArrayList<>(hillClimbing.getFitnessHistory())));

		return results;
	}
}