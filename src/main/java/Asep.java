import java.util.Scanner;

/**
 * Asep is a chatbot that tracks tasks of various types:
 * Todos, Deadlines, and Events. It supports marking tasks as done/undone,
 * listing tasks, and adding new tasks via commands.
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
     * Starts the chatbot.
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
            } else if (userInput.toLowerCase().startsWith("todo ")) {
                processTodoCommand(userInput);
            } else if (userInput.toLowerCase().startsWith("deadline ")) {
                processDeadlineCommand(userInput);
            } else if (userInput.toLowerCase().startsWith("event ")) {
                processEventCommand(userInput);
            } else {
                System.out.println("Unknown command.");
            }
        }

        scanner.close();
    }

    private void printGreeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Asep");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private void printFarewell() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private void printTaskList() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i].toString());
        }
        System.out.println("____________________________________________________________");
    }

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

    private void processTodoCommand(String command) {
        String description = command.substring(5).trim();
        if (description.isEmpty()) {
            System.out.println("Task description cannot be empty.");
            return;
        }
        addTask(new Todo(description));
    }

    private void processDeadlineCommand(String command) {
        // Remove "deadline " prefix (9 characters)
        String content = command.substring(9).trim();
        int byIndex = content.indexOf("/by");
        if (byIndex == -1) {
            System.out.println("Invalid format. Use: deadline <desc> /by <time>");
            return;
        }
        String description = content.substring(0, byIndex).trim();
        String by = content.substring(byIndex + 3).trim();
        if (description.isEmpty() || by.isEmpty()) {
            System.out.println("Task description and deadline cannot be empty.");
            return;
        }
        addTask(new Deadline(description, by));
    }

    private void processEventCommand(String command) {
        // Remove "event " prefix (6 characters)
        String content = command.substring(6).trim();
        int fromIndex = content.indexOf("/from");
        int toIndex = content.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
            System.out.println("Invalid format. Use: event <desc> /from <start> /to <end>");
            return;
        }
        String description = content.substring(0, fromIndex).trim();
        String from = content.substring(fromIndex + 5, toIndex).trim();
        String to = content.substring(toIndex + 3).trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            System.out.println("Task description, start, and end times cannot be empty.");
            return;
        }
        addTask(new Event(description, from, to));
    }

    private void addTask(Task task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = task;
            printTaskAdded(task);
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Task list is full. Cannot add more tasks.");
            System.out.println("____________________________________________________________");
        }
    }

    private void printTaskAdded(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        new Asep().run();
    }
}
