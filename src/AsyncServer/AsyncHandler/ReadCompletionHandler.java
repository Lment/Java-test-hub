package AsyncServer.AsyncHandler;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.*;

public class ReadCompletionHandler implements CompletionHandler<Integer, Void> {
        private final AsynchronousSocketChannel sc;
        private final ByteBuffer buffer;

    ReadCompletionHandler (AsynchronousSocketChannel in_sc, ByteBuffer in_buffer) {
        this.sc = in_sc;
        this.buffer = in_buffer;
    }

    @Override
    public void completed (Integer bytes, Void attachment) {

        WriteCompletionHandler wch = new WriteCompletionHandler(this.sc);

        this.buffer.flip();
        this.sc.write(this.buffer, null, wch);
        Charset charset = Charset.forName("UTF-8");
        String outputStr = new String(this.buffer.array()).trim();
        System.out.println("Writing "+outputStr+" into return buffer");

    }

    @Override
    public void failed (Throwable T, Void attachment) {
        T.printStackTrace();
    }
}
