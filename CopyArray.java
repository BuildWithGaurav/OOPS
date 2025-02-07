import java.util.Scanner;

public class CopyArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        
        int[] sourceArray = new int[n];
        int[] targetArray = new int[n];
        
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            sourceArray[i] = sc.nextInt();
        }
        
        // Copying elements
        for (int i = 0; i < n; i++) {
            targetArray[i] = sourceArray[i];
        }
        
        System.out.print("Copied Array: ");
        for (int num : targetArray) {
            System.out.print(num + " ");
        }
        
        sc.close();
    }
}
    