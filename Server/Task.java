import java.io.DataOutputStream;
import java.io.IOException;

public class Task implements Runnable {
    private final String message;
    private final DataOutputStream output;

    public Task(String message, DataOutputStream output) {
        this.message = message;
        this.output = output;
    }

    public void run() {
        try {
            this.output.writeUTF(this.message);
            System.out.println(ConsoleAssets.BLUE + "Message: [" + this.message + "] " + "has been sent" + ConsoleAssets.RESET);
        } catch (IOException e) {
            System.err.println("Task has been skipped! (Client disconnected while trying to send data)");
        }
    }
}
