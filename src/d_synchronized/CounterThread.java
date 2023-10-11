package d_synchronized;


/**
 * !!! Lese zuerst Problem and Solution !!!
 *
 */
public class CounterThread extends Thread {

    protected Counter counter = null;

    public CounterThread(Counter counter){
        this.counter = counter;
    }


    /**
     * Der Thread erhöht den Counter x mal und verringert in anschließend um
     * die gleiche Anzahl.
     */
    public void run() {
        for(int i=0; i<1_000; i++){
            counter.increment();
        }
        for(int i=0; i<1_000; i++){
            counter.decrement();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        example1();
        example2();
        example3();
        example4();
    }


    public static void example1() throws InterruptedException{

        Counter counter = new CounterNOTSynchronized();
        Thread  threadA = new CounterThread(counter);
        Thread  threadB = new CounterThread(counter);

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        /**
         * Ewarten würden wir uns eigentlich, dass der Counter am Ende 0 ist wenn jeder Thread den
         * Counter gleich oft hinauf und hinunter zählt. Aber selbst Operationen wie c++ im Counter
         * werden im Maschinencode in mehrere Befehle zerlegt, die jederzeit unterbrochen werden können,
         * was zu einem inkonsistenen Ergebnis führt.
         */
        System.out.println("Counter (not synchronized): "+counter.value());
    }

    public static void example2() throws InterruptedException{

        /** Da in der Klasse CounterSynchronized die Methoden synchronisiert sind, kann
         * ein Thread nur den Counter ändern, wenn dies kein anderer Thread gerade tut.
         *
         * Schaue dir die Klasse CounterSynchronized (weiter unten) für Details an.
         */
        Counter counter = new CounterSynchronized();
        Thread  threadA = new CounterThread(counter);
        Thread  threadB = new CounterThread(counter);

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("Counter (synchronized): "+counter.value());
    }


    public static void example3() throws InterruptedException{

        /**
         * Besitzt jeder Thread seinen eigenen Counter gibt es keine gemeinsame Ressource
         * und damit können sich die Threads auch nicht in die Quere kommen.
         */

        Counter counter1 = new CounterNOTSynchronized();
        Counter counter2 = new CounterNOTSynchronized();
        Thread  threadA = new CounterThread(counter1);
        Thread  threadB = new CounterThread(counter2);

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("multiple Counters (not synchronized): counter1="+counter1.value()+" counter2="+counter1.value());
    }

    public static void example4() throws InterruptedException{


        CounterStaticSynchronized counter1 = new CounterStaticSynchronized();
        CounterStaticSynchronized counter2 = new CounterStaticSynchronized();
        Thread  threadA = new Thread(
            () -> {
                for(int i=0; i<1_000; i++){
                    counter1.increment();
                }
                System.out.println("Counter max:"+counter1.value());
                for(int i=0; i<1_000; i++){
                    counter1.decrement();
                }
            });
        Thread  threadB = new Thread(
            () -> {
                for(int i=0; i<1_000; i++){
                    counter2.increment();
                }
                System.out.println("Counter max:"+counter1.value());
                for(int i=0; i<1_000; i++){
                    counter2.decrement();
                }
            });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        //Was passiert wenn du die Counter-Methoden in diesem Beispiel nicht synchronisiert? Probiere es aus.
        System.out.println("static Counters (synchronized): counter1="+counter1.value()+" counter2="+counter1.value());
    }
}



class CounterNOTSynchronized implements Counter {
    private int c = 0;

    public void increment() {
        c++;
    }

    public void decrement() {
        c--;
    }

    public int value() {
        return c;
    }

}

class CounterSynchronized implements Counter {
    private int c = 0;

    /**
     * Wir können zu einer Methode das Schlüsselwort "synchronized" hinzufügen um die gesamte
     * Methode zu synchronisieren. Der Monitor ist das aktuelle Objekt "this".
     *
     * Dadurch kann immer nur ein Thread eine synchronisierte Methode eine bestimmten Objekts ausführen.
     * Ruft z.B. Thread A increment() auf, kann Thread B keine der anderen synchronisierten Methoden aufrufen,
     * bis Thread A die Methode wieder verlässt.
     */
    public synchronized void increment() {
        c++;
    }

    public synchronized void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }

}

class CounterSynchronizedBlocks implements Counter {
    private int c = 0;

    /*
     * Wenn wir nicht die gesamte Methode, sondern nur Teile davon synchronisieren wollen,
     * können wir synchronized blocks einsetzen (was im konkreten Fall keinen Sinn macht, da die Methode
     * nur aus einem Statement besteht).
     *
     * Diese Klasse macht im Endeffekt genau das gleiche wie CounterSynchronized
     */
    public void increment() {
        synchronized(this) {
            c++;
        }
    }

    public void decrement() {
        synchronized(this) {
            c--;
        }
    }

    public int value() {
        synchronized(this) {
            return c;
        }
    }

}

class CounterStaticSynchronized {

    private static int c = 0;

    /**
     * Auch statische Methoden können synchronisiert werden.
     * Da es in diesem Fall aber keine this Referenz gibt, besitzt
     * das Class-Object dieser Klasse.
     */
    public static synchronized void increment() {
        c++;
    }

    public static synchronized void decrement() {
        c--;
    }

    public static synchronized int value() {
        return c;
    }
}

interface Counter {
    void increment();
    void decrement();
    int value();
}

