package model;

import javafx.scene.control.ListCell;
import viewmodel.EtudiantVM;

public class CelluleEtudiantVM extends ListCell<EtudiantVM> {

    @Override
    protected void updateItem(EtudiantVM etudiantVM, boolean empty) {
        super.updateItem(etudiantVM, empty);
        if (!empty) {
            textProperty().bind(etudiantVM.nomProperty());
        } else {
            textProperty().unbind();
            setText("");
        }
    }
}
