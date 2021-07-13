package view;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import viewmodel.OutilVM;

public class CelluleOutil extends ListCell<OutilVM> {

    private Label label = new Label();

    @Override
    protected void updateItem(OutilVM outilVM, boolean empty) {
        super.updateItem(outilVM, empty);
        if (!empty) {
            label.textProperty().bind(Bindings.format("Nom: %s, Prix: %.2f, Quantité: %d, Taille: %s",
                    outilVM.nomProperty(),
                    outilVM.prixProperty(),
                    outilVM.quantiteProperty(),
                    outilVM.tailleProperty()));
            getStylesheets().add("/css/CelluleOutil.css");
            setGraphic(label);
        } else {
            setGraphic(null);
        }
    }
}
