/**
 * Represents a command to add a new todo task.
 * <p>
 * This command creates a Todo task with the provided description, adds it to the task list,
 * saves the updated task list to storage, and displays a confirmation message to the user.
 * </p>
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Constructs a TodoCommand with the specified task description.
     *
     * @param description the description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the todo command by creating a new Todo task, adding it to the task list,
     * saving the updated list to storage, and displaying a confirmation message.
     *
     * @param tasks   the task list where the new todo task is added.
     * @param ui      the user interface used to display messages.
     * @param storage the storage system used to persist the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.add(task);
        storage.saveTasks(tasks);
        ui.showMessage("Got it. I've added this task:\n   " + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
