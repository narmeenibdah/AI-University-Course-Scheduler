package application;

import java.util.Random;

public class PopulationGenerator {

	private UniversityData data;
	private Random random;

	public PopulationGenerator(UniversityData data) {
		this.data = data;
		random = new Random();
	}

	public Population generatePopulation(int populationSize) {

		Population population = new Population();

		for (int i = 0; i < populationSize; i++) {

			Chromosome chromosome = generateChromosome();
			population.addChromosome(chromosome);
		}

		return population;
	}

	private Chromosome generateChromosome() {

		Chromosome chromosome = new Chromosome();

		for (Course course : data.getCourses()) {

			Room randomRoom = data.getRooms().get(random.nextInt(data.getRooms().size()));

			LectureTime randomTime = data.getLectureTimes().get(random.nextInt(data.getLectureTimes().size()));

			Gene gene = new Gene(course, randomRoom, randomTime);

			chromosome.addGene(gene);
		}

		return chromosome;
	}
}