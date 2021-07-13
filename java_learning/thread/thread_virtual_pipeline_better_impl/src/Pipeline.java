import java.util.Timer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pipeline {

    private static final int NUMBER_OF_STAGE = 3;

    public static void main(String[] args) {
        BlockingQueue<Result<Integer>> outSender =
                new ArrayBlockingQueue<>(NUMBER_OF_STAGE);
        BlockingQueue<Result<Integer>> inReceiver =
                new ArrayBlockingQueue<>(NUMBER_OF_STAGE);
        final BlockingQueue<Result<Integer>> firstStageEmitter = inReceiver;
        final Timer timer = new Timer();


        for (int i = 0; i < NUMBER_OF_STAGE; i++) {
            outSender = new ArrayBlockingQueue<>(NUMBER_OF_STAGE);
            new Stage(inReceiver, outSender, i).start();
            inReceiver = outSender;
        }

        new Listener(outSender).start();

        timer.schedule(
                new MyTimerTask(firstStageEmitter, timer),
                1000,
                3000);
    }
}
