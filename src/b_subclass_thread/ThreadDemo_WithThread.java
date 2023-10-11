package b_subclass_thread;

/**
 * Da die Klasse Thread selbst die Schnittstelle Runnable implementiert und
 * die run()-Methode mit leerem Programmcode bereitstellt, können wir auch Thread erweitern,
 * wenn wir eigene nebenläufige Aktivitäten programmieren wollen.
 */
public class ThreadDemo_WithThread {

    /**
     * innere Klasse (könnte natürlich auch in einem eigenen File stehen)
     */
    private static class ThreadDemoA extends Thread {
        private int n;

        public ThreadDemoA(String name, int n) {
            super(name);    //Thread besitzt bereits einen Konstruktor für einen Namen
            this.n = n;
        }

        @Override
        public void run() {
            for (int i = 0; i < n; i++) {
                System.out.println(getName() + ": " + i);
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread demoA1 = new ThreadDemoA("Thread Nr. 1", 200);
        Thread demoA2 = new ThreadDemoA("Thread Nr. 2", 100);

        /**
         * Wir müssen jetzt kein Runnable-Objekt mehr dem Konstruktor von Thread übergeben,
         * denn wenn unsere Klasse eine Unterklasse von Thread ist, reicht ein Aufruf der
         * geerbten Methode start().
         */
        demoA1.start();
        System.out.println(demoA1.getName() + " started");  //diese Anweisung wird unmittelbar nach Start des Threads ausgeführt

        demoA2.start();
        System.out.println(demoA2.getName() + " started");


        demoA1.join(); // Wartet darauf, dass Thread demoA1 fertig ist
        demoA2.join();

        System.out.println("\nfertig, alle Threads haben nun ihre Arbeit erledigt");

        /**
         * Erweitern von Thread oder Implementieren von Runnable?
         *
         * Die beste Idee wäre, Runnable-Objekte zu bauen, die dann dem Thread übergeben werden.
         * Befehlsobjekte dieser Art sind recht flexibel, da die einfachen Runnable-Objekte
         * leicht übergeben und sogar von Threads aus einem Thread-Pool ausgeführt werden können.
         * Ein Nachteil der Thread-Erweiterung ist, dass die Einfachvererbung störend sein kann;
         * erbt eine Klasse von Thread, ist die Erweiterung schon »aufgebraucht«.
         * Doch egal, ob eine Klasse Runnable implementiert oder Thread erweitert, eines bleibt:
         * eine neue Klasse.
         */
    }
}
