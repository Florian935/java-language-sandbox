package donnee;

import viewmodel.EtudiantVM;

public abstract class StubEtudiantVM {

    public static EtudiantVM getEtudiantVM() {
        EtudiantVM etudiantVM = new EtudiantVM("TOTO");
        return etudiantVM;
    }
}
