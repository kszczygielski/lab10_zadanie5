import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class BankFinder {

    public static void main(String[] args) {
        // Scanner do wczytania danych od użytkownika
        Scanner scanner = new Scanner(System.in);

        // Prośba o podanie pierwszych trzech cyfr konta
        System.out.print("Podaj pierwsze trzy cyfry numeru konta: ");
        String kontoPrefix = scanner.nextLine();

        // URL do pliku online
        String urlString = "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";

        try {
            // Pobieranie pliku online
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            boolean found = false;

            // Odczytujemy plik linia po linii
            while ((line = reader.readLine()) != null) {
                // Zakładamy, że format każdej linii jest taki, że pierwsze 3 cyfry to kod banku
                // i jest to linia zawierająca numer banku oraz nazwę banku
                String[] parts = line.split("\t"); // Zakładamy, że dane są tabulatorami rozdzielone

                if (parts.length >= 2) {
                    String bankCode = parts[0].trim();  // Numer banku
                    String bankName = parts[1].trim();  // Nazwa banku

                    // Sprawdzamy, czy numer banku pasuje do wprowadzonych trzech cyfr
                    if (bankCode.startsWith(kontoPrefix)) {
                        // Jeśli pasuje, wyświetlamy informacje
                        System.out.println("Twoje konto znajduje się w banku: " + bankName);
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("Nie znaleziono banku dla podanych cyfr.");
            }

            // Zamykamy reader
            reader.close();

        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas pobierania pliku.");
            e.printStackTrace();
        }
    }
}
