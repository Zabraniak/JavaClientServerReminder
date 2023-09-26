import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TasksHandler {
    public static final int DEFAULT_THREAD_AMOUNT = 4;
    final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(DEFAULT_THREAD_AMOUNT);
}
