import java.util.Scanner;

public class FindMissingNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the array (N-1): ");
        int n = sc.nextInt();
        
        int[] arr = new int[n];
        System.out.println("Enter " + n + " elements (from 1 to N with one missing):");
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        
        // Total sum of numbers from 1 to N
        int N = n + 1;
        int totalSum = N * (N + 1) / 2;
        
        int missingNumber = totalSum - sum;
        System.out.println("Missing number: " + missingNumber);
        
        sc.close();
    }
}
