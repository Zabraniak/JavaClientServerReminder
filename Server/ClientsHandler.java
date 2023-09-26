import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class ClientsHandler implements Runnable {
    private final ServerSocket server;

    public ClientsHandler(ServerSocket socket) {
        this.server = socket;
    }

    public void run() {
        try {
            while(server.isBound()) {
                try {
                    System.out.println(ConsoleAssets.YELLOW + "Waiting for a client..." + ConsoleAssets.RESET);
                    Socket destination = this.server.accept();
                    System.out.println(ConsoleAssets.GREEN + "Client [" + destination + "] connected!" + ConsoleAssets.RESET);
                    DataInputStream input = new DataInputStream(destination.getInputStream());
                    DataOutputStream output = new DataOutputStream(destination.getOutputStream());
                    String message = input.readUTF();
                    long delay = input.readLong();
                    TasksHandler.scheduler.schedule(new Task(message, output), delay, TimeUnit.SECONDS);
                    System.out.println(ConsoleAssets.BLUE + "Message: [" + message + "] scheduled for: " + delay + "s from now!" + ConsoleAssets.RESET);
                    System.out.println(ConsoleAssets.RED + "Client [" + destination + "] handled!" + ConsoleAssets.RESET);
                } catch (IOException e) {
                    System.err.println("Client has disconnected while sending data!");
                }
            }
        } catch (RejectedExecutionException e) {
            System.err.println("Task has been rejected! [Scheduler shutdown]");
        }
    }
}
