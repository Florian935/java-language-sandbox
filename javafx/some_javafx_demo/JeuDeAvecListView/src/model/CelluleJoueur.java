package model;

import javafx.scene.control.ListCell;

public class CelluleJoueur extends ListCell<Joueur> {

    @Override
    protected void updateItem(Joueur joueur, boolean empty) {
        super.updateItem(joueur, empty);
        if (!empty) {
            textProperty().bind(joueur.pseudoProperty().concat(" (").concat(joueur.dateVictoireProperty()).concat(")"));
        } else {
            textProperty().unbind();
            setText("");
        }
    }
}
