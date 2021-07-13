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

public class UCBouton extends HBox {

    @FXML
    private Button btnUC;

    private final StringProperty textBtnUC = new SimpleStringProperty();
        public String getTextBtnUC() { return textBtnUC.get(); }
        public StringProperty textBtnUCProperty() { return textBtnUC; }
        public void setTextBtnUC(String textBtnUC) { this.textBtnUC.set(textBtnUC); }

     private final ObjectProperty<EventHandler<javafx.event.ActionEvent>> onActionBtnUC = new SimpleObjectProperty<>(action -> textBtnUC.set("Impossible."));
        public EventHandler<javafx.event.ActionEvent> getOnActionBtnUC() { return onActionBtnUC.get(); }
        public ObjectProperty<EventHandler<javafx.event.ActionEvent>> onActionBtnUCProperty() { return onActionBtnUC; }
        public void setOnActionBtnUC(EventHandler<javafx.event.ActionEvent> onActionBtnUC) { this.onActionBtnUC.set(onActionBtnUC); }

    public UCBouton() throws IOException {
        FXMLLoader controller = new FXMLLoader(getClass().getResource("/fxml/uc/UCBouton.fxml"));
        controller.setController(this);
        controller.setRoot(this);
        getStylesheets().add("/css/uc/UCBouton.css");
        controller.load();
    }

    public void initialize() {
        btnUC.textProperty().bind(textBtnUC);
    }

    @FXML
    private void clicBouton(ActionEvent event) {
        onActionBtnUC.get().handle(event);
    }
}
