package d_synchronized;


/**
 * Wir müssen kritische Abschnitte (siehe @link Problem), also Programmblöcke, denen
 * während der Ausführung von einem Thread kein anderer Thread »reinwurschteln« darf, schützen.
 *
 * Vergleichen wir das mit einer Alltagssituation. Gehen wir aufs Klo, schließen wir die Tür
 * hinter uns. Möchte jemand anderes auf die Toilette, muss er warten. Vielleicht kommen noch
 * mehrere dazu, die müssen dann auch warten, und eine Warteschlage bildet sich.
 * Dass die Toilette besetzt ist, signalisiert die abgeschlossene Tür.
 * Jeder Wartende muss so lange vor dem Klo ausharren, bis das Schloss geöffnet wird,
 * selbst wenn der auf der Toilette Sitzende nach einer langen Nacht einnicken sollte.
 *
 * In Java sorgt ein Monitor mit Hilfe eines Locks (deutsch Schloss) für diese Zugangskontrolle.
 * Tritt ein Thread in den kritischen Abschnitt ein, muss Programmcode wie eine Tür abgeschlossen werden (engl. lock).
 * Erst wenn der Abschnitt durchlaufen wurde, darf die Tür wieder aufgeschlossen werden (engl. unlock),
 * und ein anderer Thread kann den Abschnitt betreten.
 *
 * Es gibt in Java verschiedene Möglichkeiten kritische Bereiche zu schützen.
 * Wir sehen uns synchronisierte Blöcke an.
 */
public class Solution implements Runnable {

    private static final Point p = new Point();

    @Override
    public void run() {

        int random = (int)(Math.random() * 1000);

        while ( true ) {

            int xc,yc;

            // Betritt Thread A einen synchronized Block, müssen alle anderen Threads mit der Ausführung warten,
            // bis Thread A den Block fertig abgearbeitet hat. In runden Klammern steht das Objekt, das den
            // zu verwendeten Monitor besitzt.
            // Wenn ein Thread also diesen Block betritt, wird das Schloss am Monitor von p gesperrt.
            // Möchte ein weiterer Thread dieses Codestück ausführen (mit dem gleichen p), muss er warten bis das
            // Schloss wieder geöffnet ist.
           synchronized (p) {
               p.x = random;
               p.y = random;

               xc = p.x;
               yc = p.y;
           }

            if ( xc != yc )
                System.out.println( "Aha: x=" + xc + ", y=" + yc );     //Wie kann das passieren?
        }
    }


    public static void main(String[] args) {
        new Thread( new Solution()).start();
        new Thread( new Solution()).start();
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
