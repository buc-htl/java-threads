package a_runnable_simple;



public class CounterCommand implements Runnable {
    @Override
    public void run() {
        for ( int i = 0; i < 2000; i++ ) {
            Math.sqrt(i*34234.6242); // just to waste some time
            System.out.println(i);
        }
    }
}