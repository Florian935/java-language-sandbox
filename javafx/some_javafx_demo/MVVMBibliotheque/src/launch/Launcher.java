package launch;

import donnee.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Bibliotheque;
import view.FenetrePrincipale;
import viewmodel.BibliothequeVM;

import java.io.IOException;

public class Launcher extends Application {

    private FenetrePrincipale fenetrePrincipale;

    @Override
    public void start(Stage stage) throws Exception {
        BibliothequeVM bibliothequeVM;
        Loader<Bibliotheque> loader;
        try {
            loader = new Deserializer();
            bibliothequeVM = new BibliothequeVM(loader.load());
        } catch (IOException e) {
            loader = new StubBibliotheque();
            bibliothequeVM = new BibliothequeVM(loader.load());
        }
        initialisationFenetrePrincipale(stage, bibliothequeVM);
    }

    private void initialisationFenetrePrincipale(Stage stage, BibliothequeVM bibliothequeVM) throws IOException {
        fenetrePrincipale = new FenetrePrincipale(bibliothequeVM);
        FXMLLoader controller = new FXMLLoader(getClass().getResource("/fxml/FenetrePrincipale.fxml"));
        controller.setController(fenetrePrincipale);
        Scene scene = new Scene(controller.load());
        scene.getStylesheets().add("/css/FenetrePrincipale.css");
        stage.setScene(scene);
        stage.setTitle("Inventaire de la bibliothèque");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Saver<Bibliotheque> saver = new Serializer();
        saver.save(fenetrePrincipale.getViewmodel().getModel());
    }
}
