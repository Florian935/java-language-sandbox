package view.uc;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class UCBoutonAjouter extends HBox {

    @FXML
    private Button boutonAjouter;

    private final StringProperty textBoutonAjouter = new SimpleStringProperty();
        public String getTextBoutonAjouter() { return textBoutonAjouter.get(); }
        public StringProperty textBoutonAjouterProperty() { return textBoutonAjouter; }
        public void setTextBoutonAjouter(String textBoutonAjouter) { this.textBoutonAjouter.set(textBoutonAjouter); }

    private final ObjectProperty<EventHandler<ActionEvent>> onActionBoutonAjouter = new SimpleObjectProperty<>(actionEvent -> {
            textBoutonAjouter.set("Ajout impossible.");
    });
        public EventHandler<ActionEvent> getOnActionBoutonAjouter() { return onActionBoutonAjouter.get(); }
        public ObjectProperty<EventHandler<ActionEvent>> onActionBoutonAjouterProperty() { return onActionBoutonAjouter; }
        public void setOnActionBoutonAjouter(EventHandler<ActionEvent> onActionBoutonAjouter) { this.onActionBoutonAjouter.set(onActionBoutonAjouter); }

    public UCBoutonAjouter() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/uc/UCBoutonAjouter.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
        getStylesheets().add("/css/uc/UCBoutonAjouter.css");
    }

    public void initialize() {
        boutonAjouter.textProperty().bind(textBoutonAjouter);
    }

    @FXML
    private void clicBoutonAjouter(ActionEvent event) {
        this.onActionBoutonAjouter.get().handle(event);
    }
}
