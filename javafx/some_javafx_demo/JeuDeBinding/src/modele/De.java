package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class De {

    private IntegerProperty nombreFace = new SimpleIntegerProperty();
        public int getNombreFace() { return nombreFace.get(); }
        public void setNombreFace(int nombre) { nombreFace.set(nombre); }
        public IntegerProperty nombreFaceProperty() { return nombreFace; }

    private IntegerProperty numFaceAleatoire = new SimpleIntegerProperty();
        public int getNumFaceAleatoire() { return numFaceAleatoire.get(); }
        public void setNumFaceAleatoire(int num) { numFaceAleatoire.set(num); }
        public IntegerProperty numFaceAleatoireProperty() { return numFaceAleatoire; }

    public De(int nombreFace) {
        this.nombreFace.set(nombreFace);
    }

    public void lancer() {
            genererNombreAleatoire(1, 6);
    }

    private void genererNombreAleatoire(int min, int max) {
        int range = max - min + 1;
        this.numFaceAleatoire.set((int) (Math.random() * range) + 1);
    }
}
