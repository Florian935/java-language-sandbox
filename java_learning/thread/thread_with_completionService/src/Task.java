import java.util.concurrent.Callable;

public class Task implements Callable<Integer> {

    private final int taskNum;
    private final int multiplicatorToWait;

    public Task(int taskNum, int multiplicatorToWait) {
        this.taskNum = taskNum;
        this.multiplicatorToWait = multiplicatorToWait;
    }

    @Override
    public Integer call() throws Exception {
        System.out.printf("Debut tache %s\n", Thread.currentThread().getName());
        try {
            Thread.sleep(1000L * multiplicatorToWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Fin tache %s\n", Thread.currentThread().getName());
        return taskNum;
    }
}
