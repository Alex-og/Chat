package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Session extends Thread {
    private final Socket clientSocket;
    private final Server server;
    private DataOutputStream output;
    private DataInputStream input;

    public Session(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClientSocket() throws IOException {
        this.input = new DataInputStream(clientSocket.getInputStream());
        this.output = new DataOutputStream(clientSocket.getOutputStream());
        /*output.writeUTF("Hello from server!");
        String msgFromClient = input.readUTF();
        System.out.println("msgFromClient: " + msgFromClient);*/

        /*InputStream inputStream = clientSocket.getInputStream();
        OutputStream outputStream = clientSocket.getOutputStream();*/
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;*/
        while (true) {
        String msgFromClient = input.readUTF();

            if ("quit".equalsIgnoreCase(msgFromClient)) {
                break;
            } else {
                handleMsg(msgFromClient);
            }
        }
        clientSocket.close();
    }

    private void handleMsg(String msg) throws IOException {
        List<Session> sessions = server.getSessions();
        //String msg = input.readUTF();
        for (Session session : sessions) {
            session.send(msg);
        }
    }

    private void send(String msg) throws IOException {
        output.writeUTF(msg);
    }

    /*public void getConnectToClient() {
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
    }*/

}
