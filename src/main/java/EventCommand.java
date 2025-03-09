/**
 * Represents a command to add a new event task.
 * <p>
 * This command creates an Event task using the provided description, start time, and end time,
 * adds it to the task list, saves the updated list to storage, and displays a confirmation message.
 * </p>
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs an EventCommand with the specified description, start time, and end time.
     *
     * @param description the description of the event task.
     * @param from the starting time or date of the event.
     * @param to the ending time or date of the event.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command by creating a new Event task, adding it to the task list,
     * saving the updated list to storage, and displaying a confirmation message.
     *
     * @param tasks the task list where the event task is added.
     * @param ui the user interface for displaying messages.
     * @param storage the storage system for persisting the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(description, from, to);
        tasks.add(task);
        storage.saveTasks(tasks);
        ui.showMessage("Got it. I've added this task:\n   " + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
