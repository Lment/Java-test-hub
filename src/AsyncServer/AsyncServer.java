package AsyncServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import AsyncServer.AsyncHandler.*;

public class AsyncServer {
    int port = 40000;
    public AsyncServer () {}
    public AsyncServer(int port) {this.port = port;}
    protected void start()
    {
        System.out.println("Async Server running at port "+this.port);
        try {
            AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
            server.bind(new InetSocketAddress(this.port));
            AcceptCompletionHandler ach = new AcceptCompletionHandler(server);
            server.accept(null, ach);
            System.in.read();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
