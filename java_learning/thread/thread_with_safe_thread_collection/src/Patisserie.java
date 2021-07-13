import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Patisserie {

    private final BlockingQueue<Gateau> lesGateaux = new ArrayBlockingQueue<>(5);

    public void depose(Gateau gateau) {
        this.lesGateaux.offer(gateau);
    }

    public void achete() {
        try {
            lesGateaux.take();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}