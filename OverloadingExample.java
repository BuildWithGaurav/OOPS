class MathOperations {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}

public class OverloadingExample {
    public static void main(String[] args) {
        MathOperations obj = new MathOperations();
        System.out.println("Sum (int): " + obj.add(5, 10));
        System.out.println("Sum (three int): " + obj.add(1, 2, 3));
    }
}