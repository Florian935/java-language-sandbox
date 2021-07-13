package view;

import donnees.Stub;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.*;

public class FenetrePrincipale {

    private Promotion promo = new Promotion(Stub.getLesEtudiants());
    private Promotion promo2 = new Promotion(Stub.getLesEtudiants());
    private TreeItem<String> root;

    @FXML
    private TextField textFieldTree;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private ComboBox<Etudiant> comboBox;

    @FXML
    private TextField textFieldComboNom;

    @FXML
    private TextField textFieldComboPrenom;

    @FXML
    private TableView<Etudiant> tableView;

    @FXML
    private TextField textFieldTableNom;

    @FXML
    private TextField textFieldTablePrenom;

    public void initialize() {
        initTableView();
        initTreeView();
        initCombo();
    }

    private void initTableView() {
        tableView.getSelectionModel().selectedItemProperty().addListener((__, oldValue, newValue) -> {
            if (oldValue != null) {
                destructionBindingTable(oldValue);
            } if (newValue != null) {
                constructionBindingTable(newValue);
            }
        });
        TableColumn<Etudiant, String> firstNameCol = new TableColumn<>("Student Firstname");
        TableColumn<Etudiant, String> lastNameCol = new TableColumn<>("Student Lastname");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        tableView.setItems(promo2.getLesEtudiantsObs());
        tableView.getColumns().addAll(firstNameCol, lastNameCol);
    }

    private void initTreeView() {
        root = new TreeItem<String>("Les étudiants");
        promo.getLesEtudiantsObs().forEach(e -> root.getChildren().add(new TreeItem<>(e.getNom() + " " + e.getPrenom())));
        treeView.getSelectionModel().selectedItemProperty().addListener((__, oldValue, newValue) -> {
            if (oldValue != null) {
                destructionBinding(oldValue.valueProperty());
            }
            if (newValue != null) {
                constructionBinding(newValue.valueProperty());
            }
        });
        treeView.setRoot(root);
        treeView.setCellFactory(__ -> new CelluleTreeViewEtudiant());
    }

    private void initCombo() {
        comboBox.getSelectionModel().selectedItemProperty().addListener((__, oldValue, newValue) -> {
            if (oldValue != null) {
                destructionBindingCombo(oldValue);
            }
            if (newValue != null) {
                constructionBindingCombo(newValue);
            }
        });
        comboBox.setItems(promo.getLesEtudiantsObs());
        comboBox.setCellFactory(__ -> new CelluleComboEtudiant());
    }

    private void destructionBindingCombo(Etudiant oldValue) {
        textFieldComboNom.textProperty().unbindBidirectional(oldValue.nomProperty());
        textFieldComboPrenom.textProperty().unbindBidirectional(oldValue.prenomProperty());
    }

    private void constructionBindingCombo(Etudiant newValue) {
        textFieldComboNom.textProperty().bindBidirectional(newValue.nomProperty());
        textFieldComboPrenom.textProperty().bindBidirectional(newValue.prenomProperty());
    }

    private void destructionBindingTable(Etudiant oldValue) {
        textFieldTableNom.textProperty().unbindBidirectional(oldValue.nomProperty());
        textFieldTablePrenom.textProperty().unbindBidirectional(oldValue.prenomProperty());
    }

    private void constructionBindingTable(Etudiant newValue) {
        textFieldTableNom.textProperty().bindBidirectional(newValue.nomProperty());
        textFieldTablePrenom.textProperty().bindBidirectional(newValue.prenomProperty());
    }

    private void constructionBinding(ObjectProperty<String> valueProperty) {
        textFieldTree.textProperty().bindBidirectional(valueProperty);

    }

    private void destructionBinding(ObjectProperty<String> valueProperty) {
        textFieldTree.textProperty().unbindBidirectional(valueProperty);
    }

    @FXML
    private void clicAjouterTree() {
        Etudiant etudiant = new Etudiant("TOTO5", "toto5");
        root.getChildren().add(new TreeItem<>(etudiant.getNom() + " " + etudiant.getPrenom()));
    }

    @FXML
    private void clicAjouterCombo() {
        this.comboBox.getItems().add(new Etudiant("TOTO5", "toto5"));
    }

    @FXML
    private void clicAjouterTable() {
        tableView.getItems().add(new Etudiant("TOTO5", "toto5"));
    }

    @FXML
    private void clicSupprimerTable() {
        tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void clicSupprimerCombo() {
        comboBox.getItems().remove(comboBox.getSelectionModel().getSelectedItem());
    }
}
