public class Launcher {

    public static void main(String[] args) {
        final Patisserie patisserie = new Patisserie();
        final Thread[] lesPatissiers = new Thread[5];
        final Thread[] lesClients = new Thread[5];

        for (int i = 0; i < 5; i++) {
            lesPatissiers[i] = new Thread(new Patissier(patisserie));
            lesClients[i] = new Thread(new Client(patisserie));

            lesPatissiers[i].start();
            lesClients[i].start();
        }
    }
}
