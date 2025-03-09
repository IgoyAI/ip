/**
 * Represents a todo task.
 * <p>
 * This class extends the Task class and represents a simple task that only contains a description.
 * </p>
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description the description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type identifier of this task.
     *
     * @return a string "T" representing a todo task.
     */
    @Override
    public String getType() {
        return "T";
    }
}
