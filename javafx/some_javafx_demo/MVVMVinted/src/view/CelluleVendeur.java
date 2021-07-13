package view;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import viewmodel.VendeurVM;

public class CelluleVendeur extends ListCell<VendeurVM> {

    private Label label = new Label();

    @Override
    protected void updateItem(VendeurVM vendeurVM, boolean empty) {
        super.updateItem(vendeurVM, empty);
        if (!empty) {
            getStylesheets().add("/css/CelluleVendeur.css");
            label.textProperty().bind(Bindings.format("Pseudo: %s, nombre vente: %d, note: %.2f, statut: %s",
                    vendeurVM.pseudoProperty(),
                    vendeurVM.getNbVente(),
                    vendeurVM.noteProperty(),
                    vendeurVM.statutProperty()));
            setGraphic(label);
        } else {
            setGraphic(null);
        }
    }
}
