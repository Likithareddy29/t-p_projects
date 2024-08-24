public class studentgradingsystem {

    static class Student {
        private String studentID;
        private String name;
        private String major;

        public Student(String studentID, String name, String major) {
            this.studentID = studentID;
            this.name = name;
            this.major = major;
        }

        public String getStudentID() {
            return studentID;
        }

        public String getName() {
            return name;
        }

        public String getMajor() {
            return major;
        }

        public void display() {
            System.out.println("Student ID: " + studentID + ", Name: " + name + ", Major: " + major);
        }

        public double calculateGrade(double... grades) {
            return 0.0; // This method will be overridden in derived classes
        }
    }

    static class UndergraduateStudent extends Student {
        public UndergraduateStudent(String studentID, String name, String major) {
            super(studentID, name, major);
        }

        @Override
        public double calculateGrade(double... grades) {
            double total = 0.0;
            for (double grade : grades) {
                total += grade;
            }
            return total / grades.length; // Simple average for undergraduate students
        }

        @Override
        public void display() {
            super.display();
            System.out.println("Level: Undergraduate");
        }
    }

    static class GraduateStudent extends Student {
        public GraduateStudent(String studentID, String name, String major) {
            super(studentID, name, major);
        }

        @Override
        public double calculateGrade(double... grades) {
            double weightedSum = 0.0;
            for (int i = 0; i < grades.length; i++) {
                // Assuming a simple weight system: first grade has more weight
                weightedSum += grades[i] * (i + 1);
            }
            return weightedSum / ((grades.length * (grades.length + 1)) / 2);
        }

        @Override
        public void display() {
            super.display();
            System.out.println("Level: Graduate");
        }
    }

    static class Course {
        private String courseCode;
        private String courseName;
        private double[] grades;

        public Course(String courseCode, String courseName, double[] grades) {
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.grades = grades;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getCourseName() {
            return courseName;
        }

        public double[] getGrades() {
            return grades;
        }

        public void display() {
            System.out.println("Course Code: " + courseCode + ", Course Name: " + courseName);
        }
    }

    static class Grade {
        private Student student;
        private Course course;
        private double finalGrade;

        public Grade(Student student, Course course) {
            this.student = student;
            this.course = course;
            this.finalGrade = student.calculateGrade(course.getGrades());
        }

        public void display() {
            student.display();
            course.display();
            System.out.println("Final Grade: " + finalGrade);
        }
    }

    public static void main(String[] args) {
        UndergraduateStudent ugs = new UndergraduateStudent("UG123", "Alice", "Computer Science");
        GraduateStudent gs = new GraduateStudent("G456", "Bob", "Data Science");

        double[] ugsGrades = {85.5, 90.0, 78.0};
        double[] gsGrades = {88.0, 92.0, 95.0};

        Course ugsCourse = new Course("CS101", "Intro to CS", ugsGrades);
        Course gsCourse = new Course("DS102", "Advanced Data Science", gsGrades);

        Grade ugGrade = new Grade(ugs, ugsCourse);
        Grade gGrade = new Grade(gs, gsCourse);

        System.out.println("Undergraduate Student Grade:");
        ugGrade.display();

        System.out.println("\nGraduate Student Grade:");
        gGrade.display();
    }
}
