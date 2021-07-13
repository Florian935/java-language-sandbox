import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Worker implements Runnable {
    final int index;
    final Integer[] data;
    final CyclicBarrier cyclicBarrier;

    public Worker(int index, Integer[] data, CyclicBarrier cyclicBarrier) {
        this.index = index;
        this.data = data;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        final int randomMillisToSleep = new Random().nextInt(4000);
        logTimeToSleep(Thread.currentThread().getName(), randomMillisToSleep / 1000);

        try {
            processWorker(randomMillisToSleep);
        } catch (InterruptedException | BrokenBarrierException e) {
            logError(e.getMessage());
        }
    }
    private void logTimeToSleep(String threadName, int timeToSleep) {
        System.out.printf(
                "Le thread %s va dormir pendant %d seconde(s)\n",
                threadName,
                timeToSleep);
    }

    private void processWorker(int randomMillisToSleep)
            throws InterruptedException, BrokenBarrierException {
        Thread.sleep(randomMillisToSleep);
        putValueInTab(index);
        // signalise à cyclicBarrier de décrémenter sa barrière d'attente
        // des n threads
        cyclicBarrier.await();
    }

    private void logError(String message) {
        System.out.println(message);
    }

    private void putValueInTab(int i) {
        data[i] = data[i] + i + 1;
    }
}
