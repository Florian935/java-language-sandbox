package modele;

import javafx.scene.control.Button;
import javafx.scene.control.ListCell;

public class CelluleEtudiant extends ListCell<Etudiant> {

//    HBox hbox = new HBox();
//    CheckBox checkBox = new CheckBox();
    Button button = new Button();

    @Override
    protected void updateItem(Etudiant etudiant, boolean empty) {
        super.updateItem(etudiant, empty);
        if (!empty) {
            //hbox.getChildren().addAll(button, checkBox);
            setGraphic(button);
            textProperty().bind(etudiant.nomProperty().concat(" ").concat(etudiant.prenomProperty()));
            //checkBox.textProperty().bind(etudiant.nomProperty());
            //button.textProperty().bind(etudiant.prenomProperty());
            button.textProperty().set("Supprimer");
            button.setOnAction(__ -> getListView().getItems().remove(getItem()));
        } else {
            textProperty().unbind();
            //setGraphic(null);
            setText("");
            //checkBox.textProperty().unbind();
            button.textProperty().unbind();
            //checkBox.textProperty().set("");
            setGraphic(null);
            button.textProperty().set("");
        }
    }
}
