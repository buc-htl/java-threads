package a_runnable_simple;

/**
 *  Die dem Betriebssystem bekannten aktiven Programme bestehen aus Prozessen und
 *  das Betriebssystem verwaltet deren Ausführung.
 *
 *  Zu jedem Prozess gehört mindestens ein "Thread" (dt. "Faden" oder "Ausführungstrang"), der
 *  den Programmcode ausführt. Innerhalb eines Prozesses kann es mehrere Threads geben,
 *  die alle zusammen in demselben Adressraum ablaufen.
 *
 *  Schaue einmal in deinem Taskmanager nach, wie viele Prozesse gerade laufen und wie viele Threads.
 *  Welcher Prozess besitzt gerade die meisten Threads?
 *
 *  Threads können potentiell parallel ausgeführt werden (auf mehreren Prozessoren bzw. Prozessorkernen).
 *  Aber auch bei einem einzelnen Prozessor(-kern) können Threads die Ausführungsgeschwindigkeit speichern.
 *  So kann ein Thread z.B. Berechnungen durchführen, während ein anderer Thread gerade auf Daten von der
 *  (langsamen) Festplatte wartet.
 *
 *  Wir wir Threads in Java erzeugen und verwalten können, wollen wir uns in diesem Abschnitt ansehen.
 *
 *  Für mehr Informationen siehe:
 *  @link https://openbook.rheinwerk-verlag.de/javainsel/15_001.html#u15
 */
public class Main {

    public static void main(String[] args) {

        exampleRunnableInterface();
    }

    private static void exampleRunnableInterface() {

        /**
         * Threads können über das Interface Runnable implementiert werden.
         * Das Interface besitzt eine einzige Methode run().
         * Wird ein Thread mit einem Runnable Objekt gestartet, führt der Thread
         * die Methode run() in diesem Thread aus.
         *
         * Die Klassen CounterCommand und HelloCommand implementieren das Interface Runnable
         */

        /**
         * ACHTUNG: Es darf die Methode run() nicht direkt aufgerufen werden!
         * Dann würde die run() Methode normal im "Hauptthread" des Programms ausgeführt
         * werden und nicht in einem eigenen Thread (der parallel zu allen anderen Threads laufen kann).
         *
         * Stattdessen wird ein neuer Thread erzeugt und das Runnable Objekt übergeben.
         * Mit start() wird die Ausführung des Codes gestartet.
         */


        Thread t1 = new Thread( new HelloCommand());
        t1.start();

        Thread t2 = new Thread( new CounterCommand() );
        t2.start();

        /**
         * Wenn dein Rechner nicht super flott ist, solltest du sehen, dass die Ausgaben der
         * beiden Threads abwechseln. Ohne Threads würden die run-Methoden komplett hintereinander
         * ausgeführt werden (also zuerst alle "Hallo Welt!" und dann alle Zahlen).
         */
    }
}
