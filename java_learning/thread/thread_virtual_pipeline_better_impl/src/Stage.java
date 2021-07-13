import java.util.concurrent.BlockingQueue;

public class Stage extends Thread {

    private final BlockingQueue<Result<Integer>> in;
    private final BlockingQueue<Result<Integer>> out;
    private final int number;

    public Stage(BlockingQueue<Result<Integer>> in, BlockingQueue<Result<Integer>> out, int number) {
        this.in = in;
        this.out = out;
        this.number = number;
    }


    @Override
    public void run() {
        final Work work = new Work();
        while (true) {
            try {
                Result<Integer> receivedValue = in.take();

                final int poisonPill = -1;
                if (receivedValue.getResult() == poisonPill) {
                    out.offer(receivedValue);
                    break;
                }

                Result<Integer> result =
                        work.processStage(receivedValue, number);

//                System.out.println(
//                        Thread.currentThread().getName() + ", Reçu: " + receivedValue.getResult());

                out.offer(result);
//                System.out.println(Thread.currentThread().getName() + ", Offer: " + receivedValue);
            } catch (InterruptedException e) {
                System.out.println("List vide");
            }
        }
    }
}
