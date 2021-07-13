package modele;

public class BatimentEnfant extends Batiment {

    public String texture;

    public BatimentEnfant(String nom, String texture) {
        super(nom);
        this.texture = texture;
    }
}
