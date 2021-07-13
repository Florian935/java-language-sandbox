import java.util.ArrayList;
import java.util.List;

public class Patisserie {

    private final List<Gateau> lesGateaux = new ArrayList<>();

    public int getStock() {
        return this.lesGateaux.size();
    }

    public synchronized void depose(Gateau gateau) {
        this.lesGateaux.add(gateau);
        System.out.println("Depot");
        this.notifyAll();
    }

    public synchronized void achete(int i) {
        while (getStock() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        lesGateaux.remove(i);
        System.out.println("Achat");
    }
}
