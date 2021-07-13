public class Publisher extends Thread {

    private final BoundedBuffer boundedBuffer;
    private final Integer position;

    public Publisher(BoundedBuffer boundedBuffer, int position) {
        this.boundedBuffer = boundedBuffer;
        this.position = position;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
            boundedBuffer.put(position);
            System.out.printf("Déposé l'Integer %d\n", position);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
