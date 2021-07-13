package view;

import donnee.Stub;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.De;
import modele.Joueur;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FenetrePrincipale {

    private List<Joueur> lesJoueurs = Stub.getLesJoueurs();
    De de;
    private int indexJoueur;
    private List<Button> lesBtnLancer;
    Joueur joueurEnCours;


    @FXML
    private Label lblScoreEnCours2;

    @FXML
    private Label lblScoreTotal2;

    @FXML
    private ImageView imgDe;

    @FXML
    private TextField txtField1;

    @FXML
    private Label lblScoreTotal1;

    @FXML
    private Label lblScoreEnCours1;

    @FXML
    private Button btnLancer1;

    @FXML
    private Button btnLancer2;

    public void initialize() {
        de = new De(6);
        de.numFaceAleatoireProperty().addListener((__, oldValue, newValue) -> {
            changerImage(de.getNumFaceAleatoire());
            miseAJourScore();
        });
        lesBtnLancer = new ArrayList<>() {{
            add(btnLancer1);
            add(btnLancer2);
        }};
        indexJoueur = choixPremierJoueur(2);
        joueurEnCours = lesJoueurs.get(indexJoueur);
        miseAjourButton();
        constructionBinding();
    }

    private void changementJoueur() {
        indexJoueur = indexJoueur == 1 ? 0 : 1;
        joueurEnCours = lesJoueurs.get(indexJoueur);
        miseAjourButton();
    }

    @FXML
    private void lancer() {
        de.lancer();
    }

    private void miseAJourScore() {
        if (de.numFaceAleatoireProperty().get() == 1) {
            joueurEnCours.setScoreEnCours(0);
            changementJoueur();
        } else {
            joueurEnCours.setScoreEnCours(joueurEnCours.getScoreEnCours() + de.getNumFaceAleatoire());
        }
        joueurEnCours.setScoreTotal(joueurEnCours.getScoreTotal() + de.getNumFaceAleatoire());
    }

    private void miseAjourButton() {
        lesBtnLancer.forEach(e -> e.setDisable(true));
        lesBtnLancer.get(indexJoueur).setDisable(false);
    }

    public void changerImage(int numImage) {
        String path = String.format("resource/image/%d.png", numImage);
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        imgDe.setImage(image);
    }

    private void constructionBinding() {
        joueurEnCours.pseudoProperty().bind(txtField1.textProperty());
        lblScoreTotal1.textProperty().bind(joueurEnCours.scoreTotalProperty().asString());
        lblScoreEnCours1.textProperty().bind(joueurEnCours.scoreEnCoursProperty().asString());
        lblScoreTotal2.textProperty().bind(joueurEnCours.scoreTotalProperty().asString());
        lblScoreEnCours2.textProperty().bind(joueurEnCours.scoreEnCoursProperty().asString());
    }

    private int choixPremierJoueur(int nbJoueur) {
        if (nbJoueur == 2) {
            return (int)(Math.random() * 2);
        }
        return (int)(Math.random() * nbJoueur + 1);
    }
}
