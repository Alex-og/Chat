package server;

public class ServerMain {
    private static final int PORT = 34522;

    public static void main(String[] args) {
        System.out.println("java ServerMain");
        System.out.println("Server started!");

        Server server = new Server(PORT);
        //server.start();
        server.ddd();
    }

}
