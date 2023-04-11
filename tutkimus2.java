import java.io.*;
import java.util.*;

public class tutkimus2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> pokemonit = new ArrayList<String>();
        ArrayList<String> tyypit = new ArrayList<String>();

        // Luo uusi tekstitiedosto
        System.out.println("~Tervetuloa pelaaman Pokemon peliä~");
        System.out.println("Nyt on aika tehdä sinusta Pokemon mestari ! ");
        System.out.println("Anna Pokemon tiedostollesi nimi: ");
        String tiedostoNimi = input.nextLine();
        
        try {
            FileWriter tiedosto = new FileWriter(tiedostoNimi+".txt");
            tiedosto.close();
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
            
            // Tallenna vastaukset tiedostoon
            try {
                FileWriter tiedosto = new FileWriter(tiedostoNimi+".txt", true);
                tiedosto.write(nimi + " " + tyyppi + "\n");
                tiedosto.close();
            } catch (IOException e) {
                System.out.println("Hupsista, Pokemonit pääsivät karkuun.");
            }

            System.out.println("Haluatko pyydystää vielä Pokemoneja ? (kyllä/ei) ");
            String vastaus = input.nextLine().toLowerCase();
            
            while (!vastaus.equals("kyllä") && !vastaus.equals("ei")) {
                System.out.println("Vastaathan kyllä tai ei.");
                System.out.print("Haluatko lisätä vielä yhden Pokemonin ? (kyllä/ei) ");
                vastaus = input.nextLine().toLowerCase();
            }
            if (vastaus.equals("ei")) {
                jatka = false;
            }     
        }
        
        System.out.println("Nyt on aika testata sinun Pokemon tuntemustasi !");
        
        pelaaMuistipeli(tiedostoNimi, input);
        
        }

        // Pelaa muistipeliä
        public static void pelaaMuistipeli(String tiedostoNimi, Scanner input) {
            int pisteet = 0;
            try {
                File tiedosto = new File(tiedostoNimi+".txt");
                Scanner lukija = new Scanner(tiedosto);

                while (lukija.hasNextLine()) {
                    String rivi = lukija.nextLine();
                    String[] tiedot = rivi.split(" ");
                    String pokemoni = tiedot[0];
                    String tyyppi = tiedot[1];

                    System.out.print("Mikä on " + pokemoni + " tyyppi? ");
                    String vastaus = input.nextLine();
                    if (vastaus.equalsIgnoreCase(tyyppi)) {
                        System.out.println("Hienoa työtä, matkasi näyttää hyvältä Pokemon mestariksi !");
                        pisteet++;
                    } else {
                        System.out.println("Nyt meni mönkään eli takaisin Pokemon kouluun.");
                        System.out.println("Oikea vastaus oli " + tyyppi + ".");
                    }
                }

                lukija.close();

            } catch (FileNotFoundException e) {
                System.out.println("Pokemon tiedoston lukeminen epäonnistui.");
                return;
            }

            System.out.println("Sinussa on selvästi ainesta Pokemon mestariksi =)");
            System.out.println("Sait pyydystettyä " + pisteet + " Pokemonia!");
        }
    }