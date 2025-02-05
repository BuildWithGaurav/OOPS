import java.util.Scanner;

public class Calculator {

    // Method to add two doubles
    public double add(double a, double b) {
        return a + b;
    }

    // Method to add three doubles
    public double add(double a, double b, double c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();

        // Prompting user for input
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();

        // Demonstrating method overloading
        System.out.println("Sum of two numbers: " + calc.add(num1, num2));

        // If you want to add three numbers
        System.out.print("Enter third number: ");
        double num3 = scanner.nextDouble();
        System.out.println("Sum of three numbers: " + calc.add(num1, num2, num3));

        scanner.close();
    }
}
