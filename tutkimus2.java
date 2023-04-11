import java.io.*;
import java.util.*;

public class tutkimus2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> pokemonit = new ArrayList<String>();
        ArrayList<String> tyypit = new ArrayList<String>();

        // Luo uusi tiedosto
        System.out.println("Nyt on aika tehdä sinusta Pokemon mestari! ");
        System.out.println("Anna Pokemon tiedostollesi nimi: ");
        String tiedostoNimi = input.nextLine();
        tiedostoNimi += ".txt";
        try {
            File tiedosto = new File(tiedostoNimi);
            tiedosto.createNewFile();
        } catch (IOException e) {
            System.out.println("Pokemonien pyydystys ei onnistunut :(");
            return;
        }

        // Syötä Pokemonin nimi ja tyyppi tiedostoon
        boolean jatka = true;
        while (jatka) {
            System.out.println("Anna haluamasi Pokemonin nimi: ");
            String nimi = input.nextLine();
            pokemonit.add(nimi);

            System.out.println("Kerro vielä äskeisen antamasi Pokemonin tyyppi: ");
            String tyyppi = input.nextLine();
            tyypit.add(tyyppi);

            System.out.println("Haluatko pyydystää vielä Pokemoneja? (kyllä/ei) ");
            String vastaus = input.nextLine().toLowerCase();
            
            while (!vastaus.equals("kyllä") && !vastaus.equals("ei")) {
                System.out.println("Vastaathan kyllä tai ei.");
                System.out.print("Haluatko lisätä vielä yhden Pokemonin? (kyllä/ei) ");
                vastaus = input.nextLine().toLowerCase();
            }
            if (vastaus.equals("ei")) {
                jatka = false;
            }
        }

        // Pelaa muistipeliä
        int pisteet = 0;
        for (int i = 0; i < pokemonit.size(); i++) {
            String pokemoni = pokemonit.get(i);
            String tyyppi = tyypit.get(i);

            System.out.print("Mikä on " + pokemoni + " tyyppi? ");
            String vastaus = input.nextLine();
            if (vastaus.equalsIgnoreCase(tyyppi)) {
                System.out.println("Hyvä, olet saavuttamassa Pokemon mestarin titteliä!");
                pisteet++;
            } else {
                System.out.println("Nyt meni mönkään ja takaisin Pokemon kouluun, oikea vastaus oli " + tyyppi + ".");
            }
        }

        // Tulosta pisteet ja kiitos
        System.out.println("Onnittelut pääsit pelin loppuun! Sinussa on selvästi ainesta Pokemon mestariksi!");
        System.out.println("Sait " + pisteet + " pistettä!");
        System.out.println("Kiitos pelaamisesta!");
    } 
}
