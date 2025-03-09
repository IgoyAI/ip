import java.util.ArrayList;

/**
 * Handles the loading and saving of tasks to a file.
 * <p>
 * This class is responsible for persisting the task list by reading from and writing to a file.
 * Currently, the methods are stubs and do not perform actual file I/O operations.
 * </p>
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns a TaskList.
     * <p>
     * Currently, this method is a stub that returns an empty TaskList.
     * </p>
     *
     * @return a TaskList containing tasks loaded from the file.
     * @throws AsepException if an error occurs during task loading.
     */
    public TaskList loadTasks() throws AsepException {
        // TODO: Implement file reading and Task parsing
        return new TaskList(new ArrayList<>());
    }

    /**
     * Saves the tasks in the TaskList to the file.
     * <p>
     * Currently, this method is a stub that does not perform any file operations.
     * </p>
     *
     * @param tasks the TaskList containing tasks to be saved.
     */
    public void saveTasks(TaskList tasks) {
        // TODO: Implement saving tasks to file
    }
}
