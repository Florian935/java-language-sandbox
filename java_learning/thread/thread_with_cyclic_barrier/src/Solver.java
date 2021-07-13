import java.util.concurrent.CyclicBarrier;

public class Solver {

    private final int n;
    private final Integer[] data;
    private CyclicBarrier cyclicBarrier;
    public boolean isFinish;

    public Solver(Integer[] data) {
        this.data = data;
        n = data.length;
        initCyclicBarrier();
        this.isFinish = true;
    }

    private void initCyclicBarrier() {
        // Runnable automatically processed when n threads has called
        // cyclicBarrier.await(). If no n threads has called await() method,
        // the actionBarrier is never called.
        final Runnable actionBarrier = () -> calculateSum(data);
        cyclicBarrier = new CyclicBarrier(n, actionBarrier);
    }

    // Calculate the sum of the threads who called the cyclicBarrier with
    // the await() method.
    private void calculateSum(Integer[] data) {
        int sum = 0;
        for (int d: data) {
            sum+= d;
        }
        // Signal that the barrier is reached
        isFinish = true;
        System.out.printf("Somme du tableau: %d\n", sum);
    }

    // Launch the worker to put data in data table and call the await method
    // for await that all the n threads have reach the barrier
    public void launchWorker() {
        // Signal that the barrier is currently waiting and can't be called
        isFinish = false;
        for (int i = 0; i < n; i++) {
            Thread thread = new Thread(new Worker(i, data, cyclicBarrier));
            thread.start();
        }
    }
}
