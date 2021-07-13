package model;

import javafx.scene.control.ListCell;
import viewModel.EtudiantVM;

public class CelluleEtudiantVM extends ListCell<EtudiantVM> {

    @Override
    protected void updateItem(EtudiantVM item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            textProperty().bind(item.nomProperty());
        } else {
            textProperty().unbind();
            setText("");
        }
    }
}
