import java.io.*;
import java.util.*;

public class Ohjelma {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> pokemonit = new ArrayList<String>();
        ArrayList<String> tyypit = new ArrayList<String>();

        // Luo uusi tiedosto
        System.out.print("Anna tiedoston nimi: ");
        String tiedostoNimi = input.nextLine();
        try {
            File tiedosto = new File(tiedostoNimi);
            tiedosto.createNewFile();
        } catch (IOException e) {
            System.out.println("Tiedoston luominen epäonnistui.");
            return;
        }

        // Syötä Pokemonin nimi ja tyyppi tiedostoon
        boolean jatka = true;
        while (jatka) {
            System.out.print("Anna Pokemonin nimi: ");
            String nimi = input.nextLine();
            pokemonit.add(nimi);

            System.out.print("Anna Pokemonin tyyppi: ");
            String tyyppi = input.nextLine();
            tyypit.add(tyyppi);

            System.out.print("Haluatko jatkaa? (kyllä/ei) ");
            String vastaus = input.nextLine();
            if (!vastaus.equalsIgnoreCase("kyllä")) {
                jatka = false;
            }
        }

        // Pelaa muistipeliä
        int pisteet = 0;
        for (int i = 0; i < pokemonit.size(); i++) {
            String pokemon = pokemonit.get(i);
            String tyyppi = tyypit.get(i);

            System.out.print("Mikä on " + pokemon + " tyyppi? ");
            String vastaus = input.nextLine();
            if (vastaus.equalsIgnoreCase(tyyppi)) {
                System.out.println("Oikein!");
                pisteet++;
            } else {
                System.out.println("Väärin, oikea vastaus oli " + tyyppi + ".");
            }
        }

        // Tulosta pisteet ja kiitos
        System.out.println("Sait " + pisteet + " pistettä!");
        System.out.println("Kiitos pelaamisesta!");
    }
}
