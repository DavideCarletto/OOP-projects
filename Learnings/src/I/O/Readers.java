package I.O;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Readers {

    public static void main(String[] args) throws IOException{

        String content = leggiUnFile("input.txt");
        System.out.println(content);
       
    }

    public static String leggiUnFile(String percorsoFile)throws IOException{
        
        StringBuilder sb = new StringBuilder();

        // try-with-resource
        //  funziona con oggetti che implementano l'interfaccia AutoClosable
        try(FileReader file = new FileReader(percorsoFile)){
            
            char[] buffer = new char[8]; //tendenzialmente meglio rimanere tra 512 e 4k
            int numCar;
            
            while((numCar = file.read(buffer)) != -1){
                sb.append(buffer, 0, numCar); //gli dico che metto solo i primi numCar caratteri perchè se no l'ultima lettura farebbe casino a meno che il numero di caratteri non sia esattamente un multiplo di 8
            }
            
        } //questa cosa implicitamente chiama file.close. in questo modo no necessario fare costrutto try con finally  
            
        return sb.toString();
    }

    public static void leggiUnCaratterePerVolta() throws FileNotFoundException{
        Reader r = new FileReader("input.txt");
        int ch;

        /*
         * Se ho un carattere letto normalmente
         * 0x00 0x00 CH CH
         * 
         * se invece ho raggiunto la fine del file restituisco -1
         * 
         * 0xFF 0xFF 0xFF 0xFF
         */

        while (true) {
            try {
                ch = r.read();
                if (ch == -1) {
                    System.out.println("\nFINE DEL FILE");
                    break;
                } else {
                    //ho letto un carattere
                    char c = (char) ch; // butta via i due 0x00 alti
                    System.out.print(c);
                }
            } catch (IOException e) {
                System.out.println("ERRORE DI I/O");
                return;
            }
        }

    }
}
