import java.util.concurrent.CountDownLatch;

public class MyRunnable implements Runnable {
    private final String name;
    private final CountDownLatch countDownLatch;

    public MyRunnable(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            countDownLatch.countDown();

            System.out.printf("Hello %s%d\n", name, i);
        }
    }
}
