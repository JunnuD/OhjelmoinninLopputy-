import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
    
    public class Tietovisapeli {
    
        public static void main(String[] args) throws FileNotFoundException {
            Scanner lukija = new Scanner(System.in);
            System.out.println("Nimeä tiedostosi: (.txt pääte tulee automaattisesti.) ");
            File tiedostoNimi = new File(lukija.nextLine() + ".txt");
            
            System.out.println("Syötä tiedostoon sisältöä, vaikka yksi lause. Kirjoita: ");
            String sisältö = lukija.nextLine();
    
            kirjoitaTiedostoon(tiedostoNimi, sisältö);
        
    
            PrintWriter kirjoittaja = new PrintWriter(tiedostoNimi);
    
            /*
            System.out.println("Anna jotain");
            String sana = lukija.nextLine();
            System.out.println(sana); 
            */
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
    
    
    
    // Useampi metodi.
    // Tarvitaan käyttäjän syöte --> Tee sillä jotain
    // if- ja switch- ja loop pitää olla
    // talleta tiedot teksti tiedostoon !!!
    // käsittele taulukkoa jollakin tavalla ?