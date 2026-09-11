package application;

import java.util.Random;

public class TournamentSelection {

	private Random random;

	public TournamentSelection() {
		random = new Random();
	}

	public Chromosome selectParent(Population population, int tournamentSize) {

		Chromosome best = null;

		for (int i = 0; i < tournamentSize; i++) {

			int randomIndex = random.nextInt(population.size());

			Chromosome candidate = population.getChromosomes().get(randomIndex);

			if (best == null || candidate.getFitness() > best.getFitness()) {

				best = candidate;
			}
		}

		return best;
	}
}