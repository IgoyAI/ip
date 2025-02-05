import java.util.Scanner;

/**
 * Asep is a simple chatbot that can store tasks, list them,
 * and mark/unmark tasks as done. It uses a fixed-size array to store up to 100 tasks.
 */
public class Asep {
    private static final int MAX_TASKS = 100;
    private final Task[] tasks;
    private int taskCount;

    /**
     * Constructs a new Asep chatbot.
     */
    public Asep() {
        tasks = new Task[MAX_TASKS];
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
            } else if (userInput.toLowerCase().startsWith("mark ")) {
                processMarkCommand(userInput);
            } else if (userInput.toLowerCase().startsWith("unmark ")) {
                processUnmarkCommand(userInput);
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
     * Adds a new task with the given description.
     *
     * @param taskDescription the description of the task to add
     */
    private void addTask(String taskDescription) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = new Task(taskDescription);
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Task list is full. Cannot add more tasks.");
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Prints confirmation when a task is added.
     *
     * @param taskDescription the task description that was added
     */
    private void printTaskAdded(String taskDescription) {
        System.out.println("____________________________________________________________");
        System.out.println(" added: " + taskDescription);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the current list of tasks.
     */
    private void printTaskList() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i].toString());
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Processes the 'mark' command to mark a task as done.
     *
     * @param command the full command string, e.g., "mark 2"
     */
    private void processMarkCommand(String command) {
        String[] parts = command.split("\\s+");
        if (parts.length != 2) {
            System.out.println("Invalid command format. Usage: mark <task number>");
            return;
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= taskCount) {
                System.out.println("Invalid task number.");
                return;
            }
            tasks[index].markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks[index].toString());
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * Processes the 'unmark' command to mark a task as not done.
     *
     * @param command the full command string, e.g., "unmark 2"
     */
    private void processUnmarkCommand(String command) {
        String[] parts = command.split("\\s+");
        if (parts.length != 2) {
            System.out.println("Invalid command format. Usage: unmark <task number>");
            return;
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= taskCount) {
                System.out.println("Invalid task number.");
                return;
            }
            tasks[index].markAsNotDone();
            System.out.println("____________________________________________________________");
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks[index].toString());
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * The main method to start the chatbot.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Asep().run();
    }
}
