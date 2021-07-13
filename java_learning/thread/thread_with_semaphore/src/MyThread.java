import java.util.concurrent.Semaphore;

public class MyThread extends Thread {

    private final String text;
    private final Semaphore semaphore;

    public MyThread(String text, Semaphore semaphore) {
        this.text = text;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Avant entrée %s\n", text);
            semaphore.acquire();
            System.out.printf("%s, compteur sémaphore = %d%n\n", text, semaphore.availablePermits());
            Thread.sleep(10);
            semaphore.release();
            System.out.printf(
                    "%s Fin, %d compteur(s) restant(s)\n", text, semaphore.availablePermits());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
