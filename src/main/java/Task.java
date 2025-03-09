/**
 * Represents an abstract task with a description and completion status.
 * <p>
 * This class serves as a base for different types of tasks. It encapsulates common functionality
 * such as marking a task as done or not done, and provides a template for subclasses to define
 * their specific type and additional details.
 * </p>
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the type of this task.
     * <p>
     * Subclasses should implement this method to return a specific identifier (e.g., "T" for todo,
     * "D" for deadline, "E" for event).
     * </p>
     *
     * @return a string representing the task type.
     */
    public abstract String getType();

    /**
     * Returns any extra information related to the task.
     * <p>
     * Subclasses may override this method to include additional details specific to the task type.
     * </p>
     *
     * @return a string containing extra task information.
     */
    protected String getExtraInfo() {
        return "";
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task.
     * <p>
     * The format includes the task type, completion status, description, and any extra information.
     * </p>
     *
     * @return a string representing the task.
     */
    @Override
    public String toString() {
        String statusIcon = isDone ? "X" : " ";
        return "[" + getType() + "][" + statusIcon + "] " + description + getExtraInfo();
    }
}
