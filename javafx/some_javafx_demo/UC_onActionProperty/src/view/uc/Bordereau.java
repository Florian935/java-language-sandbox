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

public class Bordereau extends HBox {

    @FXML
    private Button btn1;

    private final StringProperty btnB1 = new SimpleStringProperty();
        public String getBtnB1() { return btnB1.get(); }
        public void setBtnB1(String btnB1) { this.btnB1.set(btnB1); }
        public StringProperty btnB1Property() { return btnB1; }

    private final ObjectProperty<EventHandler<ActionEvent>> onActionB1 = new SimpleObjectProperty<>(new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            btn1.textProperty().unbind();
            btn1.setText("Pas de comportement");
        }
    });
        public EventHandler<ActionEvent> getOnActionB1() { return onActionB1.get(); }
        public ObjectProperty<EventHandler<ActionEvent>> onActionB1Property() { return onActionB1; }
        public void setOnActionB1(EventHandler<ActionEvent> onActionB1) { this.onActionB1.set(onActionB1); }

    private final ObjectProperty<EventHandler<ActionEvent>> onActionB2 = new SimpleObjectProperty<>(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            btn1.textProperty().unbind();
            btn1.setText("Pas de comportement 2");
        }
    });
        public EventHandler<ActionEvent> getOnActionB2() { return onActionB2.get(); }
        public ObjectProperty<EventHandler<ActionEvent>> onActionB2Property() { return onActionB2; }
        public void setOnActionB2(EventHandler<ActionEvent> onActionB2) { this.onActionB2.set(onActionB2); }

    public Bordereau() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/footer/Bordereau.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    public void initialize() {
            btn1.textProperty().bind(btnB1);
    }

    @FXML
    private void clicB1(ActionEvent event) {
        System.out.println("clicB1 du bordereau");
        onActionB1.get().handle(event);
    }

    @FXML
    private void clicB2(ActionEvent event) {
        System.out.println("clicB2 du bordereau");
        onActionB2.get().handle(event);
    }
}
