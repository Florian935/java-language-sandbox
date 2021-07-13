package view;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import viewmodel.LivreVM;

public class CelluleLivre extends ListCell<LivreVM> {

    private Label label = new Label();

    @Override
    protected void updateItem(LivreVM livreVM, boolean empty) {
        super.updateItem(livreVM, empty);
        if (!empty) {
            label.textProperty().bind(Bindings.format("Titre: %s, prix: %.2f€, nombre exemplaire: %d, type: %s.",
                    livreVM.titreProperty(),
                    livreVM.prixProperty(),
                    livreVM.nombreExemplaireProperty(),
                    livreVM.typeProperty()
            ));
            getStylesheets().add("/css/CelluleLivre.css");
            getStyleClass().add("cellule");
            setGraphic(label);
        } else {
            setGraphic(null);
            setText("");
        }
    }
}
