package Biblioteche;

import java.util.ArrayList;

public class Biblioteca {

    private String nome;
    private ElencoLibri elencoLibri;
    private ElencoUtenti elencoUtenti;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.elencoLibri = new ElencoLibri();
        this.elencoUtenti = new ElencoUtenti();
    }

    public void addLibro(Libro libro) throws InvalidIsbn{
        
        if(elencoLibri.checkPresenzaLibro(libro.getISBN())==true){
            throw new InvalidIsbn("Errore, libro gia presente nell'elenco!");
        }else{
            elencoLibri.addLibro(libro);
        }
    }

    public void addUtente(Utente utente) throws InvalidCode{
        // TODO: aggiungo utente e lancio eccezione se il codice gia presente

        if(elencoUtenti.checkPresenzaUtente(utente.getCodice())==true){
            throw new InvalidCode("Errore, utente gia presente nell'elenco!");
        }else{
            elencoUtenti.addUtente(utente);
        }
    }

    public ElencoLibri libriPerAutore(){
        //TODO: restituisco lista con tutti i libri in dotazione ordinati alfabeticamente secondo nomi autori
        ElencoLibri elencoLibriOrded = elencoLibri.getElencoLibriOrded();
        return elencoLibriOrded;
    }

    public ElencoUtenti utenti(){
        //TODO: restituisco utenti ordinata per valori cresenti del codice
        ElencoUtenti elencoUtentiSorted = elencoUtenti.getElencoUtentiSorted();
        return elencoUtentiSorted;
    }

    public Libro prestito(String codiceUtente, String ISBN) throws InvalidCode, InvalidIsbn{
        //TODO: utente richiede libro prestito, se codice no presente invalidCode, Se isbn no presente sollevo invalid isbn.
        //se libro no in prestito lo restituisco, se in prestito gia dal richiedente return null se in prestito da qualcun altro il richiedente inserito al fondo di una coda di richieste dda evadere e il metodo restituisce null
        
        Libro libroRichiesto = elencoLibri.getLibroFromIsbn(ISBN);
        Libro libroPrestato = null;

        if(elencoLibri.checkPresenzaLibro(ISBN)==false){
            throw new InvalidIsbn("Errore, libro non presente nell'elenco!");
        }

        if(elencoUtenti.checkPresenzaUtente(codiceUtente) == false){
            throw new InvalidCode("Errore, utente non presente nell'elenco!");
        }

        if(!libroRichiesto.isInPrestito()){
            libroRichiesto.setInPrestito(true);
            libroRichiesto.setUtentePrestito(elencoUtenti.getUtenteFromCodice(codiceUtente));
            libroPrestato = libroRichiesto;
        }

        if(libroRichiesto.isInPrestito() && !elencoUtenti.getUtenteFromCodice(codiceUtente).getCodice().equals(libroRichiesto.getUtentePrestito().getCodice())){
            libroRichiesto.addUtenteListaRichieste(elencoUtenti.getUtenteFromCodice(codiceUtente));    
        }

        return libroPrestato;
    }

    public ElencoLibri prestiti(String codiceUtente){
        //TODO: return lista con libri in prestito ad un utente ordine alfabetico secondo nomi autori 
        ElencoLibri sorted = new ElencoLibri();
        for(Libro libro: elencoLibri){
            if(libro.isInPrestito())
                sorted.add(libro);
        }

        sorted.getElencoLibriOrded();

        return sorted;
    }

    public ElencoUtenti getRichieste(String ISBN){
        //TODO: return coda di tutti gli utenti in attesa del libro
        Libro libro = elencoLibri.getLibroFromIsbn(ISBN);

        return libro.getCodaUtentiRichiedenti();
    }

    public Libro restituzione(String codiceUtente, String ISBN)throws InvalidCode, InvalidIsbn{
        //TODO: utente restituisce libro attualmente il prestito . se codice utente no presente in biblioteca exc, stss cosa libro. Se libro indicato no in prestito da utente indicato return null, altrimento libro indicato. Se ci sono richieste in coda il libro viene assegnato all'utente in cima alla coda

        Libro libro = elencoLibri.getLibroFromIsbn(ISBN);
        Utente utente = elencoUtenti.getUtenteFromCodice(codiceUtente);

        if(libro == null)
            throw new InvalidIsbn("Errore, libro non presente nell'elenco!");
        if(utente == null)
            throw new InvalidCode("Errore, utente non presente nell'elenco!");

        if(!utente.getCodice().equals(libro.getUtentePrestito().getCodice()))
            libro = null;
        else{
            if(libro.getCodaUtentiRichiedenti().size()>0){
                Utente utentePrestito = libro.getCodaUtentiRichiedenti().remove(0);
                libro.setUtentePrestito(utentePrestito);
            }else{
                libro.setInPrestito(false);
                libro.setUtentePrestito(null);
            }

        }
        return libro;
    }

    public ElencoLibri elencoPrestiti(){
        //TODO: lista tutti i libri in prestito ordinata alfabeticamente secondo nomi autori

        ElencoLibri sorted = new ElencoLibri();

        for(Libro libro: elencoLibri){
            if(libro.isInPrestito())
                sorted.add(libro);
        }

        sorted = sorted.getElencoLibriOrded();

        return sorted;
    }

    public ElencoLibri elencoRichieste(){
        //TODO: return tutti i libri per i quali esistono richieste di prestito inevase. Ordinata alfabeticamente secondo nomi autori 

        ElencoLibri sorted = new ElencoLibri();

        for(Libro libro: elencoLibri){
            if(libro.getCodaUtentiRichiedenti().size()>0)
                sorted.add(libro);
        }

        sorted = sorted.getElencoLibriOrded();

        return sorted;
    }
}
