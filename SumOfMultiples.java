public class SumOfMultiples {
    public static void main(String[] args) {
        int start = 10, end = 950;
        int sum = 0;

        // Loop through numbers between 10 and 950
        for (int i = start; i <= end; i++) {
            if (i % 18 == 0) { // Check divisibility by 18
                sum += i;
            }
        }

        // Print the sum of numbers divisible by 18
        System.out.println("The sum of integers between 10 and 950 that are divisible by both 6 and 9 is: " + sum);
    }
}
