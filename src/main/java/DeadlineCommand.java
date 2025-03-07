import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AsepException {
        Task task = new Deadline(description, by);
        tasks.add(task);
        // Pass the TaskList directly since Storage.saveTasks expects a TaskList.
        storage.saveTasks(tasks);
        ui.showMessage("Got it. I've added this task:\n   " + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
