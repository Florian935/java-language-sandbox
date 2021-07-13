public class Consumer extends Thread {
    private final BoundedBuffer boundedBuffer;

    public Consumer(BoundedBuffer boundedBuffer) {
        this.boundedBuffer = boundedBuffer;
    }

    @Override
    public void run() {
        try {
            final Integer i = boundedBuffer.take();
            System.out.printf("Pris l'Integer %d\n", i);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
