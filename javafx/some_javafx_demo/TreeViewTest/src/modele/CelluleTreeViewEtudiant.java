package modele;

import javafx.scene.control.Button;
import javafx.scene.control.TreeCell;

public class CelluleTreeViewEtudiant extends TreeCell<String> {

    private Button button = new Button("Supprimer");

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            setGraphic(button);
            textProperty().bind(itemProperty());
            button.setOnAction(__ -> getTreeView().getSelectionModel().getSelectedItem().getParent().getChildren().remove(getTreeItem()));
        } else {
            setGraphic(null);
            textProperty().unbind();
            setText("");
        }
    }
}
