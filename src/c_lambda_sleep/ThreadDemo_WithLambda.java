package c_lambda_sleep;

public class ThreadDemo_WithLambda {


    public static void printN(String name, int n) {
        try {
            for (int i = 0; i < n; i++) {

                System.out.println(name + ": " + i);

                /**
                 * Thread.sleep() schickt den Thread, der diesen Code gerade ausführt,
                 * für x Millisekunden schlafen.
                 *
                 * Der Schlaf kann durch eine InterruptedException unterbrochen werden, etwa durch interrupt().
                 * Die Ausnahme muss behandelt werden, da sie keine RuntimeException ist.
                 */
                Thread.sleep(1000);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        /**
         * Wir können das Runnable Interface auch über eine lambda Expression umsetzen.
         */
        Thread demoC1 = new Thread(() -> printN("Lamda A", 10));
        Thread demoC2 = new Thread(() -> printN("Lamda B", 20));

        demoC1.start();
        System.out.println(demoC1.getName() + " started");

        demoC2.start();
        System.out.println(demoC2.getName() + " started");

        demoC1.join();
        demoC2.join();

        System.out.println("\nfertig, alle Threads haben nun ihre Arbeit erledigt");
    }
}
