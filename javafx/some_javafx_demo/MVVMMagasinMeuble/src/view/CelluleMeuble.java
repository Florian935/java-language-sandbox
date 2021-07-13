package view;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ListCell;
import viewmodel.MeubleVM;

public class CelluleMeuble extends ListCell<MeubleVM> {

    protected void updateItem(MeubleVM meuble, boolean empty) {
        super.updateItem(meuble, empty);
        if (!empty) {
            textProperty().bind(Bindings.format("Nom: %s, état: %s, quantité: %d, prix: %2.2f",
                    meuble.nomProperty(),
                    meuble.etatProperty(),
                    meuble.quantiteProperty(),
                    meuble.prixProperty())
            );
        } else {
            textProperty().unbind();
            setText("");
        }
    }
}
