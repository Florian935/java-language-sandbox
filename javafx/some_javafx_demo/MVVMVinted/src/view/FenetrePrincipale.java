package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import viewmodel.ECommerceVM;
import viewmodel.VendeurVM;
import viewmodel.VetementVM;

import java.io.IOException;

public class FenetrePrincipale {

    private final ECommerceVM viewmodel;

    @FXML
    private ListView<VendeurVM> listViewVendeur;
    @FXML
    private TextField textFieldPseudo;
    @FXML
    private ListView<VetementVM> listViewVetement;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ImageView imageView;

    public FenetrePrincipale(ECommerceVM viewmodel) {
        this.viewmodel = viewmodel;
    }

    public void initialize() {
        initialisationListViewVendeur();
        initialisationListViewVetement();
    }

    private void initialisationListViewVetement() {
        listViewVetement.setCellFactory(unused -> new CelluleVetement());
        listViewVetement.getSelectionModel().selectedItemProperty().addListener((unused, oldV, newV) -> {
            if (oldV != null) {
                destructionBindingProprieteVetement(oldV);
            }
            if (newV != null) {
                constructionBindingProprieteVetement(newV);
            }
        });
    }

    private void destructionBindingProprieteVetement(VetementVM oldV) {
        colorPicker.valueProperty().unbindBidirectional(oldV.getCouleur().paletteRVBProperty());
        imageView.imageProperty().unbind();
    }

    private void constructionBindingProprieteVetement(VetementVM newV) {
        colorPicker.valueProperty().bindBidirectional(newV.getCouleur().paletteRVBProperty());
        imageView.imageProperty().bind(newV.imageProperty());
        settingProprieteImageView(100, 100);
    }

    private void settingProprieteImageView(int width, int height) {
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    private void initialisationListViewVendeur() {
        listViewVendeur.setCellFactory(unused -> new CelluleVendeur());
        listViewVendeur.getSelectionModel().selectedItemProperty().addListener((unused, oldValue, newValue) -> {
            if (oldValue != null) {
                destructionBinding(oldValue);
                clicDeselectionnerVetement();
            }
            if (newValue != null) {
                constructionBinding(newValue);
            }
        });
        listViewVendeur.itemsProperty().bindBidirectional(viewmodel.lesVendeursProperty());
    }

    private void constructionBinding(VendeurVM newValue) {
        textFieldPseudo.textProperty().bindBidirectional(newValue.pseudoProperty());
        listViewVetement.itemsProperty().bindBidirectional(newValue.lesVetementsProperty());
    }

    private void destructionBinding(VendeurVM oldValue) {
        textFieldPseudo.textProperty().unbindBidirectional(oldValue.pseudoProperty());
        listViewVetement.itemsProperty().unbindBidirectional(oldValue.lesVetementsProperty());
    }

    public ECommerceVM getViewmodel() {
        return viewmodel;
    }

    @FXML
    private void clicDeselectionnerVendeur() {
        this.listViewVendeur.getSelectionModel().select(-1);
        textFieldPseudo.setText("");
        clicDeselectionnerVetement();
        listViewVetement.itemsProperty().set(null);
    }

    @FXML
    private void clicAjouterVendeur() throws IOException {
        FXMLLoader controller = new FXMLLoader(getClass().getResource("/fxml/AjouterVendeur.fxml"));
        ouvertureFenetre(controller, new AjouterVendeur(), "/css/AjouterVendeur.css", "Ajouter vendeur");
        VendeurVM vendeurVM = controller.<AjouterVendeur>getController().getVendeurVM();
        if (vendeurVM != null) {
            viewmodel.ajouterVendeur(vendeurVM);
        }
    }

    private void ouvertureFenetre(FXMLLoader controller, Object fenetre, String styleSheetPath, String titre) throws IOException {
        controller.setController(fenetre);
        Scene scene = new Scene(controller.load());
        scene.getStylesheets().add(styleSheetPath);
        Stage stage = new Stage();
        initialisationProprieteFenetre(titre, scene, stage);
        stage.showAndWait();
    }

    private void initialisationProprieteFenetre(String titre, Scene scene, Stage stage) {
        stage.setTitle(titre);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(textFieldPseudo.getScene().getWindow());
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
    }

    @FXML
    private void clicSupprimerVendeur() {
        int index = listViewVendeur.getSelectionModel().getSelectedIndex();
        viewmodel.supprimerVendeur(index);
    }

    @FXML
    private void clicDeselectionnerVetement() {
        this.listViewVetement.getSelectionModel().select(-1);
        imageView.setImage(null);
        settingProprieteImageView(0, 0);
    }

    @FXML
    private void clicAjouterVetement() throws IOException {
        FXMLLoader controller = new FXMLLoader(getClass().getResource("/fxml/AjouterVetement.fxml"));
        ouvertureFenetre(controller, new AjouterVetement(), "/css/AjouterVetement.css", "Ajouter vêtement");
        VetementVM vetementVM = controller.<AjouterVetement>getController().getVetementVM();
        if (vetementVM != null) {
            int index = listViewVendeur.getSelectionModel().getSelectedIndex();
            viewmodel.getModel().getLesVendeurs().get(index).ajouterVetement(vetementVM.getModel());
        }
    }

    @FXML
    private void clicSupprimerVetement() {
        int indexVetement = listViewVetement.getSelectionModel().getSelectedIndex();
        int indexVendeur = listViewVendeur.getSelectionModel().getSelectedIndex();
        viewmodel.getModel().getLesVendeurs().get(indexVendeur).supprimerVetement(indexVetement);
    }

    @FXML
    private void clicQuitter() {
        textFieldPseudo.getScene().getWindow().hide();
    }
}
