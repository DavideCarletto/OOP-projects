package Threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EsSync {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        Task t = new Task();

        Thread th = new Thread(t);

        th.start();
        
        // while(!t.isDone()){ //cosi lo uso se non ho controlli di sincronizzazione nelle funzioni di t
        //     //Busy form of waiting...
        // }

        int risultato = t.getResult();

        System.out.println(risultato);

        //stessa cosa ma con future

        ExecutorService xs = Executors.newCachedThreadPool();

        Callable<Integer> work = () -> {
            int totale = 0;
            for (int i=0; i<10000; ++i){
                totale+=i;
            }

            return totale;
        };

        Future<Integer> res = xs.submit(work);

        xs.submit(() -> {
            int ris;
            try {
                ris = res.get();
                System.out.println(ris);
            } catch (InterruptedException | ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } //dentro al metodo get di Future c'è un meccanismo analogo a quello che abbiamo scritto a mano in task
        });
        System.out.println("Thread principale prosegue...");
        xs.shutdown();

    }

    static class Task implements Runnable{
        private int totale;
        private boolean done;

        public synchronized void run(){
            System.out.println(Thread.currentThread().getName()+" processing...");

            for (int i=0; i<10000; ++i){
                totale+=i;
            }
            done = true;
            notify(); //per chiamare notify la funzione deve essere synchronized 
        }

        public boolean isDone(){
            return done;
        }

        public synchronized int getResult(){
            try {
                while(!done){ //while e no if perchè può succedere che wait venga eseguita anche senza notify
                    System.out.println(Thread.currentThread().getName()+" waiting...");
                    wait(); //posso chiamare la wait solo se il metodo è synchronized
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return totale;
        }

    }
}
