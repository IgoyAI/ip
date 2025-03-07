import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns a TaskList.
     * For now, this stub returns an empty TaskList.
     */
    public TaskList loadTasks() throws AsepException {
        // TODO: Implement file reading and Task parsing
        return new TaskList(new ArrayList<>());
    }

    /**
     * Saves the tasks in the TaskList to the file.
     * For now, this stub does nothing.
     */
    public void saveTasks(TaskList tasks) {
        // TODO: Implement saving tasks to file
    }
}
