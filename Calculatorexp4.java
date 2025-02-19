class Calculatorexp4 {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    // Main method for execution
    public static void main(String[] args) {
        Calculatorexp4 calc = new Calculatorexp4();

        System.out.println("Sum of 5 and 10: " + calc.add(5, 10));
        System.out.println("Sum of 5.5 and 10.5: " + calc.add(5.5, 10.5));
        System.out.println("Sum of 1, 2, and 3: " + calc.add(1, 2, 3));
    }
}
