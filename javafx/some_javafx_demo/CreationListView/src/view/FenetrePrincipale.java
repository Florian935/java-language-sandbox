package view;

import donnees.Stub;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modele.CelluleJoueur;
import modele.Joueur;

import java.io.IOException;

public class FenetrePrincipale {

    private ObservableList<Joueur> equipe = Stub.getLesJoueurs();

    @FXML
    private Button btnAjouterEtudiant;
    @FXML
    private TextField textFieldPseudo;
    @FXML
    private TextField textFieldNom;
    @FXML
    private ListView<Joueur> listView;

    public void initialize() {
        listView.getSelectionModel().selectedItemProperty().addListener((__, oldValue, newValue) -> {
            if (oldValue != null) {
                destructionBinding(oldValue);
            }
            if (newValue != null) {
                constructionBinding(newValue);
            }
        });
        listView.setItems(equipe);
        listView.setCellFactory(__ -> new CelluleJoueur());
    }

    private void destructionBinding(Joueur joueur) {
        textFieldNom.textProperty().unbindBidirectional(joueur.nomProperty());
        textFieldPseudo.textProperty().unbindBidirectional(joueur.prenomProperty());
    }

    private void constructionBinding(Joueur joueur) {
        textFieldNom.textProperty().bindBidirectional(joueur.nomProperty());
        textFieldPseudo.textProperty().bindBidirectional(joueur.prenomProperty());
    }

    @FXML
    private void clicAjouterJoueur() {
        try {
            FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/AjouterJoueur.fxml"));
            ouvrirFenetreAjouterJoueur(leLoader);
            Joueur joueurCree = leLoader.<AjouterJoueur>getController().getJoueurCree();
            if (joueurCree != null) {
                equipe.add(joueurCree);
            }
        } catch (IllegalStateException | IOException e) {
            ouvrirFenetreErreur();
        }
    }

    private void ouvrirFenetreErreur() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Une erreur est survenue.");
        alert.setHeaderText("");
        alert.setTitle("Erreur #23432324");
        alert.setResizable(false);
        alert.initStyle(StageStyle.UTILITY);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(textFieldNom.getScene().getWindow());
        alert.showAndWait();
    }

    private void ouvrirFenetreAjouterJoueur(FXMLLoader leLoader) throws IOException {
        Scene scene = new Scene(leLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Ajouter un joueur");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnAjouterEtudiant.getScene().getWindow());
        stage.initStyle(StageStyle.UTILITY);
        stage.showAndWait();
    }
}
