package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleksandr Haleta
 */
public class Server /*extends Thread*/ {
    private final int serverPort;
    private List<Session> sessions = new ArrayList<>();

    public Server(int serverPort) {
        this.serverPort = serverPort;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    /*@Override
    public void run() {
        try (ServerSocket server = new ServerSocket(serverPort)) {
            while (true) {
                *//*Session session = new Session(server.accept());
                session.getConnectToClient();*//*
                Socket clientSocket = server.accept();
                Session session = new Session(this, clientSocket);
                sessions.add(session);
                session.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    public void ddd() {
        try (ServerSocket server = new ServerSocket(serverPort)) {
            while (true) {
                /*Session session = new Session(server.accept());
                session.getConnectToClient();*/
                Socket clientSocket = server.accept();
                Session session = new Session(this, clientSocket);
                sessions.add(session);
                session.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
