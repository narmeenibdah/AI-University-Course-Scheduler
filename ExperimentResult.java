package application;

public class ExperimentResult {

	private int courseCount;
	private int populationSize;

	private double mutationRate;
	private double crossoverRate;

	private int generations;
	private double bestFitness;

	private int hardViolations;
	private int softViolations;

	private long runtime;

	public ExperimentResult(int courseCount, int populationSize, double mutationRate, double crossoverRate,
			int generations, double bestFitness, int hardViolations, int softViolations, long runtime) {

		this.courseCount = courseCount;
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
		this.generations = generations;
		this.bestFitness = bestFitness;
		this.hardViolations = hardViolations;
		this.softViolations = softViolations;
		this.runtime = runtime;
	}

	public int getCourseCount() {
		return courseCount;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public double getMutationRate() {
		return mutationRate;
	}

	public double getCrossoverRate() {
		return crossoverRate;
	}

	public int getGenerations() {
		return generations;
	}

	public double getBestFitness() {
		return bestFitness;
	}

	public int getHardViolations() {
		return hardViolations;
	}

	public int getSoftViolations() {
		return softViolations;
	}

	public long getRuntime() {
		return runtime;
	}
}