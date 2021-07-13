package launch;

import donnee.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Promotion;
import view.FenetrePrincipale;
import viewModel.PromotionVM;

public class Launcher extends Application {

    private FenetrePrincipale fenetrePrincipale;

    @Override
    public void start(Stage stage) throws Exception {
        PromotionVM promotionVM;
        try {
            Loader<Promotion> leLoader = new Deserializer();
            promotionVM = new PromotionVM(leLoader.load());
        } catch (Exception e) {
            promotionVM = new PromotionVM(new StubPromotion().getPromotion());
        }
        initFenetrePrincipale(stage, promotionVM);
    }

    private void initFenetrePrincipale(Stage stage, PromotionVM promotionVM) throws java.io.IOException {
        fenetrePrincipale = new FenetrePrincipale(promotionVM);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FenetrePrincipale.fxml"));
        loader.setController(fenetrePrincipale);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Saver<Promotion> leSaver = new Serializer();
        leSaver.save(fenetrePrincipale.getViewmodel().getModel());
        super.stop();
    }
}

