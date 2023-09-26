import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private static DataOutputStream output;
    private static final String IP_REGEX = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";
    private static final String PORT_REGEX = "^([0-9]{1,5})$";


    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String IP;
            int port;
            if (args.length > 1) {
                if (!args[0].matches(IP_REGEX)) throw new IPException(args[0], " is not a valid IP address!");
                if (!args[1].matches(PORT_REGEX)) throw new PortException(args[1], " is not a valid port number!");
                IP = args[0];
                port = Integer.parseInt(args[1]);
                System.out.println(ConsoleAssets.YELLOW + "Using IP address: " + IP + ConsoleAssets.RESET);
                System.out.println(ConsoleAssets.YELLOW + "Using port number: " + port + ConsoleAssets.RESET);
            } else {
                System.out.println(ConsoleAssets.YELLOW + "No arguments passed to the program!" + ConsoleAssets.RESET);
                IP = getIP(scanner);
                port = getPort(scanner);
            }
            while (true) {
                String userMessage = getMessage(scanner);
                int userDelay = getDelay(scanner);
                connectToServer(IP, port);
                sendMessageToServer(output, userMessage, userDelay);
                startNotificationHandler(socket);
            }
        } catch(DelayException | InputMismatchException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IPException e) {
            System.err.println(e.getIP() + e.getMessage());
            System.exit(2);
        } catch (PortException e) {
            System.err.println(e.getPort() + e.getMessage());
            System.exit(3);
        }
    }

    private static String getIP(Scanner scanner) throws IPException {
        final String IP_REGEX = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";
        System.out.print("Enter IP address: ");
        String IP = scanner.nextLine();
        if (!IP.matches(IP_REGEX)) {
            throw new IPException(IP, IP + " is not a valid IP address!");
        }
        return IP;
    }

    private static int getPort(Scanner scanner) throws PortException {
        final String PORT_REGEX = "^([0-9]{1,5})$";
        System.out.print("Enter port number: ");
        String port = scanner.nextLine();
        if (!port.matches(PORT_REGEX)) {
            throw new PortException(port, port + " is not a valid port number!");
        }
        return Integer.parseInt(port);
    }

    private static String getMessage(Scanner scanner) {
        System.out.print("Enter message: ");

        return scanner.nextLine();
    }
    private static int getDelay(Scanner scanner) throws DelayException, InputMismatchException {
        System.out.print("Enter delay in seconds: ");
        int userDelay = scanner.nextInt();
        scanner.nextLine();
        if (userDelay < 0) {
            throw new DelayException(userDelay, userDelay + " is not a valid delay!");
        }
        return userDelay;
    }

    private static void connectToServer(String IP, int port) {
        try {
            socket = new Socket(IP, port);
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Server is down!");
            System.exit(2);
        }
    }
    private static void sendMessageToServer(DataOutputStream output, String userMessage, int userDelay) {
        try {
            output.writeUTF(userMessage);
            output.writeLong(userDelay);
            System.out.println("Message has been sent!");
        } catch (IOException e) {
            System.err.println("Server is down (Error while sending a message!");
            System.exit(3);
        }
    }

    private static void startNotificationHandler(Socket socket) {
        Thread notificationHandler = new Thread(new NotificationHandler(socket));
        notificationHandler.setName("NotificationHandler");
        notificationHandler.start();
    }
}