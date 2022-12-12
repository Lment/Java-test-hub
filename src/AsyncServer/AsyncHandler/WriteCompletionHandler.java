package AsyncServer.AsyncHandler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class WriteCompletionHandler implements CompletionHandler<Integer, Void> {
    private final AsynchronousSocketChannel sc;

    WriteCompletionHandler (AsynchronousSocketChannel in_sc) {
        this.sc = in_sc;
    }

    @Override
    public void completed (Integer bytes, Void attachment) {
        try {
            sc.close();
            System.out.println("Socket closed");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed (Throwable T, Void attachment) {
        T.printStackTrace();
    }
}
