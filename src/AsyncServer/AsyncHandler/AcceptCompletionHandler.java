package AsyncServer.AsyncHandler;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Void> {
    private final AsynchronousServerSocketChannel ssc;
    public AcceptCompletionHandler(AsynchronousServerSocketChannel in_ssc) {
        this.ssc = in_ssc;
    }
    @Override
    public void completed (AsynchronousSocketChannel sc, Void attachment) {
        try {
            int amount = (int) (Math.random() * 20);
            Thread.sleep(amount*1000);
            System.out.println("slept for "+amount);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        ssc.accept(null, this);
        System.out.println("Connection Established");
        ByteBuffer buffer = ByteBuffer.allocate(512);
        ReadCompletionHandler rch = new ReadCompletionHandler(sc, buffer);
        sc.read(buffer, null, rch);
        //System.out.println("Received "+buffer.toString());
    }

    @Override
    public void failed (Throwable T, Void attachment){
        T.printStackTrace();
    }
}
