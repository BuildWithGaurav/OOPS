public class SumDivisibleBy6and9 {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 12; i <= 950; i += 18) { // LCM of 6 and 9 is 18
            sum += i;
        }
        System.out.println("Sum: " + sum);
    }
}
