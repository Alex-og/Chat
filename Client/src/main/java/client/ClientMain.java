package client;

import java.io.*;
import java.net.Socket;

public class ClientMain {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 34522;

    public static void main(String[] args) {
        try(Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
            while (true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String msg = reader.readLine();
                output.writeUTF(msg);
                String replyMsg = input.readUTF();
                System.out.println(replyMsg);
            }
        } catch (IOException ex) {}
    }
}
