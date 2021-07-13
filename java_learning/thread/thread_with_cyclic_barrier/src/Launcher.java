public class Launcher {

    public static void main(String[] args) {
        final Integer[] data = {0, 0, 0, 0};
        final Solver solver = new Solver(data);

        // loop each time that the barrier is reached.
        // We make the sum of each barrier reached.
        for (int i = 0; i < 4; i++) {
            waitForFinishingBarrier(solver);
            // only launched when the previous barrier is reached
            solver.launchWorker();
        }
    }

    private static void waitForFinishingBarrier(Solver solver) {
        while (!solver.isFinish) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
