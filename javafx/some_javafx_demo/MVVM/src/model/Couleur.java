package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class Couleur {

    private ObjectProperty<Color> palette = new SimpleObjectProperty<>();
        public Color getPalette() { return palette.get(); }
        public ObjectProperty<Color> paletteProperty() { return palette; }
        public void setPalette(Color palette) { this.palette.set(palette); }

    public Couleur(double rouge, double vert, double bleu) {
        Color laCouleur = new Color(rouge/255, vert/255, bleu/255, 1);
        palette.set(laCouleur);
    }
}
