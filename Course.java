package application;

public class Course {

	private String courseId;
	private String name;
	private int numberOfStudents;
	private Instructor instructor;
	private String type;
	private StudentGroup studentGroup;

	public Course(String courseId, String name, int numberOfStudents, Instructor instructor, String type,
			StudentGroup studentGroup) {

		this.courseId = courseId;
		this.name = name;
		this.numberOfStudents = numberOfStudents;
		this.instructor = instructor;
		this.type = type;
		this.studentGroup = studentGroup;
	}

	public String getId() {
		return courseId;
	}

	public String getName() {
		return name;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public String getType() {
		return type;
	}

	public StudentGroup getStudentGroup() {
		return studentGroup;
	}

	@Override
	public String toString() {
		return courseId + " - " + name;
	}
}