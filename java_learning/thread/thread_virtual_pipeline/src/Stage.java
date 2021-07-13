import java.util.concurrent.BlockingQueue;

public class Stage extends Thread {

    private final BlockingQueue<Integer> in;
    private final BlockingQueue<Integer> out;
    private final int number;

    public Stage(BlockingQueue<Integer> in, BlockingQueue<Integer> out, int number) {
        this.in = in;
        this.out = out;
        this.number = number;
    }


    @Override
    public void run() {
        while (true) {
            try {
                int receivedValue = in.take();

                final int poisonPill = -1;
                if (receivedValue == poisonPill) {
                    out.offer(receivedValue);
                    break;
                }

//                System.out.println(Thread.currentThread().getName() + ", Reçu: " + receivedValue);
                System.out.printf(
                        "Etage %d a reçu la valeur %d\n", number, receivedValue);
                receivedValue++;
                out.offer(receivedValue);
//                System.out.println(Thread.currentThread().getName() + ", Offer: " + receivedValue);
            } catch (InterruptedException e) {
                System.out.println("List vide");
            }
        }
    }
}
