package fr.syrql.hctraining.kits.data;

import java.util.HashMap;
import java.util.List;

public class KItems {

    private String type;
    private int amount;
    private int data;
    private String name;
    private List<String> lore;
    private HashMap<String, Integer> enchants;

    public KItems(String type, int amount, int data, String name, List<String> lore, HashMap<String, Integer> enchants) {
        this.type = type;
        this.amount = amount;
        this.data = data;
        this.name = name;
        this.lore = lore;
        this.enchants = enchants;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public HashMap<String, Integer> getEnchants() {
        return enchants;
    }

    public void setEnchants(HashMap<String, Integer> enchants) {
        this.enchants = enchants;
    }
}
