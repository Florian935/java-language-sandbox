package modele;

import javafx.scene.control.Button;
import javafx.scene.control.ListCell;

public class CelluleComboEtudiant extends ListCell<Etudiant> {

    private Button buttonSupprimer = new Button("Supprimer");

    @Override
    protected void updateItem(Etudiant etudiant, boolean empty) {
        super.updateItem(etudiant, empty);
        if (!empty) {
            setGraphic(buttonSupprimer);
            textProperty().bind(etudiant.nomProperty().concat(" ").concat(etudiant.prenomProperty()));
//            buttonSupprimer.setOnAction(__ -> getListView().getItems().remove(0));
        } else {
            setGraphic(null);
            textProperty().unbind();
            setText("");
        }
    }
}
