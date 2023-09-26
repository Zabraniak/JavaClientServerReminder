public class DelayException extends Exception {
    private final int delay;

    public DelayException(int delay, String message) {
        super(message);
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }
}
