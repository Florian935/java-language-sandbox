package launch;

import donnee.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ECommerce;
import view.FenetrePrincipale;
import viewmodel.ECommerceVM;

import java.io.IOException;

public class Launcher extends Application {

    private FenetrePrincipale fenetrePrincipale;

    @Override
    public void start(Stage stage) throws Exception {
        ECommerceVM eCommerceVM;
        Loader<ECommerce> loader;
        try {
            loader = new Deserializer();
            eCommerceVM = new ECommerceVM(loader.load());
        } catch (IOException e) {
            loader = new StubEcommerce();
            eCommerceVM = new ECommerceVM(loader.load());
        }

        fenetrePrincipale = new FenetrePrincipale(eCommerceVM);
        FXMLLoader controller = new FXMLLoader(getClass().getResource("/fxml/FenetrePrincipale.fxml"));
        controller.setController(fenetrePrincipale);
        ouvertureFenetrePrincipale(stage, controller);
    }

    private void ouvertureFenetrePrincipale(Stage stage, FXMLLoader controller) throws java.io.IOException {
        Scene scene = new Scene(controller.load());
        scene.getStylesheets().add("/css/FenetrePrincipale.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Vinted");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Saver<ECommerce> saver = new Serializer();
        saver.save(fenetrePrincipale.getViewmodel().getModel());
    }
}
