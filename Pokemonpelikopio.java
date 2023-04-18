// Importoidaan tarvittavat Java-paketit
import java.io.*;
import java.util.*;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import javax.swing.*;

public class Pokemonpelikopio  {     // Koko koodi alkaa luokan määrittelyllä nimeltä "Pokemonpeli" joka sisältää ohjelman toiminnan kannalta tarvittavat metodit ja muuttujat.
    /**
    * @author Alister Gul
    * @author Junnu Danhammer
    * @date 17.4.2023 
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

    private static Clip clip;   // luo staattisen muuttujan nimeltä "clip", joka mahdollistaa äänen toistamisen
    
    /** 
     * Main osiossa laitetaan metodit oikeaan järjestykseen, jotta ohjelma runko pysyy kokonaisena
     * Myös luodaan pokemonien ja niiden tyyppien listat (new ArrayList) molemmille
     */

    public static void main(String[] args) {
    	
    	Scanner input = new Scanner(System.in);		//Luodaan scanner olio nimeltä input
        ArrayList<String> pokemonit = new ArrayList<String>();  // luodaan omat listat pokemonien nimille
        ArrayList<String> tyypit = new ArrayList<String>();     // luodaan omat listat pokemonien tyypeille
        
       // kaikki ohjelmassa olevat metodit alla
        
        soitaTunnari("Theme.wav");   

        String tiedostoNimi = luoTiedosto(input);   

        syotaPokemoninNimiJaTyyppi(input, pokemonit, tyypit, tiedostoNimi);    

        pelaaMuistipeli(tiedostoNimi, input);    

        pelaaUudestaan(input, tiedostoNimi);    
    }  
                         
    /**
     * // Metodi soitaTunnari, joka soittaa Pokemon-pelin teemakappaleen.
     * @param filePath = ohjataan ohjelmalle polku josta löytää soitettava tunnari.
     */

    public static void soitaTunnari(String filePath) {      
        try {
            if (clip != null) {
                clip.stop();  // Lopetaan jo soimassa oleva tunnari, jos ei ole "null" (Toiminta mikäli käyttäjä haluaa pelata pelin uudestaan --> tunnari alkaa alusta)
            }
            File file = new File(filePath);     // Luodaan uusi tiedosto-olio
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);   //Haetaan tiedoston syöte virta
            clip = AudioSystem.getClip();   // Luodaan uusi Clip-olio, johon tallennetaan äänitiedoston toistoon liittyvät tiedot
            clip.open(audioIn);     // tunnari alkaa soimaan
            clip.loop(Clip.LOOP_CONTINUOUSLY);  // Loputon audio loop
            clip.start();  // Aloitetaan soittamaan alusta 
        } catch (UnsupportedAudioFileException e) { // Virheilmoitus jos tunnarin toisto ei onnistu.
            System.out.println("\033[1;33m" + "Pokemon " + "\033[1;37m" + "tunnarin soittaminen epäonnistui. ");
            e.printStackTrace();
        } catch (IOException e) { // Virheilmoituksia mahdollisesti monta erilaista
            e.printStackTrace();
        } catch (LineUnavailableException e) {  // Virheilmoituksia mahdollisesti monta erilaista
            e.printStackTrace();
        }
    }

    /**
     * Tervetulo- viesti ohjelmaan ja tiedoston luonti!
     * @param input saadaan lukija käyttöön, jonka avulla luodaan ensin uusi tiedosto .txt muodossa
     * @return palauttaa käyttäjän luoman tiedostonimen takaisin ohjelmaan.
     * @exception IOexception käytössä tiedostonkäsittelyä varten
     */

    public static String luoTiedosto(Scanner input) {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        String str = 
        		"██████╗  ██████╗ ██╗  ██╗███████╗███╗   ███╗ ██████╗ ███╗   ██╗\r\n" +
        		"██╔══██╗██╔═══██╗██║ ██╔╝██╔════╝████╗ ████║██╔═══██╗████╗  ██║\r\n" +
        		"██████╔╝██║   ██║█████╔╝ █████╗  ██╔████╔██║██║   ██║██╔██╗ ██║\r\n" +
        		"██╔═══╝ ██║   ██║██╔═██╗ ██╔══╝  ██║╚██╔╝██║██║   ██║██║╚██╗██║\r\n" +
        		"██║     ╚██████╔╝██║  ██╗███████╗██║ ╚═╝ ██║╚██████╔╝██║ ╚████║\r\n" +
        		"╚═╝      ╚═════╝ ╚═╝  ╚═╝╚══════╝╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝\r\n";
        		str = str.replaceAll("█", "\033[1;33m█\033[1;34m");     // // korvaa merkin █ ensin keltaisella, sitten sinisellä, jotta saadaan haluttu vaikutelma
        		System.out.print("\033[1;33m" + str);
        		
        System.out.println("\033[1;31m" 
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
        String tiedostoNimi = input.nextLine();     // syötetään Scanner inputtiin tiedoston nimi
        System.out.println(" ");

    		try {
    	        FileWriter tiedosto = new FileWriter(tiedostoNimi + ".txt"); // Luodaan käyttäjän tekemä tiedosto
                tiedosto.close();		//sulkee tiedoston luonti prosessin luotuaan tiedoston jatkaakseen metodia
	        } catch (IOException e) {		// jos yritetään lukea tai kirjoittaa tiedostoon, joka ei ole käytettävissä niin käsittelee sen
	            System.out.println("\033[1;33m" + "Pokemonien " + "\033[1;37m" + "pyydystys ei onnistunut :("); // Virheilmoitus jos ei onnistu.
	            input.close();		// sulkee scanner inputin
	            System.exit(0);		//poistuu koko ohjelmasta jos tiedoston luonti ei onnistu
	        }
	        return tiedostoNimi;    //Palautetaan tiedosto eteenpäin syotaPokemoninNimiJaTyyppi metodin käytettäväksi
	    }

        /**
         * Tässä syötetään käyttäjän luomaan tiedostoon pelin pelaamiseen tarvittavat asiat
         * @param input samaa lukijaa hyödynnetään jälleen, jolla saadaan käyttäjän luomaan tiedostoon sisältö
         * @param pokemonit = käyttäjän itse syöttämät pokemonit, jotka saa valita vapaasti
         * @param tyypit = pokemonien tietyt tyypit (esim. vesi, tuli sähkö jne)
         * @param tiedostoNimi = Sama jonka käyttäjä loi aiemmassa vaiheessa ohjelmaa.
         */

    public static void syotaPokemoninNimiJaTyyppi(Scanner input, ArrayList<String> pokemonit, ArrayList<String> tyypit, String tiedostoNimi) {
    	boolean jatka = true;		//määrittelee boolean-tyyppisen muuttujan nimeltä "jatka" ja asettaa sen arvoksi "true"
    	while (jatka) {		//aloittaa while-silmukan, joka suoritetaan niin kauan kuin muuttuja "jatka" on totta
    	    System.out.println("Anna haluamasi" + "\033[1;33m" + " Pokemonin "+ "\033[1;37m" + "nimi: ");
            String nimi = input.nextLine();     // syötetään Scanner inputtiin pokemonien nimet
    	            
            pokemonit.add(nimi);    // Lisätään listaan pokemonin/pokemonien tyyppi/tyypit

    	    System.out.println("Kerro vielä äskeisen antamasi" +"\033[1;33m" + " Pokemonin" + "\033[1;37m" + " tyyppi: ");
            String tyyppi = input.nextLine();   // syötetään Scanner inputtiin pokemonin tyyppi
            System.out.println(" ");
            tyypit.add(tyyppi);     // Lisätään listaan pokemonin/pokemonien tyyppi/tyypit

    	    try {
    	        FileWriter tiedosto = new FileWriter(tiedostoNimi + ".txt", true);   //avataan käyttäjän luoma tiedosto   
                tiedosto.write(nimi + " " + tyyppi + "\n");		//// Kirjoitetaan käyttäjän syötteet tiedostoon
                tiedosto.close();		//suljetaan tiedosto siirtyäkseen eteenpäin metodissa
            } catch (IOException e) {		// jos yritetään lukea tai kirjoittaa tiedostoon, joka ei ole käytettävissä niin käsittelee sen
                System.out.println("Hupsista," +"\033[1;33m" + "Pokemonit" + "\033[1;37m" + " pääsivät karkuun."); // Virheilmoitus jos ei onnistu.
            }

    	    System.out.println("Haluatko pyydystää vielä" + "\033[1;33m" + " Pokemoneja" + "\033[1;37m" + "? (kyllä/ei) "); 
            String vastaus = input.nextLine().toLowerCase();	//käyttäjä syöttää scanner inputtiin haluaako lisätä pokemonea
            System.out.println(" ");

	        while (!vastaus.equals("kyllä") && !vastaus.equals("ei")) {		//jos käyttäjän syöttämä vastaus ei ole "kyllä" tai "ei" niin ohjelma tulosaa alla olevat ja kysyy uudestaan
    	        System.out.println("Vastaathan kyllä tai ei.");
                System.out.print("Haluatko lisätä vielä yhden" +"\033[1;33m" + " Pokemonin" + "\033[1;37m" + " ? (kyllä/ei) ");
				vastaus = input.nextLine().toLowerCase();	//käyttäjä syöttää uudestaan scanner inputtiin haluaako lisätä pokemonea
	        }
            if (vastaus.equals("ei")) { 	// Jos vastaus on "ei" eli false, niin halutut pokemonit on syötetty ja ohjelma jatkaa seuraavaan vaiheeseen
                jatka = false;
            }
        }
    }

    /**
     * Tässä metodissa käydään itse muistipeli läpi. Ohjelma kyselee käyttäjän omista syötteistä
     * ja vertaa täsmääkö tiedot
     * Tässä myös on pisteydenlasku ja tulostus siitä kun metodi on läpikäyty.
     * @param tiedostoNimi = käyttäjän luoma tiedosto
     * @param input = syötteen lukija
     */

    public static void pelaaMuistipeli(String tiedostoNimi, Scanner input) {
        int pisteet = 0;    // Pisteiden laskuri
            try {		
                File tiedosto = new File(tiedostoNimi + ".txt");	// avaa luodun tiedoston
                Scanner lukija = new Scanner(tiedosto);		// luo scanner olion nimeltä lukija

            while (lukija.hasNextLine()) {          //Lukija käy läpi tiedoston ja erottelee välilyönnistä nimen tyypistä.
                String rivi = lukija.nextLine(); 
                String[] tiedot = rivi.split(" ");
                String pokemoni = tiedot[0];
                String tyyppi = tiedot[1];

               System.out.print("Mikä on " + pokemoni + " tyyppi? "); // Muistipeli alkaa!
                String vastaus = input.nextLine();		// käyttäjä syöttää scanner inputtiin vastauksen tyyppi kysymykseen
                    
                if (vastaus.equalsIgnoreCase(tyyppi)) {		// Tarkistetaan vastaus pelaajan syöttämän vastauksen ja oikean vastauksen välillä.
                    System.out.println("Hienoa työtä, matkasi näyttää hyvältä" +"\033[1;33m" + " Pokemon" + "\033[1;37m" + " mestariksi !");
                    System.out.println(" ");
                    pisteet++;		//Jos vastaus on oikein, lisätään yksi piste pelaajan pisteisiin
                } else {        // jos ei mene vastaus oikein tulee pieni maininta aiheesta ja oikean vastauksen tulostus.
                    System.out.println("Nyt meni mönkään eli takaisin" + "\033[1;33m" + " Pokemon " + "\033[1;37m" + "kouluun."); 
                    System.out.println("Oikea vastaus oli " + tyyppi + ".");

                    System.out.println(" ");
                }
            }
			lukija.close();		// scanner lukija olio suljetaan

        } catch (FileNotFoundException e) {     // jos yritetään lukea tai kirjoittaa tiedostoon, joka ei ole käytettävissä niin käsittelee sen
            System.out.println("" + "\033[1;33m" + "Pokemon" + "\033[1;37m" + " tiedoston lukeminen epäonnistui.");		// Virheilmoitus jos ei onnistu.
            System.out.println(" ");
            return;		//lopettaa metodin suorituksen jos tiedoston luku ei onnistunut
        }

        System.out.println("Sinussa on selvästi ainesta " + "\033[1;33m" + "Pokemon" + "\033[1;37m" + " mestariksi =)");    
        System.out.println("Sait pyydystettyä " + pisteet + " " + "\033[1;33m" + "Pokemonia" + "\033[1;37m" + "");          // Pisteiden tulostus
        System.out.println("");
        
    }
    
    /**
     * Uudelleen pelaamista varten oleva metodi, joka tulee kun muistipeli päättyy
     * Käyttäjä saa halutessaan käydä kaiken uudestaan ilman että täytyy aloittaa ohjelma alusta
     * Toiminnallisuus luotu suoraan ohjelman sisään.
     * @param input
     * @param tiedostoNimi
     * Mikäli käyttäjä ei tahdo pelata uudelleen tulee loppu kiitokset.
     */

    public static void pelaaUudestaan(Scanner input, String tiedostoNimi) {
        System.out.println("Haluatko aloittaa " + "\033[1;33m" + "Pokemonien" + "\033[1;37m" + " metsästyksen uudelleen? (kyllä/ei)");
        String vastaus = input.nextLine().toLowerCase();		// käyttäjältä syöttää scanner inputtiin vastauksen pelin pelaaisesta uudestaan
        System.out.println( "");

        while (!vastaus.equals("kyllä") && !vastaus.equals("ei")) {		////jos käyttäjän syöttämä vastaus ei ole "kyllä" tai "ei" niin ohjelma tulosaa alla olevat ja kysyy uudestaan
            System.out.println("Vastaathan kyllä tai ei.");      
            System.out.println("Haluatko pelata uudelleen? (kyllä/ei)");
            vastaus = input.nextLine().toLowerCase();		//käyttäjä syöttää uudestaan scanner inputtiin haluaako pelata uudestaan
        }
        if (vastaus.equals("kyllä")) {          // Mikäli käyttäjä haluaa pelata uudelleen, alkaa se tästä ja siirtyy alla oleviin metodeihin.
			System.out.println("Ladataan uutta " + "\033[1;33m" + "Pokemon " + "\033[1;37m" + "peliä...");
            System.out.println("");

            // metodit jotka suoritetaan jos pelaaja haluaa pelata uudelleen
            soitaTunnari("Theme.wav"); 
            String uusiTiedostoNimi = luoTiedosto(input);
            syotaPokemoninNimiJaTyyppi(input, new ArrayList<String>(), new ArrayList<String>(), uusiTiedostoNimi);
            pelaaMuistipeli(uusiTiedostoNimi, input);
            pelaaUudestaan(input, uusiTiedostoNimi);

        } else {        // jos käyttäjä ei halua pelata alkaa se tästä ja LOPPU HEIPAT POKEMONEILTA!

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
            
            input.close(); // Suljetaan scanner lukija olio ja ollaan iloisia :)
            
            // Luodaan JFrame objekti pelin päätteeksi. Pieni söpö kuva onnitteluiden oheen. :)
            JFrame frame = new JFrame();
            frame.setLayout(new BorderLayout());

            // Luo ImageIcon ja JLabel objekti
            ImageIcon imageIcon = new ImageIcon("imageee.jpg");
            JLabel label = new JLabel(imageIcon);

            // Lisätään label ja layout keskelle näyttöä.
            frame.add(label, BorderLayout.CENTER);

            // Asetetaan koko ja näkyvyys sekä päällimmäiseksi ikkunaksi Jframe
            frame.setSize(5000, 2000);
            frame.setVisible(true);
            frame.setAlwaysOnTop(true);
            
        }
    }
}

//Hävittiin tai voitettiin hyvä peli pelattiin, kiitos.
        
    
    
