package view;

import data.Stub;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import modele.Etudiant;

import java.util.List;

public class FenetrePrincipale {

    private List<Etudiant> promo = Stub.getPromo();
    private IntegerProperty indexEnCours = new SimpleIntegerProperty(0);
        public Integer getIndexEnCours() { return indexEnCours.get(); }
        public void setIndexEnCours(Integer index) { indexEnCours.set(index); }
        public IntegerProperty indexEnCoursProperty() { return indexEnCours; }
    private int indexAffiche = 0;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    public void initialize() {
        textField2.textProperty().bindBidirectional(promo.get(indexEnCours.get()).prenomProperty());
        indexEnCours.addListener(
                (__, oldIndex, newIndex) -> constructionBindingBidirectionnel(oldIndex.intValue(), newIndex.intValue()));
        constructionBindingUnidirectionel();
    }

    @FXML
    private void clicPrecedent() {
        if (indexAffiche > 0) {
            indexAffiche--;
            indexEnCours.set(indexAffiche);
            constructionBindingUnidirectionel();
        }
    }

    @FXML
    private void clicSuivant() {
        if (indexAffiche < promo.size() -1) {
            indexAffiche++;
            indexEnCours.set(indexAffiche);
            constructionBindingUnidirectionel();
        }
    }

    private void constructionBindingUnidirectionel() {
        textField1.textProperty().bind(promo.get(indexAffiche).nomProperty());
    }

    private void constructionBindingBidirectionnel(int oldIndex, int newIndex) {
        textField2.textProperty().unbindBidirectional(promo.get(oldIndex).prenomProperty());
        textField2.textProperty().bindBidirectional(promo.get(newIndex).prenomProperty());
    }
}
