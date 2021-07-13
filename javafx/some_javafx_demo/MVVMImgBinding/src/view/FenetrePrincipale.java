package view;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import viewModel.VoitureVM;

public class FenetrePrincipale {

    private VoitureVM modelVM;

    @FXML
    private ImageView imageView;

    public FenetrePrincipale(VoitureVM modelVM) {
        this.modelVM = modelVM;
    }

    public void initialize() {
        imageView.imageProperty().bindBidirectional(modelVM.imageProperty());
    }

    @FXML
    private void clicChangerImage() {

    }
}
