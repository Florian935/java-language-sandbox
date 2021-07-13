public class Launcher {
    public static void main(String[] args) {
        final int processorsNumber = Runtime.getRuntime().availableProcessors();

        System.out.println(processorsNumber);
    }
}
