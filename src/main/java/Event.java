public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    protected String getExtraInfo() {
        return " (from: " + from + " to: " + to + ")";
    }
}
