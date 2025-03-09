/**
 * Represents a command to find tasks in the task list that contain a specific keyword.
 * <p>
 * This command iterates over the task list, checks each task's description for the keyword,
 * and displays a list of matching tasks to the user. If no tasks match the keyword, it informs the user accordingly.
 * </p>
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword the keyword to search for within task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the keyword in their descriptions.
     * <p>
     * The method builds a message that lists all matching tasks. If no tasks are found that match the keyword,
     * it appends a message indicating that no matching tasks were found.
     * </p>
     *
     * @param tasks   the task list to search through.
     * @param ui      the user interface for displaying messages.
     * @param storage the storage system (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                sb.append(count + ". " + task.toString() + "\n");
            }
        }
        if (count == 0) {
            sb.append("No matching tasks found.");
        }
        ui.showMessage(sb.toString());
    }
}
