package worker;

import resolver.Result;

public abstract class Worker implements Workeable {

    protected Result<Integer> result;
    protected int stageNumber;

    public abstract Result<Integer> processStage(Result<Integer> result, int stageNumber);

    protected void sleepThreadForMillis(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            logError("Le thread a été interrompu");
        }
    }

    protected void logError(String message) {
        System.err.println(message);
    }

    protected void logResult(String message) {
        System.out.println(message);
    }
}
