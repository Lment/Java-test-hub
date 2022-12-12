package AsyncServer;
import java.util.concurrent.Callable;

public class ServerWrap implements Callable<Void> {
    @Override
    public Void call() {
        AsyncServer server = new AsyncServer();
        server.start();
        return null;
    }
}
