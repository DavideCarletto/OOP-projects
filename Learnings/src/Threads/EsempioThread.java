package Threads;


public class EsempioThread {

    public static void main(String[] args) {
        
        Runnable daEseguire = () -> {
            for(int i=0; i<10; i++){
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        };

        Thread t = new Thread(daEseguire);
        t.start();
        daEseguire.run();
    }
}
