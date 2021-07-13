package view;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import viewmodel.VetementVM;

public class CelluleVetement extends ListCell<VetementVM> {

    private final Label labelNom = new Label();
    private final Label labelCouleur = new Label();
    private final Rectangle rectangle = new Rectangle();
    private final Circle circle = new Circle();

    @Override
    protected void updateItem(VetementVM vetementVM, boolean empty) {
        super.updateItem(vetementVM, empty);
        if (!empty) {
            HBox hBox = new HBox();
            constructionBinding(vetementVM);
            initialisationPropriete(hBox);
        } else {
            setGraphic(null);
        }
    }

    private void initialisationPropriete(HBox hBox) {
        rectangle.setWidth(30);
        rectangle.setHeight(12);
        circle.setRadius(12);
        getStylesheets().add("/css/CelluleVetement.css");
        hBox.getChildren().addAll(labelNom, circle, labelCouleur);
        setGraphic(hBox);
    }

    private void constructionBinding(VetementVM vetementVM) {
        circle.fillProperty().bind(vetementVM.getCouleur().paletteRVBProperty());
        rectangle.fillProperty().bind(vetementVM.getCouleur().paletteRVBProperty());
        labelNom.textProperty().bind(Bindings.format("%s, Couleur: ",
                vetementVM.nomProperty()
                ));
        labelCouleur.textProperty().bind(Bindings.format(" (R: %d, V: %d, B: %d",
                vetementVM.getCouleur().rougeProperty(),
                vetementVM.getCouleur().vertProperty(),
                vetementVM.getCouleur().bleuProperty()
                ));
    }
}
