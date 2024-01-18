import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class ClothingStoreServer {
    private static List<String> catalog = new ArrayList<>();
    private static List<String> shoppingCart = new ArrayList<>();

    public static void main(String[] args) {
        initializeCatalog();

        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server in ascolto sulla porta 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connessione accettata da: " + clientSocket.getInetAddress().getHostName());

                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        int totale = 0 ;
        int flag = 0 ;

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);



            functionCasualServer(input,output );




            // Chiudi le risorse
            input.close();
            output.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendCatalogToClient(PrintWriter output) {
        output.println("Benvenuto nel negozio di abbigliamento! Catalogo disponibile:");

        for (int i = 0; i < catalog.size(); i++) {
            output.println((i + 1) + ". " + catalog.get(i));
        }

    }

    private static int addToCart(String selection) {
    int totale = 0  ;

        try {
            int productIndex = Integer.parseInt(selection) - 1;

            if ( productIndex == 0 ) {
                totale += 10 ;
            }
            if ( productIndex == 1 ) {
                totale += 15 ;
            }
            if ( productIndex == 2 ) {
                totale += 56 ;
            }

            System.out.println(productIndex);

            if (productIndex >= 0 && productIndex < catalog.size()) {
                String selectedProduct = catalog.get(productIndex);
                System.out.println(selectedProduct);
                shoppingCart.add(selectedProduct);
            }
            for (String s : shoppingCart) {
                System.out.println(s);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // Gestire eventuali errori nella conversione o selezione non valida
            e.printStackTrace();
        }

        return totale;
    }

    private static void initializeCatalog() {
        catalog.add("Maglietta --> 10$ ");
        catalog.add("Pantaloni --> 15$ ");
        catalog.add("Scarpe -->  56$ ");
        catalog.add("Uscita dal carello");
        // Aggiungi altri prodotti secondo necessità
    }

    private static void  functionCasualServer(BufferedReader input,PrintWriter output ) throws IOException , UnknownHostException {

        int totale = 0 ;

        // Invia il catalogo al client
        sendCatalogToClient(output);

        // Ricevi la selezione del cliente e aggiungi al carrello
        String selection = input.readLine();

        System.out.println(selection);

        totale += addToCart(selection);

        System.out.println( " questo e il totale --> " + totale );

        // Invia conferma al client
        output.println("Prodotto aggiunto al carrello correttamente.");

        output.println("Clicca 1 se vuoi conitnuare ad acquistare, clicca 2 se vuoi smettere di acquistare");

    }



}



