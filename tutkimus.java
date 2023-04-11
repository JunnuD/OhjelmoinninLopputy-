import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ohjelma {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner lukija = new Scanner(System.in);
        System.out.println("Nimeä tiedostosi: (.txt pääte tulee automaattisesti.) ");
        File tiedostoNimi = new File(lukija.nextLine() + ".txt");

        System.out.println("Syötä tiedostoon sisältöä, vaikka yksi lause. Kirjoita: ");
        String sisältö = lukija.nextLine();

        kirjoitaTiedostoon(tiedostoNimi, sisältö);

        String[] maat = new String[5];
        String[] pääkaupungit = new String[5];

        try {
            File tiedosto = new File("maat.txt");
            Scanner tiedostonLukija = new Scanner(tiedosto);

            int indeksi = 0;
            while (tiedostonLukija.hasNextLine() && indeksi < 5) {
                String rivi = tiedostonLukija.nextLine();
                String[] tiedot = rivi.split(":");
                maat[indeksi] = tiedot[0];
                pääkaupungit[indeksi] = tiedot[1];
                indeksi++;
            }
            tiedostonLukija.close();
        } catch (FileNotFoundException e) {
            System.out.println("Tiedostoa ei löydy.");
            return;

        }

        int oikein = 0;
        for (int i = 0; i < 5; i++) {
            System.out.println("Mikä on " + maat[i] + "n" + " pääkaupunki?");
            String vastaus = lukija.nextLine();
            if (vastaus.equalsIgnoreCase(pääkaupungit[i])) {
                System.out.println("Oikein!");
                oikein++;
            } else {
                System.out.println("Väärin, oikea vastaus on " + pääkaupungit[i]);
            }
        }

        System.out.println("Peli päättyi. Saavutit " + oikein + " pistettä.");

    }

    public static void kirjoitaTiedostoon(File tiedostoNimi, String sisältö) {
        try {
            FileWriter kirjoittaja = new FileWriter(tiedostoNimi);
            kirjoittaja.write(sisältö);
            kirjoittaja.close();
            System.out.println("Kirjoitus onnistui nimeämääsi tiedostoon! ");

        } catch (IOException e) {
            System.out.println("Kirjoitus epäonnistui nimeämääsi tiedostoon. :( ");
        }
    }
}
