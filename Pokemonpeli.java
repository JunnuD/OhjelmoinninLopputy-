import java.io.*;
import java.util.*;

public class Pokemonpeli {


    public static final String RED_BOLD = "\033[1;31m"; // RED
    public static final String GREEN_BOLD = "\033[1;32m";   // GREEN
    public static final String BLUE_BOLD = "\033[1;34m";    // BLUE   JOS HALUAA BOLD POIS MUOKKAA VAIN [0,34m] MUOTOON TUON ESIM
    public static final String CYAN_BOLD = "\033[1;36m";    // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";   // WHITE
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW


    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
        ArrayList<String> pokemonit = new ArrayList<String>();
        ArrayList<String> tyypit = new ArrayList<String>();

        String tiedostoNimi = luoTiedosto(input);

        syotaPokemoninNimiJaTyyppi(input, pokemonit, tyypit, tiedostoNimi);

        pelaaMuistipeli(tiedostoNimi, input);

        pelaaUudestaan(input, tiedostoNimi);
    }                       
    

    public static String luoTiedosto(Scanner input) {
	
        System.out.println(" ");
        System.out.println("\033[1;33m" + " ~~~  " + "\033[1;31m" + "Tervetuloa pelaamaan" + "\033[1;33m" + " Pokemon " + "\033[1;31m" + "peliä" + "\033[1;33m" + " ~~~   " + "\033[1;37m");
        System.out.println(" ");
        System.out.println("Nyt on aika tehdä sinusta" + "\033[1;33m" + " Pokemon " + "\033[1;37m" + "mestari ! ");
    	       
        System.out.println("Anna " + "\033[1;33m" + "Pokemon" + "\033[1;37m" + " tiedostollesi nimi: ");
        String tiedostoNimi = input.nextLine();
        System.out.println(" ");

    		try {
    	        FileWriter tiedosto = new FileWriter(tiedostoNimi + ".txt");
                tiedosto.close();
	        } catch (IOException e) {
	            System.out.println("\033[1;33m" + "Pokemonien " + "\033[1;37m" + "pyydystys ei onnistunut :(");
	            input.close();
	            System.exit(0);
	        }
	        return tiedostoNimi;
	    }

    public static void syotaPokemoninNimiJaTyyppi(Scanner input, ArrayList<String> pokemonit, ArrayList<String> tyypit, String tiedostoNimi) {
    	boolean jatka = true;
    	while (jatka) {
    	    System.out.println("Anna haluamasi" + "\033[1;33m" + " Pokemonin "+ "\033[1;37m" + "nimi: ");
            String nimi = input.nextLine();
    	            
            pokemonit.add(nimi);

    	    System.out.println("Kerro vielä äskeisen antamasi" +"\033[1;33m" + " Pokemonin" + "\033[1;37m" + " tyyppi: ");
            String tyyppi = input.nextLine();
            System.out.println(" ");
            tyypit.add(tyyppi);

    	    try {
    	        FileWriter tiedosto = new FileWriter(tiedostoNimi + ".txt", true);
                tiedosto.write(nimi + " " + tyyppi + "\n");
                tiedosto.close();
            } catch (IOException e) {
                System.out.println("Hupsista," +"\033[1;33m" + "Pokemonit" + "\033[1;37m" + " pääsivät karkuun.");
            }

    	    System.out.println("Haluatko pyydystää vielä" + "\033[1;33m" + " Pokemoneja" + "\033[1;37m" + "? (kyllä/ei) ");
            String vastaus = input.nextLine().toLowerCase();
            System.out.println(" ");

	        while (!vastaus.equals("kyllä") && !vastaus.equals("ei")) {
    	        System.out.println("Vastaathan kyllä tai ei.");
                System.out.print("Haluatko lisätä vielä yhden" +"\033[1;33m" + " Pokemonin" + "\033[1;37m" + " ? (kyllä/ei) ");
				vastaus = input.nextLine().toLowerCase();
	        }
            if (vastaus.equals("ei")) {
                jatka = false;
            }
        }
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
                    System.out.println("Hienoa työtä, matkasi näyttää hyvältä" +"\033[1;33m" + " Pokemon" + "\033[1;37m" + " mestariksi !");
                    System.out.println(" ");
                    pisteet++;
                } else {
                    System.out.println("Nyt meni mönkään eli takaisin" + "\033[1;33m" + " Pokemon" + "\033[1;37m" + "kouluun.");
                    System.out.println("Oikea vastaus oli " + tyyppi + ".");

                    System.out.println(" ");
                }
            }
			lukija.close();

        } catch (FileNotFoundException e) {
            System.out.println("" + "\033[1;33m" + "Pokemon" + "\033[1;37m" + " tiedoston lukeminen epäonnistui.");
            return;
        }

        System.out.println("Sinussa on selvästi ainesta " + "\033[1;33m" + "Pokemon" + "\033[1;37m" + " mestariksi =)");
        System.out.println("Sait pyydystettyä " + pisteet + " " + "\033[1;33m" + "Pokemonia!" + "\033[1;37m" + "");
        System.out.println(" ");
        
    }
        
    public static void pelaaUudestaan(Scanner input, String tiedostoNimi) {
        System.out.println("Haluatko pelata uudelleen? (kyllä/ei)");
        String vastaus = input.nextLine().toLowerCase();
        System.out.println(" ");

        while (!vastaus.equals("kyllä") && !vastaus.equals("ei")) {
            System.out.println("Vastaathan kyllä tai ei.");
            System.out.println("Haluatko pelata uudelleen? (kyllä/ei)");
            vastaus = input.nextLine().toLowerCase();
        }
        if (vastaus.equals("kyllä")) {
			System.out.println("Luodaan uusi " + "\033[1;33m" + "Pokemon" + "\033[1;37m" + "  tiedosto uudelle pelille...");
            System.out.println(" ");
            String uusiTiedostoNimi = luoTiedosto(input);
            syotaPokemoninNimiJaTyyppi(input, new ArrayList<String>(), new ArrayList<String>(), uusiTiedostoNimi);
            pelaaMuistipeli(uusiTiedostoNimi, input);
            pelaaUudestaan(input, uusiTiedostoNimi);
        } else {
            System.out.println("\033[1;31m" + "~" + "\033[1;33m" + "Pokemonit" + "\033[1;31m" + " kiittävät pelaamisestasi~");
            System.out.println("");
            input.close();
        }
    }
   
}


        
    
    
