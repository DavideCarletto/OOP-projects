package Biblioteche;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Libro {

    private ArrayList<String> nomiAutori;
    private ArrayList<Utente> codaUtentiRichiedenti;
    private String titolo;
    private String editore;
    private String ISBN;
    private boolean inPrestito;
    private Utente utentePrestito;

    public Libro(ArrayList<String> nomiAutori, String titolo, String editore, String ISBN) {
        this.nomiAutori = nomiAutori;
        this.titolo = titolo;
        this.editore = editore;
        this.ISBN = ISBN;
        this.codaUtentiRichiedenti = new ArrayList<Utente>();
        this.inPrestito = false;
        this.utentePrestito = null;
    }

    public void addUtenteListaRichieste(Utente utente){
        codaUtentiRichiedenti.add(utente);
    }
    public Utente getUtentePrestito() {
        return this.utentePrestito;
    }

    public void setUtentePrestito(Utente utentePrestito) {
        this.utentePrestito = utentePrestito;
    }

    public ArrayList<Utente> getCodaUtentiRichiedenti() {
        return this.codaUtentiRichiedenti;
    }

    public void setCodaUtentiRichiedenti(ArrayList<Utente> codaUtentiRichiedenti) {
        this.codaUtentiRichiedenti = codaUtentiRichiedenti;
    }

    public boolean isInPrestito() {
        return this.inPrestito;
    }

    public boolean getInPrestito() {
        return this.inPrestito;
    }

    public void setInPrestito(boolean inPrestito) {
        this.inPrestito = inPrestito;
    }

    public ArrayList<String> getNomiAutori() {
        return this.nomiAutori;
    }

    public void setNomiAutori(ArrayList<String> nomiAutori) {
        this.nomiAutori = nomiAutori;
    }

    public String getTitolo() {
        return this.titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getEditore() {
        return this.editore;
    }

    public void setEditore(String editore) {
        this.editore = editore;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    
    @Override
    public String toString() {
        return "{" +
            " nomiAutori='" + getNomiAutori() + "'" +
            ", titolo='" + getTitolo() + "'" +
            ", editore='" + getEditore() + "'" +
            ", ISBN='" + getISBN() + "'" +
            "}";
    }

    public void orderAutori(Libro libro){
        Collections.sort(nomiAutori, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
            
        });
    }

}
