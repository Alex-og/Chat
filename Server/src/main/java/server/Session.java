package server;

import java.io.*;
import java.net.Socket;

public class Session extends Thread {
    private final Socket clientSocket;
    private BufferedReader input;
    private BufferedWriter output;

    public Session(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        handleClientSocket();
    }

    private void handleClientSocket() {
        String msgFromClient;
        try {
            msgFromClient = input.readLine();
            try {
                output.write(msgFromClient + "\n");
                output.flush();
            } catch (IOException ex) {}
            try {
                while (!clientSocket.isClosed()) {
                    msgFromClient = input.readLine();
                    if (msgFromClient.equalsIgnoreCase("exit")) {
                        this.downService();
                        break;
                    }
                    System.out.println("Msg: " + msgFromClient);
                    handleMsg(msgFromClient);
                }
            } catch (NullPointerException ne) {}
        } catch (IOException e) {
            this.downService();
        }
    }

    private void handleMsg(String msg) throws IOException {
        for (Session ss : Server.sessions) {
            ss.send(msg);
        }
    }

    private void send(String msg) throws IOException {
        output.write(msg + "\n");
        output.flush();
    }

    private void downService() {
        try {
            if (!clientSocket.isClosed()) {
                clientSocket.close();
                input.close();
                output.close();;
            }
        } catch (IOException e) {}
    }
}
