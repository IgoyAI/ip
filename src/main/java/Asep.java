import java.io.File;
import java.net.URISyntaxException;

/**
 * Represents the main application class for Asep.
 * Initializes the UI, storage, and task list, loads tasks from file,
 * and runs the main command processing loop.
 */
public class Asep {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
     * Determines the directory of the running JAR file and uses it to set the file path.
     */
    public static void main(String[] args) {
        try {
            // Determine the folder where the JAR file is located.
            File jarFile = new File(Asep.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File jarDir = jarFile.getParentFile();
            // Set the data file path to be in the same directory as the JAR.
            String filePath = jarDir.getAbsolutePath() + File.separator + "asep.txt";
            new Asep(filePath).run();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
