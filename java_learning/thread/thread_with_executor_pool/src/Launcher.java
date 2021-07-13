import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);
    private static final CountDownLatch[] countDownLatches = new CountDownLatch[4];
    private static final String[] threadNames = {"A", "B", "C", "D"};

    public static void main(String[] args) {
        processExecutorService();
        awaitCountDownLatch();

        // si je commente la méthode awaitCountDownLatch, alors le "Executor finished"
        // est affiché en parallèle du executorService. Par contre, en laissant cette méthode,
        // on attend que toutes les barrières soient terminées grâce à la méthode await.
        // A noté que du coup le Thread Main est bloqué et ne peut pas continuer son exécution
        // à cause des await.
        System.out.println("Executor finished");
        executorService.shutdown();
    }

    private static void processExecutorService() {
        for (int i = 0; i < 4; i++) {
            countDownLatches[i] = new CountDownLatch(5);
            executorService.execute(new MyRunnable(threadNames[i], countDownLatches[i]));
        }
    }

    private static void awaitCountDownLatch() {
        for (int i = 0; i < 4; i++) {
            try {
                countDownLatches[i].await();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}