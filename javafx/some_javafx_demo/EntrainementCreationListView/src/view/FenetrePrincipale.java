package view;

import donnees.Stub;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modele.CelluleEtudiant;
import modele.Etudiant;
import modele.Promotion;

import java.io.IOException;

public class FenetrePrincipale {

    private Promotion maPromo = new Promotion(Stub.getLesEtudiants());

    @FXML
    private ListView<Etudiant> listView;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    public void initialize() {
        listView.getSelectionModel().selectedItemProperty().addListener((__, oldValue, newValue) ->{
            if (oldValue != null) {
              destructionBinding(oldValue);
            }
            if (newValue != null) {
                constructionBindng(newValue);
            }
        });
        listView.setItems(maPromo.getLesEtudiantsObs());
        listView.setCellFactory(__ -> new CelluleEtudiant());
    }

    private void destructionBinding(Etudiant oldValue) {
        textField1.textProperty().unbindBidirectional(oldValue.nomProperty());
        textField2.textProperty().unbindBidirectional(oldValue.prenomProperty());
    }

    private void constructionBindng(Etudiant newValue) {
        textField1.textProperty().bindBidirectional(newValue.nomProperty());
        textField2.textProperty().bindBidirectional(newValue.prenomProperty());
    }

    @FXML
    private void clicAjouterEtudiant() {
        try {
            FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/AjouterEtudiant.fxml"));
            ouvrirFenetreAjouterEtudiant(leLoader);
            Etudiant etudiantCree = leLoader.<AjouterEtudiant>getController().getEtudiantCree();
            if (etudiantCree != null) {
                maPromo.getLesEtudiantsObs().add(etudiantCree);
            }
        } catch (Exception e){
            ouvrirFenetreErreur();
        }
    }

    private void ouvrirFenetreErreur() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Une erreur est survenue.");
        alert.setResizable(false);
        alert.setTitle("Erreur 34290432");
        alert.setHeaderText("");
        alert.setContentText("");
        alert.initOwner(textField1.getScene().getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }

    private void ouvrirFenetreAjouterEtudiant(FXMLLoader leLoader) throws IOException {
        Scene scene = new Scene(leLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.initOwner(textField1.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Ajouter un étudiant");
        stage.setResizable(false);
        stage.showAndWait();
    }
}
