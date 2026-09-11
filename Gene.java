package application;

public class Gene {

	private Course course;
	private Room room;
	private LectureTime lectureTime;

	public Gene(Course course, Room room, LectureTime lectureTime) {
		this.course = course;
		this.room = room;
		this.lectureTime = lectureTime;
	}

	public Course getCourse() {
		return course;
	}

	public Room getRoom() {
		return room;
	}

	public LectureTime getLectureTime() {
		return lectureTime;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setLectureTime(LectureTime lectureTime) {
		this.lectureTime = lectureTime;
	}

	@Override
	public String toString() {
		return course.getName() + " | " + lectureTime + " | " + room.getRoomId();
	}
}