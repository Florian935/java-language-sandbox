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
import viewmodel.EtudiantVM;
import viewmodel.PromotionVM;

import java.io.IOException;

public class FenetrePrincipale {

    private PromotionVM viewmodel;

    @FXML
    private ListView<EtudiantVM> listView;

    @FXML
    private TextField textField;

    public FenetrePrincipale(PromotionVM viewmodel) {
        this.viewmodel = viewmodel;
    }

    public void initialize() {
        listView.itemsProperty().bind(viewmodel.lesEtudiantsVMProperty());
        listView.getSelectionModel().selectedItemProperty().addListener((unused1, oldValue, newValue) -> {
            if (oldValue != null) {
                destructionBinding(oldValue);
            }
            if (newValue != null) {
                constructionBinding(newValue);
            }
        });
        listView.setCellFactory(unused -> new CelluleEtudiantVM());
    }

    private void destructionBinding(EtudiantVM etudiantVM) {
        textField.textProperty().unbindBidirectional(etudiantVM.nomProperty());
    }

    private void constructionBinding(EtudiantVM etudiantVM) {
        textField.textProperty().bindBidirectional(etudiantVM.nomProperty());
    }

    @FXML
    private void clicAjouter() {
        try {
            AjouterEtudiant ajouterEtudiant = new AjouterEtudiant();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AjouterEtudiant.fxml"));
            loader.setController(ajouterEtudiant);
            initFenetreAjouterEtudiant(loader);
            String nom = loader.<AjouterEtudiant>getController().getNom();
            if (nom != null) {
                this.viewmodel.ajouterEtudiant(nom);
            }
        } catch (Exception e) {
            afficherFenetreErreur();
        }
    }

    private void afficherFenetreErreur() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Une erreur est survenue.", ButtonType.OK);
        alert.setResizable(false);
        alert.setTitle("Erreur 342343");
        alert.setHeaderText(null);
        alert.initOwner(textField.getScene().getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }

    private void initFenetreAjouterEtudiant(FXMLLoader loader) throws IOException {
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.initOwner(textField.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Ajouter un étudiant");
        stage.setResizable(false);
        stage.showAndWait();
    }

    public PromotionVM getViewmodel() {
        return this.viewmodel;
    }
}
