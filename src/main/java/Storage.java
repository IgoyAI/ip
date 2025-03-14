import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Handles file I/O for loading and saving tasks.
 * Uses a relative file path (based on the JAR file's location) and creates directories/files if needed.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     * If the file or its directory does not exist, they are created.
     * Throws an AsepException if the file content is corrupted.
     */
    public TaskList loadTasks() throws AsepException {
        ArrayList<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filePath);
        try {
            // Create the parent directory if it doesn't exist.
            if (path.getParent() != null && !Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            // Create the file if it doesn't exist.
            if (!Files.exists(path)) {
                Files.createFile(path);
                return new TaskList(tasks);
            }
            // Read the file line by line.
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    if (parts.length < 3) {
                        throw new AsepException("Data file is corrupted: insufficient parts in line: " + line);
                    }
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    Task task;
                    switch (type) {
                        case "T":
                            task = new Todo(parts[2]);
                            break;
                        case "D":
                            if (parts.length < 4) {
                                throw new AsepException("Data file is corrupted: deadline format error in line: " + line);
                            }
                            LocalDate by = LocalDate.parse(parts[3], Deadline.INPUT_DATE_FORMAT);
                            task = new Deadline(parts[2], by);
                            break;
                        case "E":
                            if (parts.length < 5) {
                                throw new AsepException("Data file is corrupted: event format error in line: " + line);
                            }
                            task = new Event(parts[2], parts[3], parts[4]);
                            break;
                        default:
                            throw new AsepException("Data file is corrupted: unknown task type in line: " + line);
                    }
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new AsepException("Error loading tasks from file: " + e.getMessage());
        } catch (Exception e) {
            throw new AsepException("Error parsing tasks: " + e.getMessage());
        }
        return new TaskList(tasks);
    }

    /**
     * Saves the TaskList to file using the specified format.
     */
    public void saveTasks(TaskList tasks) {
        Path path = Paths.get(filePath);
        try {
            // Create the parent directory if it doesn't exist.
            if (path.getParent() != null && !Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            // Write each task in the TaskList to the file.
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                for (Task task : tasks.getTasks()) {
                    String line = "";
                    if (task instanceof Todo) {
                        line = "T | " + (task.isDone ? "1" : "0") + " | " + task.getDescription();
                    } else if (task instanceof Deadline) {
                        Deadline deadline = (Deadline) task;
                        line = "D | " + (task.isDone ? "1" : "0") + " | " + task.getDescription()
                                + " | " + deadline.getBy().format(Deadline.INPUT_DATE_FORMAT);
                    } else if (task instanceof Event) {
                        Event event = (Event) task;
                        line = "E | " + (task.isDone ? "1" : "0") + " | " + task.getDescription()
                                + " | " + event.getFrom() + " | " + event.getTo();
                    }
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
