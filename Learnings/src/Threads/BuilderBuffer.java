package Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class BuilderBuffer {

    public static void main(String[] args) {

        StringBuffer buffer = new StringBuffer();
        StringBuilder builder = new StringBuilder();
        SynchBuilder sync = new SynchBuilder();

        ExecutorService x = Executors.newCachedThreadPool(); //genera pool di thread, se ci sono dei thread liberi li usa, se non ce ne sono ne crea di nuovi

        x.submit(createWriter("A", buffer::append));
        x.submit(createWriter("B", buffer::append)); //faccio un append dentro stream buffer con due thread paralleli
        
        
        x.submit(createWriter("A", builder::append));
        x.submit(createWriter("B", builder::append));

         
        x.submit(createWriter("A", sync::append));
        x.submit(createWriter("B", sync::append));

        try {
            x.awaitTermination(1, TimeUnit.SECONDS); // lo metto perchè shutdown rischia di essere chiamato prima che i task venissero eseguiti, awaitTermination aspetta 1 secondo
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //il buffer ha u  sistema che garantisce accesso sicuro quando ho più thread concorrenti, builder non ha questa garanzia. builder infatti rischia di avere operazioni concorrenti e potrei  avere un pezzo in meno rispetto a buffer perchè le operazioni si sovrascrivono.
        System.out.println(buffer);
        System.out.println(builder);
        System.out.println(sync.b); //il sync ha tutti gli A e B perchè è sincronizzato quindi agisce in mutua esclusione.

        x.shutdown();

    }

    static class SynchBuilder{
        private StringBuilder b = new StringBuilder();

        public synchronized void append(String s){ //la parola chiave syncronized mi permette che questo metodo sia in mutua esclusione
            b.append(s);
        }
    }

    private static Runnable createWriter(String nome, Consumer<String> writer){
        return () -> {
            for(int i=0; i<10; i++)
                writer.accept(nome + i + " ");
        };
    }
}

