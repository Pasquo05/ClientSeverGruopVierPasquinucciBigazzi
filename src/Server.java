import java.io.*;
import java.net.*;



public class Server {
    public Server(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("in attesa su " + port);
                Socket socket = serverSocket.accept();
                System.out.println("ricevuta connessione:"  + socket.getInetAddress() + ":" + socket.getPort());
                (new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}







