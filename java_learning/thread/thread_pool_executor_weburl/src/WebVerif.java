import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebVerif implements Runnable {
    private final String host;

    public WebVerif(String host) {
        this.host = host;
    }

    @Override
    public void run() {
        HttpURLConnection openConnection = null;
        try {
            final URL url = new URL(host);
            openConnection = (HttpURLConnection) url.openConnection();
            openConnection.connect();
            final int statusCode = openConnection.getResponseCode();
            System.out.printf("Code de retour: %d\n", statusCode);
        } catch (IOException e) {
            System.out.printf("Impossible d'ouvrir une connexion pour le site: %s\n", host);
        } finally {
            if (openConnection != null) {
                openConnection.disconnect();
            }
        }
    }
}
