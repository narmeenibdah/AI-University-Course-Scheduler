package application;

import java.util.Random;

public class Mutation {

	private UniversityData data;
	private Random random;

	public Mutation(UniversityData data) {
		this.data = data;
		random = new Random();
	}

	public void mutate(Chromosome chromosome, double mutationRate) {

		for (Gene gene : chromosome.getGenes()) {

			if (random.nextDouble() < mutationRate) {

				int mutationType = random.nextInt(3);

				if (mutationType == 0) {

					gene.setRoom(getRandomRoom());

				} else if (mutationType == 1) {

					gene.setLectureTime(getRandomTime());

				} else {

					gene.setRoom(getRandomRoom());
					gene.setLectureTime(getRandomTime());
				}
			}
		}
	}

	private Room getRandomRoom() {

		int index = random.nextInt(data.getRooms().size());

		return data.getRooms().get(index);
	}

	private LectureTime getRandomTime() {

		int index = random.nextInt(data.getLectureTimes().size());

		return data.getLectureTimes().get(index);
	}
}