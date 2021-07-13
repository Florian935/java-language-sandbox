package resolver;

import worker.Workeable;
import worker.factory.WorkerFactoryImpl;

import java.util.concurrent.BlockingQueue;

public class Stage extends Thread {

    private final BlockingQueue<Result<Integer>> inReceiver;
    private final BlockingQueue<Result<Integer>> outSender;
    private final int stageNumber;

    public Stage(
            BlockingQueue<Result<Integer>> inReceiver,
            BlockingQueue<Result<Integer>> outSender,
            int stageNumber) {
        this.inReceiver = inReceiver;
        this.outSender = outSender;
        this.stageNumber = stageNumber;
    }


    @Override
    public void run() {
        final Workeable work = new WorkerFactoryImpl().makeWorker(stageNumber);
        while (true) {
            try {
                Result<Integer> receivedValue = inReceiver.take();

                final int poisonPill = -1;
                if (receivedValue.getResult() == poisonPill) {
                    outSender.offer(receivedValue);
                    break;
                }

                Result<Integer> result =
                        work.processStage(receivedValue, stageNumber);

//                System.out.println(
//                        Thread.currentThread().getName() + ", Reçu: " + receivedValue.getResult());

                outSender.offer(result);
//                System.out.println(Thread.currentThread().getName() + ", Offer: " + receivedValue);
            } catch (InterruptedException e) {
                System.out.println("List vide");
            }
        }
    }
}
