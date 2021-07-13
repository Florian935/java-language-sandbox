package launch;

import donnee.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Magasin;
import view.FenetrePrincipale;
import viewmodel.MagasinVM;

import java.io.IOException;

public class Launcher extends Application {

    private FenetrePrincipale fenetrePrincipale;

    @Override
    public void start(Stage stage) throws Exception {
        MagasinVM magasinVM;
        Loader<Magasin> loader;
        try {
            loader = new Deserializer();
            magasinVM = new MagasinVM(loader.load());
        } catch (IOException e) {
            loader = new StubMagasin();
            magasinVM = new MagasinVM(loader.load());
        }
        initialisationFenetrePrincipale(stage, magasinVM);
    }

    private void initialisationFenetrePrincipale(Stage stage, MagasinVM magasinVM) throws java.io.IOException {
        fenetrePrincipale = new FenetrePrincipale(magasinVM);
        FXMLLoader controller = new FXMLLoader(getClass().getResource("/fxml/FenetrePrincipale.fxml"));
        controller.setController(fenetrePrincipale);
        Scene scene = new Scene(controller.load());
        scene.getStylesheets().add("/css/FenetrePrincipale.css");
        stage.setTitle("Bricôt Dépôt");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Saver<Magasin> saver = new Serializer();
        saver.save(fenetrePrincipale.getViewmodel().getModel());
    }
}
