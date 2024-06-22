package Dizionari;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Dizionari {

    public static void main(String[] args) {
        
        Map<String, String> dizionario = new HashMap<>();
        
        dizionario.put("Ciao", "Michi");
        dizionario.put("gatto", "animale peloso con coda");
        dizionario.put("poli", "schifo");

        String def = dizionario.get("Ciao");
        System.out.println(def);

        if( dizionario.containsKey("cane")){
            System.out.println("trovato");
        }else{
            System.out.println("non trovato");
        }

        Map<String, List<String>> d2 = new HashMap<>();

        List<String> poli = new LinkedList<>();
        poli.add("Ciao");
        poli.add("Michi");

        d2.put("poli", poli);
        String  e = "animale peloso con coda";

        aggiungiDefinizione(d2, "gatto", e);

        System.out.println(d2);


        //itero sulle chiavi
        for(String s: dizionario.keySet()){

        }

        //itero sui valori
        for(String s: dizionario.values()){

        }

        //itero sulle entry, notare che l'ordine è diverso dall'ordine in cui ho fatto i put. --> Map è un hashmap, le chiavi sono memorizzate con un ordine che è associato all'hascode associato alla chiave
        for(Map.Entry<String, String> entry: dizionario.entrySet()){
            System.out.println(entry.getKey() + ": "+entry.getValue() );
        }

        //Se usassi TreeMap avrei le chiavi ordinate secondo l'ordine dei put. Di defalut si usa HashMap perchè più efficiente.

        //Esiste un'altra variante di Mappa che è LinkedHashMap, dove le chiavi sono mantenute in ordine di inserimento. Nel costruttore posso passargli un comparatore che mi ordina i dati in base ad un criterio ben definito
    }

    private static void aggiungiDefinizione(Map<String, List<String>> d2, String nome, String definizione){
        //compute serve per modificare un valore di una certa chiave del dizionario
        d2.compute(nome, (k,v)->{
            if(v == null){
                List<String> l = new LinkedList<>();
                l.add(definizione);
                return l;
            }else{
                v.add(definizione);
                return v;
            }
        });
    }
}
