import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class BankFinder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj pierwsze trzy cyfry numeru konta: ");
        String kontoPrefix = scanner.nextLine();

        String urlString = "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";

        try {
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");

                if (parts.length >= 2) {
                    String bankCode = parts[0].trim();
                    String bankName = parts[1].trim();

                    if (bankCode.startsWith(kontoPrefix)) {
                        System.out.println("Twoje konto znajduje się w banku: " + bankName);
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("Nie znaleziono banku dla podanych cyfr.");
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas pobierania pliku.");
            e.printStackTrace();
        }
    }
}
