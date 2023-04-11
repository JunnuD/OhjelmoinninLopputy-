import java.io.*;
import java.util.*;

public class tutkimus {

    // PITÄÄ SAADA METODEIKSI ERI JUTUT!

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        ArrayList<String> pokemonit = new ArrayList<String>();
        ArrayList<String> tyypit = new ArrayList<String>();

        // Luo uusi tiedosto
        System.out.println("Nyt on aika tehdä sinusta Pokémon mestari! ");
        System.out.println("Anna Pokémon tiedostollesi nimi: ");
        String tiedostoNimi = lukija.nextLine();
        System.out.println();
        try {
            File tiedosto = new File(tiedostoNimi + ".txt");
            tiedosto.createNewFile();
            System.out.println("Tiedosto " + tiedostoNimi + ".txt luotu onnistuneest!" );

        } catch (IOException e) {
            System.out.println("Pokémonien pyydystys ei onnistunut :(");
            return;
        }

        // Syötä Pokemonin nimi ja tyyppi tiedostoon
        boolean jatka = true;
        while (jatka) {
            System.out.println("Anna haluamasi Pokémonin nimi: ");
            String nimi = lukija.nextLine();
            pokemonit.add(nimi);

            System.out.println("Kerro vielä äskeisen antamasi Pokémonin tyyppi: ");
            String tyyppi = lukija.nextLine();
            tyypit.add(tyyppi);

            System.out.println("Haluatko pyydystää vielä Pokémoneja? (kyllä/ei) ");
            String vastaus = lukija.nextLine();
            if (!vastaus.equalsIgnoreCase("kyllä")) {
                jatka = false;
            }
        }

        // Tähän pitää korjata että VAIN syötteellä 'ei' se keskeyttää eikä mikä tahansa random pelitä

        // Pelaa muistipeliä
        int pisteet = 0;
        for (int i = 0; i < pokemonit.size(); i++) {
            String pokemoni = pokemonit.get(i);
            String tyyppi = tyypit.get(i);

            System.out.print("Mikä on " + pokemoni + " tyyppi? ");
            String vastaus = lukija.nextLine();
            if (vastaus.equalsIgnoreCase(tyyppi)) {
                System.out.println("Hyvä, olet saavuttamassa Pokémon mestarin titteliä!");
                pisteet++;
            } else {
                System.out.println("Nyt meni mönkään ja takaisin Pokémon kouluun, oikea vastaus oli " + tyyppi + ".");
            }
        }

        // Tulosta pisteet ja kiitos
        System.out.println("Onnittelut pääsit pelin loppuun! Sinussa on selvästi ainesta Pokémon mestariksi!");
        System.out.println("Sait " + pisteet + " pistettä!");
        System.out.println("Kiitos pelaamisesta!");
    }
}
