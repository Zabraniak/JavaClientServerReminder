import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.net.ServerSocket;

public class Server {
    private static int SERVER_PORT = 7777;
    ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            if (args.length > 0) {
                final String PORT_REGEX = "^([0-9]{1,5})$";
                if (!args[0].matches(PORT_REGEX)) {
                    throw new PortException(args[0], args[0] + " is not a valid port number!");
                } else {
                    System.out.println(ConsoleAssets.YELLOW + "Using port number: " + args[0] + ConsoleAssets.RESET);
                    SERVER_PORT = Integer.parseInt(args[0]);
                }
            } else {
                System.out.println(ConsoleAssets.YELLOW + "No arguments passed to the program!" + ConsoleAssets.RESET);
                System.out.println(ConsoleAssets.YELLOW + "Using default port number: " + SERVER_PORT + ConsoleAssets.RESET);
            }
            Server mainServer = new Server();
            System.out.println(ConsoleAssets.WHITE + "Server is running..." + ConsoleAssets.RESET);
            mainServer.startServer();
        } catch (PortException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public Server() {
        try {
            this.serverSocket = new ServerSocket(SERVER_PORT);
        } catch (IOException e) {
            System.err.println("Port is already in use!");
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.println("Port number is invalid!");
            System.exit(1);
        }
    }

    public void startServer() {
        Thread thread = new Thread(new ClientsHandler(serverSocket));
        thread.start();
    }
}
