package donnee;

import model.Couleur;
import viewmodel.VoitureVM;

public abstract class StubVoitureVM {

    public static VoitureVM getVoitureVM() {
        VoitureVM voitureVM = new VoitureVM(new Couleur(100, 100, 100));
        return voitureVM;
    }
}
