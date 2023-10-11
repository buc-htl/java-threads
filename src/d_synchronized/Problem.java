package d_synchronized;

public class Problem implements Runnable {

    private static final Point p = new Point();

    @Override
    public void run() {

        int random = (int)(Math.random() * 1000);

        while ( true ) {

            p.x = random;
            p.y = random;

            int xc = p.x,
                yc = p.y;

            if ( xc != yc )
                System.out.println( "Aha: x=" + xc + ", y=" + yc );     //Wie kann das passieren?
        }
    }


    public static void main(String[] args) {
        new Thread( new Problem()).start();
        new Thread( new Problem()).start();

        /*
            Threads laufen (pseudo-) parallel ab. Das heißt die Ausführung eines Threads kann jederzeit unterbrochen werden,
            damit die Ausführung eines anderen Threads fortgesetzt wird.

            Dies führt zu Problemen, wenn verschiedene Threads eine gemeinsame Ressource verändern wollen.
            Wenn sich zehn Nutzer einen Drucker teilen, der die Ausdrucke nicht als unteilbare Einheit bündelt,
            lässt sich leicht ausmalen, wie das Ergebnis aussieht. Seiten, Zeilen oder gar einzelne Zeichen aus
            verschiedenen Druckaufträgen werden bunt gemischt ausgedruckt.

            Wenn mehrere Threads gemeinsame Daten nur lesen, ist das unbedenklich; Schreiboperationen sind jedoch kritisch!
            Sobald es Schreiboperationen gibt, können sich Probleme ergeben.

            Zusammenhängende Programmblöcke, denen während der Ausführung von einem Thread kein anderer Thread »reinwurschteln« sollte
            und die daher besonders geschützt werden müssen, nennen sich kritische Abschnitte.

            Im angeführten Beispiel ist die gemeinsame Ressource die statische Variable. Es kann passieren, dass ein Thread unterbrochen wird während
            er die Attribute der gemeinsamen Ressource ändert.

            Beispiel:
            Thread 1 hat die Zufallzahl 1, Thread 2 die Zufallszahl 2

            Thread 1: p.x=1;
            Thread 1 wird unterbrochen

            Thread 2: p.x=2;
                      p.y=2;
            Thread 2 wird unterbrochen

            Thread 1: p.y=1;

            -> p hat somit die Koordinaten (2,1) was eigentlich nicht passieren sollte! Es kann genauso (2,1) sein, wenn die Threads anders unterbrochen werden.

            Wenn wir ein Szenario haben in dem in Abhängigkeit der Ausführungszeit der Operationen bzw. des Ort der Unterbrechung unerwünschte Effekte auftreten,
            sprechen wir von "race conditions". Solche Fehler sind besonders schwer zu debuggen, weil sich das Programm bei jeder Ausführung potentiell anders verhält.
         */

    }


    private static class Point {
        int x;
        int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
