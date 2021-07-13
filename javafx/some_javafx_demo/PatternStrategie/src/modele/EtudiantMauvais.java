package modele;

public class EtudiantMauvais implements Etudier {
    @Override
    public void methode(Batiment batiment) {
        System.out.println("Je ne suis pas sérieux, je n'étudie pas. Mon batiment: " + ((BatimentEnfant)batiment).texture);
    }
}
