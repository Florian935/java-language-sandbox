package launcher;

import model.MyCustomRunnable;
import model.MyCustomThread;

public class Launch {

    public static void main(String[] args) {
        Thread t1 = firstMethodToCreateThread();
        secondMethodToCreateThread();
        thirdMethodToCreateThread();

        waitFor(5000);
        System.out.printf("Etat du Thread de la première méthode: %s\n", t1.getState());
    }


    public static Thread firstMethodToCreateThread() {
        final Thread t1 = new Thread(() -> {
            try {
                System.out.println("Première méthode");
                Thread.sleep(2000);
                System.out.println("Première méthode après Thread.sleep de la première méthode");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        System.out.printf("Etat du Thread de la première méthode: %s\n", t1.getState());
        t1.start();
        System.out.printf("Etat du Thread de la première méthode: %s\n", t1.getState());
        return t1;
    }

    private static void secondMethodToCreateThread() {
        final MyCustomRunnable runnable = new MyCustomRunnable("Toto");
        final Thread t2 = new Thread(runnable);

        t2.start();
    }

    private static void thirdMethodToCreateThread() {
        final MyCustomThread t3 = new MyCustomThread("Titi");
        t3.start();
    }

    private static void waitFor(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
