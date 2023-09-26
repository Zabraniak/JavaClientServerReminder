import java.io.DataInputStream;
import java.net.Socket;
import java.io.IOException;

public class NotificationHandler implements Runnable {
    Socket socket;
    String message;

    public NotificationHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            message = input.readUTF();
            System.out.println(ConsoleAssets.BLUE + "\nMessage received: [" + message +"]" + ConsoleAssets.RESET);
            socket.close();
        } catch (Exception e) {
            System.err.println("Connection has been lost!");
            System.exit(2);
        } finally {
            closeSocket();
        }
    }

    private void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error while closing socket!");
            System.exit(4);
        }
    }
}
