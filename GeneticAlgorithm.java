package application;

import java.util.ArrayList;
import java.util.Comparator;

public class GeneticAlgorithm {

	public enum SelectionMethod {
		TOURNAMENT, ROULETTE_WHEEL
	}

	public enum CrossoverMethod {
		ONE_POINT, TWO_POINT
	}

	public enum MutationMethod {
		RANDOM, SWAP
	}

	private UniversityData data;

	private int populationSize;
	private int maxGenerations;
	private int tournamentSize;
	private int elitismCount;
	private int noImprovementLimit;

	private double crossoverRate;
	private double mutationRate;

	private int generationsUsed;

	private SelectionMethod selectionMethod;
	private CrossoverMethod crossoverMethod;
	private MutationMethod mutationMethod;

	private ArrayList<Double> fitnessHistory;

	private PopulationGenerator populationGenerator;
	private FitnessCalculator fitnessCalculator;

	private TournamentSelection tournamentSelection;
	private RouletteWheelSelection rouletteSelection;

	private Crossover twoPointCrossover;
	private OnePointCrossover onePointCrossover;

	private Mutation randomMutation;
	private SwapMutation swapMutation;

	public GeneticAlgorithm(UniversityData data, int populationSize, int maxGenerations, double crossoverRate,
			double mutationRate, int elitismCount) {

		this(data, populationSize, maxGenerations, crossoverRate, mutationRate, elitismCount,
				SelectionMethod.TOURNAMENT, CrossoverMethod.TWO_POINT, MutationMethod.RANDOM);
	}

	public GeneticAlgorithm(UniversityData data, int populationSize, int maxGenerations, double crossoverRate,
			double mutationRate, int elitismCount, SelectionMethod selectionMethod, CrossoverMethod crossoverMethod,
			MutationMethod mutationMethod) {

		this.data = data;
		this.populationSize = populationSize;
		this.maxGenerations = maxGenerations;
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;
		this.elitismCount = elitismCount;

		this.selectionMethod = selectionMethod;
		this.crossoverMethod = crossoverMethod;
		this.mutationMethod = mutationMethod;

		tournamentSize = 4;
		noImprovementLimit = 50;

		fitnessHistory = new ArrayList<>();

		populationGenerator = new PopulationGenerator(data);

		fitnessCalculator = new FitnessCalculator();

		tournamentSelection = new TournamentSelection();

		rouletteSelection = new RouletteWheelSelection();

		twoPointCrossover = new Crossover();

		onePointCrossover = new OnePointCrossover();

		randomMutation = new Mutation(data);

		swapMutation = new SwapMutation();
	}

	public Chromosome run() {

		fitnessHistory.clear();

		Population population = populationGenerator.generatePopulation(populationSize);

		fitnessCalculator.evaluatePopulation(population);

		Chromosome bestEver = population.getBestChromosome();

		fitnessHistory.add(bestEver.getFitness());

		int noImprovementCount = 0;
		generationsUsed = 0;

		for (int generation = 1; generation <= maxGenerations; generation++) {

			Population newPopulation = new Population();

			population.getChromosomes().sort(Comparator.comparingDouble(Chromosome::getFitness).reversed());

			// Elitism
			for (int i = 0; i < elitismCount && i < population.size(); i++) {

				Chromosome elite = copyChromosome(population.getChromosomes().get(i));

				newPopulation.addChromosome(elite);
			}

			while (newPopulation.size() < populationSize) {

				Chromosome parent1 = selectParent(population);

				Chromosome parent2 = selectParent(population);

				Chromosome[] children = performCrossover(parent1, parent2);

				performMutation(children[0]);
				performMutation(children[1]);

				newPopulation.addChromosome(children[0]);

				if (newPopulation.size() < populationSize) {

					newPopulation.addChromosome(children[1]);
				}
			}

			fitnessCalculator.evaluatePopulation(newPopulation);

			population = newPopulation;

			Chromosome currentBest = population.getBestChromosome();

			fitnessHistory.add(currentBest.getFitness());

			generationsUsed = generation;

			if (currentBest.getFitness() > bestEver.getFitness()) {

				bestEver = currentBest;
				noImprovementCount = 0;

			} else {

				noImprovementCount++;
			}

			if (bestEver.getHardViolations() == 0 && bestEver.getSoftViolations() <= 4) {

				break;
			}

			if (noImprovementCount >= noImprovementLimit) {

				break;
			}
		}

		return bestEver;
	}

	private Chromosome selectParent(Population population) {

		if (selectionMethod == SelectionMethod.ROULETTE_WHEEL) {

			return rouletteSelection.selectParent(population);
		}

		return tournamentSelection.selectParent(population, tournamentSize);
	}

	private Chromosome[] performCrossover(Chromosome parent1, Chromosome parent2) {

		if (crossoverMethod == CrossoverMethod.ONE_POINT) {

			return onePointCrossover.crossover(parent1, parent2, crossoverRate);
		}

		return twoPointCrossover.crossover(parent1, parent2, crossoverRate);
	}

	private void performMutation(Chromosome chromosome) {

		if (mutationMethod == MutationMethod.SWAP) {

			swapMutation.mutate(chromosome, mutationRate);

		} else {

			randomMutation.mutate(chromosome, mutationRate);
		}
	}

	private Chromosome copyChromosome(Chromosome original) {

		Chromosome copy = new Chromosome();

		for (Gene gene : original.getGenes()) {

			Gene copiedGene = new Gene(gene.getCourse(), gene.getRoom(), gene.getLectureTime());

			copy.addGene(copiedGene);
		}

		return copy;
	}

	public ArrayList<Double> getFitnessHistory() {
		return fitnessHistory;
	}

	public int getGenerationsUsed() {
		return generationsUsed;
	}

	public SelectionMethod getSelectionMethod() {
		return selectionMethod;
	}

	public CrossoverMethod getCrossoverMethod() {
		return crossoverMethod;
	}

	public MutationMethod getMutationMethod() {
		return mutationMethod;
	}
}