package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleksandr Haleta
 */
public class Server {
    private static final int PORT = 35522;
    static List<Session> sessions = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (!server.isClosed()) {
                Socket clientSocket = server.accept();
                sessions.add(new Session(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
