package donnees;

import viewmodel.VoitureVM;

public abstract class StubVoitureVM {

    public static VoitureVM getVoitureVM() {
        VoitureVM voitureVM = new VoitureVM("Audi", StubCouleur.getCouleur());
        return voitureVM;
    }
}
