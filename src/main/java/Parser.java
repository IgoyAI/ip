import java.time.LocalDate;

public class Parser {
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
            default:
                throw new AsepException("Oops! I don't recognize this command. Please try again.");
        }
    }

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
