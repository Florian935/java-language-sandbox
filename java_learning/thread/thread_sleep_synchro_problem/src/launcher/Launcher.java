package launcher;

import model.TestSleep;

public class Launcher {

    public static void main(String[] args) {
        try {
            TestSleep testSleep = new TestSleep("toto");
            testSleep.start();
            Thread.sleep(100);
            testSleep.interrupt();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
