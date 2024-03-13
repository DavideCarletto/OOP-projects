package Biblioteche;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ElencoUtenti extends ArrayList<Utente>{

    public void addUtente(Utente utente){
        add(utente);
    }

    public boolean checkPresenzaUtente(String codice){
        boolean value = false;

        for(Utente utente: this){
            if(codice.equals(utente.getCodice())){
                value = true;
            }
        }
        return value;
    }

    public ElencoUtenti getElencoUtentiSorted(){
        ElencoUtenti sorted = new ElencoUtenti();
        sorted.addAll(this);
        Collections.sort(sorted, new Comparator<Utente>() {

            @Override
            public int compare(Utente o1, Utente o2) {
                return o2.getCodice().compareTo(o1.getCodice());
            }
            
        });
        return sorted;
    }


    public void printElenco(){
        for(Utente utente: this){
            System.out.println(utente.toString());
        }
    }

    public Utente getUtenteFromCodice(String codiceUtente){
        for(Utente utente: this){
            if(utente.getCodice().equals(codiceUtente))
                return utente;
        }
        return null;
    }
}
