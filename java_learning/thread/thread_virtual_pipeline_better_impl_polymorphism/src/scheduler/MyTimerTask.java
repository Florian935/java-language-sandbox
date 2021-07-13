package scheduler;

import resolver.Result;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

public class MyTimerTask extends TimerTask {

    private final BlockingQueue<Result<Integer>> firstStageEmitter;
    private final Timer timer;

    public MyTimerTask(BlockingQueue<Result<Integer>> firstStageEmitter, Timer timer) {
        this.firstStageEmitter = firstStageEmitter;
        this.timer = timer;
    }

    @Override
    public void run() {
        final Integer[] numbersListToAdd = initNumbersArray(5, 40);
        final int randomIndex = getRandomValueFromFloor(numbersListToAdd.length);

        final Result<Integer> valueToOffer =
                new Result<>(numbersListToAdd[randomIndex]);
        this.firstStageEmitter.offer(valueToOffer);

        stopSchedulerIfIsPoisonPill(valueToOffer.getResult());
    }

    private Integer[] initNumbersArray(int arraySize, int maxFloor) {
        final Integer[] numbersList = new Integer[arraySize];
        numbersList[0] = -1; // Poison pill

        for (int i = 1; i < arraySize; i ++) {
            numbersList[i] = getRandomValueFromFloor(maxFloor);
        }

        return numbersList;
    }

    private int getRandomValueFromFloor(int length) {
        final Random random = new Random();
        return random.nextInt(length);
    }

    private void stopSchedulerIfIsPoisonPill(int valueToOffer) {
        if (isPoisonPill(valueToOffer)) {
            stopScheduler();
        }
    }

    private void stopScheduler() {
        cancel();
        timer.cancel();
    }

    private boolean isPoisonPill(int valueToOffer) {
        final int poisonPill = -1;

        return valueToOffer == poisonPill;
    }
}
