package application;

import java.util.ArrayList;
import java.util.Random;

public class HillClimbing {

	private UniversityData data;
	private FitnessCalculator fitnessCalculator;
	private Random random;

	private int maximumIterations;
	private int iterationsUsed;

	private ArrayList<Double> fitnessHistory;

	public HillClimbing(UniversityData data, int maximumIterations) {

		this.data = data;
		this.maximumIterations = maximumIterations;

		fitnessCalculator = new FitnessCalculator();

		random = new Random();

		fitnessHistory = new ArrayList<>();
	}

	public Chromosome run() {

		PopulationGenerator generator = new PopulationGenerator(data);

		Population initialPopulation = generator.generatePopulation(1);

		Chromosome current = initialPopulation.getChromosomes().get(0);

		fitnessCalculator.evaluateChromosome(current);

		fitnessHistory.clear();
		fitnessHistory.add(current.getFitness());

		int noImprovementCount = 0;

		for (int iteration = 1; iteration <= maximumIterations; iteration++) {

			Chromosome neighbor = createNeighbor(current);

			fitnessCalculator.evaluateChromosome(neighbor);

			if (neighbor.getFitness() > current.getFitness()) {

				current = neighbor;
				noImprovementCount = 0;

			} else {

				noImprovementCount++;
			}

			fitnessHistory.add(current.getFitness());

			iterationsUsed = iteration;

			if (current.getHardViolations() == 0 && current.getSoftViolations() <= 4) {

				break;
			}

			if (noImprovementCount >= 100) {
				break;
			}
		}

		return current;
	}

	private Chromosome createNeighbor(Chromosome current) {

		Chromosome neighbor = copyChromosome(current);

		int randomGeneIndex = random.nextInt(neighbor.getGenes().size());

		Gene selectedGene = neighbor.getGenes().get(randomGeneIndex);

		if (random.nextBoolean()) {

			Room newRoom;

			do {

				int roomIndex = random.nextInt(data.getRooms().size());

				newRoom = data.getRooms().get(roomIndex);

			} while (newRoom == selectedGene.getRoom());

			selectedGene.setRoom(newRoom);

		} else {

			LectureTime newTime;

			do {

				int timeIndex = random.nextInt(data.getLectureTimes().size());

				newTime = data.getLectureTimes().get(timeIndex);

			} while (newTime.equals(selectedGene.getLectureTime()));

			selectedGene.setLectureTime(newTime);
		}

		return neighbor;
	}

	private Chromosome copyChromosome(Chromosome original) {

		Chromosome copy = new Chromosome();

		for (Gene gene : original.getGenes()) {

			Gene copiedGene = new Gene(gene.getCourse(), gene.getRoom(), gene.getLectureTime());

			copy.addGene(copiedGene);
		}

		return copy;
	}

	public int getIterationsUsed() {
		return iterationsUsed;
	}

	public ArrayList<Double> getFitnessHistory() {
		return fitnessHistory;
	}
}