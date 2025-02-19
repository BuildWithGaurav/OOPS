class University {
    static String universityName = "UPES"; // Static variable shared by all objects
    String studentName; // Non-static variable unique to each object

    // Constructor to initialize student name
    University(String studentName) {
        this.studentName = studentName;
    }

    // Static method to display university name
    static void displayUniversityName() {
        System.out.println("University Name: " + universityName);
    }

    // Instance method to display student details
    void displayStudentDetails() {
        System.out.println("Student Name: " + studentName + ", University: " + universityName);
    }

    // Main method for execution
    public static void main(String[] args) {
        // Displaying the university name using a static method
        University.displayUniversityName();

        // Creating multiple student objects
        University student1 = new University("Gaurav");
        University student2 = new University("Rahul");
        University student3 = new University("Pragati");

        // Displaying student details
        student1.displayStudentDetails();
        student2.displayStudentDetails();
        student3.displayStudentDetails();

        // Demonstrating that the static variable is shared
        System.out.println("\nChanging university name to 'IIT Delhi'...");
        University.universityName = "IIT Delhi";

        // Displaying details again to see the updated university name
        student1.displayStudentDetails();
        student2.displayStudentDetails();
        student3.displayStudentDetails();
    }
}
