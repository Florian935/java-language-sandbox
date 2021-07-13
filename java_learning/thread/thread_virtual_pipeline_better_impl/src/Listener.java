import java.util.concurrent.BlockingQueue;

public class Listener extends Thread {

    private final BlockingQueue<Result<Integer>> resultList;

    public Listener(BlockingQueue<Result<Integer>> resultList) {
        this.resultList = resultList;
    }

    @Override
    public void run() {
        while (true) {
            try {
                final Result<Integer> result = resultList.take();

                final int poisonPill = -1;
                if (result.getResult() == poisonPill) {
                    System.out.println("Pillule empoisonnée reçue, pipeline terminé");
                    break;
                }
                System.out.printf("Resultat: %d\n", result.getResult());
            } catch (InterruptedException e) {
                System.out.println("List vide");
            }
        }
    }
}
