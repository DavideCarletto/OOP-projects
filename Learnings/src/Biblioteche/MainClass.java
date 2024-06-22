package Biblioteche;

import java.util.ArrayList;
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
            
            scelta = Integer.parseInt(scanner.nextLine());

            switch (scelta) {
                case 1:
                    System.out.println("Inserisci i valori del libro\n");
                    System.out.println("Autori (premere 0 per uscire):");
                    String nomeAutore;
                    int count=1;
                    
                    ArrayList<String> nomiAutori = new ArrayList<String>();
                    String titolo = null;
                    String editore = null;
                    String ISBN = null;

                    do {
                        System.out.print("Autore " + count + ": ");
                        nomeAutore = scanner.nextLine();
                        if (!nomeAutore.equals("0")) {
                            nomiAutori.add(nomeAutore);
                            count++;
                        }
                    } while (!nomeAutore.equals("0"));
            

                    System.out.println("Titolo: ");
                    titolo = scanner.nextLine();
                    System.out.println("Editore: ");
                    editore = scanner.nextLine();
                    System.out.println("ISBN: ");
                    ISBN = scanner.nextLine();

                    Libro libro = new Libro(nomiAutori, titolo, editore, ISBN);
                    
                    try {
                        biblioteca.addLibro(libro);
                    } catch (InvalidIsbn e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                    System.out.println("Libro creato correttamente!");
                    break;
            
                case 2:
                    System.out.println("Inseisci i dati dell'utente.\n");

                    String codice = null;
                    String nomeUtete = null;
                    String cognome = null;

                    System.out.println("Codice: ");
                    codice = scanner.nextLine();
                    System.out.println("Nome: ");
                    nomeUtete = scanner.nextLine();
                    System.out.println("Cognome: ");
                    cognome = scanner.nextLine();

                    Utente utente = new Utente(codice, nomeUtete, cognome);
                    
                    try {
                        biblioteca.addUtente(utente);
                    } catch (InvalidCode e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    System.out.println("Utente creato correttamente! ");
                    break;
            
                case 3:
                    ElencoLibri elencoLibri = biblioteca.libriPerAutore();
                    elencoLibri.printElenco();
                    break;
            
                case 4:
                    ElencoUtenti elencoUtenti = biblioteca.utenti();
                    elencoUtenti.printElenco();
                    break;
            
                case 5:
                    String codiceRichiedente = null, libroRichiesto = null;

                    System.out.println("Inserire i dati relativi alla richiesta.\n");
                    System.out.println("Codice dell'utente: ");
                    codiceRichiedente = scanner.nextLine();
                    System.out.println("ISBN del libro che si vuole richiedere: ");
                    libroRichiesto = scanner.nextLine();

                    try {
                        biblioteca.prestito(codiceRichiedente, libroRichiesto);
                    } catch (InvalidCode e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvalidIsbn e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                    break;
            
                case 6:
                    System.out.println("Inserire i dati relativi all'utente.\n");
                    
                    String codiceUtente = null;
                    System.out.println("Codice utente: ");
                    codiceUtente = scanner.nextLine();

                    ElencoLibri libriInPrestitoDaUtente = biblioteca.prestiti(codiceUtente);
                    libriInPrestitoDaUtente.printElenco();

                    break;
            
                case 7:
                    System.out.println("Inserire i dati relativi al libro.\n");

                    String ISBNPerElencoRichieste = null;
                    System.out.println("ISBN: ");
                    ISBNPerElencoRichieste = scanner.nextLine();

                    ElencoUtenti utentiRichiedenti =  biblioteca.getRichieste(ISBNPerElencoRichieste);
                    utentiRichiedenti.printElenco();

                    break;
            
                case 8:
                    System.out.println("Inserire i dati relativi alla restituzione.\n");

                    String utenteRestitutore = null, libroDaRestituire = null;
                    System.out.println("Codice utente che vuole restituire: ");
                    utenteRestitutore = scanner.nextLine();
                    System.out.println("ISBN libro che si vuole restituire: ");
                    libroDaRestituire = scanner.nextLine();

                    try {
                        biblioteca.restituzione(utenteRestitutore, libroDaRestituire);
                    } catch (InvalidCode e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvalidIsbn e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    break;
            
                case 9:
                    ElencoLibri libriInPrestito = biblioteca.elencoPrestiti();
                    libriInPrestito.printElenco();
                    break;

                case 10:
                    ElencoLibri libriConRichieste = biblioteca.elencoRichieste();
                    libriConRichieste.printElenco();
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
