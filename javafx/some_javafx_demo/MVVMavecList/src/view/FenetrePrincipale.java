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
        initListView();
    }

    private void initListView() {
        listView.itemsProperty().bind(viewmodel.lesEtudiantsProperty());
        listView.getSelectionModel().selectedItemProperty().addListener((unused, oldValue, newValue) -> {
            if (oldValue != null) {
                destructionBinding(oldValue);
            }
            if (newValue != null) {
                constructionBinding(newValue);
            }
        });
        listView.setCellFactory(unused -> new CelluleEtudiantVM());
    }

    private void destructionBinding(EtudiantVM oldValue) {
        textField.textProperty().unbindBidirectional(oldValue.nomProperty());
    }

    private void constructionBinding(EtudiantVM newValue) {
        textField.textProperty().bindBidirectional(newValue.nomProperty());
    }

    public PromotionVM getViewmodel() {
        return viewmodel;
    }

    @FXML
    private void clicAjouterEtudiant() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AjouterEtudiant.fxml"));
            ouvrirFenetreAjouterEtudiant(loader);
            String nom = loader.<AjouterEtudiant>getController().getNom();
            if (nom != null) {
                this.viewmodel.ajouterEtudiant(nom);
            }
        } catch (Exception e) {
            ouvrirFenetreErreur();
        }
    }

    private void ouvrirFenetreErreur() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Une erreur est survenue.", ButtonType.OK);
        alert.setHeaderText("");
        alert.setTitle("Erreur lors de l'ouverture de la fenêtre d'ajout d'un étudiant.");
        alert.setResizable(false);
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(textField.getScene().getWindow());
        alert.showAndWait();
    }

    private void ouvrirFenetreAjouterEtudiant(FXMLLoader loader) throws java.io.IOException {
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Ajouter un étudiant");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(textField.getScene().getWindow());
        stage.initStyle(StageStyle.UTILITY);
        stage.showAndWait();
    }

    @FXML
    private void clicSupprimerEtudiant() {
        viewmodel.supprimerEtudiant(listView.getSelectionModel().getSelectedIndex());
        clicDeselectionner();
    }

    @FXML
    private void clicDeselectionner() {
        listView.getSelectionModel().select(-1);
        textField.setText("");
    }

    @FXML
    private void clicQuitter() {
        textField.getScene().getWindow().hide();
    }
}
