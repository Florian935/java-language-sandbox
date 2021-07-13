package launch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.FenetrePrincipale;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FenetrePrincipale.fxml"));
        loader.setController(new FenetrePrincipale());
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}
