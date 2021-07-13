package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.CelluleEtudiantVM;
import viewModel.EtudiantVM;
import viewModel.PromotionVM;

import java.io.IOException;

public class FenetrePrincipale {

    @FXML
    private TextField textField;

    private PromotionVM viewmodel;

    @FXML
    private ListView<EtudiantVM> listViewEtudiant;

    public FenetrePrincipale(PromotionVM viewmodel) {
        this.viewmodel = viewmodel;
    }

    public void initialize() {
        listViewEtudiant.itemsProperty().bind(viewmodel.lesEtudiantsVMProperty());
        listViewEtudiant.setCellFactory(unused -> new CelluleEtudiantVM());
        listViewEtudiant.getSelectionModel().selectedItemProperty().addListener((unused, oldV, newV) -> {
            if (oldV != null) {
                textField.textProperty().unbindBidirectional(oldV.nomProperty());
            }
            if (newV != null) {
                textField.textProperty().bindBidirectional(newV.nomProperty());
            }
        });
    }

    public PromotionVM getViewmodel() {
        return viewmodel;
    }

    @FXML
    private void clicAjouterEtudiant() {
        try {
            FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/AjouterEtudiant.fxml"));
            ouvrirFenetreAjouterEtudiant(leLoader);
            String nom = leLoader.<AjouterEtudiant>getController().getNom();
            if (nom != null) {
                viewmodel.ajouterEtudiant(nom);
            }
        } catch (Exception e) {
            afficherErreur();
        }
    }

    private void ouvrirFenetreAjouterEtudiant(FXMLLoader leLoader) throws IOException {
        Scene scene = new Scene(leLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(textField.getScene().getWindow());
        stage.initStyle(StageStyle.UTILITY);
        stage.showAndWait();
    }

    private void afficherErreur() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Une erreur est survenue.", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Erreur 29320943");
        alert.setResizable(false);
        alert.initStyle(StageStyle.UTILITY);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(textField.getScene().getWindow());
        alert.showAndWait();
    }

    @FXML
    private void clicSupprimerEtudiant() {
        viewmodel.supprimerEtudiant(listViewEtudiant.getSelectionModel().getSelectedIndex());
        resetText();
    }

    private void resetText() {
        textField.setText("");
    }

    @FXML
    private void clicDeselectionner() {
        listViewEtudiant.getSelectionModel().select(-1);
        resetText();
    }

    @FXML
    private void clicQuitter() {
        textField.getScene().getWindow().hide();
    }
}
