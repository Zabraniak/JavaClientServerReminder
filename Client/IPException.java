public class IPException extends Exception {
    private final String IP;

    public IPException(String IP, String message) {
        super(message);
        this.IP = IP;
    }

    public String getIP() {
        return IP;
    }
}
