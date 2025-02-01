import java.util.Scanner;

public class GreatestNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter three numbers: ");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        if (a >= b) {
            if (a >= c) {
                System.out.println("Greatest number is: " + a);
            } else {
                System.out.println("Greatest number is: " + c);
            }
        } else {
            if (b >= c) {
                System.out.println("Greatest number is: " + b);
            } else {
                System.out.println("Greatest number is: " + c);
            }
        }
        scanner.close();
    }
}
