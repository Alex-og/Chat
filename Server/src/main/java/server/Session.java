package server;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Session {
    private final Socket socket;

    public Session(Socket socket) {
        this.socket = socket;
    }

    public void getConnectToClient() {
        try (DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {
            String request = inputStream.readUTF();
            System.out.println("received: " + request);//For test
            Message message = new Gson().fromJson(request, Message.class);

            Service service = new Service(message);
            //service.startProg();
            String response = new Gson().toJson(service.getResponse());
            if (response.length() > 0) {
                outputStream.writeUTF(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
