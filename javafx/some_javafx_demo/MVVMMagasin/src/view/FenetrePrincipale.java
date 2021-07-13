package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import viewmodel.CouleurVM;
import viewmodel.MagasinVM;
import viewmodel.VetementVM;

import java.io.IOException;

public class FenetrePrincipale {

    private MagasinVM viewmodel;
    private IntegerProperty stockTotal = new SimpleIntegerProperty();

    @FXML
    private ListView<VetementVM> listViewVetements;

    @FXML
    private ListView<CouleurVM> listViewCouleurs;

    @FXML
    private Label labelVetement;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Label labelStockTotal;

    public FenetrePrincipale(MagasinVM magasin) {
        this.viewmodel = magasin;
    }

    public void initialize() {
        this.stockTotal.bind(viewmodel.stockTotalProperty());
        labelStockTotal.textProperty().bind(viewmodel.stockTotalProperty().asString());
        listViewVetements.itemsProperty().bind(viewmodel.lesVetementsProperty());
        listViewVetements.getSelectionModel().selectedItemProperty().addListener((unused, oldV, newV) -> {
            if (oldV != null) {
                destructionBinding();
            }
            if (newV != null) {
                constructionBinding(newV);
            }
        });
        listViewCouleurs.getSelectionModel().selectedItemProperty().addListener((unused, oldV, newV) -> {
            if (oldV != null) {
                colorPicker.valueProperty().unbindBidirectional(oldV.couleurProperty());
            }
            if (newV != null) {
                colorPicker.valueProperty().bindBidirectional(newV.couleurProperty());
            }
        });
        constructionCellFactory();

    }

    private void constructionCellFactory() {
        listViewVetements.setCellFactory(unused -> new CelluleVetementVM());
        listViewCouleurs.setCellFactory(unused -> new CelulleCouleurVM());
    }

    private void destructionBinding() {
        labelVetement.textProperty().unbind();
        listViewCouleurs.itemsProperty().unbind();
    }

    private void constructionBinding(VetementVM newV) {
        labelVetement.textProperty().bind(newV.nomProperty());
        listViewCouleurs.itemsProperty().bind(newV.lesCouleursProperty());
    }

    public MagasinVM getViewmodel() {
        return viewmodel;
    }

    @FXML
    private void clicDeselectionnerVetement() {
        this.listViewVetements.getSelectionModel().select(-1);
        this.listViewCouleurs.itemsProperty().set(null);
        clicDeselectionnerCouleur();
        labelVetement.setText("");
    }

    @FXML
    private void clicDeselectionnerCouleur() {
        this.listViewCouleurs.getSelectionModel().select(-1);
    }

    @FXML
    private void clicAjouterQuantite() { majQuantite(1); }

    @FXML
    private void clicEnleverQuantite() {
        if (! (listViewVetements.getSelectionModel().getSelectedItem().getQuantite() == 0)) {
            majQuantite(-1);
        }
    }

    private void majQuantite(int quantite) {
        viewmodel.stockTotalProperty().set(viewmodel.getStockTotal() + quantite);
        VetementVM vetementSelectionne = this.listViewVetements.getSelectionModel().selectedItemProperty().get();
        vetementSelectionne.quantiteProperty().set(vetementSelectionne.getQuantite() + quantite);
    }

    @FXML
    private void clicAjouterVetement() {
        try {
            FXMLLoader controller = new FXMLLoader(getClass().getResource("/fxml/AjouterVetement.fxml"));
            ouvrirFenetreAjouterVetement(controller);
            VetementVM vetementVM = controller.<AjouterVetement>getController().getVetementVM();
            if (vetementVM != null) {
                viewmodel.ajouterVetement(vetementVM);
                viewmodel.stockTotalProperty().set(viewmodel.getStockTotal() + vetementVM.getQuantite());
            }
        } catch (IOException e) {

        }

    }

    private void ouvrirFenetreAjouterVetement(FXMLLoader controller) throws IOException {
        controller.setController(new AjouterVetement(viewmodel));
        Scene scene = new Scene(controller.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Ajouter vêtement");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.initOwner(labelVetement.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void clicQuitter() {
        labelVetement.getScene().getWindow().hide();
    }

    @FXML
    private void clicSupprimerVetement() {
        majQuantite(-listViewVetements.getSelectionModel().selectedItemProperty().get().quantiteProperty().get());
        viewmodel.supprimerVetement(listViewVetements.getSelectionModel().selectedItemProperty().get().getModel());
        clicDeselectionnerVetement();
    }

    @FXML
    private void clicSupprimerCouleur() {
        int indexVetement = listViewVetements.getSelectionModel().getSelectedIndex();
        int indexCouleur = listViewCouleurs.getSelectionModel().getSelectedIndex();
        viewmodel.supprimerCouleur(indexVetement, indexCouleur);
    }

    @FXML
    private void clicAjouterCouleur() {
        try {
            FXMLLoader controller = new FXMLLoader(getClass().getResource("/fxml/AjouterCouleur.fxml"));
            ouvrirFenetreAjouterCouleur(controller);
            Color laCouleur = controller.<AjouterCouleur>getController().getLaCouleur();
            if (laCouleur != null) {
                ajouterCouleur(laCouleur);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ajouterCouleur(Color laCouleur) {
        int index = listViewVetements.getSelectionModel().getSelectedIndex();
        viewmodel.getLesVetements().get(index).ajouterCouleur(laCouleur);
    }

    private void ouvrirFenetreAjouterCouleur(FXMLLoader controller) throws IOException {
        controller.setController(new AjouterCouleur());
        Scene scene = new Scene(controller.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Ajouter une couleur");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(labelVetement.getScene().getWindow());
        stage.initStyle(StageStyle.UTILITY);
        stage.showAndWait();
    }
}
