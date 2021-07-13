package model;

public class TestSleep extends Thread{

    public TestSleep(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            System.out.println(this.getName() + " a été lancé");
            Thread.sleep(100);
            System.out.println(this.getName() + " est terminé");
        } catch (InterruptedException e) {
            System.out.println(this.getName() + " a été interrompu");
        }
    }

}
