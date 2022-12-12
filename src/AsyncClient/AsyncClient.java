package AsyncClient;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncClient {

    private String ip = "localhost";
    private int port = 40000;
    private String payload = "00209999997778222223";
    AsyncClient(String in_ip, int in_port, String in_payload)
    {
        this.ip = in_ip;
        this.port = in_port;
        this.payload = in_payload;
    }
    AsyncClient() {}
    void start()
    {
        try {
            AsynchronousSocketChannel sc = AsynchronousSocketChannel.open();
            SocketAddress serverAddr = new InetSocketAddress(this.ip, this.port);
            Future<Void> res = sc.connect(serverAddr);
            System.out.println("Will connect to remote");

            res.get();
            System.out.println("Connection Established");

            String outStr = this.payload;
            ByteBuffer buffer = ByteBuffer.wrap(outStr.getBytes());
            Future<Integer> sender = sc.write(buffer);
            sender.get();

            buffer.flip();
            Future<Integer> receiver = sc.read(buffer);
            System.out.println("Received : "+new String(buffer.array()).trim());
            receiver.get();
            buffer.clear();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
