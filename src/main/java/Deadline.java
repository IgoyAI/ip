import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specified due date.
 * <p>
 * This class extends the Task class and adds functionality specific to tasks that have a deadline.
 * </p>
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * The date format used for parsing input dates in the format "yyyy-MM-dd".
     */
    public static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * The date format used for displaying dates in the format "MMM dd yyyy".
     */
    public static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description the description of the task.
     * @param by the due date for the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the due date of this deadline task.
     *
     * @return the due date.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns the type identifier of this task.
     * <p>
     * For a deadline task, the type is represented by the letter "D".
     * </p>
     *
     * @return a string "D" representing a deadline task.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns additional information about the task.
     * <p>
     * For a deadline task, this includes the formatted due date.
     * </p>
     *
     * @return a string containing the due date formatted using OUTPUT_DATE_FORMAT.
     */
    @Override
    protected String getExtraInfo() {
        return " (by: " + by.format(OUTPUT_DATE_FORMAT) + ")";
    }
}
