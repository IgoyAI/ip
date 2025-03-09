/**
 * Represents the exit command for the Asep application.
 * <p>
 * When executed, this command displays a farewell message and signals the application to exit.
 * </p>
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * <p>
     * This method displays a farewell message using the user interface.
     * </p>
     *
     * @param tasks the task list (not used in this command).
     * @param ui the user interface used to display the farewell message.
     * @param storage the storage system (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
    }

    /**
     * Indicates whether this command should terminate the application.
     *
     * @return true, indicating that the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
