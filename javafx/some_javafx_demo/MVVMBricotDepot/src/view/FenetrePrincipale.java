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
import viewmodel.CouleurVM;
import viewmodel.MagasinVM;
import viewmodel.OutilVM;

import java.io.IOException;

public class FenetrePrincipale {

    private MagasinVM viewmodel;

    @FXML
    private ListView<OutilVM> listViewOutils;
    @FXML
    private ListView<CouleurVM> listViewCouleur;
    @FXML
    private TextField textFieldNom;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ImageView imageView;

    public FenetrePrincipale(MagasinVM viewmodel) {
        this.viewmodel = viewmodel;
    }

    public void initialize() {
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        initialisationListViewOutil();
        initialisationListViewCouleur();
    }

    private void initialisationListViewCouleur() {
        listViewCouleur.getSelectionModel().selectedItemProperty().addListener((unused, oldValue, newValue) -> {
            if (oldValue != null) {
                destructionBindingColorPicker(oldValue);
            }
            if (newValue != null) {
                constructionBindingColorPicker(newValue);
            }
        });
        listViewCouleur.setCellFactory(unused -> new CelluleCouleur());
    }

    private void constructionBindingColorPicker(CouleurVM newValue) {
        colorPicker.valueProperty().bindBidirectional(newValue.couleurProperty());
    }

    private void destructionBindingColorPicker(CouleurVM oldValue) {
        colorPicker.valueProperty().unbindBidirectional(oldValue.couleurProperty());
    }

    private void initialisationListViewOutil() {
        listViewOutils.itemsProperty().bind(viewmodel.lesOutilsProperty());
        listViewOutils.getSelectionModel().selectedItemProperty().addListener((unused, oldValue, newValue) -> {
            if (oldValue != null) {
                destructionBinding(oldValue);
            }
            if (newValue != null) {
                constructionBinding(newValue);
            }
        });
        listViewOutils.setCellFactory(unused -> new CelluleOutil());
    }

    private void constructionBinding(OutilVM newValue) {
        textFieldNom.textProperty().bindBidirectional(newValue.nomProperty());
        listViewCouleur.itemsProperty().bindBidirectional(newValue.lesCouleursProperty());
        initialisationImageView(newValue);
    }

    private void initialisationImageView(OutilVM newValue) {
        imageView.imageProperty().bindBidirectional(newValue.imageProperty());
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
    }

    private void destructionBinding(OutilVM oldValue) {
        textFieldNom.textProperty().unbindBidirectional(oldValue.nomProperty());
        listViewCouleur.itemsProperty().unbindBidirectional(oldValue.lesCouleursProperty());
        imageView.imageProperty().unbindBidirectional(oldValue.imageProperty());
    }

    public MagasinVM getViewmodel() {
        return viewmodel;
    }

    @FXML
    private void clicDeselectionnerOutil() {
        listViewOutils.getSelectionModel().select(-1);
        textFieldNom.setText("");
        clicDeselectionnerCouleur();
        imageView.setImage(null);
        listViewCouleur.itemsProperty().set(null);
    }

    @FXML
    private void clicAjouterOutil() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AjouterOutil.fxml"));
        ouvertureFenetre(loader, new AjouterOutil(), "/css/AjouterOutil.css", "Ajouter outil");
        OutilVM outilVM = loader.<AjouterOutil>getController().getOutilVM();
        if (outilVM != null) {
            viewmodel.ajouterOutil(outilVM);
        }
    }

    private void ouvertureFenetre(FXMLLoader loader, Object controller, String stylePathCss, String titre) throws IOException {
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(stylePathCss);
        Stage stage = new Stage();
        stage.setScene(scene);
        settingFenetre(titre, stage);
        stage.showAndWait();
    }

    private void settingFenetre(String titre, Stage stage) {
        stage.initStyle(StageStyle.UTILITY);
        stage.initOwner(textFieldNom.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle(titre);
    }

    @FXML
    private void clicSupprimerOutil() {
        int index = listViewOutils.getSelectionModel().getSelectedIndex();
        listViewCouleur.itemsProperty().set(null);
        viewmodel.supprimerOutil(index);
        textFieldNom.setText("");
    }

    @FXML
    private void clicDeselectionnerCouleur() {
        listViewCouleur.getSelectionModel().select(-1);
    }

    @FXML
    private void clicAjouterCouleur() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AjouterCouleur.fxml"));
        ouvertureFenetre(loader, new AjouterCouleur(), "/css/AjouterCouleur.css", "Ajouter couleur");
        CouleurVM couleurVM = loader.<AjouterCouleur>getController().getCouleurVM();
        if (couleurVM != null) {
            int index = listViewOutils.getSelectionModel().getSelectedIndex();
            viewmodel.getModel().getLesOutils().get(index).ajouterCouleur(couleurVM.getModel());
        }
    }

    @FXML
    private void clicSupprimerCouleur() {
        int indexCouleur = listViewCouleur.getSelectionModel().getSelectedIndex();
        int indexOutil = listViewOutils.getSelectionModel().getSelectedIndex();
        viewmodel.getModel().getLesOutils().get(indexOutil).supprimerCouleur(indexCouleur);
    }

    @FXML
    private void clicQuitter() {
        textFieldNom.getScene().getWindow().hide();
    }
}
