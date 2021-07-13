package view.footer;

import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class Footer extends HBox {

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;



    private final StringProperty btnABinder = new SimpleStringProperty();
        public String getBtnABinder() { return btnABinder.get(); }
        public StringProperty btnABinderProperty() { return btnABinder; }
        public void setBtnABinder(String btnABinder) { this.btnABinder.set(btnABinder); }

    private final StringProperty btnABinder2 = new SimpleStringProperty();
    public String getBtnABinder2() { return btnABinder2.get(); }
    public StringProperty btnABinder2Property() { return btnABinder2; }
    public void setBtnABinder2(String btnABinder2) { this.btnABinder2.set(btnABinder2); }

    private final ObjectProperty<EventHandler<ActionEvent>> onActionBtn1 = new SimpleObjectProperty<>(new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            btn1.textProperty().unbind();
            btn1.setText("Comportement par défaut.");
        }
    });
        public EventHandler<ActionEvent> getOnActionBtn1() { return onActionBtn1.get(); }
        public ReadOnlyObjectProperty<EventHandler<ActionEvent>> onActionBtn1Property() { return onActionBtn1; }
        public void setOnActionBtn1(EventHandler<ActionEvent> onActionBtn1) { this.onActionBtn1.set(onActionBtn1); }

    private final ObjectProperty<EventHandler<ActionEvent>> onActionBtn2 = new SimpleObjectProperty<>(new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            btn2.textProperty().unbind();
            btn2.setText("Comportement par défaut.");
        }
    });

    public Footer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/footer/Footer.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    public void initialize() {
            btn1.textProperty().bind(btnABinder);
    }

    @FXML
    private void clicBtn1(ActionEvent action) {
        onActionBtn1.get().handle(action);
    }

    @FXML
    private void clicBtn2(ActionEvent action) {
            onActionBtn2.get().handle(action);
    }
}
