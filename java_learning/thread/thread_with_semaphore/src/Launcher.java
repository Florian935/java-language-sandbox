import java.util.concurrent.Semaphore;

public class Launcher {
    
    public static void main (String[] args) {
        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 5; i++) {
            MyThread myThread = new MyThread(String.format("Semaphore n°%d", i + 1), semaphore);
            myThread.start();
        }
    }
}
