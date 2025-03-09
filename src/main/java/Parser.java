import java.time.LocalDate;

/**
 * The Parser class is responsible for interpreting user input commands
 * and converting them into corresponding Command objects.
 * <p>
 * It supports various commands such as "bye", "list", "mark", "unmark", "delete",
 * "todo", "deadline", "event", and "find". If the command or its format is invalid,
 * an AsepException is thrown.
 * </p>
 */
public class Parser {

    /**
     * Parses a full command string and returns the appropriate Command object.
     * <p>
     * The method determines the command type based on the first word of the input,
     * extracts necessary arguments, and creates a corresponding Command instance.
     * Supported commands include:
     * <ul>
     *   <li>"bye" for exiting the application</li>
     *   <li>"list" for listing all tasks</li>
     *   <li>"mark", "unmark", and "delete" for task operations (requires a task number)</li>
     *   <li>"todo" for adding a todo task (requires a description)</li>
     *   <li>"deadline" for adding a deadline task (requires a description and a date)</li>
     *   <li>"event" for adding an event task (requires a description, start, and end time)</li>
     *   <li>"find" for searching tasks by a keyword</li>
     * </ul>
     * </p>
     *
     * @param fullCommand the full command input from the user.
     * @return a Command object corresponding to the parsed command.
     * @throws AsepException if the command is unrecognized or if its format is invalid.
     */
    public static Command parse(String fullCommand) throws AsepException {
        String commandWord = fullCommand.split(" ")[0].toLowerCase();
        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parseTaskIndex(fullCommand));
            case "unmark":
                return new UnmarkCommand(parseTaskIndex(fullCommand));
            case "delete":
                return new DeleteCommand(parseTaskIndex(fullCommand));
            case "todo":
                String todoDesc = fullCommand.substring(5).trim();
                if (todoDesc.isEmpty()) {
                    throw new AsepException("The description of a todo cannot be empty.");
                }
                return new TodoCommand(todoDesc);
            case "deadline":
                String deadlineContent = fullCommand.substring(9).trim();
                int byIndex = deadlineContent.indexOf("/by");
                if (byIndex == -1) {
                    throw new AsepException("Invalid deadline format. Use: deadline <desc> /by <date>");
                }
                String deadlineDesc = deadlineContent.substring(0, byIndex).trim();
                String byStr = deadlineContent.substring(byIndex + 3).trim();
                if (deadlineDesc.isEmpty() || byStr.isEmpty()) {
                    throw new AsepException("Deadline description and date cannot be empty.");
                }
                LocalDate byDate;
                try {
                    byDate = LocalDate.parse(byStr, Deadline.INPUT_DATE_FORMAT);
                } catch (Exception e) {
                    throw new AsepException("Invalid date format. Please use yyyy-MM-dd.");
                }
                return new DeadlineCommand(deadlineDesc, byDate);
            case "event":
                String eventContent = fullCommand.substring(6).trim();
                int fromIndex = eventContent.indexOf("/from");
                int toIndex = eventContent.indexOf("/to");
                if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
                    throw new AsepException("Invalid event format. Use: event <desc> /from <start> /to <end>");
                }
                String eventDesc = eventContent.substring(0, fromIndex).trim();
                String from = eventContent.substring(fromIndex + 5, toIndex).trim();
                String to = eventContent.substring(toIndex + 3).trim();
                if (eventDesc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    throw new AsepException("Event description, start, and end times cannot be empty.");
                }
                return new EventCommand(eventDesc, from, to);
            case "find":
                String searchTerm = fullCommand.substring(5).trim();
                if (searchTerm.isEmpty()) {
                    throw new AsepException("Search term cannot be empty.");
                }
                return new FindCommand(searchTerm);
            default:
                throw new AsepException("Oops! I don't recognize this command. Please try again.");
        }
    }

    /**
     * Parses the task index from a command string.
     * <p>
     * This helper method extracts the task number from commands that operate on tasks,
     * such as "mark", "unmark", and "delete". The method expects the command to contain
     * exactly two parts: the command word and the task number. The task number is converted
     * from a 1-based index to a 0-based index.
     * </p>
     *
     * @param fullCommand the full command string which includes a task number.
     * @return the zero-based index of the task.
     * @throws AsepException if the command format is incorrect or if the task number is invalid.
     */
    private static int parseTaskIndex(String fullCommand) throws AsepException {
        String[] parts = fullCommand.split("\\s+");
        if (parts.length != 2) {
            throw new AsepException("Invalid command format. Usage: <command> <task number>");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            return index;
        } catch (NumberFormatException e) {
            throw new AsepException("Invalid task number.");
        }
    }
}
