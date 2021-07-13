package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.De;
import modele.Joueur;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FenetrePrincipaleControleur {

    int nombreAleatoire;
    Joueur joueur1;
    Joueur joueur2;
    De de;
    int totalEnCours = 0;
    int total = 0;
    int numJoueur = 0;
    List<Joueur> lesJoueurs;
    List<Label> lesLabelsTotal;
    List<Label> lesLabelsTotalEnCours;
    List<Button> lesbuttonLancer;

    @FXML
    private Button buttonLancerJoueur1;

    @FXML
    private Button buttonLancerJoueur2;

    @FXML
    private Label labelTotalJoueur1 = new Label();

    @FXML
    private Label labelTotalJoueur2 = new Label();

    @FXML
    private ImageView imgViewFaceDe;

    @FXML
    private Label labelTotalEnCours1;

    @FXML
    private Label labelTotalEnCours2;

    @FXML
    private Label labelMessagePerdu;

    @FXML
    public void initialize() {

        initValeur();
        desactiverButtonLancer();
    }

    private void initValeur() {
        de = new De(6);
        joueur1 = new Joueur(0, "Joueur1");
        joueur2 = new Joueur(1, "Joueur2");
        lesJoueurs = new ArrayList<>();
        lesJoueurs.add(joueur1);
        lesJoueurs.add(joueur2);
        lesLabelsTotal = new ArrayList<>();
        lesLabelsTotal.add(labelTotalJoueur1);
        lesLabelsTotal.add(labelTotalJoueur2);
        lesLabelsTotalEnCours = new ArrayList<>();
        lesLabelsTotalEnCours.add(labelTotalEnCours1);
        lesLabelsTotalEnCours.add(labelTotalEnCours2);
        lesbuttonLancer = new ArrayList<>();
        lesbuttonLancer.add(buttonLancerJoueur1);
        lesbuttonLancer.add(buttonLancerJoueur2);
    }

    @FXML
    private void lancer() {
        labelMessagePerdu.setText("");
        nombreAleatoire = genererNombreAleatoire(1, de.getNombreFace());
        if (nombreAleatoire == 1) {
            manchePerdue();
            changerJoueur();
        } else {
            totalEnCours += nombreAleatoire;
            valoriserLabelEtScore();
        }
        changerImage();
    }

    private int genererNombreAleatoire(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }

    private void changerImage() {
        String path = new StringBuilder("resources/img/")
                .append(nombreAleatoire)
                .append(".png").toString();
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        imgViewFaceDe.setImage(image);
    }

    private void desactiverButtonLancer() {
        lesbuttonLancer.forEach(e -> e.setDisable(true));
        lesbuttonLancer.get(numJoueur).setDisable(false);
    }

    @FXML
    private void changerJoueur() {
        lesLabelsTotalEnCours.get(numJoueur).setText("");
        setNumJoueur();
        desactiverButtonLancer();
        totalEnCours = 0;
    }

    private void manchePerdue() {
        labelMessagePerdu.setText("Vous êtes tombé sur la face 1, au joueur suivant de jouer !");
        lesJoueurs.get(numJoueur).setScore(lesJoueurs.get(numJoueur).getScore() - totalEnCours);
        lesLabelsTotal.get(numJoueur).setText(String.valueOf(lesJoueurs.get(numJoueur).getScore()));
    }

    private void valoriserLabelEtScore() {
        total = lesJoueurs.get(numJoueur).getScore() + nombreAleatoire;
        lesJoueurs.get(numJoueur).setScore(total);
        lesLabelsTotalEnCours.get(numJoueur).setText(String.valueOf(totalEnCours));
        lesLabelsTotal.get(numJoueur).setText(String.valueOf(total));
    }

    private void setNumJoueur() {
        numJoueur++;
        if (numJoueur > lesJoueurs.size() - 1) {
            numJoueur = 0;
        }
    }
}
