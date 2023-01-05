package fr.syrql.hctraining.profile.factory;

import fr.syrql.hctraining.profile.data.Profile;

import java.util.UUID;

public class ProfileFactory {

    public Profile create(UUID uuid) {
        return new Profile(uuid, 1000, 0,0,null);
    }
}
