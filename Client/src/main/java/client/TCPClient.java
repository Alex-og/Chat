package client;

public class TCPClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 35522;

    public static void main(String[] args) {
        new Client(SERVER_ADDRESS, SERVER_PORT);
    }
}
