package Biblioteche;

import java.util.Scanner;

public class MainClass {


    public static void main(String[] args) {
        int scelta = 0;
        Biblioteca biblioteca = new Biblioteca("Biblioteca");
        Scanner scanner = new Scanner(System.in);

        System.out.println("------------BIBLIOTECA_MENU------------\n");
        
        do{
            System.out.println("1- Aggiungi libro");
            System.out.println("2- Aggiungi utente");
            System.out.println("3- Visualizza libri in dotazione");
            System.out.println("4- Visualizza utenti");
            System.out.println("5- Richiedi prestito");
            System.out.println("6- Visualizza lista dei libri in prestito di un utente");
            System.out.println("7- Visualizza elenco richieste per un libro");
            System.out.println("8- Restituzione libro");
            System.out.println("9- Visualizza elenco libri in prestito");
            System.out.println("10- Visualizza elenco libri per cui esistono richieste di prestito inevase");
            System.out.println("0- Esci\n");
            System.out.print("Scegli: ");
            
            scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    
                    break;
            
                case 2:
                    
                    break;
            
                case 3:
                    
                    break;
            
                case 4:
                    
                    break;
            
                case 5:
                    
                    break;
            
                case 6:
                    
                    break;
            
                case 7:
                    
                    break;
            
                case 8:
                    
                    break;
            
                case 9:
                    
                    break;

                case 10:
                    
                    break;

                case 0:
                    System.out.println("Exit\n");
                    break;
            
                default:
                    System.out.println("Valore non valido. Riprovare.");
                    break;
            }
        }while(scelta != 0);
    }
}
