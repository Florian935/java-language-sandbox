package view;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import viewmodel.CouleurVM;

public class CelluleCouleur extends ListCell<CouleurVM> {

    private Label label = new Label();
    private Rectangle rectangle = new Rectangle();

    @Override
    protected void updateItem(CouleurVM couleurVM, boolean empty) {
        super.updateItem(couleurVM, empty);
        if (!empty) {
            HBox hbox = new HBox();
            constructionBinding(couleurVM);
            initialisationElementGraphique(couleurVM, hbox);
        } else {
            setGraphic(null);
        }
    }

    private void constructionBinding(CouleurVM couleurVM) {
        label.textProperty().bind(Bindings.format("R: %d, V: %d, B: %d",
                couleurVM.rougeProperty(),
                couleurVM.vertProperty(),
                couleurVM.bleuProperty()
                )
        );
    }

    private void initialisationElementGraphique(CouleurVM couleurVM, HBox hbox) {
        rectangle.setHeight(12);
        rectangle.setWidth(30);
        rectangle.fillProperty().bind(couleurVM.couleurProperty());
        getStylesheets().add("/css/CelluleCouleur.css");
        getStyleClass().add("cellule-couleur");
        hbox.getChildren().addAll(rectangle, label);
        setGraphic(hbox);
    }
}
