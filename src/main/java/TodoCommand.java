public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.add(task);
        storage.saveTasks(tasks);
        ui.showMessage("Got it. I've added this task:\n   " + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
