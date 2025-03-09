/**
 * Represents a command to mark a task as completed.
 * <p>
 * This command marks the task at the specified index as done, updates the storage,
 * and displays a confirmation message to the user.
 * </p>
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index the index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command by marking the task at the given index as done.
     * <p>
     * The method first validates that the provided index is within the bounds of the task list.
     * If the index is invalid, an AsepException is thrown. Otherwise, the task is marked as done,
     * the updated task list is saved to storage, and a confirmation message is displayed.
     * </p>
     *
     * @param tasks   the task list containing all tasks.
     * @param ui      the user interface used for displaying messages.
     * @param storage the storage system used to persist the task list.
     * @throws AsepException if the provided index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AsepException {
        if (index < 0 || index >= tasks.size()) {
            throw new AsepException("Invalid task number.");
        }
        tasks.get(index).markAsDone();
        storage.saveTasks(tasks);
        ui.showMessage("Nice! I've marked this task as done:\n   " + tasks.get(index));
    }
}
