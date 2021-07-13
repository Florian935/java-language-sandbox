package launch;

import donnee.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Promotion;
import view.FenetrePrincipale;
import viewmodel.PromotionVM;

import java.io.IOException;

public class Launcher extends Application {

    private PromotionVM viewmodel;
    private FenetrePrincipale fenetrePrincipale;

    @Override
    public void start(Stage stage) throws Exception {
        Loader<Promotion> loader = new Deserializer();
        try {
            viewmodel = new PromotionVM(loader.load());
        } catch (IOException e) {
            Promotion promo = new StubPromotion().getPromotion();
            viewmodel = new PromotionVM(promo);
        }

        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/FenetrePrincipale.fxml"));
        fenetrePrincipale = new FenetrePrincipale(viewmodel);
        leLoader.setController(fenetrePrincipale);
        Scene scene = new Scene(leLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Saver<Promotion> saver = new Serializer();
        saver.save(fenetrePrincipale.getViewmodel().getModel());
    }
}
