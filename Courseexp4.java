class Courseexp4 {
    String courseName;
    String courseCode;

    // Constructor using 'this' keyword
    Courseexp4(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    // Method to display course details
    void displayCourseDetails() {
        System.out.println("Course Name: " + courseName);
        System.out.println("Course Code: " + courseCode);
    }

    // Main method for execution
    public static void main(String[] args) {
        // Creating Course objects
        Courseexp4 course1 = new Courseexp4("Object-Oriented Programming", "CS101");
        Courseexp4 course2 = new Courseexp4("Data Structures", "CS102");

        // Displaying course details
        System.out.println("Course 1 Details:");
        course1.displayCourseDetails();
        
        System.out.println("\nCourse 2 Details:");
        course2.displayCourseDetails();
    }
}
