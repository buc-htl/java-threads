package a_runnable_simple;


public class HelloCommand implements Runnable {
    @Override public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("Hallo Welt!");
        }
    }
}