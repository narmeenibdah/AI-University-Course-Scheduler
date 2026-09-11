package application;

import java.util.ArrayList;

public class Population {

	private ArrayList<Chromosome> chromosomes;

	public Population() {
		chromosomes = new ArrayList<>();
	}

	public void addChromosome(Chromosome chromosome) {
		chromosomes.add(chromosome);
	}

	public ArrayList<Chromosome> getChromosomes() {
		return chromosomes;
	}

	public int size() {
		return chromosomes.size();
	}

	public Chromosome getBestChromosome() {

		Chromosome best = chromosomes.get(0);

		for (Chromosome chromosome : chromosomes) {

			if (chromosome.getFitness() > best.getFitness()) {
				best = chromosome;
			}
		}

		return best;
	}
}