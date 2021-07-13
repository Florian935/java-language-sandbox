package view;

import data.Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modele.CelluleEtudiant;
import modele.Etudiant;
import modele.Promotion;

public class FenetrePrincipale {

    private Promotion promo = new Promotion(Stub.getPromo());

    @FXML
    private ListView<Etudiant> listView;

    @FXML
    private Button btnAjouter;

    @FXML
    private TextField txtField1;

    @FXML
    private TextField txtField2;

    public void initialize() {
        listView.getSelectionModel().selectedItemProperty().addListener(((__, oldValue, newValue) -> {
            if (oldValue != null) {
                destructionBindingBidirectionnel(oldValue);
            }
            if (newValue != null) {
                constructionBindingBidirectionnel(newValue);
            }
        }));
        listView.setItems(promo.getLesEtudiants());
        listView.setCellFactory(__ -> new CelluleEtudiant());
    }

    private void destructionBindingBidirectionnel(Etudiant oldValue) {
        txtField1.textProperty().unbindBidirectional(oldValue.nomProperty());
        txtField2.textProperty().unbindBidirectional(oldValue.prenomProperty());
    }

    private void constructionBindingBidirectionnel(Etudiant newValue) {
        txtField1.textProperty().bindBidirectional(newValue.nomProperty());
        txtField2.textProperty().bindBidirectional(newValue.prenomProperty());
    }

    @FXML
    private void ajouterEtudiant() {
        try {
            FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/AjouterEtfudiant.fxml"));
            ouvrirFenetreAjouterEtudiant(leLoader);
            Etudiant etudiantCree = leLoader.<AjouterEtudiant>getController().getEtudiantCreer();
            if (etudiantCree != null) {
                promo.getLesEtudiants().add(etudiantCree);
            }
        } catch (Exception e) {
            ouvrirFenetreErreur();
        }
    }

    private void ouvrirFenetreErreur() {
        Alert alertPopUp = new Alert(Alert.AlertType.ERROR, "Une erreur est survenue dans l'application.");
        alertPopUp.initModality(Modality.APPLICATION_MODAL);
        alertPopUp.initStyle(StageStyle.UTILITY);
        alertPopUp.setTitle("Erreur 532534");
        alertPopUp.setHeaderText(null);
        alertPopUp.setHeight(150);
        alertPopUp.setWidth(300);
        alertPopUp.setResizable(false);
        alertPopUp.showAndWait();
    }

    private void ouvrirFenetreAjouterEtudiant(FXMLLoader leLoader) throws java.io.IOException {
        Scene scene = new Scene(leLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnAjouter.getScene().getWindow());
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Ajouter étudiant");
        stage.setResizable(false);
        stage.showAndWait();
    }
}
