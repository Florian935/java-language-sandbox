import java.util.Random;

public class Banane {

    private final int taille;

    public Banane() {
        this.taille = generateRandomTaille();
    }

    private int generateRandomTaille() {
        final Random random = new Random();
        return random.nextInt(100);
    }

    public int getTaille() {
        return taille;
    }

    @Override
    public String toString() {
        return "Banane{" +
                "taille=" + taille +
                '}';
    }
}
