import java.util.Scanner;

/**
 * Handles user interaction by reading input and displaying messages.
 * <p>
 * This class utilizes a Scanner to read user input from the standard input stream
 * and provides methods to display greetings, farewells, task lists, general messages,
 * and error messages.
 * </p>
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the Scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the greeting message to the user.
     */
    public void showGreeting() {
        printDivider();
        System.out.println(" Hello! I'm Asep");
        System.out.println(" What can I do for you?");
        printDivider();
    }

    /**
     * Displays the farewell message to the user.
     */
    public void showFarewell() {
        printDivider();
        System.out.println(" Bye. Hope to see you again soon!");
        printDivider();
    }

    /**
     * Reads a line of user input from the standard input.
     *
     * @return the trimmed user input as a String.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList the TaskList containing tasks to be displayed.
     */
    public void showTaskList(TaskList taskList) {
        printDivider();
        System.out.println(" Here are the tasks in your list:");
        if (taskList.isEmpty()) {
            System.out.println(" No tasks found.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + taskList.get(i));
            }
        }
        printDivider();
    }

    /**
     * Displays a general message to the user.
     *
     * @param message the message to be displayed.
     */
    public void showMessage(String message) {
        printDivider();
        System.out.println(message);
        printDivider();
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage the error message to be displayed.
     */
    public void showError(String errorMessage) {
        printDivider();
        System.out.println(" " + errorMessage);
        printDivider();
    }

    /**
     * Prints a divider line to separate different sections of the output.
     * <p>
     * This private method is used internally to enhance the readability of console output.
     * </p>
     */
    private void printDivider() {
        System.out.println("____________________________________________________________");
    }
}
