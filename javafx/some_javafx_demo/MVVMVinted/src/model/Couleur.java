package model;

import model.utils.RVB;
import model.utils.Subject;

import java.io.Serializable;

public class Couleur extends Subject implements Serializable {

    private static final Long serialVersionUID = 123L;
    private RVB paletteRVB;
    public transient static final String PROP_COULEUR = "couleur";

    public Couleur(RVB paletteRVB) {
        this.paletteRVB = paletteRVB;
    }

    public RVB getPaletteRVB() {
        return paletteRVB;
    }

    public void changerCouleur(RVB paletteRVB) {
        this.paletteRVB = paletteRVB;
        getSupport().firePropertyChange(PROP_COULEUR, null, paletteRVB);
    }
}
