package modele;

public class EtudiantSerieux implements Etudier {
    @Override
    public void methode(Batiment batiment) {
        System.out.println("Je suis sérieux j'étudie avec mon cerveau. Mon batiment: " + batiment.nom);
    }


}
