public class Patissier implements Runnable{

    private final Patisserie patisserie;

    public Patissier(Patisserie patisserie) {
        this.patisserie = patisserie;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        patisserie.depose(new Gateau());
    }
}