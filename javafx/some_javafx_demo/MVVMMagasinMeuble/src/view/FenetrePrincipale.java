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
import viewmodel.CouleurVM;
import viewmodel.MagasinVM;
import viewmodel.MeubleVM;

import java.io.IOException;

public class FenetrePrincipale {

    private MagasinVM viewmodel;

    @FXML
    private ListView<MeubleVM> listViewMeuble;
    @FXML
    private TextField textFieldNom;
    @FXML
    private ListView<CouleurVM> listViewCouleur;
    @FXML
    private ColorPicker colorPicker;

    public FenetrePrincipale(MagasinVM magasinVM) {
        viewmodel = magasinVM;
    }

    public void initialize() {
        initializeListViewMeuble();
        initializeListViewCouleur();
    }

    private void initializeListViewCouleur() {
        listViewCouleur.setCellFactory(unused -> new CelluleCouleur());
        listViewCouleur.getSelectionModel().selectedItemProperty().addListener((unused, oldValue, newValue) -> {
            if (oldValue != null) {
                colorPicker.valueProperty().unbindBidirectional(oldValue.couleurProperty());
            }
            if (newValue != null) {
                colorPicker.valueProperty().bindBidirectional(newValue.couleurProperty());
            }
        });
    }

    private void initializeListViewMeuble() {
        listViewMeuble.itemsProperty().bind(viewmodel.lesMeublesProperty());
        listViewMeuble.getSelectionModel().selectedItemProperty().addListener((unused, oldValue, newValue) -> {
            if (oldValue != null) {
                destructionBinding(oldValue);
            }
            if (newValue != null) {
                constructionBinding(newValue);
            }
        });
        listViewMeuble.setCellFactory(unused -> new CelluleMeuble());
    }

    private void constructionBinding(MeubleVM newValue) {
        textFieldNom.textProperty().bindBidirectional(newValue.nomProperty());
        listViewCouleur.itemsProperty().bind(newValue.lesCouleursProperty());
    }

    private void destructionBinding(MeubleVM oldValue) {
        textFieldNom.textProperty().unbindBidirectional(oldValue.nomProperty());
        listViewCouleur.itemsProperty().unbind();
    }

    public MagasinVM getViewmodel() {
        return viewmodel;
    }

    @FXML
    private void clicBoutonAjouter() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AjouterMeuble.fxml"));
        loader.setController(new AjouterMeuble());
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("/css/AjouterMeuble.css");
        Stage stage = new Stage();
        initialisationFenetreAjouterMeuble(scene, stage);
        MeubleVM meubleVM = loader.<AjouterMeuble>getController().getMeubleVM();
        if (meubleVM != null) {
            viewmodel.ajouterMeuble(meubleVM);
        }
    }

    private void initialisationFenetreAjouterMeuble(Scene scene, Stage stage) {
        stage.setTitle("Ajouter un meuble");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.initOwner(textFieldNom.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
