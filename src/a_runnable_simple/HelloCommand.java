package a_runnable_simple;


public class HelloCommand implements Runnable {
    @Override public void run() {
        for (int i = 0; i < 2000; i++) {
            Math.sqrt(i*934234.6242); // just to waste some time
            System.out.println("Hallo Welt!");
        }
    }
}