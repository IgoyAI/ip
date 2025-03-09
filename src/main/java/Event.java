/**
 * Represents an event task with a specified start and end time.
 * <p>
 * This class extends the Task class by adding event-specific details, including
 * the start (from) and end (to) times of the event.
 * </p>
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description the description of the event.
     * @param from the start time of the event.
     * @param to the end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the type identifier for an event task.
     *
     * @return a string "E" representing an event task.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns additional information about the event task.
     * <p>
     * This includes the start and end times formatted as part of the task details.
     * </p>
     *
     * @return a string containing the start and end times of the event.
     */
    @Override
    protected String getExtraInfo() {
        return " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the start time of the event.
     *
     * @return the start time as a string.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the end time as a string.
     */
    public String getTo() {
        return to;
    }
}
