public class Launcher {

    public static void main(String[] args) {
        final BoundedBuffer boundedBuffer = new BoundedBuffer();

        for (int i = 0; i < 20; i++) {
            Consumer consumer = new Consumer(boundedBuffer);
            Publisher publisher = new Publisher(boundedBuffer, i);

            consumer.start();
            publisher.start();
        }
    }
}
