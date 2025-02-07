public class CountOccurrences {
    public static void main(String[] args) {
        int[] arr = {2, 3, 2, 5, 2, 6};
        int target = 2;
        int count = 0;

        for (int num : arr) {
            if (num == target) {
                count++;
            }
        }
        
        System.out.println("Occurrences of " + target + ": " + count);
    }
}
