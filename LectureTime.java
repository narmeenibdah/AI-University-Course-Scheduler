package application;

import java.util.Objects;

public class LectureTime {

	private String day;
	private String time;

	public LectureTime(String day, String time) {
		this.day = day;
		this.time = time;
	}

	public String getDay() {
		return day;
	}

	public String getTime() {
		return time;
	}

	@Override
	public boolean equals(Object object) {

		if (this == object) {
			return true;
		}

		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		LectureTime other = (LectureTime) object;

		return day.equals(other.day) && time.equals(other.time);
	}

	@Override
	public int hashCode() {
		return Objects.hash(day, time);
	}

	@Override
	public String toString() {
		return day + " " + time;
	}
}