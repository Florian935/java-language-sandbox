package viewmodel;

import javafx.beans.property.*;
import javafx.scene.paint.Color;
import model.Couleur;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public class CouleurVM implements PropertyChangeListener {

    private Couleur model;

    private final ObjectProperty<Color> couleur = new SimpleObjectProperty<>();
        public Color getCouleur() { return couleur.get(); }
        public void setCouleur(Color color) { couleur.set(color); }
        public ObjectProperty<Color> couleurProperty() { return couleur; }

    private final IntegerProperty rouge = new SimpleIntegerProperty();
        public double getRouge() { return rouge.get(); }
        public void setRouge(int rouge) { this.rouge.set(rouge); }
        public ReadOnlyIntegerProperty rougeProperty() { return rouge; }

    private final IntegerProperty vert = new SimpleIntegerProperty();
        public double getVert() { return vert.get(); }
        public void setVert(int vert) { this.vert.set(vert); }
        public ReadOnlyIntegerProperty vertProperty() { return vert; }

    private final IntegerProperty bleu = new SimpleIntegerProperty();
        public int getBleu() { return bleu.get(); }
        public void setBleu(int bleu) { this.bleu.set(bleu); }
        public ReadOnlyIntegerProperty bleuProperty() { return bleu; }

    public CouleurVM(Couleur model) {
        this.model = model;
        this.model.ajouterListener(this);
        couleur.addListener((unused1, unused2, newV) -> {
            this.rouge.set((int) (newV.getRed() * 255));
            this.vert.set((int) (newV.getGreen() * 255));
            this.bleu.set((int) (newV.getBlue() * 255));
            this.model.setCouleur(newV.getRed(), newV.getGreen(), newV.getBlue());
        });
        couleur.set(new Color(
                this.model.getCouleur().get("rouge"),
                this.model.getCouleur().get("vert"),
                this.model.getCouleur().get("bleu"),
                1));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Couleur.PROP_COULEUR:
                this.couleur.set(new Color(
                        ((HashMap<String, Double>)evt.getNewValue()).get("rouge"),
                        ((HashMap<String, Double>)evt.getNewValue()).get("vert"),
                        ((HashMap<String, Double>)evt.getNewValue()).get("bleu"),
                        1
                ));
                break;
        }
    }
}
