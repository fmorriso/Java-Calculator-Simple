import java.util.Scanner;

public class Main {

    private static final Scanner kb = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.format("Java version: %s%n", getJavaVersion());

        performOneCalculation();
    }

    private static void performOneCalculation() {
        double a = getNumber("Enter the first number>");
        double b = getNumber("Enter the second number>");
        String operation = getOperation();
        double result = performOperation(a, b, operation);
        System.out.println("result of " + a + " " + operation + " " + b + " = " + result);
    }

    private static double performOperation(double a, double b, String operation) {
        if (operation.equals("+")) return a + b;
        else if (operation.equals("-")) return a - b;
        else if (operation.equals("*")) return a * b;
        else if (operation.equals("/")) return a / b;
        else if (operation.equals("%")) return a % b;
        else return Double.NaN;
    }

    private static double getNumber(String msg) {

        double num = Double.NaN;
        System.out.print(msg);
        boolean needValidNumber = true;
        do {
            try {
                num = Double.parseDouble(kb.nextLine());
                needValidNumber = false;
            } catch (NumberFormatException ex) {
                System.out.println("That's not a number. Try again.");
            }
        } while (needValidNumber);
        return num;
    }

    private static String getOperation() {

        String validOperations = "+-*/%";
        String operation = "";
        boolean waitingForValidOperation = true;
        System.out.println("Valid operations are " + validOperations);
        do {
            System.out.print("What operation do you want to perform?>");
            operation = kb.nextLine();
            if (validOperations.contains(operation)) waitingForValidOperation = false;
            else System.out.println("Invalid choice. Try again.");

        } while (waitingForValidOperation);
        return operation;

    }

    /**
     * get the java version that is running the current program
     *
     * @return string containing the java version running the current program
     */
    private static String getJavaVersion() {
        Runtime.Version runTimeVersion = Runtime.version();
        return String.format("%s.%s.%s.%s", runTimeVersion.feature(), runTimeVersion.interim(), runTimeVersion.update(), runTimeVersion.patch());
    }

}