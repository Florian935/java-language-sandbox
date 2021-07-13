package model;

public class ExempleConcurrent extends Thread {
    public static int compte = 0;

    public void run() {
        int tmp = compte;

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        tmp = tmp + 1;
        compte = tmp;
    }
}
