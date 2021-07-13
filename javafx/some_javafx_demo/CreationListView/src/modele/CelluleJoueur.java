package modele;

import javafx.scene.control.Button;
import javafx.scene.control.ListCell;

public class CelluleJoueur extends ListCell<Joueur> {

    private Button buttonSupprimer = new Button();

    @Override
    protected void updateItem(Joueur joueur, boolean empty) {
        super.updateItem(joueur, empty);
        if (!empty && joueur != null) {
            buttonSupprimer.setText("Supprimer");
            setGraphic(buttonSupprimer);
            textProperty().bind(joueur.nomProperty().concat(" ").concat(joueur.prenomProperty()));
            buttonSupprimer.setOnAction(__ -> getListView().getItems().remove(getItem()));
        } else {
            textProperty().unbind();
            setGraphic(null);
            setText("");
            buttonSupprimer.setText("");
        }
    }
}
