import java.util.Scanner;

public class CalculatorSimple
{
    private static final Scanner kb = new Scanner(System.in);

    public static void main(String[] args)
    {
        System.out.format("Java version: %s%n", getJavaVersion());

        boolean keepCalculating = true;
        do {
            performOneCalculation();
            keepCalculating = askYesNoQuestion("Perform another calculation? (y/n/si/unhuh)> ");
        } while (keepCalculating);

        System.out.println("Thank you for using my calculator");
    }

    /** get the java version that is running the current program
     * @return string containing the java version running the current program
     */
    private static String getJavaVersion()
    {
        Runtime.Version runTimeVersion = Runtime.version();
        return String.format("%s.%s.%s.%s", runTimeVersion.feature(), runTimeVersion.interim(), runTimeVersion.update(), runTimeVersion.patch());
    }

    /** Ask a yes/no question.
     * @param question String containing the question to ask.
     * @return true if the answer is yes; false otherwise.
     */
    private static boolean askYesNoQuestion(String question) {
        System.out.print(question);
        String answer = kb.nextLine();
        if (answer.isEmpty()) return false;
        if (answer.substring(0,1).equalsIgnoreCase("y")) return true;
        if (answer.substring(0,1).equalsIgnoreCase("n")) return false;
        if (answer.substring(0,2).equalsIgnoreCase("si")) return true;
        if (answer.substring(0,2).equalsIgnoreCase("un")) return true;
        return false;
    }


    /**
     * Performs a single calculation.
     */
    private static void performOneCalculation() {
        double a = getNumber("Enter the first number> ");
        double b = getNumber("Enter the second number> ");
        String operation = getOperation();
        double result = performOperation(a, b, operation);
        System.out.println("result of " + a + " " + operation + " " + b + " = " + result);
    }

    /**
     * Performs the specified operation on the two input numbers.
     *
     * @param a         - the first number
     * @param b         - the second number
     * @param operation - the desired operation (+, -, *, / or %)
     * @return double precision result of the operation on the two numbers.
     */
    private static double performOperation(double a, double b, String operation) {
        if (operation.equals("+")) return a + b;
        else if (operation.equals("-")) return a - b;
        else if (operation.equals("*")) return a * b;
        else if (operation.equals("/")) return a / b;
        else if (operation.equals("%")) return a % b;
        else return Double.NaN;
    }

    /**
     * Prompts user for a valid number
     *
     * @param msg - the prompt to be used
     * @return double precision number
     * @implNote The user is trapped in this method until they enter a valid number.
     */
    private static double getNumber(String msg) {
        double num = Double.NaN;
        boolean waitingForValidNumber = true;
        do {
            System.out.print(msg);
            try {
                num = Double.parseDouble(kb.nextLine());
                waitingForValidNumber = false;
            } catch (NumberFormatException ex) {
                System.out.println("That's not a number. Try again.");
            }
        } while (waitingForValidNumber);
        return num;
    }

    /**
     * Prompts for a valid operation.
     *
     * @return String containing the operation symbol.
     * @implNote Valid operations are (+, -, *, / or %)
     */
    private static String getOperation() {

        final String VALID_OPERATIONS = "+-*/%";
        String operation = "";
        boolean waitingForValidOperation = true;
        do {
            System.out.println("Valid operations are " + VALID_OPERATIONS);
            System.out.print("What operation do you want to perform?> ");
            operation = kb.nextLine().substring(0, 1);
            if (VALID_OPERATIONS.contains(operation)) waitingForValidOperation = false;
            else System.out.println("Invalid choice. Try again.");

        } while (waitingForValidOperation);
        return operation;

    }

}