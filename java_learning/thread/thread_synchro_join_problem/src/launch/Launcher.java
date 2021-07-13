package launch;

import model.ExempleConcurrent;

public class Launcher {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new ExempleConcurrent();
        Thread t2 = new ExempleConcurrent();
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.printf("compteur = %d\n", ExempleConcurrent.compte);
    }
}
