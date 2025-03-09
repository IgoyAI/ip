/**
 * Represents an abstract command in the Asep application.
 * <p>
 * All commands must implement the execute method to define their specific actions
 * on the task list, user interface, and storage.
 * </p>
 */
public abstract class Command {
    /**
     * Executes the command using the provided task list, UI, and storage.
     *
     * @param tasks   the task list to operate on.
     * @param ui      the user interface for displaying messages.
     * @param storage the storage handler for data persistence.
     * @throws AsepException if an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AsepException;

    /**
     * Indicates whether this command should cause the application to exit.
     *
     * @return false by default; override in exit commands to return true.
     */
    public boolean isExit() {
        return false;
    }
}
