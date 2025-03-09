/**
 * Represents a command to mark a task as not completed.
 * <p>
 * This command updates the status of a task at a specified index to "not done",
 * saves the updated task list to storage, and displays a confirmation message.
 * </p>
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index the index of the task to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command by marking the task at the given index as not done.
     * <p>
     * This method validates the task index, updates the task's status,
     * saves the updated task list to storage, and shows a confirmation message.
     * If the index is invalid, an AsepException is thrown.
     * </p>
     *
     * @param tasks   the task list containing tasks.
     * @param ui      the user interface used to display messages.
     * @param storage the storage system used to persist the updated task list.
     * @throws AsepException if the provided task index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AsepException {
        if (index < 0 || index >= tasks.size()) {
            throw new AsepException("Invalid task number.");
        }
        tasks.get(index).markAsNotDone();
        storage.saveTasks(tasks);
        ui.showMessage("OK, I've marked this task as not done yet:\n   " + tasks.get(index));
    }
}
