package view;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Couleur;
import model.Taille;
import model.Vetement;
import viewmodel.MagasinVM;
import viewmodel.VetementVM;

import java.util.ArrayList;
import java.util.List;

public class AjouterVetement {

    private VetementVM vetementVM;
    private final MagasinVM viewmodel;
    private Taille tailleChoisie;
    private List<Couleur> lesCouleurs;

    @FXML
    private TextField textFieldNom;

    @FXML
    private TextField textFieldPrix;

    @FXML
    private TextField textFieldQuantite;

    @FXML
    private ComboBox<Taille> comboBox;

    @FXML
    private ColorPicker colorPicker;

    public AjouterVetement(MagasinVM magasinVM) {
        this.viewmodel = magasinVM;
    }

    public void initialize() {
        lesCouleurs = new ArrayList<>();
        comboBox.itemsProperty().bind(viewmodel.lesTaillesProperty());
        comboBox.getSelectionModel().selectedItemProperty().addListener((unused1, unused2, valueSelected)
                -> tailleChoisie = valueSelected);
        colorPicker.valueProperty().addListener((unused1, unused2, newV) -> {
            lesCouleurs.removeAll(lesCouleurs);
            lesCouleurs.add(new Couleur(newV.getRed(), newV.getGreen(), newV.getBlue()));
        });
    }

    public VetementVM getVetementVM() {
        return vetementVM;
    }

    @FXML
    private void clicAjouter() {
        vetementVM = new VetementVM(new Vetement(
                textFieldNom.getText(),
                Float.parseFloat(textFieldPrix.getText()),
                Integer.parseInt(textFieldQuantite.getText()),
                tailleChoisie,
                lesCouleurs
        )
        );
        fermerFenetre();
    }

    @FXML
    private void clicAnnuler() {
        vetementVM = null;
        fermerFenetre();
    }

    private void fermerFenetre() {
        textFieldNom.getScene().getWindow().hide();
    }
}
