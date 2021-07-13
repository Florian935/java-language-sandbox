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

    FenetrePrincipale fenetrePrincipale;

    @Override
    public void start(Stage stage) throws Exception {
        PromotionVM promotionVM;
        Loader<Promotion> loader = new Deserializer();
        try {
            promotionVM = new PromotionVM(loader.load());
        } catch (IOException e) {
            promotionVM = new PromotionVM(new StubPromotion().getPromotion());
        }

        initFenetre(stage, promotionVM);
    }

    private void initFenetre(Stage stage, PromotionVM promotionVM) throws IOException {
        fenetrePrincipale = new FenetrePrincipale(promotionVM);
        FXMLLoader loaderFenetre = new FXMLLoader(getClass().getResource("/fxml/FenetrePrincipale.fxml"));
        loaderFenetre.setController(fenetrePrincipale);
        Scene scene = new Scene(loaderFenetre.load());
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
