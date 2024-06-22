package Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;


public class Collezioni {

    
    public static void main(String[] args) {

        Collection<String> c = new ArrayList<>(); //conviene usare il tipo più generale possibile in modo da avere più flessiiblità possibile
        
        //implementandola così non posso chiamare metodo get(index)

        c.add("Alla");
        c.add("dell'");
        c.add("fiera");
        c.add("est");
        c.add("per");
        
        
        for(String s: c){
            System.out.println(s);
        }

        // System.out.println(c);  //chiamo il toString su ogni elemento
        // System.out.println(c.contains("Ciao michi")); no molto efficiente perchè scandisce in modo sequenziale i valori del vettore -> o(n)

        int N = 10000000;
        long t0 = System.currentTimeMillis();
        // Collection<Integer> i = new LinkedList<>(); 
        // Collection<Integer> i = new TreeSet<>(); 
        // Collection<Integer> i = new HashSet<>(); 


        // for(int j=0; j<N;j++){
        //     i.add(j);
        // }

        // System.out.println(i.contains(N-1));

        // System.out.println("Tempo impiegato: "+(System.currentTimeMillis()-t0)+" ms");


        //Stammerda mi dà errore perchè io elimino il dato e poi vado avanti nel ciclo ma itero sull'oggetto appena eliminato e non so 
        // più cosa fare--> necessario definire iteratore di stinghe;
        // for(String s: c){ 
        //     if(s.length()>4){
        //         c.remove(s);
        //     }
        // }


        //Così è giusto
        // Iterator<String> it = c.iterator();
        // while (it.hasNext()) {
        //     String s = it.next();
        //     if(s.length()>4)
        //         it.remove();
        // } 

        // Il casino è quando ho due iteratori sulla stessa collection--> se da una parte elimino l'altro iteratore non capisce più

        //oppure posso usare le lamda combinate con removeIf
        // c.removeIf(s -> s.length()>4);

        
        // System.out.println(c);

        //Nella classe Collections si trovano gli algorimti che mi servono per una collection
        Collection<String> c2 = Collections.unmodifiableCollection(c); //Questo mi dice che non posso modificarlo, quindi add mi darà errore
        // c2.add("Soldi");

        // Collections.sort(c); non funziona perchè di base non ha il concetto di posizione. sort funziona soltato su collezioni che implementano l'interfaccia List

        List<String> l = (List<String>)c;
        Collections.sort(l);

        //oppure posso definire un comparatore
        Collections.sort(l, Comparator.comparing(String::length)); //comparing estrae la chiave su cui basare l'ordinamento della lista

        System.out.println(c);

    }
}
