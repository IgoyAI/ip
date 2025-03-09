import java.time.LocalDate;

/**
 * Represents a command to add a new deadline task.
 * <p>
 * This command creates a Deadline task using the provided description and due date,
 * adds it to the task list, saves the updated list to storage, and displays a confirmation message.
 * </p>
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    /**
     * Constructs a DeadlineCommand with the specified task description and due date.
     *
     * @param description the description of the deadline task.
     * @param by the due date of the deadline task.
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the deadline command by creating a new Deadline task, adding it to the task list,
     * saving the updated list to storage, and displaying a confirmation message.
     *
     * @param tasks the task list to which the new deadline task is added.
     * @param ui the user interface used to display messages.
     * @param storage the storage system used to persist the task list.
     * @throws AsepException if an error occurs while saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AsepException {
        Task task = new Deadline(description, by);
        tasks.add(task);
        // Pass the TaskList directly since Storage.saveTasks expects a TaskList.
        storage.saveTasks(tasks);
        ui.showMessage("Got it. I've added this task:\n   " + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
