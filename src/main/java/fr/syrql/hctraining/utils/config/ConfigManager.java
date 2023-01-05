package fr.syrql.hctraining.utils.config;

import fr.syrql.hctraining.HCTraining;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private HCTraining hcTraining;
    private final FileConfiguration config;

    public ConfigManager(HCTraining hcTraining) {
        this.hcTraining = hcTraining;
        this.config = hcTraining.getConfig();
    }

    public String getString(String path) {
        return ChatColor.translateAlternateColorCodes('&', hcTraining.getConfig().getString(path));
    }

    public List<String> getStringList(String path) {

        List<String> stringList = hcTraining.getConfig().getStringList(path);
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
        hcTraining.saveConfig();
        hcTraining.reloadConfig();
    }
}
