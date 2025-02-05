import java.util.Scanner;

public class Student {
    private String name;
    private int age;

    // Default constructor
    public Student() {
        this.name = "Unknown";
        this.age = 0;
    }

    // Parameterized constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Method to display student details
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating object using default constructor
        Student student1 = new Student();
        System.out.println("Student 1 Details:");
        student1.display();
        System.out.println();

        // Taking user input for the second student
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        // Creating object using parameterized constructor
        Student student2 = new Student(name, age);
        System.out.println("Student 2 Details:");
        student2.display();

        scanner.close();
    }
}
