import java.io.*;
import java.util.*;

public class Ohjelma {

    public static void main(String[] args) {
        
        // Kysytään käyttäjältä tiedoston nimi ja luodaan uusi tiedosto
        Scanner lukija = new Scanner(System.in);
        System.out.println("Anna tiedoston nimi: ");
        String tiedostoNimi = lukija.nextLine();
        tiedostoNimi += ".txt";
        try {
            File tiedosto = new File(tiedostoNimi);
            tiedosto.createNewFile();
            System.out.println("Tiedosto " + tiedostoNimi + " luotu.");
        } catch (IOException e) {
            System.out.println("Tiedoston luonti epäonnistui.");
        }
        
        // Kysytään käyttäjältä pokemonien nimiä ja tallennetaan ne tiedostoon
        try {
            PrintWriter kirjoittaja = new PrintWriter(new FileWriter(tiedostoNimi, true));
            String vastaus;
            do {
                System.out.println("Anna pokemonin nimi: ");
                String nimi = lukija.nextLine();
                kirjoittaja.println(nimi);
                System.out.println("Jatketaanko? (kyllä/ei)");
                vastaus = lukija.nextLine();
            } while (vastaus.equals("kyllä"));
            kirjoittaja.close();
            System.out.println("Pokemonit tallennettu tiedostoon.");
        } catch (IOException e) {
            System.out.println("Tiedostoon kirjoittaminen epäonnistui.");
        }
        
        // Luetaan tiedosto ja tallennetaan pokemonit listaan
        ArrayList<String> pokemonit = new ArrayList<>();
        try {
            Scanner tiedostonLukija = new Scanner(new File(tiedostoNimi));
            while (tiedostonLukija.hasNextLine()) {
                pokemonit.add(tiedostonLukija.nextLine());
            }
            tiedostonLukija.close();
        } catch (FileNotFoundException e) {
            System.out.println("Tiedostoa ei löydy.");
        }
        
        // Aloita muistipeli
        int pisteet = 0;
        for (String pokemon : pokemonit) {
            System.out.println("Mikä oli " + pokemon + "?");
            String vastaus = lukija.nextLine();
            if (vastaus.equals(pokemon)) {
                System.out.println("Oikein!");
                pisteet++;
            } else {
                System.out.println("Väärin. Oikea vastaus oli " + pokemon + ".");
            }
        }
        
        System.out.println("Peli päättyi. Saavutit " + pisteet + " pistettä. Kiitos pelistä!");
    }
}
