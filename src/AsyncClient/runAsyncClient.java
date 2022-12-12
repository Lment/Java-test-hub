package AsyncClient;
public class runAsyncClient {
    public static void main(String[] args) {
        Thread test1 = new Thread(new ClientWrap("00209999997778444444"), "test1");
        test1.start();
        Thread test2 = new Thread(new ClientWrap("00209999997778555555"), "test2");
        test2.start();
        Thread test3 = new Thread(new ClientWrap("00209999997778666666"), "test3");
        test3.start();
        Thread test4 = new Thread(new ClientWrap("00209999997778777777"), "test4");
        test4.start();
    }
}