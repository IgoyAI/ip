import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showGreeting() {
        printDivider();
        System.out.println(" Hello! I'm Asep");
        System.out.println(" What can I do for you?");
        printDivider();
    }

    public void showFarewell() {
        printDivider();
        System.out.println(" Bye. Hope to see you again soon!");
        printDivider();
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

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

    public void showMessage(String message) {
        printDivider();
        System.out.println(message);
        printDivider();
    }

    public void showError(String errorMessage) {
        printDivider();
        System.out.println(" " + errorMessage);
        printDivider();
    }

    private void printDivider() {
        System.out.println("____________________________________________________________");
    }
}
