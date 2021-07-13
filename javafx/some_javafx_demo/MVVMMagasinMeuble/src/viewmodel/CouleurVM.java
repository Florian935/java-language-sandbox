package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import model.Couleur;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public class CouleurVM implements PropertyChangeListener {

    private Couleur model;

    private final ObjectProperty<Color> couleur = new SimpleObjectProperty<>();
        public Color getCouleur() { return couleur.get(); }
        public ObjectProperty<Color> couleurProperty() { return couleur; }
        public void setCouleur(Color couleur) {
        this.couleur.set(couleur); }

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
        this.model.ajouterListener(this);
        initialisationCouleur(model);
    }

    private void initialisationCouleur(Couleur model) {
        this.couleur.addListener((unused1, unused2, newColor) -> {
            this.model.setCouleur(newColor.getRed(), newColor.getGreen(), newColor.getBlue());
        });
        this.couleur.set(new Color(
                model.getCouleur().get("rouge"),
                model.getCouleur().get("vert"),
                model.getCouleur().get("bleu"),
                1)
        );
    }

    @Override
//    @SuppressWarnings("unchecked")
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Couleur.PROP_COULEUR:
                this.couleur.set(new Color(
                        (double) ((HashMap<?, ?>)evt.getNewValue()).get("rouge"),
                        ((HashMap<String, Double>)evt.getNewValue()).get("vert"),
                        ((HashMap<String, Double>)evt.getNewValue()).get("bleu"),
                        1)
                );
                this.rouge.set((int) (((HashMap<String, Double>)evt.getNewValue()).get("rouge") * 255));
                this.vert.set((int) (((HashMap<String, Double>)evt.getNewValue()).get("vert") * 255));
                this.bleu.set((int) (((HashMap<String, Double>)evt.getNewValue()).get("bleu") * 255));
        }
    }
}
