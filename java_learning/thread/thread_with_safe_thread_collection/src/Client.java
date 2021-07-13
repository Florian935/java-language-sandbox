public class Client implements Runnable {

    private final Patisserie patisserie;

    public Client(Patisserie patisserie) {
        this.patisserie = patisserie;
    }

    @Override
    public void run() {
        patisserie.achete();
        System.out.println("Achat");
    }
}