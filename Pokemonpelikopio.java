import java.io.*;
import java.util.*;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Pokemonpelikopio {
    /**
    * @author Alister Gul
    * @author Junnu Danhammer
    * @date 16.4.2023 
    */



    /**
    * Otetaan erilaisia värejä käyttöön ohjelmaa varten!
    */

    public static final String RED_BOLD = "\033[1;31m";     // RED
    public static final String GREEN_BOLD = "\033[1;32m";   // GREEN
    public static final String BLUE_BOLD = "\033[1;34m";    // BLUE   JOS HALUAA BOLD POIS MUOKKAA VAIN [0,34m] MUOTOON TUON ESIM
    public static final String CYAN_BOLD = "\033[1;36m";    // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";   // WHITE
    public static final String YELLOW_BOLD = "\033[1;33m";  // YELLOW

    /**
     * 
     * Main osiossa laitetaan metodit oikeaan järjestykseen, jotta ohjelma runko pysyy kokonaisena
     */

    public static void main(String[] args) {
    	
    	Scanner input = new Scanner(System.in);
        ArrayList<String> pokemonit = new ArrayList<String>();
        ArrayList<String> tyypit = new ArrayList<String>();
        
        soitaTunnari("Theme.wav");

        String tiedostoNimi = luoTiedosto(input);

        syotaPokemoninNimiJaTyyppi(input, pokemonit, tyypit, tiedostoNimi);

        pelaaMuistipeli(tiedostoNimi, input);

        pelaaUudestaan(input, tiedostoNimi);
    }  
                         
    /**
     * @param filePath = ohjataan ohjelmalle polku josta löytää soitettava tunnari.
     */

    public static void soitaTunnari(String filePath) {
        try {
            File file = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tervetulo- viesti ohjelmaan! 
     * @param input saadaan lukija käyttöön, jonka avulla luodaan ensin uusi tiedosto .txt muodossa
     * @return palauttaa käyttäjän luoman tiedostonimen takaisin ohjelmaan.
     * @exception IOexception käytössä tiedostonkäsittelyä varten
     */

    public static String luoTiedosto(Scanner input) {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    	System.out.println("\033[1;33m" 
        		+ "██████╗  ██████╗ ██╗  ██╗███████╗███╗   ███╗ ██████╗ ███╗   ██╗\r\n"
        		+ "██╔══██╗██╔═══██╗██║ ██╔╝██╔════╝████╗ ████║██╔═══██╗████╗  ██║\r\n"
        		+ "██████╔╝██║   ██║█████╔╝ █████╗  ██╔████╔██║██║   ██║██╔██╗ ██║\r\n"
        		+ "██╔═══╝ ██║   ██║██╔═██╗ ██╔══╝  ██║╚██╔╝██║██║   ██║██║╚██╗██║\r\n"
        		+ "██║     ╚██████╔╝██║  ██╗███████╗██║ ╚═╝ ██║╚██████╔╝██║ ╚████║\r\n"
        		+ "╚═╝      ╚═════╝ ╚═╝  ╚═╝╚══════╝╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝\r\n\033[1;31m" 
                + "╔═════════════════════════════════════════════════════════════╗\r\n"
                + "║                           \033[1;30mMade by\033[1;31m                           ║\r\n"
                + "║                         \033[1;30mAlister Gul\033[1;31m                         ║\r\n"
                + "║                       \033[1;30mJunnu Dannhammer\033[1;31m                      ║\r\n"
                + "╚═════════════════════════════════════════════════════════════╝");

        System.out.println(" ");
        System.out.println("\033[1;33m" + " ~~~  " + "\033[1;31m" + "Tervetuloa pelaamaan" + "\033[1;33m" + " Pokemon " + "\033[1;31m" + "peliä" + "\033[1;33m" + "  ~~~   " + "\033[1;37m");
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

        /**
         * @param input samaa lukijaa hyödynnetään jälleen, jolla saadaan käyttäjän luomaan tiedostoon sisältö
         * @param pokemonit = käyttäjän itse syöttämät pokemonit, jotka saa valita vapaasti
         * @param tyypit = pokemonien tietyt tyypit (esim. vesi, tuli sähkö jne)
         * @param tiedostoNimi = Sama jonka käyttäjä loi aiemmassa vaiheessa ohjelmaa.
         */

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

    /**
     * Tässä metodissa käydään itse muistipeli läpi. Ohjelma kyselee käyttäjän omista syötteistä
     * ja vertaa täsmääkö tiedot
     * Tässä myös on pisteydenlasku  ja tulostus siitä kun metodi on läpikäyty.
     * @param tiedostoNimi 
     * @param input
     */

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
                    System.out.println("Nyt meni mönkään eli takaisin" + "\033[1;33m" + " Pokemon " + "\033[1;37m" + "kouluun.");
                    System.out.println("Oikea vastaus oli " + tyyppi + ".");

                    System.out.println(" ");
                }
            }
			lukija.close();

        } catch (FileNotFoundException e) {
            System.out.println("" + "\033[1;33m" + "Pokemon" + "\033[1;37m" + " tiedoston lukeminen epäonnistui.");
            System.out.println(" ");
            return;
        }

        System.out.println("Sinussa on selvästi ainesta " + "\033[1;33m" + "Pokemon" + "\033[1;37m" + " mestariksi =)");
        System.out.println("Sait pyydystettyä " + pisteet + " " + "\033[1;33m" + "Pokemonia" + "\033[1;37m" + "");
        System.out.println(" ");
        
    }
    
    /**
     * Uudelleen pelaamista varten oleva metodi, joka tulee kun muistipeli päättyy
     * Käyttäjä saa halutessaan käydä kaiken uudestaan ilman että täytyy aloittaa ohjelma alusta
     * Toiminnallisuus luotu suoraan ohjelman sisään.
     * @param input
     * @param tiedostoNimi
     * Mikäli käyttähä ei tahdo pelata uudelleen tulee loppu kiitokset.
     */

    public static void pelaaUudestaan(Scanner input, String tiedostoNimi) {
        System.out.println("Haluatko aloittaa " + "\033[1;33m" + "Pokemonien" + "\033[1;37m" + " metsästyksen uudelleen? (kyllä/ei)");
        String vastaus = input.nextLine().toLowerCase();
        System.out.println(" ");

        while (!vastaus.equals("kyllä") && !vastaus.equals("ei")) {
            System.out.println("Vastaathan kyllä tai ei.");
            System.out.println("Haluatko pelata uudelleen? (kyllä/ei)");
            vastaus = input.nextLine().toLowerCase();
        }
        if (vastaus.equals("kyllä")) {
			System.out.println("Ladataan uutta " + "\033[1;33m" + "Pokemon " + "\033[1;37m" + "peliä...");
            System.out.println(" ");
            String uusiTiedostoNimi = luoTiedosto(input);
            syotaPokemoninNimiJaTyyppi(input, new ArrayList<String>(), new ArrayList<String>(), uusiTiedostoNimi);
            pelaaMuistipeli(uusiTiedostoNimi, input);
            pelaaUudestaan(input, uusiTiedostoNimi);
        } else {
            System.out.println("\033[1;33m" + " ~~~  " + "\033[1;31m" + "Kiitokset pelaamisesta " + "\033[1;33m" + "Pokemonit " + "\033[1;33m" + "\033[1;31m" + "sanoivat" +  "\033[1;33m" +"  ~~~" + "\033[0m");
            System.out.println("");
            System.out.println(""
        			+ "     ⬛⬛⬛⬛⬛⬛\r\n"
            		+ "   ⬛🟥🟥🟥🟥🟥🟥⬛\r\n"
            		+ "  ⬛🟥🟥🟥🟥🟥🟥🟥⬛\r\n"
            		+ " ⬛🟥🟥🟥🟥🟥🟥🟥🟥⬛\r\n"
            		+ "⬛🟥🟥🟥🟥🟥🟥🟥🟥🟥⬛\r\n"
            		+ "⬛🟥🟥🟥⬛⬛⬛🟥🟥🟥⬛\r\n"
            		+ "⬛⬛⬛⬛⬛⬜⬛⬛⬛⬛⬛\r\n"
            		+ "⬛⬜⬜⬜⬛⬛⬛⬜⬜⬜⬛\r\n"
            		+ "⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛\r\n"
            		+ " ⬛⬜⬜⬜⬜⬜⬜⬜⬜⬛\r\n"
            		+ "  ⬛⬜⬜⬜⬜⬜⬜⬜⬛\r\n"
            		+ "   ⬛⬜⬜⬜⬜⬜⬜⬛\r\n"
            		+ "     ⬛⬛⬛⬛⬛⬛");
            System.out.println("");
            
            input.close();
        }
    }
}


        
    
    
