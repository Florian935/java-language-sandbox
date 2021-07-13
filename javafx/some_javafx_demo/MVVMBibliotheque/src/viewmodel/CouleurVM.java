package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import model.Couleur;
import model.utils.RVB;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CouleurVM implements PropertyChangeListener {

    private Couleur model;

    private final ObjectProperty<Color> couleur = new SimpleObjectProperty<>();
        public Color getCouleur() { return couleur.get(); }
        public ObjectProperty<Color> couleurProperty() { return couleur; }
        public void setCouleur(Color couleur) { this.couleur.set(couleur); }

    private final IntegerProperty rouge = new SimpleIntegerProperty();
        public int getRouge() { return rouge.get(); }
        public IntegerProperty rougeProperty() { return rouge; }
        public void setRouge(int rouge) { this.rouge.set(rouge); }

    private final IntegerProperty vert = new SimpleIntegerProperty();
    public int getVert() { return vert.get(); }
    public IntegerProperty vertProperty() { return vert; }
    public void setVert(int vert) { this.vert.set(vert); }

    private final IntegerProperty bleu = new SimpleIntegerProperty();
    public int getbleu() { return bleu.get(); }
    public IntegerProperty bleuProperty() { return bleu; }
    public void setbleu(int bleu) { this.bleu.set(bleu); }

    public CouleurVM(Couleur model) {
        this.model = model;
        this.model.ajouterListener(this);
        initialisationCouleurProperty();
    }

    private void initialisationCouleurProperty() {
        ajoutListenerCouleur();
        this.couleur.set(new Color(
                this.model.getCouleur().getRouge(),
                this.model.getCouleur().getVert(),
                this.model.getCouleur().getBleu(),
                1
        ));
    }

    private void ajoutListenerCouleur() {
        this.couleur.addListener((unused1, unused2, newV) -> {
            this.model.changerCouleur(
                    newV.getRed(),
                    newV.getGreen(),
                    newV.getBlue()
            );
        });
    }

    public Couleur getModel() {
        return model;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Couleur.PROP_COULEUR:
                this.couleur.set(new Color(
                        ((RVB) evt.getNewValue()).getRouge(),
                        ((RVB) evt.getNewValue()).getVert(),
                        ((RVB) evt.getNewValue()).getBleu(),
                        1
                ));
                this.rouge.set( (int) (( (RVB) evt.getNewValue()).getRouge() * 255));
                this.vert.set( (int) (( (RVB) evt.getNewValue()).getVert() * 255));
                this.bleu.set( (int) (( (RVB) evt.getNewValue()).getBleu() * 255));
                break;
        }
    }
}
