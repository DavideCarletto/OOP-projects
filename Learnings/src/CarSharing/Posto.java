package CarSharing;

public class Posto {

    private Posizione posizione;
    private Lato lato;
    private boolean conducente;
    private boolean prenotato;

    /**
     * sta roba serve per fare doucmentazione (quando chiamo un metodo mi dà descrizioni sui parametri)
     * @param posizione  
     * @param lato
     * @param conducente
     * @param prenotato
     */

    public Posto(Posizione posizione, Lato lato, boolean conducente, boolean prenotato) {
        this.posizione = posizione;
        this.lato = lato;
        this.conducente = conducente;
        this.prenotato = prenotato;
    }


    public Posizione getPosizione() {
        return this.posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }

    public Lato getLato() {
        return this.lato;
    }

    public void setLato(Lato lato) {
        this.lato = lato;
    }

    public boolean isConducente() {
        return this.conducente;
    }

    public boolean getConducente() {
        return this.conducente;
    }

    public void setConducente(boolean conducente) {
        this.conducente = conducente;
    }

    public boolean isPrenotato() {
        return this.prenotato;
    }

    public boolean getPrenotato() {
        return this.prenotato;
    }

    public void setPrenotato(boolean prenotato) {
        this.prenotato = prenotato;
    }


    @Override
    public String toString() {
        return "{" +
            " posizione='" + getPosizione() + "'" +
            ", lato='" + getLato() + "'" +
            ", conducente='" + isConducente() + "'" +
            ", prenotato='" + isPrenotato() + "'" +
            "}";
    }

}

