import java.util.Scanner;

public class PatternPrint {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();
        
        for (int i = 1; i <= rows; i++) {
            if (i % 2 == 1) { // Odd rows print '?'
                for (int j = 0; j < 2 * i - 1; j++) {
                    System.out.print("? ");
                }
            } else { // Even rows print '#'
                for (int j = 0; j < 2 * i - 1; j++) {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
        
        sc.close();
    }
}
