package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import viewmodel.BibliothequeVM;
import viewmodel.CouleurVM;
import viewmodel.LivreVM;

import java.io.IOException;

public class FenetrePrincipale {

    private BibliothequeVM viewmodel;

    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ListView<LivreVM> listViewLivre;
    @FXML
    private ListView<CouleurVM> listViewCouleur;
    @FXML
    private TextField textFieldTitre;

    public FenetrePrincipale(BibliothequeVM viewmodel) {
        this.viewmodel = viewmodel;
    }

    public void initialize() {
        initialisationListViewLivre();
        initialisationListViewCouleur();
    }

    private void initialisationListViewCouleur() {
        listViewCouleur.getSelectionModel().selectedItemProperty().addListener((unused, oldV, newV) -> {
            if (oldV != null) {
                colorPicker.valueProperty().unbindBidirectional(oldV.couleurProperty());
            }
            if (newV != null) {
                colorPicker.valueProperty().bindBidirectional(newV.couleurProperty());
            }
        });
        listViewCouleur.setCellFactory(unused -> new CelluleCouleur());
    }

    private void initialisationListViewLivre() {
        listViewLivre.itemsProperty().bind(viewmodel.lesLivresProperty());
        listViewLivre.getSelectionModel().selectedItemProperty().addListener((unused, oldValue, newValue) -> {
            if (oldValue != null) {
                destructionBinding(oldValue);
            }
            if (newValue != null) {
                constructionBinding(newValue);
                listViewCouleur.getSelectionModel().select(-1);
            }
        });
        listViewLivre.setCellFactory(unused -> new CelluleLivre());
        listViewLivre.getSelectionModel().select(-1);
    }

    private void constructionBinding(LivreVM newValue) {
        textFieldTitre.textProperty().bindBidirectional(newValue.titreProperty());
        listViewCouleur.itemsProperty().bindBidirectional(newValue.lesCouleursProperty());
    }

    private void destructionBinding(LivreVM oldValue) {
        textFieldTitre.textProperty().unbindBidirectional(oldValue.titreProperty());
        listViewCouleur.itemsProperty().unbindBidirectional(oldValue.lesCouleursProperty());
    }

    public BibliothequeVM getViewmodel() {
        return viewmodel;
    }

    @FXML
    private void clicBtnAjouterCouleur() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AjouterCouleur.fxml"));
        initialisationFenetre(loader, new AjouterCouleur(), "/css/AjouterCouleur.css", "Ajouter une couleur");
        CouleurVM couleurVM = loader.<AjouterCouleur>getController().getCouleurChoisie();
        if (couleurVM != null) {
            int index = listViewLivre.getSelectionModel().getSelectedIndex();
            viewmodel.getModel().getLesLivres().get(index).ajouterCouleur(couleurVM.getModel());
        }
    }

    @FXML
    private void clicAjouterLivre() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AjouterLivre.fxml"));
        initialisationFenetre(loader, new AjouterLivre(), "/css/AjouterLivre.css", "Ajouter un livre");
        LivreVM livreVM = loader.<AjouterLivre>getController().getLivreVM();
        if (livreVM != null) {
            viewmodel.ajouterLivre(livreVM);
        }
    }

    private void initialisationFenetre(FXMLLoader loader, Object controller, String stylePath, String titre) throws IOException {
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(stylePath);
        initialisationParametreFenetre(stage, titre);
        stage.showAndWait();
    }

    private void initialisationParametreFenetre(Stage stage, String titre) {
        stage.setResizable(false);
        stage.setTitle(titre);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(textFieldTitre.getScene().getWindow());
        stage.initStyle(StageStyle.UTILITY);
    }

    @FXML
    private void clicDeselectionnerLivre() {
        this.listViewLivre.getSelectionModel().select(-1);
        textFieldTitre.setText("");
        clicDeselectionnerCouleur();
        this.listViewCouleur.itemsProperty().set(null);

    }

    @FXML
    private void clicDeselectionnerCouleur() {
        this.listViewCouleur.getSelectionModel().select(-1);
    }

    @FXML
    private void clicSupprimerLivre() {
        this.viewmodel.supprimerLivre(listViewLivre.getSelectionModel().getSelectedIndex());
        clicDeselectionnerLivre();
    }

    @FXML
    private void clicBtnSupprimerCouleur() {
        int indexCouleur = listViewCouleur.getSelectionModel().getSelectedIndex();
        int indexLivre = listViewLivre.getSelectionModel().getSelectedIndex();
        this.viewmodel.getModel().getLesLivres().get(indexLivre).supprimerCouleur(indexCouleur);
    }

    @FXML
    private void clicBtnQuitter() {
        textFieldTitre.getScene().getWindow().hide();
    }
}
