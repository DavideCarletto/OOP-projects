package Streams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EsStream {

    public static void main(String[] args) {
        Collection<String> parole = new ArrayList<>();


        parole.add("ciao");
        parole.add("come");
        parole.add("va");
        parole.add("spero");
        parole.add("tutto");
        parole.add("bene");

        // String[] c = parole.stream().map(String::toString).toArray(String[]::new);
        // Stream.of(c).sorted().distinct().forEach(System.out::println);

        // parole.stream().sorted().distinct().forEach(System.out::println);


        long t0 = System.currentTimeMillis();
        //iterate prende elemento precendete e gli applica quello che voglio
        Stream.iterate(0, x -> x+2).limit(10000000);
        System.out.println("Tempo di esecuzione: "+(System.currentTimeMillis()-t0));

        //intStream ci mette molto meno
        t0 = System.currentTimeMillis();
        IntStream.iterate(0, x->x+2).limit(10000000);
        System.out.println("Tempo di esecuzione: "+(System.currentTimeMillis()-t0));

        IntStream.range(0, 20).filter(i->i%2==0).forEach(System.out::println);

        // System.out.println("Serie di Fibonacci:");
        // //generate ha bisogno di un supplier
        // int[] seed = {1,1};
        // Stream.generate(() -> {
        //     int next = seed[0]+seed[1];
        //     int result = seed[0];

        //     seed[0] = seed[1];
        //     seed[1] = next;

        //     return result;
        // })
        // .limit(10)
        // .forEach(System.out::println);

        //skippo le prime 2 parole
        // parole.stream().skip(2).forEach(System.out::println);

        //map mi prende in ingresso uno stream e produce uno stream di elementi restituiti dalla lambda
        parole.stream().map(String::toUpperCase).forEach(System.out::println);

        //reduce serve  per combinare gli elementi di uno stream restituendo un risultato finale

        int sum = IntStream.range(0, 101)
        // .parallel() // se voglio migliorare le prestazioni
        .reduce(Integer::sum) //oppure (a,b)-> a+b
        .orElse(0)
        ;

        System.out.println(sum);

        class Acc { int n; }

        int s = Stream.of(1,2,3,4,5).
        collect(Acc::new, // supplier, istanziato una sola volta per inizializzare l'accumulatore 
        (a,i) -> a.n+=i, // accumulator a cui aggiungo un elemento dello stream. aggiungo valore corrente i al valore di n dell'accumulatore
        (a1,a2)->a1.n+=a2.n // combiner, serve solo quando sto facendo roba in parallelo per combinare i risultati
        ).n;

        System.out.println(s);


        List<Integer> n = Stream.of(1,2,3,4,5,6).
        collect(LinkedList::new,// supplier
        List::add, // accumulator
        List::addAll); // combiner

        //stessa roba di sopra
        List<Integer> n2 = Stream.of(1,2,3,4,5,6)
        .toList();

        n.stream().forEach(System.out::println);
        n2.stream().forEach(System.out::println);

        
    }


}
