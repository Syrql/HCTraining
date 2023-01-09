package fr.syrql.hctraining.kits.factory;

import fr.syrql.hctraining.kits.data.KItems;
import fr.syrql.hctraining.kits.data.Kit;

public class KitFactory {

    public Kit create(String kitName, KItems[] stuffs, KItems[] armor) {
        return new Kit(kitName, stuffs, armor);
    }
}
