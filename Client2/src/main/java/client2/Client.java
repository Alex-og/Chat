package client2;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Oleksandr Haleta
 */
public class Client {
    private Socket socket;
    private BufferedReader input;
    private BufferedWriter output;
    private BufferedReader inputUser;
    private String address;
    private int port;
    private String nickName;
    private String localTime;



    public Client(String address, int port) {
        this.address = address;
        this.port = port;
        try {
            this.socket = new Socket(address, port);
        } catch (IOException e) {
            try {
                throw new IOException("Socket failed");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        try {
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.pressNickname();
            new ReadMsg().start();
            new WriteMsg().start();
        } catch (IOException e) {
            this.downService();
        }
    }

    private void pressNickname() {
        System.out.print("Press your nick: ");
        try {
            nickName = inputUser.readLine();
            output.write("Hello " + nickName + "\n");
            output.flush();
        } catch (IOException ignored) {}
    }

    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                input.close();
                output.close();
            }
        } catch (IOException ignored) {}
    }

    private class ReadMsg extends Thread {
        @Override
        public void run() {
            String string;
            try {
                while (true) {
                    string = input.readLine();
                    if (string.equalsIgnoreCase("exit")) {
                        Client.this.downService();
                        break;
                    }
                    System.out.println(string);
                }
            } catch (IOException e) {
                Client.this.downService();
            }
        }
    }

    public class WriteMsg extends Thread {
        @Override
        public void run() {
            while (true) {
                String string;
                try {
                    localTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    string = inputUser.readLine();
                    if (string.equalsIgnoreCase("exit")) {
                        output.write("Exit" + "\n");
                        Client.this.downService();
                        break;
                    } else {
                        output.write("(" + localTime + ")" + nickName + ": " + string + "\n");
                    }
                    output.flush();
                } catch (IOException e) {
                    Client.this.downService();
                }
            }
        }
    }
}