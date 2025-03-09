/**
 * Represents the main application class for Asep.
 * <p>
 * This class initializes the UI, storage, and task list, loads tasks from a file,
 * and runs the main command processing loop.
 * </p>
 */
public class Asep {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an Asep application instance with the specified file path for task storage.
     * <p>
     * Initializes the user interface and storage system. It attempts to load the tasks
     * from the given file, and if an error occurs, displays an error message and creates an empty task list.
     * </p>
     *
     * @param filePath the file path where task data is stored.
     */
    public Asep(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasks();
        } catch (AsepException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main application loop.
     * <p>
     * Continuously reads user commands from the UI, parses them, and executes them until an exit command is issued.
     * Any exceptions during command processing are caught and an error message is displayed.
     * </p>
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (AsepException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * The main entry point of the Asep application.
     * <p>
     * Creates a new instance of Asep with a default file path for task storage and starts the application.
     * </p>
     *
     * @param args command line arguments (not used).
     */
    public static void main(String[] args) {
        new Asep("./data/asep.txt").run();
    }
}
