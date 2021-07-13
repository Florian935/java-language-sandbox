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

public class BoutonAction extends HBox {

    @FXML
    private Button btnAjouter;

    private final StringProperty textBtnAjouter = new SimpleStringProperty();
        public String getTextBtnAjouter() { return textBtnAjouter.get(); }
        public StringProperty textBtnAjouterProperty() { return textBtnAjouter; }
        public void setTextBtnAjouter(String textBtnAjouter) { this.textBtnAjouter.set(textBtnAjouter); }

    private final ObjectProperty<EventHandler<ActionEvent>> onActionBtnAjouter = new SimpleObjectProperty<>(action ->
            textBtnAjouter.set("Ajout impossible.")
    );
        public EventHandler<ActionEvent> getOnActionBtnAjouter() { return onActionBtnAjouter.get(); }
        public ObjectProperty<EventHandler<ActionEvent>> onActionBtnAjouterProperty() { return onActionBtnAjouter; }
        public void setOnActionBtnAjouter(EventHandler<ActionEvent> onActionBtnAjouter) { this.onActionBtnAjouter.set(onActionBtnAjouter); }

    public BoutonAction() throws IOException {
        FXMLLoader controller = new FXMLLoader(getClass().getResource("/fxml/uc/BoutonAction.fxml"));
        controller.setController(this);
        controller.setRoot(this);
        getStylesheets().add("/css/uc/BoutonAction.css");
        controller.load();
    }

    public void initialize() {
        btnAjouter.textProperty().bind(textBtnAjouter);
    }

    @FXML
    private void clicBtnAjouter(ActionEvent event) {
        onActionBtnAjouter.get().handle(event);
    }
}
