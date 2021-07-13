package view;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ListCell;
import viewmodel.VetementVM;

public class CelluleVetementVM extends ListCell<VetementVM> {

    @Override
    protected void updateItem(VetementVM vetementVM, boolean empty) {
        super.updateItem(vetementVM, empty);
        if (!empty) {
            textProperty().bind(Bindings.format("%s (taille: %s, quantité restante: %d)",
                    vetementVM.nomProperty(),
                    vetementVM.tailleProperty(),
                    vetementVM.quantiteProperty()
                    )
            );
        } else {
            textProperty().unbind();
            setText("");
        }
    }
}
