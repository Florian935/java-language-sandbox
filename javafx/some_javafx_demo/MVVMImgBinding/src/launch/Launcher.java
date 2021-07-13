package launch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Voiture;
import view.FenetrePrincipale;
import viewModel.VoitureVM;

public class Launcher extends Application {

    private FenetrePrincipale fenetrePrincipale;

    @Override
    public void start(Stage stage) throws Exception {
        VoitureVM voitureVM = new VoitureVM(new Voiture("resources/images/1.png"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FenetrePrincipale.fxml"));
        fenetrePrincipale = new FenetrePrincipale(voitureVM);
        loader.setController(fenetrePrincipale);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}
