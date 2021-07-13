package model;

public class De {

    private int nombreFace = 6;

    public De() {}

    public De(int nombreFace) {
        this.nombreFace = nombreFace;
    }

    public int lancerDe() {
        int min = 1;
        int range = nombreFace - min + 1;
        return (int)(Math.random() * range) + 1;
    }

    public int getNombreFace() {
        return nombreFace;
    }

    public void setNombreFace(int nombreFace) {
        this.nombreFace = nombreFace;
    }
}
