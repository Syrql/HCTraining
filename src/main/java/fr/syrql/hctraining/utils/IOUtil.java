package fr.syrql.hctraining.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.syrql.hctraining.arena.data.Arena;
import fr.syrql.hctraining.profile.data.Profile;

public class IOUtil {

    private Gson gson;

    public IOUtil() {
        this.gson = createGsonInstance();
    }


    public Gson createGsonInstance() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
    }

    public String serialize(Arena playerData) {
        return this.gson.toJson(playerData);
    }

    public Arena deserialize(String json) {
        return this.gson.fromJson(json, Arena.class);
    }

    public String serializeProfile(Profile playerData) {
        return this.gson.toJson(playerData);
    }

    public Profile deserializeProfile(String json) {
        return this.gson.fromJson(json, Profile.class);
    }

}

