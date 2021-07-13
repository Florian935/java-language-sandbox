public class Patissier implements Runnable {

    private final Patisserie patisserie;

    public Patissier(Patisserie patisserie) {
        this.patisserie = patisserie;
    }

    @Override
    public void run() {
        patisserie.depose(new Gateau());
        System.out.println("Depot");
    }
}