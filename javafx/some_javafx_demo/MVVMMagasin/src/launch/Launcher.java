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
        MagasinVM magasin;
        Loader<Magasin> loader;
        try {
            loader = new Deserializer();
            magasin = new MagasinVM(loader.load());
         } catch (IOException e) {
            loader = new StubMagasin();
            magasin = new MagasinVM(loader.load());
        }
        initialisationFenetrePrincipale(stage, magasin);
    }

    private void initialisationFenetrePrincipale(Stage stage, MagasinVM magasin) throws IOException {
        fenetrePrincipale = new FenetrePrincipale(magasin);
        FXMLLoader controller = new FXMLLoader(getClass().getResource("/fxml/FenetrePrincipale.fxml"));
        controller.setController(fenetrePrincipale);
        Scene scene = new Scene(controller.load());
        stage.setScene(scene);
        stage.setTitle("Gestion du magasin");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Saver<Magasin> saver = new Serializer();
        saver.save(fenetrePrincipale.getViewmodel().getModel());
    }
}
