package fr.syrql.spigot.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.syrql.spigot.bots.nms.Bot;

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

    public String serialize(Bot playerData) {
        return this.gson.toJson(playerData);
    }

    public Bot deserialize(String json) {
        return this.gson.fromJson(json, Bot.class);
    }

}

