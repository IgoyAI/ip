import java.util.Scanner;
import java.util.ArrayList;

public class Asep {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    public Asep() {
        this.storage = new Storage("./data/asep.txt");
        this.tasks = storage.loadTasks();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        printGreeting();

        while (true) {
            try {
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
                } else if (userInput.toLowerCase().startsWith("delete ")) {
                    processDeleteCommand(userInput);
                } else if (userInput.toLowerCase().startsWith("todo ")) {
                    processTodoCommand(userInput);
                } else if (userInput.toLowerCase().startsWith("deadline ")) {
                    processDeadlineCommand(userInput);
                } else if (userInput.toLowerCase().startsWith("event ")) {
                    processEventCommand(userInput);
                } else {
                    throw new AsepException("Oops! I don't recognize this command. Please try again.");
                }
            } catch (AsepException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" An unexpected error occurred: " + e.getMessage());
                System.out.println("____________________________________________________________");
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
        if (tasks.isEmpty()) {
            System.out.println(" No tasks found.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    private void processMarkCommand(String command) throws AsepException {
        int index = parseTaskIndex(command, "mark");
        tasks.get(index).markAsDone();
        storage.saveTasks(tasks);
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + tasks.get(index));
        System.out.println("____________________________________________________________");
    }

    private void processUnmarkCommand(String command) throws AsepException {
        int index = parseTaskIndex(command, "unmark");
        tasks.get(index).markAsNotDone();
        storage.saveTasks(tasks);
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks.get(index));
        System.out.println("____________________________________________________________");
    }

    private void processDeleteCommand(String command) throws AsepException {
        int index = parseTaskIndex(command, "delete");
        Task removedTask = tasks.remove(index);
        storage.saveTasks(tasks);
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removedTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private void processTodoCommand(String command) throws AsepException {
        String description = command.substring(5).trim();
        if (description.isEmpty()) {
            throw new AsepException("Oops! The description of a todo cannot be empty.");
        }
        addTask(new Todo(description));
    }

    private void processDeadlineCommand(String command) throws AsepException {
        String content = command.substring(9).trim();
        int byIndex = content.indexOf("/by");
        if (byIndex == -1) {
            throw new AsepException("Invalid deadline format. Use: deadline <desc> /by <time>");
        }
        String description = content.substring(0, byIndex).trim();
        String by = content.substring(byIndex + 3).trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new AsepException("Deadline description and time cannot be empty.");
        }
        addTask(new Deadline(description, by));
    }

    private void processEventCommand(String command) throws AsepException {
        String content = command.substring(6).trim();
        int fromIndex = content.indexOf("/from");
        int toIndex = content.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
            throw new AsepException("Invalid event format. Use: event <desc> /from <start> /to <end>");
        }
        String description = content.substring(0, fromIndex).trim();
        String from = content.substring(fromIndex + 5, toIndex).trim();
        String to = content.substring(toIndex + 3).trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new AsepException("Event description, start, and end times cannot be empty.");
        }
        addTask(new Event(description, from, to));
    }

    private void addTask(Task task) {
        tasks.add(task);
        storage.saveTasks(tasks);
        printTaskAdded(task);
    }

    private void printTaskAdded(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private int parseTaskIndex(String command, String action) throws AsepException {
        String[] parts = command.split("\\s+");
        if (parts.length != 2) {
            throw new AsepException("Invalid command format. Usage: " + action + " <task number>");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new AsepException("Invalid task number.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new AsepException("Invalid task number.");
        }
    }

    public static void main(String[] args) {
        new Asep().run();
    }
}
