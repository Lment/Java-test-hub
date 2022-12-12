package AsyncServer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class runAsyncServer {
    public static void main(String[] args) {
        int maxThreads = 1;
        ExecutorService executor = Executors.newFixedThreadPool(maxThreads);
        executor.submit(new ServerWrap());
    }
}