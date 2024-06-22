package StreamCollectors;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollectors {
    public static void main(String[] args) {
        String[] parole = {"ciao", "come", "va", "spero", "tutto", "bene", "sos"};

        Stream.of(parole)
        .sorted()
        .distinct()
        .forEach(System.out::println);
        ;

        //più efficiente rispetto a sopra
        Set<String> paroleNoDup = 
        Stream.of(parole)
        // .collect(Collectors.toSet()) //questa non ha l'ordinamento
        // .collect(Collectors.toCollection(()-> new TreeSet<String>()))
        .collect(Collectors.toCollection(TreeSet::new)) //meglio questa perchè le lambda di per sè usano il method reference, qui avrei direttamente quello 
        ;
        System.out.println(paroleNoDup);

        Map<Character, Long /*List<String>*/> iniziali = 
        Stream.of(parole)
        .map(String::toUpperCase)
        .distinct()
        .collect(
            // Collectors.groupingBy(s-> s.charAt(0)) //sta roba mi restituisce una mappa che raggruppa sulla base del primo carattere
           
            // Collectors.groupingBy(s ->s.charAt(0), //seconda variante del collect con downstream
            // Collectors.toList()) //equivalente a quello sopra, è il down stream e quindi il valore della chiave

            // Collectors.groupingBy(s->s.charAt(0),
            // Collectors.counting()) //questo restituisce un long quindi come valori della map ho un long, counting serve per essere usato qua dentro, non altrove

            Collectors.groupingBy( s-> s.charAt(0),
            () -> new TreeMap<>(Comparator.reverseOrder()), //specifico supplier, ovvero anzichè una semplice mappa sarà un treemap e gli specifico un ordine
            Collectors.counting()
            )
        )
        ;

        iniziali.forEach((chiave, valori) -> {
            System.out.println(chiave + ": \n\t"+ valori);
        });

        Map<Character, Integer /*List<String>*/> iniziali2 = 
        Stream.of(parole)
        .map(String::toUpperCase)
        .distinct()
        .collect(
            Collectors.groupingBy( s-> s.charAt(0),
            () -> new TreeMap<>(Comparator.reverseOrder()),
            Collectors.collectingAndThen(
                Collectors.counting(), 
                l -> l.intValue()) //modifico ciò che mi restituisce counting, restituisce la stessa cosa ma anzichè long mi dà un integer
            )
        )
        ;

        //Voglio una mappa che faccia corrispondere ad ogni iniziale la somma delle lunghezze delle parole con tale iniziale
        Stream.of(parole)
        .map(String::toUpperCase)
        .distinct()
        // .map(String::length) //non funziona perchè la map mi restituisce uno stream di integer, su cui non posso chiamare la charat
        .collect(
            Collectors.groupingBy(
            s->s.charAt(0),
            Collectors.mapping(String::length, Collectors.summarizingInt(Integer::intValue)) //mapping trasforma tutti gli elementi e poi fa la collezione
            )
        );
    }
}
