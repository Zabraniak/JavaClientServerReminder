public class PortException extends Exception {
    private final String port;

    public PortException(String port, String message) {
        super(message);
        this.port = port;
    }

    public String getPort() {
        return port;
    }
}