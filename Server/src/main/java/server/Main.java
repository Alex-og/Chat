package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int PORT = 34522;

    public static void main(String[] args) {
        System.out.println("java Main");
        System.out.println("Server started!");
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                /*Session session = new Session(server.accept());
                session.getConnectToClient();*/
                Socket clientSocket = server.accept();
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                //OutputStream outputStream = clientSocket.getOutputStream();
                //outputStream.write("Hello from server!\n".getBytes());
                output.writeUTF("Hello from server!");
                String msgFromClient = input.readUTF();
                System.out.println("msgFromClient: " + msgFromClient);
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
