package application;

import java.util.Random;

public class Crossover {

	private Random random;

	public Crossover() {
		random = new Random();
	}

	public Chromosome[] crossover(Chromosome parent1, Chromosome parent2, double crossoverRate) {

		Chromosome child1 = new Chromosome();
		Chromosome child2 = new Chromosome();

		int chromosomeSize = parent1.getGenes().size();

		if (random.nextDouble() > crossoverRate) {

			copyChromosome(parent1, child1);
			copyChromosome(parent2, child2);

			return new Chromosome[] { child1, child2 };
		}

		int point1 = random.nextInt(chromosomeSize);
		int point2 = random.nextInt(chromosomeSize);

		while (point1 == point2) {
			point2 = random.nextInt(chromosomeSize);
		}

		int startPoint = Math.min(point1, point2);
		int endPoint = Math.max(point1, point2);

		for (int i = 0; i < chromosomeSize; i++) {

			Gene parent1Gene = parent1.getGenes().get(i);
			Gene parent2Gene = parent2.getGenes().get(i);

			if (i >= startPoint && i <= endPoint) {

				child1.addGene(copyGene(parent2Gene));
				child2.addGene(copyGene(parent1Gene));

			} else {

				child1.addGene(copyGene(parent1Gene));
				child2.addGene(copyGene(parent2Gene));
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