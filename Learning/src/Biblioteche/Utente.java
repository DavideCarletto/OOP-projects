package Biblioteche;

public class Utente {

    private String codice;
    private String nome;
    private String cognome;


    public Utente(String codice, String nome, String cognome) {
        this.codice = codice;
        this.nome = nome;
        this.cognome = cognome;
    }


    public String getCodice() {
        return this.codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }


    @Override
    public String toString() {
        return "{" +
            " codice='" + getCodice() + "'" +
            ", nome='" + getNome() + "'" +
            ", cognome='" + getCognome() + "'" +
            "}";
    }

    
}
