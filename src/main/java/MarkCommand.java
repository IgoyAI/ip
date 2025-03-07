public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

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
