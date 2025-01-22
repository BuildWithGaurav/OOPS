public class Calc {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Calc <number1> <operator> <number2>");
            return;
        }
        
        try {
            int num1 = Integer.parseInt(args[0]);
            String operator = args[1];
            int num2 = Integer.parseInt(args[2]);
            
            switch (operator) {
                case "+":
                    System.out.println("Sum of " + num1 + " and " + num2 + " is " + (num1 + num2));
                    break;
                case "-":
                    System.out.println("Difference of " + num1 + " and " + num2 + " is " + (num1 - num2));
                    break;
                case "*":
                    System.out.println("Product of " + num1 + " and " + num2 + " is " + (num1 * num2));
                    break;
                case "/":
                    if (num2 != 0) {
                        System.out.println("Division of " + num1 + " by " + num2 + " is " + (num1 / num2));
                    } else {
                        System.out.println("Error: Division by zero is not allowed.");
                    }
                    break;
                default:
                    System.out.println("Invalid operator! Use +, -, *, or /.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid integers.");
        }
    }
}
