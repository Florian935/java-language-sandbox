import java.io.IOException;
import java.io.PipedWriter;
import java.util.Random;

public class Sender extends Thread {

    private final Random random = new Random();
    private final PipedWriter writer = new PipedWriter();

    public PipedWriter getWriter() {
        return writer;
    }

    @Override
    public void run() {
        char finisher = 0;
        while (finisher != 'z') {
            for (char c = 'A'; c <= 'z'; c++) {
                finisher = c;
                try {
                    writer.write(c);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }
        }
    }
}
