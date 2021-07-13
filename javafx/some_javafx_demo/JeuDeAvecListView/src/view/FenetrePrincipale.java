package view;

import data.Stub;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.CelluleJoueur;
import model.De;
import model.Joueur;
import model.Partie;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FenetrePrincipale {

    private Partie partie = new Partie(new ArrayList<>());
    private List<Joueur> lesJoueurs = Stub.getJoueurs();
    private De de;
    private List<Button> lesButtons;
    private List<Label> lesLabelsScoreEnCours;
    private List<Label> lesLabelsScoreTotaux;
    private List<TextField> lesTxtFields;
    private int face;
    public static Joueur joueurVictorieux;
    private IntegerProperty indexEnCours = new SimpleIntegerProperty(choixPremierJoueur());
    private ObjectProperty<Image> url = new SimpleObjectProperty<>();
    @FXML
    private ListView<Joueur> listView;
    @FXML
    private Label lblAfficherInfo;
    @FXML
    private Button btnLancerJoueur1;
    @FXML
    private Button btnLancerJoueur2;
    @FXML
    private TextField txtFieldJoueur1;
    @FXML
    private TextField txtFieldJoueur2;
    @FXML
    private ImageView imgView;
    @FXML
    private Label lblScoreEnCours1;
    @FXML
    private Label lblScoreEnCours2;
    @FXML
    private Label lblScoreTotal1;
    @FXML
    private Label lblScoreTotal2;

    public FenetrePrincipale() {
    }

    public void initialize() {
        initList();
        indexEnCours.addListener((__, oldValue, newValue) -> miseAJourBouton((int) newValue));
        int ancienneValeur = indexEnCours.get();
        indexEnCours.set(choixPremierJoueur());
        while (ancienneValeur == indexEnCours.get()) {
            indexEnCours.set(choixPremierJoueur());
        }
        de = new De();
        constructionBinding();
        listView.setItems(partie.joueursProperty());
        listView.setCellFactory(__ -> new CelluleJoueur());
    }

    private void constructionBinding() {
        imgView.imageProperty().bind(url);
        for (int i = 0; i <= lesJoueurs.size() - 1; i++) {
            constructionBindingLabel(lesLabelsScoreEnCours.get(i), lesJoueurs.get(i).scoreEnCoursProperty());
            constructionBindingLabel(lesLabelsScoreTotaux.get(i), lesJoueurs.get(i).scoreTotalProperty());
            constructionBindingTextField(lesJoueurs.get(i), lesTxtFields.get(i));
            constructionBindingTextField(lesJoueurs.get(i), lesTxtFields.get(i));
        }
    }

    private void initList() {
        initButtonList();
        initLabelScoreEnCoursList();
        initLabelScoreTotalList();
        initTextFieldList();
    }

    private void initTextFieldList() {
        lesTxtFields = new ArrayList<>() {{
            add(txtFieldJoueur1);
            add(txtFieldJoueur2);
        }};
    }

    private void initLabelScoreTotalList() {
        lesLabelsScoreTotaux = new ArrayList<>() {{
            add(lblScoreTotal1);
            add(lblScoreTotal2);
        }};
    }

    private void initLabelScoreEnCoursList() {
        lesLabelsScoreEnCours = new ArrayList<>() {{
            add(lblScoreEnCours1);
            add(lblScoreEnCours2);
        }};
    }

    private void constructionBindingLabel(Label lbl, IntegerProperty score) {
        lbl.textProperty().bind(score.asString());
    }

    private void miseAJourBouton(int newValue) {
        lesButtons.forEach(e -> e.setDisable(true));
        lesButtons.get(newValue).setDisable(false);
    }

    private void initButtonList() {
        lesButtons = new ArrayList<>() {{
            add(btnLancerJoueur1);
            add(btnLancerJoueur2);
        }};
    }

    private void constructionBindingTextField(Joueur joueur, TextField txtField) {
        joueur.pseudoProperty().bind(txtField.textProperty());
    }

    private void changerImage(int face) {
        String path = String.format("resource/img/%d.png", face);
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        url.set(image);

    }

    private int choixPremierJoueur() {
        return (int) (Math.random() * lesJoueurs.size());
    }

    @FXML
    private void lancer() {
        face = de.lancerDe();
        changerImage(face);
        miseAJourPropriete();
    }

    private void miseAJourPropriete() {
        if (face == 1) {
            resetScore(true);
            changerJoueur();
            lblAfficherInfo.setText("Perdu ! C'est au joueur " + lesJoueurs.get(indexEnCours.get()).getPseudo() + " de jouer !");
        } else {
            lesJoueurs.get(indexEnCours.get()).setScoreTotal(lesJoueurs.get(indexEnCours.get()).getScoreTotal() + face);
            lesJoueurs.get(indexEnCours.get()).setScoreEnCours(lesJoueurs.get(indexEnCours.get()).getScoreEnCours() + face);
            lblAfficherInfo.setText("");
            partieTerminee(lesJoueurs.get(indexEnCours.get()).getScoreTotal());
        }
    }

    private void partieTerminee(int scoreTotal) {
        if (scoreTotal >= 40) {
            joueurVictorieux = lesJoueurs.get(indexEnCours.get());
            try {
                partie.getJoueursObs().add(new Joueur(lesJoueurs.get(indexEnCours.get()).getPseudo()));
                FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/PartieTerminee.fxml"));
                ouvrirFenetreVictoire(leLoader);
                Boolean rejouer = leLoader.<PartieTerminee>getController().getRejouer();
                rejouer(rejouer);
            } catch (Exception e) {
                ouvrirFenetreErreur();
            }
        }

        /* TODO:
        -Créer une classe Partie pour mettre le code à l'intérieur (trop de lignes de code ici)
         */
    }

    private void rejouer(Boolean rejouer) {
        if (rejouer) {
        lesJoueurs.forEach(j -> {
            j.setScoreTotal(0);
            j.setScoreEnCours(0);
        });
        } else {
            txtFieldJoueur1.getScene().getWindow().hide();
        }
    }

    private void ouvrirFenetreErreur() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Une erreur est survenue.");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initStyle(StageStyle.UTILITY);
        alert.setResizable(false);
        alert.setTitle("Erreur #3439434");
        alert.setHeaderText("");
        alert.setHeight(150);
        alert.setWidth(300);
        alert.showAndWait();
    }

    private void ouvrirFenetreVictoire(FXMLLoader leLoader) throws IOException {
        Scene scene = new Scene(leLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initOwner(lblAfficherInfo.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.showAndWait();
    }

    private void changerJoueur() {
        if (indexEnCours.get() >= lesJoueurs.size() - 1) {
            indexEnCours.set(0);
        } else {
            indexEnCours.set(indexEnCours.get() + 1);
        }
    }

    private void resetScore(boolean reset) {
        if (reset) {
        lesJoueurs.get(indexEnCours.get()).setScoreTotal(lesJoueurs.get(indexEnCours.get()).getScoreTotal() - lesJoueurs.get(indexEnCours.get()).getScoreEnCours());
        } else {
            lesJoueurs.get(indexEnCours.get()).setScoreTotal(lesJoueurs.get(indexEnCours.get()).getScoreTotal());
        }
        lesJoueurs.get(indexEnCours.get()).setScoreEnCours(0);
    }

    @FXML
    private void sauvegarder() {
        resetScore((false));
        changerJoueur();
    }
}
