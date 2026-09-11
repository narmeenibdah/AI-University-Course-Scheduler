package application;

import java.util.ArrayList;

public class Instructor {

	private String instructorId;
	private String name;
	private ArrayList<LectureTime> unavailableTimes;

	public Instructor(String instructorId, String name) {
		this.instructorId = instructorId;
		this.name = name;
		this.unavailableTimes = new ArrayList<>();
	}

	public String getInstructorId() {
		return instructorId;
	}

	public String getName() {
		return name;
	}

	public ArrayList<LectureTime> getUnavailableTimes() {
		return unavailableTimes;
	}

	public void addUnavailableTime(LectureTime lectureTime) {
		unavailableTimes.add(lectureTime);
	}

	public boolean isAvailable(LectureTime lectureTime) {
		return !unavailableTimes.contains(lectureTime);
	}

	@Override
	public String toString() {
		return instructorId + " - " + name;
	}
}