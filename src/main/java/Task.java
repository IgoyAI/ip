public abstract class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public abstract String getType();

    protected String getExtraInfo() {
        return "";
    }

    // New getter method for the task description
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String statusIcon = isDone ? "X" : " ";
        return "[" + getType() + "][" + statusIcon + "] " + description + getExtraInfo();
    }
}
