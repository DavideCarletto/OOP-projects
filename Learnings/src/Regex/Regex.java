package Regex;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Stream;

public class Regex {

    public static void main(String[] args) {
        
        String re = "\"[^\"\\\\]\"";

        String[] parole = data.Lyrics.ALLA_FIERA_DELL_EST.split("[ ,.?\n']+");

        Stream<String> sp = Stream.of(parole);
        int min = sp.mapToInt(String::length).min().orElse(0);
        sp = Stream.of(parole);
        int max = sp.mapToInt(String::length).max().orElse(0);
        System.out.println(min+ " : " +max);
        
        //usando un collettore (invece di creare due volte lo stream)
        // min=Integer.MAX_VALUE;
        // max=Integer.MIN_VALUE;
        // int[] acc = {Integer.MAX_VALUE, Integer.MIN_VALUE}; //necessario perchè usare solo min e max non funzionerebbe perchè non uso dei valori final nella lambda. Quindi o rendo final min e max o uso un array, pero non lo uso perchè meglio metterlo direttamente nel combiner
        int[] res = Stream.of(parole)
        .mapToInt(String::length)
        .parallel()
        .collect(() -> new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}, 
        (acc,l) -> { //per ogni sottothread crea un acc
            if(l<acc[0]) acc[0] = l;
            if(l> acc[1]) acc[1] = l;
        }, 
        (a1,a2) -> {//accoppia ogni acc creato per ogni sottothread
            if(a2[0]< a1[0]) a1[0] = a2[0];//necessario scrivere il combiner se voglio usare parallel
            if(a2[1]> a1[1]) a1[1] = a2[1];
        });
        
        System.out.println(res[0]+ " : " +res[1]);


        //calcolare frequenza delle parole
        Map<String, Long> freq= Stream.of(parole)
        .map(String::toLowerCase)
        .collect(
            groupingBy(
                parola -> parola,
                counting()
            )
        );

        // System.out.println(freq);


        //Fare graduatoria delle parole più lunghe
        SortedMap<Long, List<String>> freqOrdinate = Stream.of(parole)
        .map(String::toLowerCase)
        .collect(
            groupingBy(
                parola -> parola, //per ogni parola ho il numero di occorrenze
                counting()
            )
        ).entrySet().stream()
        .collect(groupingBy(
            e-> e.getValue(), //raggruppo sulla base del valore che hanno
            // TreeMap::new, //Le metto in un treeMap (ordinato in senso crescente), quindi avrò un treemap in cui la chiave è il numero di occorrenze e i il valore saranno tutte le parole con quella frequenza
            () -> new TreeMap<>(Comparator.reverseOrder()),
            mapping(Map.Entry::getKey, toList()) //le raggruppo in una lista
        ));

        // System.out.println(freqOrdinate);

        // trovare l'elenco delle lettere utilizzate nelle parole
        Map<Character, Long> freqLettere = 
        Stream.of(parole)
        .flatMap(s -> s.chars().mapToObj(c -> (char)c))//flatMap prende un elemento dello stream e mi restituisce uno stream e automaticamente concatena tutti gli stream
        .map(c -> Character.toLowerCase(c))
        .collect(groupingBy(
            Function.identity(), //uguale a scrivere c->
            counting()
        ))
        ; 

        System.out.println(freqLettere);


        //trovare il numero di orccorrenze delle lettere
        // freqLettere.entrySet().stream()
        // .sorted(Comparator.comparing(Map.Entry::getValue).reversed());
    }
}
