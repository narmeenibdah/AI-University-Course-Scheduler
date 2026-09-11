package application;

import java.util.Random;

public class RouletteWheelSelection {

	private Random random;

	public RouletteWheelSelection() {
		random = new Random();
	}

	public Chromosome selectParent(Population population) {

		double totalFitness = 0;

		for (Chromosome chromosome : population.getChromosomes()) {

			totalFitness += chromosome.getFitness();
		}

		double randomValue = random.nextDouble() * totalFitness;

		double currentTotal = 0;

		for (Chromosome chromosome : population.getChromosomes()) {

			currentTotal += chromosome.getFitness();

			if (currentTotal >= randomValue) {
				return chromosome;
			}
		}

		return population.getChromosomes().get(population.size() - 1);
	}
}