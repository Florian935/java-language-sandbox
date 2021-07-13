import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class Singe implements Callable<Integer> {
    private Integer numberOfBananasCollected = 0;
    private final int index;
    private final List<Integer> casiers;

    public Singe(int index, List<Integer> casiers) {
        this.index = index;
        this.casiers = casiers;
    }

    @Override
    public Integer call() {
        ramasserBanane();
        casiers.add(index, numberOfBananasCollected);

        return numberOfBananasCollected;
    }

    private void ramasserBanane() {
        final Random random = new Random();
        numberOfBananasCollected = random.nextInt(10);
        System.out.printf(
                "Le singe %d a ramassé %d banane(s)\n", index + 1, numberOfBananasCollected);
    }
}
