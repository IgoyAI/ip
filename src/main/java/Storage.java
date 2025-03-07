import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns a TaskList.
     * (For simplicity, this stub just returns an empty TaskList.
     * In a real application you would read the file and create Task objects.)
     */
    public TaskList loadTasks() throws AsepException {
        // TODO: Implement file reading and Task parsing
        return new TaskList(new ArrayList<>());
    }

    /**
     * Saves the tasks in the TaskList to the file.
     * (For simplicity, this stub does nothing. You can implement the I/O as needed.)
     */
    public void saveTasks(TaskList tasks) {
        // TODO: Implement saving tasks to file
    }
}
