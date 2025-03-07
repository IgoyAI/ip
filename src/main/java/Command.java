public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AsepException;
    public boolean isExit() {
        return false;
    }
}
