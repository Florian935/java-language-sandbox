import java.io.IOException;
import java.io.PipedReader;

public class Receiver extends Thread {

    private final PipedReader reader;

    public Receiver(Sender sender) throws IOException {
        reader = new PipedReader(sender.getWriter());
    }

    @Override
    public void run() {
        char finisher = 0;
        while (finisher != 'z') {
            try {
                finisher = (char) (reader.read());
                System.out.println("Receiver: " + finisher);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
