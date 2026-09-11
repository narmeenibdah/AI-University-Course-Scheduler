package application;

import java.util.Random;

public class SwapMutation {

	private Random random;

	public SwapMutation() {
		random = new Random();
	}

	public void mutate(Chromosome chromosome, double mutationRate) {

		int chromosomeSize = chromosome.getGenes().size();

		if (chromosomeSize < 2) {
			return;
		}

		for (int i = 0; i < chromosomeSize; i++) {

			if (random.nextDouble() < mutationRate) {

				int secondIndex;

				do {

					secondIndex = random.nextInt(chromosomeSize);

				} while (secondIndex == i);

				Gene firstGene = chromosome.getGenes().get(i);

				Gene secondGene = chromosome.getGenes().get(secondIndex);

				Room firstRoom = firstGene.getRoom();

				LectureTime firstTime = firstGene.getLectureTime();

				firstGene.setRoom(secondGene.getRoom());

				firstGene.setLectureTime(secondGene.getLectureTime());

				secondGene.setRoom(firstRoom);

				secondGene.setLectureTime(firstTime);
			}
		}
	}
}