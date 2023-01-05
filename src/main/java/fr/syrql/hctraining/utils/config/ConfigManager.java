package fr.syrql.spigot.utils.config;

import fr.syrql.spigot.Hades;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private Hades hades;
    private final FileConfiguration config;

    public ConfigManager(Hades hades) {
        this.hades = hades;
        this.config = hades.getConfig();
    }

    public String getString(String path) {
        return ChatColor.translateAlternateColorCodes('&', hades.getConfig().getString(path));
    }

    public List<String> getStringList(String path) {

        List<String> stringList = hades.getConfig().getStringList(path);
        ArrayList<String> toReturn = new ArrayList<>();

        stringList.forEach(line -> toReturn.add(ChatColor.translateAlternateColorCodes('&', line)));

        return toReturn;
    }

    public void setDouble(String path, double value) { config.set(path, value); }

    public int getInt(String path) {
        return config.getInt(path);
    }

    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    public double getDouble(String path) {
        return config.getDouble(path);
    }

    public double getFloat(String path) {
        return config.getDouble(path);
    }

    public long getLong(String path){
        return config.getLong(path);
    }

    public FileConfiguration getConfig() { return config; }

    public void updateConfig() {
        hades.saveConfig();
        hades.reloadConfig();
    }
}
