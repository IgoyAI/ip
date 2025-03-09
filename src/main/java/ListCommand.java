/**
 * Represents a command to display the list of all tasks.
 * <p>
 * When executed, this command instructs the user interface to show the complete task list.
 * </p>
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by displaying the entire task list using the user interface.
     *
     * @param tasks   the task list containing all tasks.
     * @param ui      the user interface used to display the task list.
     * @param storage the storage system (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
