import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class Singe implements Callable<List<Banane>> {
    private List<Banane> bananasCollected = new ArrayList<>();
    private final int index;
    private final List<List<Banane>> casiers;

    public Singe(int index, List<List<Banane>> casiers) {
        this.index = index;
        this.casiers = casiers;
    }

    @Override
    public List<Banane> call() {
        ramasserBanane();
        casiers.set(index, bananasCollected);

        return bananasCollected;
    }

    private void ramasserBanane() {
        bananasCollected = generateRandomBananaList();
        System.out.printf(
                "Le singe %d a ramassé %d banane(s)\n", index + 1, bananasCollected.size());
    }

    private List<Banane> generateRandomBananaList() {
        final List<Banane> bananas = new ArrayList<>();
        final Random random = new Random();
        final int randomIteration = random.nextInt(4);
        for (int i = 0; i < randomIteration; i++) {
            bananas.add(new Banane());
        }

        return bananas;
    }
}
