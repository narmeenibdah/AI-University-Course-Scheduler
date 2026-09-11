package application;

import java.util.ArrayList;

public class Chromosome {

	private ArrayList<Gene> genes;

	private int hardViolations;
	private int softViolations;
	private int totalPenalty;
	private double fitness;

	public Chromosome() {
		genes = new ArrayList<>();
	}

	public void addGene(Gene gene) {
		genes.add(gene);
	}

	public ArrayList<Gene> getGenes() {
		return genes;
	}

	public int getHardViolations() {
		return hardViolations;
	}

	public int getSoftViolations() {
		return softViolations;
	}

	public int getTotalPenalty() {
		return totalPenalty;
	}

	public double getFitness() {
		return fitness;
	}

	public void updateFitness(int hardViolations, int softViolations) {

		this.hardViolations = hardViolations;
		this.softViolations = softViolations;

		totalPenalty = (100 * hardViolations) + (10 * softViolations);

		fitness = 1.0 / (1 + totalPenalty);
	}

	@Override
	public String toString() {

		String result = "";

		for (Gene gene : genes) {
			result += gene + "\n";
		}

		result += "Hard violations: " + hardViolations + "\n";
		result += "Soft violations: " + softViolations + "\n";
		result += "Penalty: " + totalPenalty + "\n";
		result += "Fitness: " + fitness;

		return result;
	}
}