package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FitnessCalculator {

	public void evaluatePopulation(Population population) {

		for (Chromosome chromosome : population.getChromosomes()) {
			evaluateChromosome(chromosome);
		}
	}

	public void evaluateChromosome(Chromosome chromosome) {

		int hardViolations = calculateHardViolations(chromosome);
		int softViolations = calculateSoftViolations(chromosome);

		chromosome.updateFitness(hardViolations, softViolations);
	}

	private int calculateHardViolations(Chromosome chromosome) {

		int violations = 0;
		ArrayList<Gene> genes = chromosome.getGenes();


		for (Gene gene : genes) {

			Course course = gene.getCourse();
			Room room = gene.getRoom();
			LectureTime lectureTime = gene.getLectureTime();

			if (course.getNumberOfStudents() > room.getCapacity()) {
				violations++;
			}

			if (!course.getType().equalsIgnoreCase(room.getType())) {
				violations++;
			}

			if (!course.getInstructor().isAvailable(lectureTime)) {
				violations++;
			}
		}

		// مقارنة كل مادتين مع بعض
		for (int i = 0; i < genes.size(); i++) {

			for (int j = i + 1; j < genes.size(); j++) {

				Gene first = genes.get(i);
				Gene second = genes.get(j);

				if (first.getLectureTime().equals(second.getLectureTime())) {

					// H1: Instructor conflict
					if (first.getCourse().getInstructor().getInstructorId()
							.equals(second.getCourse().getInstructor().getInstructorId())) {

						violations++;
					}

					// H2: Room conflict
					if (first.getRoom().getRoomId().equals(second.getRoom().getRoomId())) {

						violations++;
					}

					// H3: Student group conflict
					if (first.getCourse().getStudentGroup().getGroupId()
							.equals(second.getCourse().getStudentGroup().getGroupId())) {

						violations++;
					}
				}
			}
		}

		return violations;
	}

	private int calculateSoftViolations(Chromosome chromosome) {

		int violations = 0;

		// S1: Avoid early classes
		// S2: Avoid late classes

		for (Gene gene : chromosome.getGenes()) {

			String time = gene.getLectureTime().getTime();

			if (time.equals("08:00")) {
				violations++;
			}

			if (time.equals("11:00")) {
				violations++;
			}
		}

		// S3: Minimize student gaps
		violations += calculateStudentGaps(chromosome);

		return violations;
	}

	private int calculateStudentGaps(Chromosome chromosome) {

		int gaps = 0;

		Map<String, ArrayList<Integer>> groupSchedules = new HashMap<>();

		for (Gene gene : chromosome.getGenes()) {

			String groupId = gene.getCourse().getStudentGroup().getGroupId();

			String day = gene.getLectureTime().getDay();

			String key = groupId + "-" + day;

			int hour = Integer.parseInt(gene.getLectureTime().getTime().substring(0, 2));

			groupSchedules.computeIfAbsent(key, value -> new ArrayList<>()).add(hour);
		}

		for (ArrayList<Integer> hours : groupSchedules.values()) {

			Collections.sort(hours);

			for (int i = 0; i < hours.size() - 1; i++) {

				int difference = hours.get(i + 1) - hours.get(i);

				if (difference > 1) {
					gaps += difference - 1;
				}
			}
		}

		return gaps;
	}
}