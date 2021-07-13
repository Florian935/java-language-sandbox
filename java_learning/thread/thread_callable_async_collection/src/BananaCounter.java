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
    private final List<FutureTask<List<Banane>>> futureTasks;
    private final ExecutorService executorService;
    private final List<List<Banane>> casiers;

    public BananaCounter(int nbThreadPool) {
        this.nbThreadPool = nbThreadPool;
        this.executorService = Executors.newFixedThreadPool(nbThreadPool);
        futureTasks = new ArrayList<>();
        casiers = Collections.synchronizedList(new ArrayList<>());
    }

    public void processTasks() {
        for (int i = 0; i < nbThreadPool; i++) {
            casiers.add(i, Collections.emptyList());
            FutureTask<List<Banane>> task = new FutureTask<>(new Singe(i, casiers));
            futureTasks.add(task);
            executorService.execute(task);
        }
    }

    public void asyncWaitForAllTasksAndCountBananas() {
        int nbTaskFinished = 0;
        final List<FutureTask<List<Banane>>> finishedTasks = new ArrayList<>();

        while (nbTaskFinished < 10) {
            for (FutureTask<List<Banane>> task : futureTasks) {
                if (isTaskDoneAndNotAlreadyFinished(finishedTasks, task)) {
                    finishedTasks.add(task);
                    nbTaskFinished++;
                    try {
                        numberOfBananasCollected += task.get().size();
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }

        System.out.printf("Nombre de bananes collectées: %d\n", numberOfBananasCollected);
        executorService.shutdown();
    }

    private boolean isTaskDoneAndNotAlreadyFinished(
            List<FutureTask<List<Banane>>> finishedTasks, FutureTask<List<Banane>> task) {
        return task.isDone() && !finishedTasks.contains(task);
    }
}
