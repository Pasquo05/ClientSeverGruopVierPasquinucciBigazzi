import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ClothingStoreClient {
    public static void main(String[] args) {

        try {
        Socket socket = new Socket("localhost", 12345);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        fuctioncaasual(input, output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fuctioncaasual(BufferedReader input, PrintWriter output ) throws IOException , UnknownHostException {



            // Ricevi e stampa il catalogo dal server

            String catalogItem;

            for( int i=0 ; i<5 ; i++) {
                catalogItem = input.readLine();
                System.out.println( catalogItem );
            }


            // Richiedi all'utente di selezionare un prodotto
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Seleziona un prodotto inserendo il numero corrispondente:");

            String selection = reader.readLine();

            // Invia la selezione al server
            output.println(selection);
            // Ricevi e stampa la conferma dal server
            String confirmation = input.readLine();

            System.out.println("Risposta dal server: " + confirmation);

    }





}