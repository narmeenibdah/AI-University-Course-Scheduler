package application;

import java.util.Random;

public class OnePointCrossover {

	private Random random;

	public OnePointCrossover() {
		random = new Random();
	}

	public Chromosome[] crossover(Chromosome parent1, Chromosome parent2, double crossoverRate) {

		Chromosome child1 = new Chromosome();

		Chromosome child2 = new Chromosome();

		int chromosomeSize = parent1.getGenes().size();

		if (chromosomeSize < 2 || random.nextDouble() > crossoverRate) {

			copyChromosome(parent1, child1);
			copyChromosome(parent2, child2);

			return new Chromosome[] { child1, child2 };
		}

		int crossoverPoint = 1 + random.nextInt(chromosomeSize - 1);

		for (int i = 0; i < chromosomeSize; i++) {

			Gene parent1Gene = parent1.getGenes().get(i);

			Gene parent2Gene = parent2.getGenes().get(i);

			if (i < crossoverPoint) {

				child1.addGene(copyGene(parent1Gene));

				child2.addGene(copyGene(parent2Gene));

			} else {

				child1.addGene(copyGene(parent2Gene));

				child2.addGene(copyGene(parent1Gene));
			}
		}

		return new Chromosome[] { child1, child2 };
	}

	private void copyChromosome(Chromosome source, Chromosome destination) {

		for (Gene gene : source.getGenes()) {

			destination.addGene(copyGene(gene));
		}
	}

	private Gene copyGene(Gene gene) {

		return new Gene(gene.getCourse(), gene.getRoom(), gene.getLectureTime());
	}
}