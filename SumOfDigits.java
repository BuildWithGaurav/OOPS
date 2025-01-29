import java.util.Scanner;

public class SumOfDigits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input from user
        System.out.print("Enter an integer: ");
        int num = scanner.nextInt();
        scanner.close(); // Close scanner

        int sum = 0;

        // Calculate sum of digits using while loop
        while (num != 0) {
            sum += num % 10; // Extract last digit and add to sum
            num /= 10;       // Remove last digit
        }

        // Print the sum of digits
        System.out.println("The sum of digits is: " + sum);
    }
}
