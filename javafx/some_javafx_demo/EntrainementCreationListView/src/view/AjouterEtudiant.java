package view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modele.Etudiant;


public class AjouterEtudiant {

    private Etudiant etudiantCree;
    @FXML
    private Button btnAjouter;

    @FXML
    private TextField textFieldNom;

    @FXML
    private TextField textFieldPrenom;

    private BooleanProperty disable = new SimpleBooleanProperty();
        public boolean getDisable() { return disable.get(); }
        public void setDisable(boolean disable) { this.disable.set(disable); }
        public BooleanProperty disableProperty() { return disable; }
    private StringProperty nom = new SimpleStringProperty();
        public String getNom() { return nom.get(); }
        public void setNom(String nom) { this.nom.set(nom); }
        public StringProperty nomProperty() { return nom; }
    private StringProperty prenom = new SimpleStringProperty();
        public String getPrenom() { return prenom.get(); }
        public void setPrenom(String prenom) { this.prenom.set(prenom); }
        public StringProperty prenomProperty() { return prenom; }

    public void initialize() {
        constructionListener(nom, prenom);
        constructionListener(prenom, nom);
        constructionBinding();
    }

    private void constructionListener(StringProperty firstField, StringProperty secondField) {
        firstField.addListener((__, ___, newValue) -> {
            if (!newValue.equals("") && !secondField.get().equals("")) {
                setDisable(false);
            } else {
                setDisable(true);
            }
        });
    }

    private void constructionBinding() {
        nomProperty().bind(textFieldNom.textProperty());
        prenomProperty().bind(textFieldPrenom.textProperty());
        btnAjouter.disableProperty().bind(disableProperty());

    }

    @FXML
    private void clicAjouter() {
        etudiantCree = new Etudiant(textFieldNom.getText(), textFieldPrenom.getText());
        fermerFenetre();
    }

    @FXML
    private void clicAnnuler() {
        fermerFenetre();
    }

    private void fermerFenetre() {
        textFieldNom.getScene().getWindow().hide();
    }

    public Etudiant getEtudiantCree() {
        return etudiantCree;
    }
}
