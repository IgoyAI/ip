/**
 * Represents a command to delete a task from the task list.
 * <p>
 * This command removes the task at a specified index from the task list, updates the storage,
 * and displays a confirmation message. If the specified index is out of bounds, it throws an AsepException.
 * </p>
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index the index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task at the specified index from the task list,
     * saving the updated task list to storage, and displaying a confirmation message.
     *
     * @param tasks the task list from which the task is removed.
     * @param ui the user interface used to display messages.
     * @param storage the storage system used to persist the updated task list.
     * @throws AsepException if the specified index is invalid or if an error occurs during saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AsepException {
        if (index < 0 || index >= tasks.size()) {
            throw new AsepException("Invalid task number.");
        }
        Task removedTask = tasks.remove(index);
        storage.saveTasks(tasks);
        ui.showMessage("Noted. I've removed this task:\n   " + removedTask +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
