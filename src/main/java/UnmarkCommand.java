public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

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
