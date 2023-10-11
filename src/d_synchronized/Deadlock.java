package d_synchronized;


/**
 * !!! Schaue dir zuerst die anderen Klassen an!!!
 *
 *
 * Wenn uns synchroniserte Bereiche helfen, warum synchronsieren wir nicht einfach alles?
 * Einerseits würden wir dadurch die Performancevorteile mit Threads zum größten Teil wieder
 * einbüßen, weil die Threads die meiste Zeit dann auf Locks warten.
 *
 * Andererseits laufen wir dann in die Gefahr eines Deadlocks.
 * Ein Deadlock (zu Deutsch etwa Blockade) kommt beispielsweise dann vor, wenn ein Thread A eine Ressource belegt,
 * die ein anderer Thread B haben möchte, und Thread B eine Ressource belegt, die A gerne nutzen würde.
 * In dieser Situation können beide nicht vor und zurück und befinden sich in einem dauernden Wartezustand.
 * Deadlocks können in Java-Programmen nicht erkannt und verhindert werden.
 * Es ist also die Aufgabe des Programmierers diesen ungünstigen Zustand gar nicht erst herbeizuführen.
 *
 * Bemühen wir wieder einen Vergleich aus dem Alltag. Hans und Susi wollen essen. Das können sie nur mit Messer
 * und Gabel und es gibt jeweils ein Messer und eine Gabel. Wenn jetzt Susi das Messer nimmt und Hans die Gabel
 * und beide dann darauf warten, das fehlende Essbesteck zu bekommen, wenn der anderen mit dem Essen fertig ist,
 * werden beide wohl verhungern.
 */


public class Deadlock {

    private static String fork="Gabel";
    private static String knife="Messer";

    public static void main(String[] args) {


        Thread threadA= new Thread(()->{
            while (true) {
                synchronized (fork){
                    //stelle den Zugriff auf die Gabel sicher
                    synchronized (knife) {
                        //stelle den Zugriff auf das Messer sicher
                        System.out.println("Thread A kann essen.");
                    }
                }
            }

        });

        Thread threadB= new Thread(()->{
            while (true) {
                synchronized (knife){
                    //stelle den Zugriff auf die Messer sicher
                    synchronized (fork) {
                        //stelle den Zugriff auf das Gabel sicher
                        System.out.println("Thread B kann essen.");
                    }
                }
            }

        });

        threadA.start();
        threadB.start();
        //Kannst du den Code so ändern, dass kein Deadlock entsteht?
    }


}
