import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class BananaCounter {
    private final int nbThreadPool;
    private Integer numberOfBananasCollected = 0;
    private final List<FutureTask<Integer>> tasks;
    private final ExecutorService executorService;
    private final List<Integer> casiers;

    public BananaCounter(int nbThreadPool) {
        this.nbThreadPool = nbThreadPool;
        this.executorService = Executors.newFixedThreadPool(nbThreadPool);
        tasks = new ArrayList<>();
        casiers = Collections.synchronizedList(new ArrayList<>());
    }

    public void processTasks() {
        for (int i = 0; i < nbThreadPool; i++) {
            casiers.add(i, 0);
            FutureTask<Integer> task = new FutureTask<>(new Singe(i, casiers));
            tasks.add(task);
            executorService.execute(task);
        }
    }

    public void nonAsyncWaitForAllTasksAndCountBananas() {
        for (int i = 0; i < 10; i++) {
            try {
                if (tasks.get(i).isDone()) {
                    numberOfBananasCollected += tasks.get(i).get();
                }

                if (!tasks.get(i).isDone()) {
                    numberOfBananasCollected += tasks.get(i).get();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Nombre de bananes collectées: %d\n", numberOfBananasCollected);
        executorService.shutdown();
    }
}
