import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * <p>
 * This class encapsulates an ArrayList of Task objects and provides methods to add, remove,
 * retrieve, and check tasks within the list.
 * </p>
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks the ArrayList of Task objects to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the Task object to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param index the index of the task to be removed.
     * @return the Task object that was removed.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index from the task list.
     *
     * @param index the index of the task to retrieve.
     * @return the Task object at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the underlying ArrayList of tasks.
     * <p>
     * This method exposes the internal representation of the task list.
     * </p>
     *
     * @return the ArrayList containing all Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
