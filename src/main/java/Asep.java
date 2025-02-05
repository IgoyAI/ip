import java.util.Scanner;

/**
 * AsepBot is a simple chatbot that can store tasks and list them on request.
 * It uses a fixed-size array to store up to 100 tasks.
 */
public class Asep {
    private static final int MAX_TASKS = 100;
    private final String[] tasks;
    private int taskCount;

    /**
     * Constructs a new Asep.
     */
    public Asep() {
        tasks = new String[MAX_TASKS];
        taskCount = 0;
    }

    /**
     * Runs the chatbot, reading user input and processing commands.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        printGreeting();

        while (true) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                printFarewell();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                printTaskList();
            } else {
                addTask(userInput);
                printTaskAdded(userInput);
            }
        }

        scanner.close();
    }

    /**
     * Prints the greeting message.
     */
    private void printGreeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Asep");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the farewell message.
     */
    private void printFarewell() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task description to add
     */
    private void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = task;
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Task list is full. Cannot add more tasks.");
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Prints the confirmation message when a task is added.
     *
     * @param task the task description that was added
     */
    private void printTaskAdded(String task) {
        System.out.println("____________________________________________________________");
        System.out.println(" added: " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the list of tasks.
     */
    private void printTaskList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * The main method to start the AsepBot.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Asep asepBot = new Asep();
        asepBot.run();
    }
}
