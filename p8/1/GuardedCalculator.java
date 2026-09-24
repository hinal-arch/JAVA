import java.util.Scanner;


class DivideByZeroException extends Exception {

    public DivideByZeroException(String message) {
        super(message);
    }
}

public class GuardedCalculator {

    public static double calculate(double num1, double num2, char operator)
            throws DivideByZeroException {

        switch (operator) {

            case '+' -> {
                return num1 + num2;
            }

            case '-' -> {
                return num1 - num2;
            }

            case '*' -> {
                return num1 * num2;
            }

            case '/' -> {
                if (num2 == 0) {
                    throw new DivideByZeroException(
                            "Cannot divide by zero."
                    );
                }
                return num1 / num2;
            }

            default -> throw new IllegalArgumentException(
                        "Invalid operator."
                );
        }
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            boolean success = false;
            
            while (!success) {
                
                try {
                    
                    System.out.print("Enter first number: ");
                    double num1 = Double.parseDouble(sc.nextLine());
                    
                    System.out.print("Enter second number: ");
                    double num2 = Double.parseDouble(sc.nextLine());
                    
                    System.out.print("Enter operator (+, -, *, /): ");
                    char operator = sc.nextLine().charAt(0);
                    
                    double result =
                            calculate(num1, num2, operator);
                    
                    System.out.println("Result = " + result);
                    
                    success = true;
                    
                } catch (NumberFormatException e) {
                    
                    System.out.println(
                            "Invalid number! Please enter a valid number."
                    );
                    
                } catch (DivideByZeroException e) {
                    
                    System.out.println(
                            "Error: " + e.getMessage()
                    );
                    
                } catch (IllegalArgumentException e) {
                    
                    System.out.println(
                            "Error: " + e.getMessage()
                    );
                    
                } finally {
                    
                    System.out.println(
                            "Calculation attempt completed."
                    );
                    
                    System.out.println("----------------------------");
                }
            }
        }
    }
}