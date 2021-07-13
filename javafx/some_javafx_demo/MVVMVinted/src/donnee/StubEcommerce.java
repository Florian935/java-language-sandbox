package donnee;

import model.ECommerce;

public class StubEcommerce implements Loader<ECommerce> {

    @Override
    public ECommerce load() {
        ECommerce eCommerce = new ECommerce();
        StubVendeur.getLesVendeurs().forEach(eCommerce::ajouterVendeur);

        return eCommerce;
    }
}
