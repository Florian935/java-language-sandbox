package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import model.Couleur;
import model.utils.RVB;
import model.utils.Subscriber;

import java.beans.PropertyChangeEvent;

public class CouleurVM implements Subscriber {

    private final Couleur model;

    private final ObjectProperty<Color> paletteRVB = new SimpleObjectProperty<>();
        public Color getPaletteRVB() { return paletteRVB.get(); }
        public ObjectProperty<Color> paletteRVBProperty() { return paletteRVB; }
        public void setPaletteRVB(Color paletteRVB) { this.paletteRVB.set(paletteRVB); }

    private final IntegerProperty rouge = new SimpleIntegerProperty();
        public int getRouge() { return rouge.get(); }
        public IntegerProperty rougeProperty() { return rouge; }
        public void setRouge(int rouge) { this.rouge.set(rouge); }

    private final IntegerProperty vert = new SimpleIntegerProperty();
        public int getVert() { return vert.get(); }
        public IntegerProperty vertProperty() { return vert; }
        public void setVert(int vert) { this.vert.set(vert); }

    private final IntegerProperty bleu = new SimpleIntegerProperty();
        public int getBleu() { return bleu.get(); }
        public IntegerProperty bleuProperty() { return bleu; }
        public void setBleu(int bleu) { this.bleu.set(bleu); }

    public CouleurVM(Couleur model) {
        this.model = model;
        subscribe(this.model);
        this.paletteRVB.addListener((unused1, unused2, newCouleur) -> this.model.changerCouleur(new RVB(
                newCouleur.getRed(),
                newCouleur.getGreen(),
                newCouleur.getBlue()
        )));
        this.paletteRVB.set(new Color(
                model.getPaletteRVB().getRouge(),
                model.getPaletteRVB().getVert(),
                model.getPaletteRVB().getBleu(),
                1
        ));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Couleur.PROP_COULEUR:
                paletteRVB.set(new Color(
                        ((RVB) evt.getNewValue()).getRouge(),
                        ((RVB) evt.getNewValue()).getVert(),
                        ((RVB) evt.getNewValue()).getBleu(),
                        1
                        ));
                rouge.set((int) (((RVB) evt.getNewValue()).getRouge() * 255));
                vert.set((int) (((RVB) evt.getNewValue()).getVert() * 255));
                bleu.set((int) (((RVB) evt.getNewValue()).getBleu() * 255));
                break;
        }
    }
}
