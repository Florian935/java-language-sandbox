import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws IOException {
        final Sender sender = new Sender();
        final Receiver receiver = new Receiver(sender);

        sender.start();
        receiver.start();
    }
}
