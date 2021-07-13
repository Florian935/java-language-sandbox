import java.util.concurrent.BlockingQueue;

public class Listener extends Thread {

    private final BlockingQueue<Integer> resultList;

    public Listener(BlockingQueue<Integer> resultList) {
        this.resultList = resultList;
    }

    @Override
    public void run() {
        while (true) {
            try {
                final int result = resultList.take();

                final int poisonPill = -1;
                if (result == poisonPill) {
                    System.out.println("Pillule empoisonnée reçue, pipeline terminé");
                    break;
                }
                System.out.printf("Resultat: %d\n", result);
            } catch (InterruptedException e) {
                System.out.println("List vide");
            }
        }
    }
}
