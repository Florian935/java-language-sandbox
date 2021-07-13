package model;

import javafx.scene.control.ListCell;
import viewmodel.EtudiantVM;

public class CelluleEtudiantVM extends ListCell<EtudiantVM> {

    @Override
    protected void updateItem(EtudiantVM etudiant, boolean empty) {
        super.updateItem(etudiant, empty);
        if (!empty) {
            textProperty().bind(etudiant.nomProperty());
        } else {
            textProperty().unbind();
            setText("");
        }
    }
}
