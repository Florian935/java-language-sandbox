import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pipeline {

    private static final int NUMBER_OF_STAGE = 3;
    private static final int POISON_PILL = -1;

    public static void main(String[] args) {
        BlockingQueue<Integer> first;
        BlockingQueue<Integer> out = new ArrayBlockingQueue<>(NUMBER_OF_STAGE);
        BlockingQueue<Integer> in = new ArrayBlockingQueue<>(NUMBER_OF_STAGE);
        first = in;


        for (int i = 0; i < NUMBER_OF_STAGE; i++) {
            out = new ArrayBlockingQueue<>(NUMBER_OF_STAGE);
            new Stage(in, out, i).start();
            in = out;
        }

        new Listener(out).start();

        first.add(7);
        try {
            Thread.sleep(3000);

        } catch (InterruptedException ex) {
            Logger.getLogger(Pipeline.class.getName()).log(Level.SEVERE, null, ex);
        }

        first.add(2);
        try {
            Thread.sleep(3000);

        } catch (InterruptedException ex) {
            Logger.getLogger(Pipeline.class.getName()).log(Level.SEVERE, null, ex);
        }

        first.add(4);
        try {
            Thread.sleep(3000);

        } catch (InterruptedException ex) {
            Logger.getLogger(Pipeline.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Poison pill, stop the pipeline properly
        first.add(-1);
    }
}
