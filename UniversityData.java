package application;

import java.util.ArrayList;

public class UniversityData {

    private ArrayList<Course> courses;
    private ArrayList<Instructor> instructors;
    private ArrayList<Room> rooms;
    private ArrayList<LectureTime> lectureTimes;
    private ArrayList<StudentGroup> studentGroups;

    public UniversityData() {
        this(10);
    }

    public UniversityData(int courseCount) {

        if (courseCount < 1 || courseCount > 50) {
            throw new IllegalArgumentException(
                    "Course count must be between 1 and 50"
            );
        }

        courses = new ArrayList<>();
        instructors = new ArrayList<>();
        rooms = new ArrayList<>();
        lectureTimes = new ArrayList<>();
        studentGroups = new ArrayList<>();

        createLectureTimes();
        createRooms();
        createInstructors();
        createStudentGroups();
        createCourses();
        resizeDataset(courseCount);
    }

    private void createLectureTimes() {

        String[] days = {
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday"
        };

        String[] times = {
                "08:00",
                "09:00",
                "10:00",
                "11:00"
        };

        for (String day : days) {
            for (String time : times) {
                lectureTimes.add(new LectureTime(day, time));
            }
        }
    }

    private void createRooms() {

        rooms.add(new Room("R101", 50, "Lecture"));
        rooms.add(new Room("R102", 40, "Lecture"));
        rooms.add(new Room("R103", 35, "Lecture"));
        rooms.add(new Room("R104", 25, "Lecture"));

        rooms.add(new Room("LAB1", 30, "Lab"));
        rooms.add(new Room("LAB2", 25, "Lab"));
    }

    private void createInstructors() {

        Instructor i01 = new Instructor("I01", "Dr. Ahmad");
        Instructor i02 = new Instructor("I02", "Dr. Sara");
        Instructor i03 = new Instructor("I03", "Dr. Omar");
        Instructor i04 = new Instructor("I04", "Dr. Lina");
        Instructor i05 = new Instructor("I05", "Dr. Ali");
        Instructor i06 = new Instructor("I06", "Dr. Noor");
        Instructor i07 = new Instructor("I07", "Dr. Huda");
        Instructor i08 = new Instructor("I08", "Dr. Khaled");
        Instructor i09 = new Instructor("I09", "Dr. Rami");
        Instructor i10 = new Instructor("I10", "Dr. Maya");

        i01.addUnavailableTime(
                new LectureTime("Monday", "10:00")
        );

        i02.addUnavailableTime(
                new LectureTime("Sunday", "08:00")
        );

        i03.addUnavailableTime(
                new LectureTime("Wednesday", "11:00")
        );

        i04.addUnavailableTime(
                new LectureTime("Thursday", "08:00")
        );

        i05.addUnavailableTime(
                new LectureTime("Tuesday", "10:00")
        );

        i06.addUnavailableTime(
                new LectureTime("Tuesday", "11:00")
        );

        i07.addUnavailableTime(
                new LectureTime("Monday", "08:00")
        );

        i08.addUnavailableTime(
                new LectureTime("Wednesday", "09:00")
        );

        i09.addUnavailableTime(
                new LectureTime("Sunday", "11:00")
        );

        i10.addUnavailableTime(
                new LectureTime("Thursday", "10:00")
        );

        instructors.add(i01);
        instructors.add(i02);
        instructors.add(i03);
        instructors.add(i04);
        instructors.add(i05);
        instructors.add(i06);
        instructors.add(i07);
        instructors.add(i08);
        instructors.add(i09);
        instructors.add(i10);
    }

    private void createStudentGroups() {

        studentGroups.add(
                new StudentGroup("G1", "First Year")
        );

        studentGroups.add(
                new StudentGroup("G2", "Second Year")
        );

        studentGroups.add(
                new StudentGroup("G3", "Third Year")
        );

        studentGroups.add(
                new StudentGroup("G4", "Fourth Year")
        );

        studentGroups.add(
                new StudentGroup("G5", "Lab Students")
        );
    }

    private void createCourses() {

        StudentGroup g1 = studentGroups.get(0);
        StudentGroup g2 = studentGroups.get(1);
        StudentGroup g3 = studentGroups.get(2);
        StudentGroup g4 = studentGroups.get(3);
        StudentGroup g5 = studentGroups.get(4);

        courses.add(new Course(
                "C01",
                "Artificial Intelligence",
                35,
                instructors.get(0),
                "Lecture",
                g3
        ));

        courses.add(new Course(
                "C02",
                "Database",
                40,
                instructors.get(1),
                "Lecture",
                g3
        ));

        courses.add(new Course(
                "C03",
                "Networks",
                30,
                instructors.get(2),
                "Lecture",
                g3
        ));

        courses.add(new Course(
                "C04",
                "Operating Systems",
                35,
                instructors.get(0),
                "Lecture",
                g3
        ));

        courses.add(new Course(
                "C05",
                "Software Engineering",
                45,
                instructors.get(3),
                "Lecture",
                g4
        ));

        courses.add(new Course(
                "C06",
                "Algorithms",
                40,
                instructors.get(4),
                "Lecture",
                g2
        ));

        courses.add(new Course(
                "C07",
                "Data Mining",
                25,
                instructors.get(5),
                "Lecture",
                g4
        ));

        courses.add(new Course(
                "C08",
                "Computer Graphics",
                30,
                instructors.get(6),
                "Lab",
                g5
        ));

        courses.add(new Course(
                "C09",
                "Web Programming",
                25,
                instructors.get(7),
                "Lab",
                g5
        ));

        courses.add(new Course(
                "C10",
                "Machine Learning",
                35,
                instructors.get(5),
                "Lecture",
                g4
        ));

        // لإزالة تحذير أن بعض المتغيرات غير مستخدمة
        if (g1 == null || g2 == null) {
            throw new IllegalStateException(
                    "Student groups were not created"
            );
        }
    }

    private void resizeDataset(int courseCount) {

        String[] additionalCourseNames = {
                "Discrete Mathematics",
                "Data Structures",
                "Computer Architecture",
                "Programming Languages",
                "Mobile Application Development",
                "Cloud Computing",
                "Cyber Security",
                "Information Retrieval",
                "Human Computer Interaction",
                "Advanced Databases",
                "Distributed Systems",
                "Parallel Computing",
                "Web Security",
                "Software Testing",
                "Requirements Engineering",
                "Project Management",
                "Network Security",
                "Digital Logic",
                "Embedded Systems",
                "Internet of Things",
                "Big Data Analytics",
                "Natural Language Processing",
                "Deep Learning",
                "Computer Vision",
                "Robotics",
                "Expert Systems",
                "Game Development",
                "Multimedia Systems",
                "E-Commerce",
                "Data Warehousing",
                "Bioinformatics",
                "Blockchain Technology",
                "DevOps",
                "Quantum Computing",
                "Compiler Design",
                "Theory of Computation",
                "Numerical Methods",
                "Linear Algebra",
                "Probability and Statistics",
                "Graduation Project"
        };

        while (courses.size() > courseCount) {
            courses.remove(courses.size() - 1);
        }

        while (courses.size() < courseCount) {

            int courseNumber = courses.size() + 1;

            String courseId =
                    String.format("C%02d", courseNumber);

            String courseName =
                    additionalCourseNames[courseNumber - 11];

            Instructor instructor =
                    instructors.get(
                            (courseNumber - 1)
                            % instructors.size()
                    );

            StudentGroup group =
                    studentGroups.get(
                            (courseNumber - 1)
                            % studentGroups.size()
                    );

            String type;
            int numberOfStudents;

            if (courseNumber % 5 == 0) {
                type = "Lab";
                numberOfStudents =
                        courseNumber % 10 == 0 ? 25 : 30;
            } else {
                type = "Lecture";
                numberOfStudents =
                        25 + ((courseNumber % 6) * 5);
            }

            Course course = new Course(
                    courseId,
                    courseName,
                    numberOfStudents,
                    instructor,
                    type,
                    group
            );

            courses.add(course);
        }
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<LectureTime> getLectureTimes() {
        return lectureTimes;
    }

    public ArrayList<StudentGroup> getStudentGroups() {
        return studentGroups;
    }
}