package AsyncClient;
public class ClientWrap implements Runnable{
    private String payload;
    ClientWrap (String in_payload) {
        this.payload = in_payload;
    }
    public void run() {
        AsyncClient client = new AsyncClient("192.168.0.1", 40000, this.payload);
        client.start();
    }
}
