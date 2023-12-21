import java.io.*;
import java.net.*;
import java.util.*;


public class Client  extends Thread {
        Socket socket;
        public Client(Socket socket) { this.socket = socket; }
        public void run() {
            try {
                BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter bufferedWriter =  new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                while (true) {
                    String string = bufferedReader.readLine();
                    bufferedWriter.write(string);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


