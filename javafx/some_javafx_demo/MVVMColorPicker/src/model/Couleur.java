package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class Couleur {

    private final ObjectProperty<Color> palette = new SimpleObjectProperty<>();
        public Color getPalette() { return palette.get(); }
        public void setPalette(Color color) { this.palette.set(color); }
        public ObjectProperty<Color> paletteProperty() { return palette; }

    private Color colorPalette;

    public Couleur(double rouge, double vert, double bleu) {
        Color color = new Color(rouge / 255, vert / 255, bleu / 255, 1);
        this.palette.set(color);
        this.colorPalette = color;
    }

    public Color getColorPalette() {
        return colorPalette;
    }
}
