public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

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
