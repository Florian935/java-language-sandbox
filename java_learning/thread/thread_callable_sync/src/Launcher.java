public class Launcher {
    public static void main(String[] args) {
        final BananaCounter bananaCounter = new BananaCounter(10);
        bananaCounter.processTasks();
        bananaCounter.nonAsyncWaitForAllTasksAndCountBananas();
    }
}
