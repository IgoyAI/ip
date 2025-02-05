public abstract class Task {
    protected final String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the task type as a single letter (e.g., "T", "D", "E").
     *
     * @return the type of task
     */
    public abstract String getType();

    /**
     * Returns any extra information specific to the task type.
     * Default is empty.
     *
     * @return extra task info (e.g., deadline or event timing)
     */
    protected String getExtraInfo() {
        return "";
    }

    /**
     * Returns the string representation of the task.
     * Example: [T][ ] borrow book
     *
     * @return the formatted task string
     */
    @Override
    public String toString() {
        String statusIcon = isDone ? "X" : " ";
        return "[" + getType() + "][" + statusIcon + "] " + description + getExtraInfo();
    }
}
