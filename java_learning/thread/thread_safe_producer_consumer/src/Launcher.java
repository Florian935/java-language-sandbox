import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Launcher {
    public static void main(String[] args) {
        final BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<>();
        final ExecutorService producerExecutorService =
                Executors.newFixedThreadPool(2);
        final ExecutorService consumerExecutorService =
                Executors.newFixedThreadPool(2);

        for (int i = 1; i < 3; i++) {
            producerExecutorService.submit(new Producer(sharedQueue, i));
            consumerExecutorService.submit(new Consumer(sharedQueue, i));
        }

        producerExecutorService.shutdown();
        consumerExecutorService.shutdown();
    }
}
